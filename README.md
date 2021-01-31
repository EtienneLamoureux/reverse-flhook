# Reverse FLHook
This project reserve-engineers some of the capabilities of [FLHook](https://github.com/DiscoveryGC/FLHook), giving players direct access to some servers-side functions.

- [Features](#features)
- [Usage](#usage)
  * [Requirements](#requirements)
  * [Installation](#installation)
- [Development](#development)
  * [Requirements](#requirements-1)
  * [Installation](#installation-1)
    + [Eclipse](#eclipse)

## Features
See the [help file](/src/main/resources/help.txt).

## Usage
### Requirements
- Java 11+
- Local installation of [Discovery Freelancer](https://discoverygc.com/)

### Installation
1. Download the [latest jar file](https://github.com/EtienneLamoureux/reverse-flhook/releases)
2. Locate your local Discovery Freelancer installation
3. Navigate to the jar location
4. Create a new file, named `application.yml`
5. In the new file, paste the following, using a **plain-text editor**: `data.ini.rootPath: <local Discovery Freelancer installation directory>`
6. Open a command prompt
7. Navigate to the jar location
8. Run it using `java -jar reverseFLHook-<version>.jar <command> <arguments> [<flags>]`

## Development
### Requirements
- Java JDK 11+
- Local installation of [Discovery Freelancer](https://discoverygc.com/)

### Installation
#### Eclipse
1. Read the license
2. Clone the repository
3. Adjust the values in `src/main/resources/application.yml` to your local setup
4. In a Git bash, run `./gradle.bat clean build --refresh-dependencies`
5. In Eclipse, click File > Import... > Existing gradle project
6. In Eclipse, right-click the project > Run as > JUnit Test
7. Code!