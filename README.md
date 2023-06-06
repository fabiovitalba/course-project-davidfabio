# Polygon Wars
## 1. About
Polygon Wars is a twin stick shooter video game. In this game you control a shape which can shoot down enemies.

### 1.1. Demo
Add a link to a demo of your project.

## 2. Authors
This project was created by:
* David Pittracher 
* Fabio Vitalba

## 3. Usage
Unfortunately LibGDX only has unofficial maven support. So in order to run the game you will have to execute the steps below.

### Build
To build the project, run:
```shell
mvn package
```

You can also compile
```shell
mvn compile
```
or test the project
```shell
mvn test
```

### Execution
To play the game, run:
```shell
mvn exec:exec
```

Alternatively you can also run the jar-directly, in this case run the following:
#### On Windows & Linux:
```shell
java -jar ./target/polygonwars-1.0.0-jar-with-dependencies.jar
```

#### On MacOS:
```shell
java -jar -XstartOnFirstThread ./target/polygonwars-1.0.0-jar-with-dependencies.jar
```
> Note: The flag `-XstartOnFirstThread` is only required for MacOS, if you're on any other Operating system, you should omit this flag.

### Documentation
In order to generate the documentation for the project use the following command:
```shell
mvn javadoc:javadoc
```

## 4. Implementation
### 4.1. Architectural Overview
Our Project is divided into three main Java Packages:
- org.davidfabio.**game**
  - Contains anything that represents the Game-World and Game-Flow.
- org.davidfabio.**ui**
  - Contains anything that creates User Interfaces (Main Menu, In-game UI, ...)
- org.davidfabio.**utils**
  - Contains anything that is useful for all packages or is not strictly Game-World-specific.

We have some diagrams showing the relationship between our implemented classes. Please have a look at these diagrams before delving into our codebase.
- **UI Flowchart**
  - This flowchart explains very well how the Programm is started and which classes are called to reach the actual Game.
  - [UI Flowchart](https://github.com/Programming-Project-2021-22/course-project-davidfabio/blob/main/UI-FLOWCHART.md)
- **Game Class-Diagram**
  - This Class Diagram illustrates how the Game-World is composed. It does not contain all specific classes and subclasses, but it's a good start to understand what is present in a Game-World and how it's connected.
  - [Game Class Diagram](https://github.com/Programming-Project-2021-22/course-project-davidfabio/blob/main/GAME-CLASS-DIAGRAM.md)
- **World Class-Diagram**
  - This Class Diagram shows all the main classes like `player`, `enemy`, `entity`, `bullet` and all their derivatives.
  - [World Class Diagram](https://github.com/Programming-Project-2021-22/course-project-davidfabio/blob/main/WORLD-CLASS-DIAGRAM.md)

### 4.2. Third-Party Libraries
These are the Third-Party Libraries we've used:
- **LibGDX**: This library provides us with some basic functionalities needed to create a game. We were able to use features like: Shape-Renderers, Cameras, Sound-players and more.
- **Shade UI**: (This is no library, but a preexisting Resource we're using.) Skin for UI (Buttons, Progress Bars, and so on). It is freely available under [czyzby's GDX Skins GitHub Repository](https://github.com/czyzby/gdx-skins).
- **Jackson**: This library allows us to easily serialize and deserialize objects into JSON format for storage in a file.

### 4.3. Programming Techniques
List and explain how you used the 10 programming techniques required for this project.
- **Graphical User Interface**: We used the built-in Libraries of LibGDX in order to create various User Interfaces for Game Menus, Settings, Highscores and User Interface in Game. 
- **Method Overriding**: Since we're using inheritance and interfaces we predefine a few methods at a high level (Example `Entity` provides `init()`, `update()`, `render()` methods.). However, some Entities require special behaviour and therefore need to override the original implementation of the method. For example the `Player` class requires a different `update()` method than the `EnemyChaser` class.
- **Interfaces**: `Movable`, `Attackable`, `Attacker` Interfaces _**...to be explained...**_
- **Method Overloading**: Various methods use the overloading technique to provide easy-to-use methods. For example:
  - In the `UIBuilder` class we use method overloading to provide various functions to create UI-controls in default settings, or by overloading, with specific settings.
- **Collections**: In order to store various data we use collections. For example:
  - When storing past `Score`s in order to list the highscores.
  - When storing enemies in the `World`-class to periodically update their behaviour and spawn new ones.
- **Try-Catch-Blocks**: In order to safely load the sounds and music we rely on Try-Catch blocks to avoid any errors on missing files. We also use these Try-Catch blocks to avoid RunTimeErrors while writing or reading from files.
- **File I/O**: In order to store settings and scores we use files and therefore we need to write and read from files.
- **Serialization**: In order to store Settings and Scores in a sensible way, we serialize them into a JSON. We use Jackson for this.
- **Deserialization**: In order to read our serialized Settings and Scores we need to deserialize them from JSON. We use Jackson for this.
- **Test Hooks**: For our Test Suite we use `@BeforeAll` and `@AfterAll` in order to clean our Test environment. For example the `JSONFileManagementTest` uses these hooks to clean the Files created.
- **Streams**: In the High Scores screen we use streams to show the best 5 scores present in the Scores List.

### 4.4. Tests
Testing a LibGDX application has proven to be very tricky. This is because of two reasons mainly:
- Testing UI is always difficult.
- Many LibGDX-methods, which we use throughout our game, can only be run in the main thread and require some kind of initialization. Both requirements that we cannot satisfy through jUnit Tests. Therefore Classes like the `Camera` cannot be tested as they would require Gdx.graphics to be set up, which again requires an Application to run on the main thread.

Because of these constraints we tried to test as much of our non-LibGDX-related classes as possible. Obviously this still leaves a lot of room for potential errors.

## 5. Experience
### 5.1. Overall Experience
Overall, we had fun iterating over the various features and ideas of the Project. Both David and I learned a lot in the process. We also learned from eachother since we both have different backgrounds in programming and are used to different styles of programming.
We were very quick at building a working prototype and this helped us get a lot of the groundwork done early.

### 5.2. Division of Responsibilities
Describe the roles and responsibilities each member had in this project.
- **David Pittracher:** Game Design
- **Fabio Vitalba:** UI Design, Documentation, Project structure

### 5.3. Main Challenges
Elaborate on the main challenges each group member faced throughout the project and how they were surpassed.
- **David Pittracher:** ...
- **Fabio Vitalba:** Migrating from gradle (LibGDX's preferred Bundler) to Maven was quite painful. Coordinating the work was difficult at first, but once we figured out how to use the GitHub Issues it was a lot better.

### 5.4. Learning Outcomes
Describe what you learned with this project.
- **David Pittracher:** ...
- **Fabio Vitalba:** I learned a new library called `LibGDX` and David helped me learn something more regarding Game Development.
