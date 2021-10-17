package investigation;

import parser.Parser;
import scene.Scene;
import scene.SceneList;
import scene.SceneListBuilder;
import suspect.Suspect;
import ui.Ui;

import java.io.FileNotFoundException;

public class Investigation {
    private static InvestigationStages stage;
    private static SceneList sceneList;
    private static Scene currentScene;
    private static String currentSuspect;
    private static Parser parser;
    private static Ui ui;

    private static final String FILE_NOT_FOUND = "File not found for scene";
    private static final String WRONG_INDEX_GIVEN = "Sorry please enter index within range";
    private static final String ENTER_VALID_COMMAND = "Please enter a valid user command";
    private static final String INVALID_COMMAND = "Invalid command";

    private static final String KILLER_WENDY = "Wendy";

    public Investigation(Parser parser, Ui ui) {
        this.parser = parser;
        this.ui = ui;
        stage = InvestigationStages.SUSPECT_STAGE;
        sceneList = SceneListBuilder.buildSceneList(ui);

        currentScene = sceneList.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    public void printCurrentInvestigation() {
        if (stage == InvestigationStages.SUSPECT_STAGE) {
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

    //public boolean performUserCommand(String userInput) {
    //    switch (userInput) {
    //    case "/exit":
    //        return true;
    //    case "/help":
    //        ui.printListOfCommands();
    //        return false;
    //    case "/next":
    //        boolean isEndScene = sceneList.nextScene();
    //        if (isEndScene) {
    //            System.out.println(WHO_KILLED_YOU);
    //            String guess = ui.readUserInput();
    //           if (guess.equals(KILLER_WENDY)) {
    //                System.out.println(CORRECT_ANSWER);
    //            } else {
    //                System.out.println(WRONG_ANSWER);
    //            }
    //            return true;
    //        }
    //        currentScene = sceneList.getCurrentScene();
    //        stage = InvestigationStages.SUSPECT_STAGE;
    //        try {
    //            currentScene.runScene();
    //        } catch (FileNotFoundException e) {
    //            System.out.println(FILE_NOT_FOUND);
    //        }
    //        return false;
    //    default:
    //        investigateScene(userInput);
    //    }
    //    return false;
    //}

    public void investigateScene(String userInput) {
        switch (stage) {
        case SUSPECT_STAGE:
            currentSuspect = parser.getSuspectNameFromIndex(sceneList.getCurrentSceneIndex(), userInput);
            if (currentSuspect == null) {
                System.out.println(WRONG_INDEX_GIVEN);
            } else {
                stage = InvestigationStages.CLUE_STAGE;
            }
            break;
        case CLUE_STAGE:
            int suspectNumClues = currentScene.investigateSuspect(currentSuspect).getNumClues();
            try {
                int index = Integer.parseInt(userInput);
                if (index > suspectNumClues) {
                    System.out.println(WRONG_INDEX_GIVEN);
                } else if (index == 0) {
                    stage = InvestigationStages.SUSPECT_STAGE;
                } else {
                    System.out.println(currentScene.investigateSuspect(currentSuspect).getClues().get(index - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println(ENTER_VALID_COMMAND);
                return;
            }
            break;
        default:
            System.out.println(INVALID_COMMAND);
        }
    }

    public boolean completedGame() {
        boolean isLastScene = getNextScene();
        if (isLastScene) {
            ui.printSuspectKillerMessage();
            String suspectedKiller = ui.readUserInput();
            return checkSuspectedKiller(suspectedKiller);
        } else {
            return false;
        }
    }

    private boolean checkSuspectedKiller(String suspectedKiller) {
        if (suspectedKiller.equals(KILLER_WENDY)) {
            ui.printCorrectMessage();
            return true;
        } else {
            ui.printWrongMessage();
            return false;
        }
    }

    public boolean getNextScene() {
        return sceneList.nextScene();
    }

    public void runScenes() {
        currentScene = sceneList.getCurrentScene();
        stage = InvestigationStages.SUSPECT_STAGE;
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }
}
