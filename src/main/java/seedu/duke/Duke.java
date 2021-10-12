package seedu.duke;

import scene.SceneList;
import scene.SceneListBuilder;
import suspect.Suspect;

import ui.Ui;
import parser.Parser;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    private static Parser parser;
    private static SceneList sceneList;
    private static Suspect choseSuspect;
    private static Suspect realSuspect;

    private static String userName;

    public static void initializeGame() {
        // Initialise new parser object
        parser = new Parser();

        // Initialise a new Ui object
        ui = new Ui();
        sceneList = SceneListBuilder.buildSceneList(ui);
        ui.printWelcomeMessage();

        // We ask the user to give a name
        ui.askForUsername();
        ui.printEmptyLine();
        userName = ui.readUserInput();
        ui.printEmptyLine();

        // print welcome message with username
        ui.printEmptyLine();
        ui.printWelcomeMessage(userName);
        ui.printEmptyLine();
        // SuspectList suspects = new SuspectList(ui);

    }

    public static void main(String[] args) {
        initializeGame();
        boolean userExit = false;
        while (!userExit) {
            String userInput = ui.readUserInput();
            String userInputParsed = parser.parseUserInput(userInput);
            ui.printEmptyLine();
            switch (userInputParsed) {
            case "/exit":
                userExit = true;
                break;
            case "/help":
                ui.printListOfCommands();
                break;
            case "/clues":
                // Prints list of clues
                break;
            case "/notes":
                ui.printNotesMessage();
                break;
            case "clue":
                int clueNumber = parser.parseClueNumber(userInput);
                ui.getClue(clueNumber);
                break;
            default:
                break;
            }
            ui.printEmptyLine();
        }
        ui.printExitMessage();
    }
}
