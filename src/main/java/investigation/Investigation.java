package investigation;

import clue.Clue;
import storage.Storage;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import parser.Parser;
import scene.Scene;
import scene.SceneList;
import suspect.SuspectList;
import ui.Ui;
import note.Note;
import note.NoteList;
import java.util.ArrayList;

public class Investigation {
    private static InvestigationStages stage;
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

    public Investigation(SuspectList clueTracker) {
        this.clueTracker = clueTracker;
        Storage.openNoteFromFile(notes);
        setSuspectStage();
    }

    public InvestigationStages getStage() {
        return stage;
    }

    public String getCurrentSuspectName() {
        return currentSuspect;
    }

    public void setCurrentSuspect(int index) {
        currentSuspect = parser.getSuspectNameFromIndex(currentScene, index);
    }

    public void investigateScene(Integer index, SceneList sceneList)
            throws InvalidSuspectException, InvalidClueException {
        switch (stage) {
        case SUSPECT_STAGE:
            setCurrentSuspect(index);
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
                clueTracker.setClueChecked(currentSuspect, currentClueInScene);
                ui.printSelectedClue(currentClueInScene);
            }
            break;
        default:
            ui.printIndexCommand();
        }
    }


    public void processNote(SceneList sceneList) {
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
            Note newNote = new Note(noteContent, noteTitle, sceneList.getCurrentSceneIndex());
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

    public boolean checkSuspectedKiller(SceneList sceneList) {
        ui.printAllSuspectInCurrentScene(sceneList.getCurrentScene());
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
