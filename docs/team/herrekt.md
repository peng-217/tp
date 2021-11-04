# Herrekt's Project Portfolio Page

## Project: The Great Detective

### Given below are my contributions to the project:

* New Feature: Added the class `SuspectListBuilder`.
  * What it does: Allows the creation of SuspectList to be more efficient, by reading a text file containing the clues
  and suspects and adding them into the specified SuspectList.
  * Justification: Clues were previously added manually by creating classes extending from `Clue`.
  Each extension contains the required detail of the individual clue and are added into the SuspectList.
  * Highlights: This enhancement automates clue and suspect addition into SuspectList, by-passing the need of creating 
  class extension from `Clue`, allowing the code to follow a correct OOP line of approach instead.


* New Feature: Allowed the `/note` command to take in more parameters for faster typing.
    * What it does: Instead of just single word commands being inputted in note creation, 
  users can create, read, search, or delete a note in at least two lines.
    * Justification: This would be more suited to fast-typers, and allow user-made notes to be more efficiently managed.
    * Highlights: This enhancement allows a note command, which could take up to five separate inputs,
  to be created with two inputs instead.