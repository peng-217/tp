package seedu.duke;

import scene.Scene;
import scene.SceneList;
import scene.SceneListBuilder;

import suspect.Suspect;
import ui.Ui;
import parser.Parser;

import java.io.FileNotFoundException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    private static Parser parser;
    private static SceneList sceneList;
    private static Scene currentScene;

    private static String userName;
    private static Levels level;

    private static String currentSuspect;

    public static void initializeGame() {
        // Initialise new parser object
        parser = new Parser();

        // Initialise a new Ui object
        ui = new Ui();
        ui.printWelcomeMessage();

        level = Levels.SUSPECT_STAGE;
        sceneList = SceneListBuilder.buildSceneList(ui);

        currentScene = sceneList.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            System.out.println("File not found for scene");
        }

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
            printCurrentInvestigation();

            String userInput = ui.readUserInput();
            String userInputParsed = parser.parseUserInput(userInput);

            isExit = performUserCommand(userInput);
        }
    }

    private static void printCurrentInvestigation() {
        if (level == Levels.SUSPECT_STAGE) {
            System.out.println("Scene " + (sceneList.getCurrentSceneIndex() + 1) + " Investigation");
            System.out.println("Who do you want to investigate?");
            ui.printSuspects(currentScene.getSuspectList());
        } else {
            System.out.print("Scene " + (sceneList.getCurrentSceneIndex() + 1) + " Investigation");
            System.out.println(" - " + currentSuspect);
            System.out.println("0. Go back to list of suspects");
            Suspect suspect = currentScene.investigateSuspect(currentSuspect);
            ui.printListOfClues(suspect.getClues());
        }
    }

    private static boolean performUserCommand(String userInput) {
        switch (userInput) {
        case "/exit":
            return true;
        case "/help":
            ui.printListOfCommands();
            return false;
        case "/next":
            boolean isEndScene = sceneList.nextScene();
            if (isEndScene) {
                System.out.println("Who do you think killed you?");
                String guess = ui.readUserInput();
                if (guess.equals("Wendy")) {
                    System.out.println("Correct answer");
                } else {
                    System.out.println("Wrong answer");
                }
                return true;
            }
            currentScene = sceneList.getCurrentScene();
            level = Levels.SUSPECT_STAGE;
            try {
                currentScene.runScene();
            } catch (FileNotFoundException e) {
                System.out.println("File not found for scene");
            }
            return false;
        default:
            investigateScene(userInput);
        }
        return false;
    }

    private static void investigateScene(String userInput) {
        switch (level) {
        case SUSPECT_STAGE:
            try {
                currentSuspect = getSuspectNameFromIndex(Integer.parseInt(userInput));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid user command");
                return;
            }
            if (currentSuspect != null) {
                level = Levels.CLUE_STAGE;
            } else {
                System.out.println("Sorry please enter index within range");
            }
            break;
        case CLUE_STAGE:
            int suspectNumClues = currentScene.investigateSuspect(currentSuspect).getNumClues();
            try {
                int index = Integer.parseInt(userInput);
                if (index > suspectNumClues) {
                    System.out.println("Sorry please enter index within range");
                } else if (index == 0) {
                    level = Levels.SUSPECT_STAGE;
                } else {
                    System.out.println(currentScene.investigateSuspect(currentSuspect).getClues().get(index - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid user command");
                return;
            }
            break;
        default:
            System.out.println("Invalid command");
        }
    }

    private static String getSuspectNameFromIndex(int index) {
        int currentScene = sceneList.getCurrentSceneIndex();
        if (index == 1) {
            return "Father";
        } else if (index == 2 & currentScene >= 1) {
            return "Kevin";
        } else if (index == 3 & currentScene >= 1) {
            return "Wendy";
        } else if (index == 4 & currentScene >= 2) {
            return "Ling";
        } else if (index == 5 & currentScene >= 2) {
            return "Zack";
        } else {
            return null;
        }
    }

    public enum Levels {
        SUSPECT_STAGE, CLUE_STAGE
    }
}
