# Java + VS Code Project Setup (JDK 24 + Node 24)

## 1. Install JDK 24.0.2
1. Download `openjdk-24.0.2_windows-x64_bin.zip` from [https://jdk.java.net/24/](https://jdk.java.net/24/).
2. Extract it to a permanent location, e.g.:
3. Update Windows Environment Variables:
- **JAVA_HOME** → `C:\Program Files\Java\jdk-24.0.2` or where ever the folder is
- Add `%JAVA_HOME%\bin` to the **Path** variable.
4. Verify in a new terminal:
```powershell
java -version
```

## 2. Configure VS Code to Use JDK 24
Open VS Code → Ctrl+Shift+P → Java: Configure Java Runtime.
Under Installed JDKs, click Add… → select C:\Program Files\Java\jdk-24.0.2.
Set it as default for the workspace.
In .vscode/settings.json, add:
```json
{
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-24",
            "path": "C:\\\\Program Files\\\\Java\\\\jdk-24.0.2",
            "default": true
        }
    ],
    "java.import.generatesMetadataFilesAtProjectRoot": true,
    "files.exclude": {
        "**/.project": true,
        "**/.classpath": true,
        "**/.settings/": true,
        "**/.factorypath": true
    },
    "editor.formatOnSave": true
}
```
## 3. Lock Gradle to Java 24
In build.gradle, add:
```json
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}
```
## 4. Update Node.js for SonarCloud Extension
I would suggest using nvm  
The SonarCloud VS Code extension requires Node.js ≥ 20, and we’re using Node.js 24:

Download Node.js 24.x from https://nodejs.org/en.

Install it (or replace your existing Node install).

Verify:
node -v