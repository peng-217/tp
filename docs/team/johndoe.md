# Peng Fei - Project Portfolio Page

## Project: The Great Detective
The Great Detective is an interactive murder-mystery game.


### Summary of Contributions

- `Feature`: Note Class
    - What it does: Note is the basic unit of note function, it contains scene index, title, and content.
    - Justification: This allows user to take a note with scene index, title and content.

- `Feature`: NoteList class
    - What it does: The NoteList is a collection of all notes that user created, and it performs all note-related functions, such as\
      create a note, open a note, delete notes, search notes using keywords/scene index.
    - Justification:

- `Feature`: GameNoteFileManager class
    - What it does: The GameNoteFileManager stores all the existing notes locally, and it will open all stored notes when the game is\
      started. A new note file will be created if there is corruption being detected in the old note file.
    - Justification:

-`Code Contribution`: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=peng-217&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25)
-`Enhancements implemented`:
-`Documentation`:
 - `User Guide`
   - Added documentation for the features `/note`, `/quit`, `keywords`, `index`
 - `Developer Guide`
   - Added documentation and UML diagram for 'Ui' class