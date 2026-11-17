[README(2).md](https://github.com/user-attachments/files/34954971/README.2.md)
# Game Engine From Dimash

**Author:** DINMUKHAMMED SAILAU  
**Group:** SE-2530  
**Repository:** https://github.com/sailaudinmuhammed-cmyk/sdp-final

A console-based Java game engine created for a Software Design Patterns final project. It integrates six design patterns: **Builder, Factory Method, Bridge, Strategy, Command, and Facade**.

## Features

- **Edit Mode:** create characters, choose maps, inspect a scene, undo and redo edits.
- **Run Mode:** round-based battles using Attack and Hide strategies with map modifiers.
- **Debug Mode:** inspect state and switch between plain console and ASCII-style output.

## Run

Requires **JDK 17+** (tested on OpenJDK 21). Run `Main.java` from the IDE, or from the folder containing `source/src`:

```bash
mkdir -p out
javac -d out $(find source/src -name "*.java")
java -cp out Main
```

On Windows, the easiest method is to mark `source/src` as **Sources Root** in IntelliJ IDEA and run `Main.java`. The source submission ZIP contains a minimal `Main.java` launcher.

## Project structure

`assets/` — maps and characters  
`factory/` — map factories  
`behavior/` — interchangeable character strategies  
`command/` — reversible editing commands  
`render/` — output abstraction and implementations  
`engine/` — scene and main application facade

Full design documentation, UML diagrams, source listings, and test evidence are provided separately in the project report.
