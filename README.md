# Reverse FLHook
This project reserve-engineers some of the capabilities of [FLHook](https://github.com/DiscoveryGC/FLHook), giving players direct access to some servers-side functions.

- [Features](#features)
- [Usage](#usage)
  * [Requirements](#requirements)
  * [Installation](#installation)
    + [Example](#example)
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
2. Navigate to the jar location
3. Create a new file, next to the jar, named `application.yml`
4. Using a **plain-text editor**, copy [this](https://github.com/EtienneLamoureux/reverse-flhook/blob/main/src/main/resources/application.yml) into the new file
5. Change the value `data.ini.rootPath` to point to your local Discovery Freelancer installation
6. Save and close the new file
7. Open a command prompt
8. Navigate to the jar location
9. Run it using `java -jar reverse-flhook-<version>.jar <command> <arguments> [<flags>]`

#### Example
```cmd
$ java -jar reverseFLHook-1.3.0.jar survey li01 li01_07_base -auto -copy
  ___                                        ___   _      _  _               _
 | _ \  ___  __ __  ___   _ _   ___  ___    | __| | |    | || |  ___   ___  | |__
 |   / / -_) \ V / / -_) | '_| (_-< / -_)   | _|  | |__  | __ | / _ \ / _ \ | / /
 |_|_\ \___|  \_/  \___| |_|   /__/ \___|   |_|   |____| |_||_| \___/ \___/ |_\_\

40011C05-2CBE93B3-37362526-7F1A2F20-30ADA171-C3CA7401-6165EE75
Hyperspace coordinates copied to clipboard!
Typing hyperspace coordinates in 5 seconds...
Done!
```

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