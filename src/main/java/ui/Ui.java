package ui;

import clue.Clue;
import suspect.Suspect;
import suspect.SuspectList;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GAME_NAME = "Classic Adventure Text Game";
    private static final String WELCOME_MESSAGE = "Welcome to the " + GAME_NAME + "!\n";
    private static final String INTRODUCE_MYSELF = "HELLO! I am \n" + LOGO;
    private static final String ASK_FOR_USERNAME = "Before we get started, how do I address you?";
    private static final String GOODBYE_MESSAGE = "Goodbye.";
    private static final String LIST_OF_COMMAND_AVAILABLE_MESSAGE =
            "Here are the list of commands available to you.";
    private static final String LIST_OF_CLUES_MESSAGE =
            "Here are the list of clues available to you.";
    private static final String LIST_OF_NOTES_MESSAGE =
            "Here are the list of notes available to you.";
    private static final String lineSeparator = "==============================";
    private Scanner scanner;

    public void printEmptyLine() {
        System.out.println(lineSeparator);
    }

    public void printIntroductionMessage() {
        System.out.println(INTRODUCE_MYSELF);
    }

    public void askForUsername() {
        System.out.println(ASK_FOR_USERNAME);
    }

    public String readUserInput() {
        this.scanner = new Scanner(System.in);
        String userInput = this.scanner.nextLine();
        return userInput;
    }

    public void printExitMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void printWelcomeMessage(String userName) {
        String welcomeMessage = "Welcome " + userName
                + " to the " + GAME_NAME + "!";
        System.out.println(welcomeMessage);
    }

    public void printListOfCommands() {
        System.out.println(LIST_OF_COMMAND_AVAILABLE_MESSAGE);
        System.out.println("/help");
        System.out.println("/clues");
        System.out.println("/suspect");
        System.out.println("/note");
    }

    public void printListOfClues(ArrayList<Clue> clues) {
        System.out.println(LIST_OF_CLUES_MESSAGE);
        for (Clue clue : clues) {
            System.out.println(clue.toString());
        }
    }

    public void printNotesMessage() {
        System.out.println(LIST_OF_NOTES_MESSAGE);
        System.out.println("1. This is a place holder");
    }

    public void getClue(int clueNumber) {
        String str = "this is a clue placeholder";
        System.out.println("Clue number " + clueNumber
                + " " + str);
    }

    public void printSuspects(SuspectList suspects) {
        System.out.println("Please choose a suspect that you think is the real murderer from the list:");
        int i = 0;
        for (Map.Entry<String, Suspect> suspectEntry : suspects.getSuspects().entrySet()) {
            System.out.println((i + 1) + ". " + suspectEntry.getKey());
            i++;
        }
    }
}
