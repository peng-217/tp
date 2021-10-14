package seedu.duke;

import investigation.Investigation;
import ui.Ui;
import parser.Parser;
import command.Command;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    private static Parser parser;
    private static Investigation investigation;

    private static String userName;

    public static void initializeGame() {
        // Initialise new parser object
        parser = new Parser();

        // Initialise a new Ui object
        ui = new Ui();
        ui.printWelcomeMessage();
        investigation = new Investigation(parser, ui);

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

    public static void main(String[] args) {
        initializeGame();
        runUntilExitCommand();
        ui.printExitMessage();
    }

    private static void runUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            investigation.printCurrentInvestigation();
            String userInput = ui.readUserInput();
            Command commandFromUser = parser.getCommandFromUser(userInput);
            commandFromUser.execute(ui, investigation);
            isExit = commandFromUser.exit();
        }
    }
}
