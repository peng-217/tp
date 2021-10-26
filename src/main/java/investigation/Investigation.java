package investigation;

import clue.Clue;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import parser.Parser;
import scene.Scene;
import scene.SceneList;
import suspect.SuspectList;
import ui.Ui;
import java.util.ArrayList;

public class Investigation {
    private static InvestigationStages stage;
    private static Scene currentScene;
    private static String currentSuspect;
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();
    private final SuspectList clueTracker;
    private static final String WRONG_INDEX_GIVEN = "Sorry please enter index within range";
    private static final String INVALID_COMMAND = "Invalid command";
    private static final String SUSPECT_WENDY_LOWER = "wendy";
    private static final String SUSPECT_FATHER_LOWER = "father";
    private static final String SUSPECT_LING_LOWER = "ling";
    private static final String SUSPECT_ZACK_LOWER = "zack";
    private static final String SUSPECT_KEVIN_LOWER = "kevin";

    public Investigation(SuspectList clueTracker) {
        this.clueTracker = clueTracker;
        setSuspectStage();
    }

    public InvestigationStages getStage() {
        return stage;
    }

    public String getCurrentSuspectName() {
        return currentSuspect;
    }

    public void investigateScene(Integer index, Scene scene)
            throws InvalidSuspectException, InvalidClueException {
        switch (stage) {
        case SUSPECT_STAGE:
            currentSuspect = parser.getSuspectNameFromIndex(scene, index);
            setClueStage();
            break;
        case CLUE_STAGE:
            currentScene = scene;
            int suspectNumClues = currentScene.investigateSuspect(currentSuspect).getNumClues();
            if (index > suspectNumClues) {
                throw new InvalidClueException(WRONG_INDEX_GIVEN);
            } else if (index == 0) {
                setSuspectStage();
            } else {
                Clue currentClueInScene = currentScene.investigateSuspect(currentSuspect).getClues().get(index - 1);
                clueTracker.setClueChecked(currentSuspect, currentClueInScene);
                ui.printSelectedClue(currentClueInScene);
            }
            break;
        default:
            ui.printIndexCommand();
        }
    }

    public boolean checkSuspectedKiller(SceneList sceneList) {
        ui.printAllSuspectInCurrentScene(sceneList.getCurrentScene());


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
        boolean nameGivenIsASuspect;
        String suspectedKiller = ui.readUserInput();
        nameGivenIsASuspect = parser.validSuspectNameGiven(suspectedKiller);
        if (nameGivenIsASuspect) {
            killerFound = killerFoundCorrectly(suspectedKiller);
            return killerFound;
        } else {
            ui.printAskUserEnterSuspectName();
            killerFound = checkSuspectedKiller(sceneList);
        }
        return killerFound;
    }

    private boolean killerFoundCorrectly(String suspectedKiller) {
        String suspectedKillerLowerCase = suspectedKiller.toLowerCase();
        return suspectedKillerLowerCase.equals(SUSPECT_WENDY_LOWER);
    }

    public ArrayList<Clue> getSuspectCheckedClues(String name) {
        return clueTracker.getSuspectCheckedClues(name);
    }

    public void restartGame() {
        setSuspectStage();
    }

    public void setSuspectStage() {
        stage = InvestigationStages.SUSPECT_STAGE;
    }

    private void setClueStage() {
        stage = InvestigationStages.CLUE_STAGE;
    }
}
