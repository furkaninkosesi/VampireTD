# VampireTD

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

## Game Design Process

### Game Logic Updates
- **Create UIbar**: **done**
- Bar logic Design
- Weakness and Strength Features Against Different Ammo Types (?)
- Tower's priority
- Tower Place Logic
- Random Wave Generator

### Enemy Logic 
- ~~Vampire(Default)~~ : **done**
- Noble Vampire(High Health, Low Speed)
- Bat Swarm (Swarm)
- Bloodsucker Vampire(Self-Healing Ability)

### Tower Logic 

- ~~Archer Tower~~ : **done**
- Magic Tower : **in process**
- Garlic Trap Tower
- Sunburst Obelisk

### Assets: 
- **All towers (with Rank 1-2-3 versions) & projectiles & animations** 
- **All enemies**
- level's map
- Control Bar
- UIbar (with small **update** icons for each tower)

### Updates:
- in process
 