# VS Code Migration Guide (Minerva)

This folder includes a `.vscode/` configuration prepped for Visual Studio Code.

## What I detected
- Maven project: False
- Gradle project: True
- Eclipse metadata (.project/.classpath): False
- Source dirs: ['src/main/java', 'src']
- Java version (from pom): (not detected)

## How to open in VS Code
1. Open the **Minerva/** folder in VS Code.
2. When prompted, install the recommended extensions.
3. If Maven or Gradle is used, use the sidebar "Maven"/"Gradle" views to build and test.
4. Use **Run and Debug** → *Debug (Launch) - Current File* for ad-hoc runs/debugging.

## JDK
Ensure a JDK 17+ is installed and selected in VS Code:
- Press `Ctrl+Shift+P` → **Java: Configure Java Runtime**.

## Notes
- If your project is Eclipse-only (no pom.xml / build.gradle), consider adding Maven or Gradle for dependency management.
- For Lombok, install the Lombok VS Code extension and ensure annotation processing is enabled.
