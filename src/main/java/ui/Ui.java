package ui;

import clue.Clue;
import investigation.Investigation;
import investigation.InvestigationStages;
import scene.Scene;
import scene.SceneList;
import scene.SceneTypes;
import suspect.SuspectList;
import note.Note;
import note.NoteList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String GAME_NAME = "Classic Adventure Text Game";
    private static final String WELCOME_MESSAGE = "Welcome to the " + GAME_NAME + "!\n";
    private static final String GOODBYE_MESSAGE = "Goodbye.";
    private static final String LIST_OF_COMMAND_AVAILABLE_MESSAGE =
            "Here are the list of commands available to you.";
    private static final String LIST_OF_NOTES_MESSAGE =
            "Here are the list of notes available to you.";
    private static final String LINE_SEPARATOR = "==============================";
    private static final String SELECTED_NOTES_MESSAGE =
            "Here are the list of notes found given keywords:";
    private static final String DELETE_NOTE_MESSAGE =
            "Ok! The note has been successfully deleted!";
    private static final String SAVE_NOTE_MESSAGE =
            "Ok! The new note has been successfully created and saved.";
    private static final String WHO_KILLED_YOU = "Who do you think killed you?";
    private static final String HELP_COMMAND = "/help";
    private static final String EXIT_COMMAND = "/exit";
    private static final String NEXT_COMMAND = "/next";
    private static final String NOTE_COMMAND = "/note";
    private static final String VIEW_COMMAND = "/view";
    private static final String RESTART_COMMAND = "/restart";
    private static final String INVALID_INPUT_GIVEN = "Invalid input! Type '/help' to see the available commands.";
    private static final String INVALID_NUMBER_SUSPECT =
            "Invalid number! To select a suspect, please input its corresponding number.";
    private static final String INVALID_NUMBER_CLUE =
            "Invalid number! To select a clue, please input its corresponding number.";
    private static final String ASK_FOR_CLUE_OR_SUSPECT_NUMBER =
            "To investigate suspects or clues, please input their corresponding number.";
    private static final String LIST_ALL_NOTES_MESSAGE = "Here is the note you want:";
    private static final String VIEWING_CHECKED_CLUES_MESSAGE = "Here are the clues that you have gathered.\n";
    private static final String FILE_NOT_FOUND = "File not Found";
    private static final String ALL_SUSPECT_MESSAGE = "Here are all the suspects";
    private static final String WHO_TO_INVESTIGATE_MESSAGE = "Who do you want to investigate?";
    private static final String NEXT_SCENE = "Enter \"/next\" to go to the next scene.";
    private static final String SCENE_FILE_MISSING_MESSAGE =
            "File containing number of scene and its order is missing";
    private static final String INVALID_INDEX = "Invalid index";
    private static final String ASK_USER_RETYPE_KILLER_NAME =
            "Invalid suspect name given. Please enter one of the suspect name below.";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printExitMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }


    public void printListOfCommands() {
        System.out.println(LIST_OF_COMMAND_AVAILABLE_MESSAGE);
        System.out.println(HELP_COMMAND);
        System.out.println(EXIT_COMMAND);
        System.out.println(NEXT_COMMAND);
        System.out.println(NOTE_COMMAND);
        System.out.println(VIEW_COMMAND);
        System.out.println(RESTART_COMMAND);
        System.out.println(ASK_FOR_CLUE_OR_SUSPECT_NUMBER);
    }

    public void printListOfClues(ArrayList<Clue> clues) {
        int i = 0;
        for (Clue clue : clues) {
            System.out.println((i + 1) + ". " + clue.getClueName().trim());
            i++;
        }
    }

    public void printListOfSearchedClues(ArrayList<Clue> clues) {
        int i = 0;
        for (Clue clue : clues) {
            if (clue.isChecked()) {
                System.out.println((i + 1) + ". " + clue.getClueName().trim());
                i++;
            }
        }
    }

    public void printCurrentInvestigation(Investigation investigation, SceneList sceneList) {
        if (investigation.getStage() == InvestigationStages.SUSPECT_STAGE) {
            this.printCurrentSuspectPage(sceneList);
        } else {
            this.printInvestigationMessage(sceneList.getCurrentSceneIndex());
            this.printCurrentCluePage(investigation.getCurrentSuspectName(), sceneList.getCurrentScene());
            this.printGoNextSceneMessage();
        }
    }

    public void printCurrentSuspectPage(SceneList sceneList) {
        if (sceneList.getCurrentSceneType() == SceneTypes.INVESTIGATE_SCENE) {
            this.printInvestigationMessage(sceneList.getCurrentSceneIndex());
            this.printWhoToInvestigate();
            this.printSuspects(sceneList.getCurrentScene().getSuspectList());
        }
    }

    public void printCurrentCluePage(String suspectName, Scene scene) {
        System.out.println(" - " + suspectName);
        System.out.println("0. Go back to list of suspects");
        this.printListOfClues(scene.investigateSuspect(suspectName).getClues());
    }

    public void printViewingCheckedCluesMessage() {
        System.out.println(VIEWING_CHECKED_CLUES_MESSAGE);
    }

    public void printSaveNoteMessage() {
        System.out.println(SAVE_NOTE_MESSAGE);
    }

    public void printExistingNotes(NoteList notes,int orderIndex) {
        System.out.println(LIST_ALL_NOTES_MESSAGE);
        System.out.println("scene " + notes.getIndexNote(orderIndex - 1).getNoteSceneIndex());
        System.out.println(notes.getIndexNote(orderIndex - 1).getNoteTitle());
        System.out.println(notes.getIndexNote(orderIndex - 1).getNoteContent());
        System.out.println(LINE_SEPARATOR);
    }

    public void printOpenNoteMessage(NoteList notes) {
        printNoteTitle(notes);
        System.out.println("Do you want to search a note (type in 'search') or "
                + "directly open a note (type in 'open')?");
    }

    public void printAllNotes(NoteList notes) {
        for (int i = 0; i < notes.getSize(); i++) {
            System.out.println((i + 1) + "." + notes.getIndexNote(i).getNoteTitle());
        }
    }

    public void printDeleteNoteMessage() {
        System.out.println(DELETE_NOTE_MESSAGE);
    }

    public void printNoteTitle(NoteList notes) {
        System.out.println(LIST_OF_NOTES_MESSAGE);
        for (int i = 0; i < notes.getSize(); i++) {
            System.out.println((i + 1) + "." + " " + notes.getIndexNote(i).getNoteTitle());
        }
    }

    public void printSelectedNote(ArrayList<Note> result) {
        System.out.println(SELECTED_NOTES_MESSAGE);
        for (int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + "." + " " + "scene " + result.get(i).getNoteSceneIndex());
            System.out.println(result.get(i).getNoteTitle());
            System.out.println(result.get(i).getNoteContent());
        }
        System.out.println();
        System.out.println(LINE_SEPARATOR);
    }


    public void printSuspects(SuspectList suspects) {
        System.out.println(suspects.toString());
    }

    public void printSuspectKillerMessage() {
        System.out.println(WHO_KILLED_YOU);
    }

    public void printFileErrorMessage() {
        System.out.println(FILE_NOT_FOUND);
    }

    public void printInvalidClueMessage() {
        System.out.println(INVALID_NUMBER_CLUE);
    }

    public void printInvalidSuspectMessage() {
        System.out.println(INVALID_NUMBER_SUSPECT);
    }

    public void printInvalidCommandMessage() {
        System.out.println(INVALID_INPUT_GIVEN);
    }

    public void printMissingSceneFileMessage() {
        System.out.println(SCENE_FILE_MISSING_MESSAGE);
    }

    public void printAllSuspectsMessage() {
        System.out.println(ALL_SUSPECT_MESSAGE);
    }

    public void printInvestigationMessage(int sceneNumber) {
        System.out.println("Scene " + sceneNumber + " Investigation");
    }

    public void printWhoToInvestigate() {
        System.out.println(WHO_TO_INVESTIGATE_MESSAGE);
    }

    public void printGoNextSceneMessage() {
        System.out.println(NEXT_SCENE);
    }

    public void printIndexCommand() {
        System.out.println(INVALID_INDEX);
    }

    public void printAskUserEnterSuspectName() {
        System.out.println(ASK_USER_RETYPE_KILLER_NAME);
    }

    public void printAllSuspectInCurrentScene(Scene scene) {
        printAllSuspectsMessage();
        printSuspects(scene.getSuspectList());
        printSuspectKillerMessage();
    }

    public void printSelectedClue(Clue currentClueInScene) {
        System.out.println(currentClueInScene.toString());
    }

    public void printNoteInstructions() {
        System.out.println("Do you want to create a new note"
                + " or open an existing note or delete a note?");
    }

    public void printNoteTitleInstructions() {
        System.out.println("Please enter the title for this note"
                + " (if you do not need title, type a spacing instead:");
    }

    public void printNoteTextInstructions() {
        System.out.println("Please enter your note:");
    }

    public void printNoteOpenSearchInstructions() {
        System.out.println("Do you want to search a note (type in 'search') or "
                + "directly open a note (type in 'open')?");
    }

    public void printNoteSearchInstructions() {
        System.out.println("Do you want to search by keyword or scene index?");
    }

    public void printNoteSearchKeyWordInstructions() {
        System.out.println("Please enter keywords");
    }

    public void printNoteSearchSceneIndexInstructions() {
        System.out.println("Please enter scene index:");
    }

    public void printNoteOpenInstructions() {
        System.out.println("Please type in the index of the note to open it:");
    }

    public void printNoteListStarter() {
        System.out.println("Here are the notes you have: ");
    }

    public void printNoteDeleteInstructions() {
        System.out.println("Please enter the index of the note you want to delete.");
    }

    public void printNoteMissingError(int size) {
        System.out.println("Invalid index! There are only " + size + " notes currently.");
    }
}
