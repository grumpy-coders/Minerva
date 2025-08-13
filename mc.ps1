<# mc.ps1 — bootstrap Minerva (Forge 1.21.x) #>

param(
	[string]$JdkHome = "D:\jdks\jdk-21.0.2",
	[string]$GradleVersion = "8.10.2",
	[switch]$RunClient = $false,
	[switch]$SkipFixPublishing = $false
)

$ErrorActionPreference = "Stop"
Push-Location $PSScriptRoot
try {
	function EnsureJson($path, $patch) {
		if (!(Test-Path $path)) {
			New-Item -ItemType Directory -Path (Split-Path $path) -Force | Out-Null
			'{}' | Set-Content $path -Encoding UTF8
		}
		$json = Get-Content $path -Raw | ConvertFrom-Json -AsHashtable
		foreach ($k in $patch.Keys) { $json[$k] = $patch[$k] }
		($json | ConvertTo-Json -Depth 10) | Set-Content $path -Encoding UTF8
	}

	function Set-GradleProp($path, $key, $value) {
		if (!(Test-Path $path)) { New-Item -ItemType File -Path $path -Force | Out-Null }
		$val = ($value -replace '\\', '/')  # .properties escapes backslashes
		$txt = Get-Content $path -Raw
		if ($txt -match "^(?m)$([regex]::Escape($key))=") {
			$txt = [regex]::Replace($txt, "^(?m)$([regex]::Escape($key))=.*$", "$key=$val")
		}
		else {
			if ($txt -and -not $txt.EndsWith("`n")) { $txt += "`n" }
			$txt += "$key=$val`n"
		}
		Set-Content $path $txt -Encoding UTF8
	}

	Write-Host "== enforcing jdk 21 paths ==" -ForegroundColor Cyan
	EnsureJson ".vscode\settings.json" @{
		"java.configuration.runtimes"  = @(@{ name = "JavaSE-21"; path = $JdkHome })
		"java.import.gradle.java.home" = $JdkHome
		"gradle.java.home"             = $JdkHome
	}

	Write-Host "== forcing gradle to use jdk 21 (non-destructive) ==" -ForegroundColor Cyan
	Set-GradleProp "gradle.properties" "org.gradle.java.home" $JdkHome
	Set-GradleProp "gradle.properties" "org.gradle.jvmargs" "-Xmx4g"

	Write-Host "== pinning gradle wrapper to $GradleVersion ==" -ForegroundColor Cyan
	$wrap = "gradle\wrapper\gradle-wrapper.properties"
	if (!(Test-Path $wrap)) { throw "wrapper file not found: $wrap" }
	(Get-Content $wrap -Raw) `
		-replace "distributionUrl=.*", "distributionUrl=https://services.gradle.org/distributions/gradle-$GradleVersion-bin.zip" `
	| Set-Content $wrap -Encoding UTF8

	if (-Not $SkipFixPublishing -and (Test-Path "build.gradle")) {
		$b = Get-Content "build.gradle" -Raw
		if ($b -match "(?s)\bpublishing\s*\{" -and $b -notmatch "id\s*['""]maven-publish['""]") {
			Write-Host "== adding 'maven-publish' plugin (publishing{} detected) ==" -ForegroundColor Yellow
			$b = $b -replace "(?s)plugins\s*\{", "plugins {\r\n    id 'maven-publish'"
			Set-Content "build.gradle" $b -Encoding UTF8
		}
	}

	Write-Host "== stopping gradle daemons ==" -ForegroundColor Cyan
	./gradlew --stop | Out-Null

	Write-Host "== clearing stale caches (8.12.1) ==" -ForegroundColor Cyan
	Remove-Item -Recurse -Force ".gradle" -ErrorAction SilentlyContinue
	Remove-Item -Recurse -Force "$env:USERPROFILE\.gradle\caches\8.12.1" -ErrorAction SilentlyContinue
	Remove-Item -Recurse -Force "$env:USERPROFILE\.gradle\wrapper\dists\gradle-8.12.1*" -ErrorAction SilentlyContinue
	Remove-Item -Recurse -Force "$env:USERPROFILE\.gradle\jdks" -ErrorAction SilentlyContinue
	Remove-Item -Recurse -Force "$env:USERPROFILE\.gradle\caches\forge_gradle\minecraft_user_repo" -ErrorAction SilentlyContinue
	Remove-Item -Recurse -Force "$env:USERPROFILE\.gradle\caches\modules-2\files-2.1\net.minecraftforge\forge\1.21.8-58.0.5*" -ErrorAction SilentlyContinue

	Write-Host "== verifying versions ==" -ForegroundColor Cyan
	./gradlew --no-daemon --version

	Write-Host "== fetching deps & generating vscode runs ==" -ForegroundColor Cyan
	./gradlew clean --refresh-dependencies genVSCodeRuns	
	./gradlew buildEnvironment

	if ($RunClient) {
		Write-Host "== launching runClient ==" -ForegroundColor Cyan
		./gradlew runClient
	}

	Write-Host "`nall set. if vscode still shows stale problems:" -ForegroundColor Green
	Write-Host " - Command Palette → 'Java: Clean Java Language Server Workspace'"
	Write-Host " - then → 'Developer: Reload Window'"
}
finally {
	Pop-Location
}
