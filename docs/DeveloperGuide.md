# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

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

### [Proposed] Clue Reader and Organizer

Clues used in different scenes can be kept in txt file and created following a specific format.
It uses `java.io.File` and implements:
* `clueReader(TEXT_LOCATION.txt)` -- where `TEXT_LOCATION.txt` is the directory containing the specified text file.

This method will search for the specified text file, throwing a `FileNotFoundException` if it is missing.
It will read the file and store the clues as the Class `Clue`, under the specified `Suspect` instance which is then stored in a `SuspectList` class.



## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
