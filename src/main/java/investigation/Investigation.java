package investigation;


import storage.Storage;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import parser.Parser;
import scene.Scene;
import scene.SceneList;
import scene.SceneListBuilder;
import suspect.Suspect;
import ui.Ui;
import note.Note;
import note.NoteList;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Investigation {
    private static InvestigationStages stage;
    private static SceneList sceneList;
    private static Scene currentScene;
    private static String currentSuspect;
    private static Parser parser;
    private static Ui ui;
    private static Storage storage;
    private static NoteList notes;
    private static int defaultTitleCounter = 1;

    private static final String FILE_NOT_FOUND = "File not found for scene";
    private static final String WRONG_INDEX_GIVEN = "Sorry please enter index within range";
    private static final String ENTER_VALID_COMMAND = "Please enter a valid user command";
    private static final String INVALID_COMMAND = "Invalid command";

    private static final String KILLER_WENDY = "Wendy";

    public Investigation(Parser parser, Ui ui) {
        this.parser = parser;
        this.ui = ui;
        storage = new Storage();
        notes = new NoteList(ui);
        stage = InvestigationStages.SUSPECT_STAGE;
        sceneList = SceneListBuilder.buildSceneList(ui);
        Storage.openNoteFromFile(notes);

        currentScene = sceneList.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    public void printCurrentInvestigation() {
        if (stage == InvestigationStages.SUSPECT_STAGE) {
            if (sceneList.getCurrentSceneIndex() == 0
                    | sceneList.getCurrentSceneIndex() == 4
                    | sceneList.getCurrentSceneIndex() == 5
                    | sceneList.getCurrentSceneIndex() == 6) {
                return;
            }
            System.out.println("Scene " + (sceneList.getCurrentSceneIndex()) + " Investigation");
            System.out.println("Who do you want to investigate?");
            ui.printSuspects(currentScene.getSuspectList());
        } else {
            System.out.print("Scene " + (sceneList.getCurrentSceneIndex()) + " Investigation");
            System.out.println(" - " + currentSuspect);
            System.out.println("0. Go back to list of suspects");
            Suspect suspect = currentScene.investigateSuspect(currentSuspect);
            ui.printListOfClues(suspect.getClues());
        }
    }


    public void investigateScene(Integer index) throws InvalidSuspectException, InvalidClueException {
        switch (stage) {
        case SUSPECT_STAGE:
            currentSuspect = parser.getSuspectNameFromIndex(sceneList.getCurrentSceneIndex(), index);
            stage = InvestigationStages.CLUE_STAGE;
            break;
        case CLUE_STAGE:
            int suspectNumClues = currentScene.investigateSuspect(currentSuspect).getNumClues();
            if (index > suspectNumClues) {
                throw new InvalidClueException(WRONG_INDEX_GIVEN);
            } else if (index == 0) {
                stage = InvestigationStages.SUSPECT_STAGE;
            } else {
                System.out.println(currentScene.investigateSuspect(currentSuspect).getClues().get(index - 1));
            }
            break;
        default:
            System.out.println(INVALID_COMMAND);
        }
    }

    public boolean isACorrectGuess() {
        // int isTimeToGuess = getNextSceneFromSceneList();
        ui.printSuspectKillerMessage();
        String suspectedKiller = ui.readUserInput();
        return checkSuspectedKiller(suspectedKiller);
    }

    public void processNote() {
        System.out.println("Do you want to create a new note"
                + " or open a existing note or delete a note?");
        String userChoice = ui.readUserInput();
        if (userChoice.equals("create")) {
            System.out.println("Please enter the title for this note"
                    + " (if you do not need title, type a spacing instead:");
            String transientTitle = ui.readUserInput();
            String noteTitle;
            if (!transientTitle.equals(" ")) {
                noteTitle = transientTitle;
            } else {
                noteTitle = "DEFAULT(" + (defaultTitleCounter++) + ")";
            }
            System.out.println("Please enter your note:");
            String noteContent = ui.readUserInput();
            Note newNote = new Note(noteContent, noteTitle, (sceneList.getCurrentSceneIndex() + 1));
            notes.createNote(newNote,(sceneList.getCurrentSceneIndex() + 1));
        } else if (userChoice.equals("open")) {
            ui.printNoteTitle(notes);
            System.out.println("Do you want to search a note (type in 'search') or "
                    + "directly open a note (type in 'open')?");
            String userInput = ui.readUserInput();
            if (userInput.contains("search")) {
                System.out.println("Do you want to search by keyword or scene index?");
                userInput = ui.readUserInput();
                if (userInput.equals("keyword")) {
                    System.out.println("Please enter keywords");
                    String keywords = ui.readUserInput();
                    System.out.println(keywords);
                    ui.printSelectedNote(notes.searchNoteUsingTitle(keywords, notes));
                } else {
                    System.out.println("Please enter scene index:");
                    int sceneIndex = Integer.parseInt(ui.readUserInput());
                    ui.printSelectedNote(notes.searchNotesUsingSceneIndex(sceneIndex,notes));
                }
            } else {
                System.out.println("Please type in the index of the note to open it:");
                //here the index is not scene index, it is the index in the list
                int inputOrderIndex = Integer.parseInt(ui.readUserInput());
                ui.printExistingNotes(notes,inputOrderIndex);
            }
        } else {
            System.out.println("Here are the notes you have: ");
            ui.printAllNotes(notes);
            System.out.println("Please enter the index of the note you want to delete");
            int deletedNoteIndex = Integer.parseInt(ui.readUserInput()) - 1;
            notes.deleteNote(deletedNoteIndex);
        }
    }

    private boolean checkSuspectedKiller(String suspectedKiller) {
        return suspectedKiller.equals(KILLER_WENDY);
    }

    public int getNextSceneFromSceneList() {
        return sceneList.isLastScene();
    }


    public void getNextSceneFromSceneList(boolean isACorrectGuess) {
        sceneList.incrementSeceneAfterGuessing(isACorrectGuess);
    }

    public void runScenes() {
        currentScene = sceneList.getCurrentScene();
        stage = InvestigationStages.SUSPECT_STAGE;
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }
}
