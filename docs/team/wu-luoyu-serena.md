##Wu Luoyu's Project Portfolio Page

### Project: The Great Detective

_The Great Detective_ is a desktop CLI application that allows players to enjoy the fun of role-playing and logical reasoning to find out the truth of a murder case. The player gets the chance to investigate in the case by gathering information about the events that lead to the murder and clues about the suspects.

Given below are my contributions to the project.


* **Key Architecture**: Added Narrative class
  * What it does: models the storytelling process of each scene of the game, including key methods such as `getNarrative()` and `displayNarrative()`.
  * Justification: Narratives which immerse players in the story are essential for the gameplay.


* **Key Architecture**: Added Clue class
  * What it does: models a clue object which consists of key attributes image, description and isChecked, and methods such as setCheck() and toString().
  * Justification: Clues are fundamentals of the interactive gameplay, providing interesting materials for users to investigate in.
  * Highlights: Each clue is given a succinct name and an ASCII art image to allow enhanced visuals of the game.


* **New Feature**: Added a view command
    * What it does: allows the user to review the clues he/she has gathered
    * Justification: This feature improves the product significantly because a user can easily forget the clues they have gathered due to the large number of clues available and a convenient way for him to review it should be provided.
    * Highlights: This enhancement affects existing commands such as investigation. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and additional helper classes such as `SearchedClueTracker`.
    
* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=WU-LUOYU-SERENA&tabRepo=AY2122S1-CS2113-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated view command so that it takes in names of suspects as arguments, allowing the user to query clues that have been gathered for specific suspects instead of all (Pull requests [\#78](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/78), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_