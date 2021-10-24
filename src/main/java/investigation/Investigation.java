package investigation;


import clue.Clue;
import clue.CheckedClueTrackerBuilder;
import scene.SceneTypes;
import storage.GameDataFileDecoder;
import storage.GameDataFileManager;
import storage.Storage;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import parser.Parser;
import scene.Scene;
import scene.SceneList;
import scene.SceneListBuilder;
import suspect.Suspect;
import suspect.SuspectList;
import ui.Ui;
import note.Note;
import note.NoteList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Investigation {
    private static InvestigationStages stage;
    private static SceneList sceneList;
    private static Scene currentScene;
    private static String currentSuspect;
    private static Parser parser;
    private static Ui ui;
    private static Storage storage;
    private static GameDataFileDecoder dataFile;
    private static NoteList notes;
    private static SuspectList clueTracker;
    private static int defaultTitleCounter = 1;
    private static final String FILE_NOT_FOUND = "File not found for scene";
    private static final String WRONG_INDEX_GIVEN = "Sorry please enter index within range";
    private static final String INVALID_COMMAND = "Invalid command";
    private static final String KILLER_WENDY = "Wendy";
    private static final String GAME_DATA_FILE_NAME = "GameData.txt";

    public Investigation(Parser parser, Ui ui) throws FileNotFoundException {
        this.parser = parser;
        this.ui = ui;
        dataFile = new GameDataFileDecoder(ui, new GameDataFileManager(GAME_DATA_FILE_NAME));
        storage = new Storage();
        notes = new NoteList(ui);
        stage = InvestigationStages.SUSPECT_STAGE;

        sceneList = SceneListBuilder.buildSceneList(ui, dataFile);
        clueTracker = CheckedClueTrackerBuilder.buildClueTracker(ui);

        Storage.openNoteFromFile(notes);

        currentScene = sceneList.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    private void chooseSuspectToInvestigate() {
        SceneTypes sceneType = getSceneType();
        if (sceneType == SceneTypes.INVESTIGATE_SCENE) {
            ui.printInvestigationMessage(sceneList.getCurrentSceneIndex());
            ui.printWhoToInvestigate();
            ui.printSuspects(currentScene.getSuspectList());
        }
    }

    private void chooseClueToInvestigate() {
        ui.printInvestigationMessage(sceneList.getCurrentSceneIndex());
        System.out.println(" - " + currentSuspect);
        System.out.println("0. Go back to list of suspects");
        Suspect suspect = currentScene.investigateSuspect(currentSuspect);
        ui.printListOfClues(suspect.getClues());
        ui.printGoNextSceneMessage();
    }

    public void printCurrentInvestigation() {
        if (stage == InvestigationStages.SUSPECT_STAGE) {
            chooseSuspectToInvestigate();
        } else {
            chooseClueToInvestigate();
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
                Clue currentClueInScene = currentScene.investigateSuspect(currentSuspect).getClues().get(index - 1);
                int indexInClueTracker = clueTracker.getClueIndex(currentSuspect, currentClueInScene.getClueName());
                Clue currentClueInTracker = clueTracker.getSuspectAllClues(currentSuspect).get(indexInClueTracker);
                clueTracker.setClueChecked(currentSuspect, currentClueInTracker);
                System.out.println(currentClueInScene);
            }
            break;
        default:
            System.out.println(INVALID_COMMAND);
        }
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
            Note newNote = new Note(noteContent, noteTitle,sceneList.getCurrentSceneIndex());
            notes.createNote(newNote, (sceneList.getCurrentSceneIndex()));
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
                    ui.printSelectedNote(notes.searchNotesUsingSceneIndex(sceneIndex, notes));
                }
            } else {
                System.out.println("Please type in the index of the note to open it:");
                //here the index is not scene index, it is the index in the list
                int inputOrderIndex = Integer.parseInt(ui.readUserInput());
                ui.printExistingNotes(notes, inputOrderIndex);
            }
        } else {
            System.out.println("Here are the notes you have: ");
            ui.printAllNotes(notes);
            System.out.println("Please enter the index of the note you want to delete");
            int deletedNoteIndex = Integer.parseInt(ui.readUserInput()) - 1;
            notes.deleteNote(deletedNoteIndex);
        }
    }

    public void checkSuspectedKiller() {
        ui.printAllSuspectsMessage();
        ui.printSuspects(currentScene.getSuspectList());
        ui.printSuspectKillerMessage();
        String suspectedKiller = ui.readUserInput();
        boolean killerFound = suspectedKiller.equals(KILLER_WENDY);
        goToCorrectFinalScene(killerFound);
    }

    private void goToCorrectFinalScene(boolean killerFound) {
        sceneList.incrementSceneAfterGuessing(killerFound);
    }

    public ArrayList<Clue> getSuspectCheckedClues(String name) {
        return clueTracker.getSuspectCheckedClues(name);
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

    public SceneTypes getSceneType() {
        return sceneList.getSceneType();
    }

    public void updateScene() {
        sceneList.incrementSceneNumber();
    }

    public void restartGame() {
        sceneList.resetCurrentSceneIndex();
        stage = InvestigationStages.SUSPECT_STAGE;
        runScenes();
    }
}
