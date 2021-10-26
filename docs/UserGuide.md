# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

**Notes about the command format:**
* Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in /view [NAME]..., NAME is a parameter which can be used as /view Father.
* Items in square brackets are optional.
  e.g /view [NAME]... can be used as /view or /view Father.
* Items with ... after them can be used multiple times including zero times. 
  e.g. [NAME]... can be used as   (i.e. 0 times), Father, Father Ling etc.

### Taking notes whenever user want: `/note`
This allows user to take note with title and content whenever they want.

### Search notes with keywords: `keywords`
This allows user to search the notes using keywords in title

Example of usage:
```
/note
```
```
Do you want to create a new note or open a existing note or delete a note?
```
```
open
```
```
Here are the list of notes available to you.
1. BOOKSHELF
2. CAR
3. LIVING ROOM WITH BLOOD
Do you want to search a note (type in 'search') or directly open a note (type in 'open')?
```
```
search
```
```
Do you want to search by keyword or scene index?
```
```
keyword
```
```
Please enter keywords
```
```
BLOOD
```
```
Here are the list of notes found given keywords:
1. scene 2
LIVING ROOM WITH BLOOD
There is blood in living room, so I think suspect is Wendy.

==============================
```
### Search notes with scene index: `index`
This allows user to search notes with scene index.

Example of usage:
```
/note
```
```
Do you want to create a new note or open a existing note or delete a note?
```
```
open
```
```
Here are the list of notes available to you.
1. BOOKSHELF
2. CAR
3. LIVING ROOM WITH BLOOD
   Do you want to search a note (type in 'search') or directly open a note (type in 'open')?
   ```
```
   search
   ```
```
   Do you want to search by keyword (type 'keyword') or scene index (type 'index')?
   ```
```
   index
   ```
```
   Please enter scene index:
   ```
```
   2
   ```
```
   Here are the list of notes found given keywords:
1. scene 2
   BOOKSHELF
   There are many books on the bookshelf.
2. scene 2
   CAR

3. scene 2
   LIVING ROOM WITH BLOOD
   There is blood in living room, so I think suspect is Wendy.

==============================
```
### Moving to the next scene: `/next`
This allows the user to go the next scene.

Example of usage: 

```
/next
```

### Exit the game: `/exit`
This allows the user to exit the game.

Example of usage:

```
/exit
```
```
Goodbye.
```

### Go back to the previous scene: `/back`
This allows the user to return to the previous scene.

Example of usage:

```
Scene 1 Investigation
Who do you want to investigate?
1. Father
```
```
/back
```
```
------------------
| Who Killed Me? |
------------------

I woke up and found myself dead. The Spirit Guide from the Hell told me that the only way to revive my soul is for me to find the murderer, eliminating the grudge in my soul. So I have to go back 24 hours ago and find the murderer from the perspective of my soul.

----------------
| Instructions |
----------------

Here are the commands that you can enter:
"/help" - view this command list
"/exit" - exit the game
"/next" - move on to the next scene or the next stage of a scene
"/note" - create a new note/ open a note/ delete a note
"/view" - view all the clues that you have gathered
"/restart" - restart the game from beginning

Now, enter "/next" to start your journey to the truth.
```

### Viewing the list of commands available: `/help`
This allows the user to view the list of commands available.

Format: `/help`

Example of usage:

```
/help
```
```
Here are the list of commands available to you.
/help
/exit
/next
/note
/view
/restart
```

### Restarting the game: `/restart`
This allows the user to restart the game.

Example of usage:

```
/restart
```
```
------------------
| Who Killed Me? |
------------------

I woke up and found myself dead. The Spirit Guide from the Hell told me that the only way to revive my soul is for me to find the murderer, eliminating the grudge in my soul. So I have to go back 24 hours ago and find the murderer from the perspective of my soul.

----------------
| Instructions |
----------------

Here are the commands that you can enter:
"/help" - view this command list
"/exit" - exit the game
"/next" - move on to the next scene or the next stage of a scene
"/note" - create a new note/ open a note/ delete a note
"/view" - view all the clues that you have gathered
"/restart" - restart the game from beginning

Now, enter "/next" to start your journey to the truth.
```


### Investigating suspect: `KEYWORD OR INDEX`
Users can investigate the suspect using either the suspect's name or the suspect number.

Format: `[/investigate] KEYWORD OR INDEX`

- /investigate is an optional command for the user to use.
- The user has to enter a valid suspect name or the suspect number.
- Suspect name is not case-sensitive.

Example of usage:

```
Scene 1 Investigation
Who do you want to investigate?
1. Father
```

The following command below are valid commands
- 1
- father
- /investigate father

```
Scene 1 Investigation
 - Father
0. Go back to list of suspects
1. Insurance Documents
2. Map
3. Phone Call
4. Text Message
Enter "/next" to go to the next scene.
```


### Investigating clue: `INDEX`
This allows the user to investigate the clue based on the index.

Format: `INDEX`

- The index has to be a number based on the clue number given to the users to choose.

Example of usage:

```
Scene 1 Investigation
 - Father
0. Go back to list of suspects
1. Insurance Documents
2. Map
3. Phone Call
4. Text Message
Enter "/next" to go to the next scene.
```
```
1
```
```
------------------------------------------------
              Insurance Documents
                __________
               ()_________)
                \ ~~~~~~~~ \
                  \ ~~~~~~   \
                    \__________\
                     ()__________)
I went to the room and asked my father to have
lunch. He hurriedly put away the paper on his
hand. I recognized it from the perspective of
my soul that it was a few insurance documents.
It seemed that my father bought insurance for
our family members a few years ago, amount
insured more than ten thousand.

Scene 1 Investigation
 - Father
0. Go back to list of suspects
1. Insurance Documents
2. Map
3. Phone Call
4. Text Message
Enter "/next" to go to the next scene.
```

### Choosing the killer: `KEYWORD`
This allows the user to choose the killer based on the user name.

Format: `KEYWORD`

- The name has to be one of the name of the suspects given to the users to choose.
- The name given by the user is not case-sensitive.

Example of usage:

```
Here are all the suspects
1. Father
2. Kevin
3. Wendy
4. Ling
5. Zack
Who do you think killed you?
```
```
Wendy
```
```
This is an ending for guessing correctly.
```

### Viewing checked clues: `/view`

Views the clues that have been gathered from investigations.

Format: `/view [NAME]...`

* NAME(s) provided must be one/more of the suspects' names.
* If valid names are provided, only clues gathered that are specific to those suspects will be shown.

Examples:

* `/view` Displays all clues that have been gathered.
* `/view Father` Displays clues that have been gathered and are specific to Father.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Add todo `todo n/TODO_NAME d/DEADLINE`

|Action| Format & Example |
|--------|----------|
| Next | /next |
| Restart | /restart |
| Exit | /exit |
| Back | /back |
| Investigate | [/investigate] INDEX or KEYWORD e.g., /investigate father |
| Choose Killer | KEYWORD e.g., Father |
| View Clues | /view [NAME]...
