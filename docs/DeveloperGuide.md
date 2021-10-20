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
