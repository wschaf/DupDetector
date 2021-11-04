# Contributing to DupDetector
The codebase is a Java project which is built with Gradle.

The codebase is stored on Github [here.](https://github.com/wschaf/DupDetector)

## Setup
To setup, first clone the repository to your development machine.
```powershell
git@github.com:wschaf/DupDetector.git
```
Visual Studio Code should automatically detect that DupDetector is a Java project.

## Build
To build the project, you should first install Gradle:

```
sudo apt install gradle
```

Next, you can run the Gradle build with the command:
```
./gradlew build --info
```

## References
- [Oracle Java Code Conventions Guide](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf)

## Submitting a Pull Request
1. All development should occur on your own branch, based on main: `git checkout -b BranchName`
1. Stage any added files: `git add .`
2. Create a commit: `git commit -a -m "A descriptive commit message"`
3. Push the commit to the remote: `git push origin BranchName`
4. GitHub's UI will notice that you have pushed changes and ask if you would like to create a Pull Request.
5. Describe the changes you have made in the pull request comments area.
6. At least one reviewer must approve your PR before it can merge into main.
7. Caveats: Your PR must build and complete all unit tests without errors.