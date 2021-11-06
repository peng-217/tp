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


* New Feature: Created exceptions for invalid clues and suspects and missing scenes and narratives.
  * What it does: Allows a more coherent organization of errors as developers will know what goes wrong.
  * Justification: This makes the code more defensive as errors when caught can be easily identified leading to the
  correct handling response (e.g., correct UI error message).


* Code contributed:[RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Herrekt&tabRepo=AY2122S1-CS2113-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) 


* Enhancements to existing features:
  * Reduced coupling within the `Investigation` class, removing its reliance from the `NoteList` class 
  (Pull requests #87, # 96) .
  * Improved OOP of the `Narrative` class and its instantiation (Pull request #59).
  * Added additional methods in the `Parser` class enabling it to take in additional inputs for the note command,
  and also overloaded certain methods in the `NoteList` class to allow multiple inputs at once (Pull requests #213).
  * Created assertion tests for the different exceptions 
  (Pull request #83, and in the later PRs as constant changes were made to the `Investigation` class).
  * Ui fixes were made to improve the quality of message when related to error messages (Pull requests #213, #228).


* Documentation: 
  * User guide:
    * Added documentation for the features `/note` (Pull requests #105, #116).
  * Developer guide:
    * Added implementation details for `SceneList` and `SuspectList` related classes. 
    Created sequence diagram to show initiation of SceneList and SuspectList via their corresponding builders
    (Pull requests #105, #112, #228).
    * Javadocs:
      * Added javadocs for class creation like `SuspectList`, `NoteCommand`, and `SceneList` (Pull request #221).
  

