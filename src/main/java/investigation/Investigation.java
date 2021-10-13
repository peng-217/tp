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

    public Investigation(Parser parser, Ui ui) {
        this.parser = parser;
        this.ui = ui;
        stage = InvestigationStages.SUSPECT_STAGE;
        sceneList = SceneListBuilder.buildSceneList(ui);

        currentScene = sceneList.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            System.out.println("File not found for scene");
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

    public boolean performUserCommand(String userInput) {
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
            stage = InvestigationStages.SUSPECT_STAGE;
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

    private void investigateScene(String userInput) {
        switch (stage) {
        case SUSPECT_STAGE:
            currentSuspect = parser.getSuspectNameFromIndex(sceneList.getCurrentSceneIndex(), userInput);
            if (currentSuspect == null) {
                System.out.println("Sorry please enter index within range");
            } else {
                stage = InvestigationStages.CLUE_STAGE;
            }
            break;
        case CLUE_STAGE:
            int suspectNumClues = currentScene.investigateSuspect(currentSuspect).getNumClues();
            try {
                int index = Integer.parseInt(userInput);
                if (index > suspectNumClues) {
                    System.out.println("Sorry please enter index within range");
                } else if (index == 0) {
                    stage = InvestigationStages.SUSPECT_STAGE;
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

}
