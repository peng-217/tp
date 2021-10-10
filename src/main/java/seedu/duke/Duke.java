package seedu.duke;

import suspect.Suspect;
import suspect.SuspectList;
import java.util.Scanner;

import Ui.Ui;
import Parser.Parser;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    private static Parser parser;
    private static Suspect choseSuspect;
    private static Suspect realSuspect;

    private static String userName;

    public static void initializeGame() {
        // Initialise new parser object
        parser = new Parser();

        // Initialise a new Ui object
        ui = new Ui();
        ui.printIntroductionMessage();

        // We ask the user to give a name
        ui.askForUsername();
        ui.printEmptyLine();
        userName = ui.readUserInput();
        ui.printEmptyLine();

        // print welcome message with username
        ui.printEmptyLine();
        ui.printWelcomeMessage(userName);
        ui.printEmptyLine();
//        SuspectList suspects = new SuspectList(ui);

    }


    public static void main(String[] args) {
        initializeGame();
        boolean userExit = false;
        while (!userExit) {
            String userInput = ui.readUserInput();
            String userInputParsed = parser.parseUserInput(userInput);
            ui.printEmptyLine();
            if (userInputParsed.equals("/exit")) {
                userExit = true;
            } else if (userInputParsed.equals("/help")) {
                ui.printListOfCommands();
            }
            ui.printEmptyLine();
        }
        ui.printExitMessage();
    }
}
