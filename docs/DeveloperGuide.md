# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Architecture

### Parser component
**API:** Parser.java 

The parser component is used to parse the input given by the user. 

How the parser work
- When the user gives an input, the parser tries to return a command based the input given.
- If the input does not generate a valid command type, it throws the invalidInputException.

The sequence diagram below demonstrates how the parser works.

{INSERT DIAGRAM HERE}
>>>>>>> e7de3ad9b4526fbac1d798c6348b53efd66ad6a8

### Note component
**API:** Note.java

The note component allows user to create / open / delete /search note. 

How the note work
- When user want to take note, a note with title and content will be created and added 
  to note list.
- Notes in the note list can be found by their titles and scene index.
- Unwanted notes can be deleted.

### UI component
**API:** Ui.java 

The ui component communicates with the user via the terminal. Other component call methods of 
ui to print output to terminal. 

How the ui work
- Print messages to terminal depending on the scene.
- Print corresponding output to terminal according to input command.

### Command component
**API:** Command.java 

The command component takes in the input given by the user
and generates a command based on the input given. 

Command component is an abstract class.
Functional command extends the command class. 
Eg NextCommand, ViewCommand Etc.

How the command work
- Each command has an execute command.
- Execute command based on the command type.

### Investigation component
**API:** Investigation.java

The investigation class manages the investigation scene in each
investigation scene. 

How the investigation works
- When an investigation command is returned from the parser, we investigate the input given by the user.
- For each scene, the investigation class display the scene's narrative.
- The investigation class is also used to determine if the user has managed to find the correct killer from the game.


### Clue component
**API:** Clue.java

### Narrative component
**API:** Narrative.java

The narrative class generates the story for each of the scene.

### Scene component
**API:** Scene.java

The scene class contains and produces the narrative for the scene.

How the scene class work
- Each scene has a scene type.
- For each scene type, we interact differently from the user.

See below for example.
- The introduction scene shows the introductory message to the user.
- The investigation scene asks the user either investigate a suspect or look into a clue.

### Search component
**API:** Search.java

### Storage component
**API:** Storage.java

### Suspect component
**API:** Suspect.java

The `Suspect` class contains an `ArrayList` of the class `Clue`. 

How the suspect class work:

  * Different suspects in a particular scene are stored in the `SuspectList` class.
  * Suspects are stored via a `LinkedHashMap<String, Suspect>`, with the string being the suspect's name.

See below for example:

  * The first investigation scene has a `SuspectList` containing one name, "Father", 
and four clues within its corresponding `Suspect` class.

![](Suspect.png)


## Implementation

### Display checked-clues feature

This feature allows the user to review the clues that have been gathered. The clues will be displayed according to the suspect they belong to.
To implement this feature, a clue tracker that contains all 5 suspects and all the clues corresponding to each suspect is used.
Whenever a clue is checked out by the user, the respective clue in the clue tracker will be marked as checked.
When the view feature is invoked, clues in the clue tracker will be iterated through. Once a checked-clue is found, it will be printed out for user tp review.

An alternative to this would be to update the clue status under each scene. However, this does not allow the display of clues according to different suspects.

### Local Game Data Storage

The local Game Data Storage feature allows users to save the current game progress and resume the saved progress in the Future.
It is facilitated by ```java.io.File``` and ```java.io.FileWriter```. 
It implements the following operations 
- ```Storage#checkPath()``` -- Checks if there is a valid path to the data file, and creates a new data file if the data file is missing or hte path is invalid.
- ```Storage#readFile()``` -- Reads all the lines in the data files and store the information into a ```ArrayList<String>``` type Array List, then close the file.
- ```Storage#rewriteFile()``` -- Erase the content of the data file and rewrite from the start, then save and close the file.

At first ```Storage file = new Storage("name.txt")```, initialise the ```Storage``` class type with the name of the file. 
Then ```file.checkPath()``` will check for existing data file and creates a new data file if the path ```./Data/name.txt```is invalid.
Then read the file and store the information into array list using ```ArrayList<String> content = file.readFile()```.
Eventually, edit the content and rewrite to data file using ```file.rewriteFile(content)```

###Taking Notes For Specified Scene

This note-taking feature allows users to take note whenever they want, and store these notes locally. All the locally saved notes will be loaded and accessible
for users to open. Each note contains three parts: scene index, title and content. The note index will be automatically set according to the current scene that 
user is investigating. Note tile and content are fulfilled by users. Default title will be given if user does not give a title. User can also search for an 
existing note by either search its title/scene index or directly open it by its sequence number (in the note list). User can also delete the unwanted notes by
typing in its sequence number.

### SuspectListBuilder

Suspects and clues used in different scenes can be kept in a txt file and created following a specific format.
It uses `java.io.File`, `java.util.Scanner`, and is implemented as:
* `suspectListBuilder(String fileLocation, SuspectList suspectList)` -- where `fileLocation` is the directory 
containing the specified text file and `suspectList` is the instance of class `SuspectList` that the suspects 
and clues are to be added into.

This method will search for the specified text file, throwing a `FileNotFoundException` if it is missing.
The text file will be written in such a way that the program can recognize how many suspects
and how many clues there are. It will first add the suspects from the file into the suspectList 
via the method `addSuspect(String suspectName, Suspect suspect)`, and then the clues via the 
method `addClueForSuspect(String suspectName, Clue clueToAdd)` to the suspect with the corresponding `suspectName`.



## Appendix


## Product scope
### Target user profile

- enjoy the playing interactive game
- enjoy mystery genre
- enjoy reading
- wants to take a break from visual games

{Describe the target user profile}

### Value proposition

- Provide an alternative game for users to exercise creative thinking

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see all commands available|understand the game mechanics|
|v1.0|user|investigate the suspects available|better understand the suspect|
|v1.0|user|investigate the clues available|understand the story line better|
|v1.0|user|choose the suspect|see if I am able to solve the crime|
|v2.0|user|resume the game after exiting|continue the game instead of restarting|
|v2.0|user|write notes|look at the notes I have written for each scene and suspect|
|v2.0|user|go back to the previous scene|recap the previous scene|
|v2.0|user|investigate suspect using name or their index|more than one way to investigate a suspect|

## Non-Functional Requirements
1. The game should work as long as java 11 is installed on the local machine.
2. A working keyboard to play the game and a monitor to read the text.

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
