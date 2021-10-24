package seedu.duke;

import command.InvalidCommand;
import exceptions.InvalidInputException;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import investigation.Investigation;
import ui.Ui;
import parser.Parser;
import command.Command;

import java.io.FileNotFoundException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    private static Parser parser;
    private static Investigation investigation;

    private static String userName;

    public static void initializeGame() throws FileNotFoundException {
        // Initialise new parser object
        parser = new Parser();

        // Initialise a new Ui object
        ui = new Ui();
        ui.printWelcomeMessage();
        investigation = new Investigation();

        // We ask the user to give a name
        // ui.askForUsername();
        // ui.printEmptyLine();
        // userName = ui.readUserInput();
        // ui.printEmptyLine();

        // print welcome message with username
        // ui.printEmptyLine();
        // ui.printWelcomeUser(userName);
        // ui.printEmptyLine();
        // SuspectList suspects = new SuspectList(ui);

    }

    public static void main(String[] args) throws FileNotFoundException {
        initializeGame();
        runUntilExitCommand();
    }

    private static void runUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            investigation.printCurrentInvestigation();
            String userInput = ui.readUserInput();
            Command commandFromUser = new InvalidCommand();
            try {
                commandFromUser = parser.getCommandFromUser(userInput);
                commandFromUser.execute(ui, investigation);
            } catch (InvalidSuspectException e1) {
                ui.printInvalidSuspectMessage();
            } catch (InvalidClueException e2) {
                ui.printInvalidClueMessage();
            } catch (InvalidInputException e3) {
                ui.printInvalidCommandMessage();
            }
            isExit = commandFromUser.exit();
        }
    }
}
