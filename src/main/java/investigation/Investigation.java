package investigation;

import clue.Clue;
import scene.SceneTypes;
import storage.Storage;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import parser.Parser;
import scene.Scene;
import scene.SceneList;
import suspect.Suspect;
import suspect.SuspectList;
import ui.Ui;
import note.Note;
import note.NoteList;
import java.util.ArrayList;

public class Investigation {
    private static InvestigationStages stage;
    private final SceneList sceneList;
    private static Scene currentScene;
    private static String currentSuspect;
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();
    private static NoteList notes = new NoteList(ui);
    private final SuspectList clueTracker;
    private static int defaultTitleCounter = 1;
    private static final String WRONG_INDEX_GIVEN = "Sorry please enter index within range";
    private static final String INVALID_COMMAND = "Invalid command";
    private static final String SUSPECT_WENDY_LOWER = "wendy";
    private static final String SUSPECT_FATHER_LOWER = "father";
    private static final String SUSPECT_LING_LOWER = "ling";
    private static final String SUSPECT_ZACK_LOWER = "zack";
    private static final String SUSPECT_KEVIN_LOWER = "kevin";

    public Investigation(SceneList sceneList, SuspectList clueTracker) {
        this.sceneList = sceneList;
        this.clueTracker = clueTracker;
        Storage.openNoteFromFile(notes);
        setSuspectStage();
        sceneList.runCurrentScene();
    }

    private void chooseSuspectToInvestigate() {
        SceneTypes sceneType = sceneList.getCurrentSceneType();
        currentScene = sceneList.getCurrentScene();

        if (sceneType == SceneTypes.INVESTIGATE_SCENE) {
            ui.printInvestigationMessage(sceneList.getCurrentSceneIndex());
            ui.printWhoToInvestigate();
            ui.printSuspects(currentScene.getSuspectList());
        }
    }

    private void chooseClueToInvestigate() {
        currentScene = sceneList.getCurrentScene();
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
            setClueStage();
            break;
        case CLUE_STAGE:
            currentScene = sceneList.getCurrentScene();
            int suspectNumClues = currentScene.investigateSuspect(currentSuspect).getNumClues();
            if (index > suspectNumClues) {
                throw new InvalidClueException(WRONG_INDEX_GIVEN);
            } else if (index == 0) {
                setSuspectStage();
            } else {
                Clue currentClueInScene = currentScene.investigateSuspect(currentSuspect).getClues().get(index - 1);
                int indexInClueTracker = clueTracker.getClueIndex(currentSuspect, currentClueInScene.getClueName());
                Clue currentClueInTracker = clueTracker.getSuspectAllClues(currentSuspect).get(indexInClueTracker);
                clueTracker.setClueChecked(currentSuspect, currentClueInTracker);
                System.out.println(currentClueInScene);
            }
            break;
        default:
            ui.printIndexCommand();
        }
    }


    public void processNote() {
        System.out.println("Do you want to create a new note"
                + " or open a existing note or delete a note?");
        String userChoice = ui.readUserInput();
        if (userChoice.equals("create")) {

            createNoteProcess();

        } else if (userChoice.equals("open")) {

            openNoteProcess();

        } else {

            deleteNoteProcess();
        }
    }

    public void createNoteProcess() {
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
        Note newNote = new Note(noteContent, noteTitle, sceneList.getCurrentSceneIndex());
        notes.createNote(newNote);
    }

    public void openNoteProcess() {
        if (notes.getSize() == 0) {
            ui.printNoNoteMessage();
        } else {
            ui.printOpenNoteMessage(notes);
            String userInput = ui.readUserInput();
            if (userInput.contains("search")) {
                System.out.println("Do you want to search by keyword (type 'keyword')" 
                       + " or scene index (type 'index')?");
                userInput = ui.readUserInput();
                if (userInput.equals("keyword")) {

                    keywordSearch();

                } else {

                    indexSearch();

                }
            } else {

                openNoteDirectly();

            }
        }
    }

    public void deleteNoteProcess() {
        if (notes.getSize() == 0) {
            ui.printNoNoteMessage();
        } else {
            System.out.println("Here are the notes you have: ");
            ui.printAllNotes(notes);
            System.out.println("Please enter the index of the note you want to delete"
                    + " (type in 'all' if you want to delete all notes)");
            String userInput = ui.readUserInput();
            if (userInput.equals("all")) {
                notes.deleteAllNote();
            } else {
                int deletedNoteIndex = Integer.parseInt(ui.readUserInput()) - 1;
                notes.deleteNote(deletedNoteIndex);
            }
        }
    }


    public void openNoteDirectly() {
        System.out.println("Please type in the index of the note to open it:");
        //here the index is not scene index, it is the index in the list
        int inputOrderIndex = Integer.parseInt(ui.readUserInput());
        ui.printExistingNotes(notes, inputOrderIndex);
    }

    public void keywordSearch() {
        System.out.println("Please enter keywords");
        String keywords = ui.readUserInput();
        System.out.println(keywords);
        ui.printSelectedNote(notes.searchNoteUsingTitle(keywords, notes));
    }

    public void indexSearch() {
        System.out.println("Please enter scene index:");
        int sceneIndex = Integer.parseInt(ui.readUserInput());
        ui.printSelectedNote(notes.searchNotesUsingSceneIndex(sceneIndex, notes));
    }

    public void checkSuspectedKiller() {
        currentScene = sceneList.getCurrentScene();
        ui.printAllSuspectInCurrentScene(currentScene);
        boolean killerFound;
        boolean nameGivenIsASuspect = false;

        while (!nameGivenIsASuspect) {
            String suspectedKiller = ui.readUserInput();
            nameGivenIsASuspect = correctSuspectNameGiven(suspectedKiller);
            if (nameGivenIsASuspect) {
                killerFound = killerFoundCorrectly(suspectedKiller);
                goToCorrectFinalScene(killerFound);
            } else {
                ui.printAskUserEnterSuspectName();
                ui.printAllSuspectInCurrentScene(currentScene);
            }
        }
    }

    private boolean killerFoundCorrectly(String suspectedKiller) {
        String suspectedKillerLowerCase = suspectedKiller.toLowerCase();
        return suspectedKillerLowerCase.equals(SUSPECT_WENDY_LOWER);
    }

    private boolean correctSuspectNameGiven(String suspectedKiller) {
        String suspectedKillerLowerCase = suspectedKiller.toLowerCase();
        switch (suspectedKillerLowerCase) {
        case SUSPECT_WENDY_LOWER:
            return true;
        case SUSPECT_FATHER_LOWER:
            return true;
        case SUSPECT_KEVIN_LOWER:
            return true;
        case SUSPECT_LING_LOWER:
            return true;
        case SUSPECT_ZACK_LOWER:
            return true;
        default:
            return false;
        }
    }

    private void goToCorrectFinalScene(boolean killerFound) {
        sceneList.setSceneNumberAfterSuspecting(killerFound);
    }

    public ArrayList<Clue> getSuspectCheckedClues(String name) {
        return clueTracker.getSuspectCheckedClues(name);
    }

    public void restartGame() {
        setSuspectStage();
        sceneList.resetAllScenes();
    }

    public void setSuspectStage() {
        stage = InvestigationStages.SUSPECT_STAGE;
    }

    private void setClueStage() {
        stage = InvestigationStages.CLUE_STAGE;
    }
}
