package seedu.duke;

import clue.CheckedClueTrackerBuilder;
import command.InvalidCommand;
import exceptions.InvalidInputException;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import exceptions.MissingSceneFileException;
import investigation.Investigation;
import scene.SceneList;
import scene.SceneListBuilder;
import storage.GameDataFileDecoder;
import storage.GameDataFileManager;
import suspect.SuspectList;
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
    private static GameDataFileDecoder dataFile;
    private static SceneList sceneList;
    private static SuspectList clueTracker;
    private static final String GAME_DATA_FILE_NAME = "GameData.txt";

    public static void initializeGame() throws FileNotFoundException {
        // Initialise new parser object
        parser = new Parser();

        // Initialise a new Ui object
        ui = new Ui();
        ui.printWelcomeMessage();

        dataFile = new GameDataFileDecoder(ui, new GameDataFileManager(GAME_DATA_FILE_NAME));

        try {
            sceneList = SceneListBuilder.buildSceneList(ui, dataFile);
            clueTracker = CheckedClueTrackerBuilder.buildClueTracker();
        } catch (MissingSceneFileException e) {
            ui.printMissingSceneFileMessage();
        }

        investigation = new Investigation(clueTracker);
        sceneList.runCurrentScene();
    }

    public static void main(String[] args) throws FileNotFoundException {
        initializeGame();
        runUntilExitCommand();
    }

    private static void runUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            ui.printCurrentInvestigation(investigation, sceneList);
            String userInput = ui.readUserInput();
            Command commandFromUser = new InvalidCommand();
            try {
                commandFromUser = parser.getCommandFromUser(userInput);
                commandFromUser.execute(ui, investigation, sceneList);
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
