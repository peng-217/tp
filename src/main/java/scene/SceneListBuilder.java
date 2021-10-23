package scene;

import narrative.Narrative;
import storage.GameDataFileDecoder;
import ui.Ui;
import suspect.SuspectList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static suspect.SuspectList.suspectListBuilder;


public class SceneListBuilder {

    public static SceneList buildSceneList(Ui ui, GameDataFileDecoder dataFile) throws FileNotFoundException {
        /*
        SuspectList suspectsScene1 = new SuspectList(ui);
        SuspectList suspectsScene2 = new SuspectList(ui);
        SuspectList suspectsScene3 = new SuspectList(ui);

        try {
            suspectListBuilder("data/scene1clues.txt", suspectsScene1);
            suspectListBuilder("data/scene2clues.txt", suspectsScene2);
            suspectListBuilder("data/scene3clues.txt", suspectsScene3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

         */

        SceneList sceneList = new SceneList(dataFile.getCurrentSceneIndex(), dataFile);
        addScenes("data/scenesWithNarratives.txt", sceneList, ui);
        /*
        Scene introScene = new IntroductionScene(new Narrative("resources/Introduction.txt"), null);
        Scene firstScene = new InvestigateScene(new Narrative("resources/FirstScene.txt"), suspectsScene1);
        Scene secondScene = new InvestigateScene(new Narrative("resources/SecondScene.txt"), suspectsScene2);
        Scene thirdScene = new InvestigateScene(new Narrative("resources/ThirdScene.txt"), suspectsScene3);
        Scene guessKillerScene = new GuessKillerScene(new Narrative("GuessKillerScene.txt"), suspectsScene3);
        Scene correctEndingScene = new CorrectKillerScene(new Narrative("CorrectEnding.txt"), null);
        Scene wrongEndingScene = new WrongKillerScene(new Narrative("WrongEnding.txt"), null);
        Scene truthScene = new TruthScene(new Narrative("Truth.txt"), null);

        sceneList.addScene(introScene);
        sceneList.addScene(firstScene);
        sceneList.addScene(secondScene);
        sceneList.addScene(thirdScene);
        sceneList.addScene(guessKillerScene);
        sceneList.addScene(correctEndingScene);
        sceneList.addScene(wrongEndingScene);
        sceneList.addScene(truthScene);

         */


        return sceneList;
    }

    private static void addScenes(String fileLocation, SceneList sceneList, Ui ui) throws FileNotFoundException {
        File f = new File(fileLocation);
        Scanner sc = new Scanner(f);

        int numOfScenes = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numOfScenes; i++) {
            String condition = sc.nextLine();
            String narrativeFileLocation = sc.nextLine();
            String sceneTypeInString = sc.nextLine();
            SceneTypes sceneType = Enum.valueOf(SceneTypes.class, sceneTypeInString);
            SuspectList suspectList;
            Scene scene;
            if (Objects.equals(condition, "")) {
                suspectList = null;
            } else {
                String cluesFileLocation = sc.nextLine();
                suspectList = new SuspectList(ui);
                try {
                    suspectListBuilder(cluesFileLocation, suspectList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            scene = new Scene(new Narrative(narrativeFileLocation), suspectList, sceneType);
            sceneList.addScene(scene);
        }
    }
}
