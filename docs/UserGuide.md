# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Moving to the next scene: `/next`
This allows the user to go the next scene.

Example of usage: 

```
/next
```

### View the list of commands available: `/help`
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

### Restart the game: `/restart`
This allows the user to restart the game.

Example of usage:

```
/restart
```

### Investigate suspect: `INDEX`
This allows the user to investigate the clue based on the index.

Format: `INDEX`

- The index has to be a number based on the clue number given to the users to choose.

Example of usage:

```
Scene 1 Investigation
Who do you want to investigate?
1. Father
```
```
1
```
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


### Investigate clue: `INDEX`
This allows the user to investigate the suspect based on the index.

Format: `INDEX`

- The index has to be a number based on the suspect number given to the users to choose.

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

### Choose the killer: `NAME`
This allows the user to choose the killer based on the user name.

Format: `NAME`

- The name has to be one of the name of the suspects given to the users to choose.

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


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
