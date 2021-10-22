package scene;

import narrative.Narrative;
import storage.GameDataFileDecoder;
import ui.Ui;
import suspect.SuspectList;
import java.io.FileNotFoundException;
import static suspect.SuspectList.suspectListBuilder;


public class SceneListBuilder {

    public static SceneList buildSceneList(Ui ui, GameDataFileDecoder dataFile) {
        SuspectList suspectsScene1 = new SuspectList(ui);
        SuspectList suspectsScene2 = new SuspectList(ui);
        SuspectList suspectsScene3 = new SuspectList(ui);

        try {
            suspectListBuilder("data/scene1.txt", suspectsScene1);
            suspectListBuilder("data/scene2.txt", suspectsScene2);
            suspectListBuilder("data/scene3.txt", suspectsScene3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SceneList sceneList = new SceneList(dataFile.getCurrentSceneIndex(),dataFile);
        Scene introScene = new IntroductionScene(new Narrative("Introduction.txt"), null);
        Scene firstScene = new InvestigateScene(new Narrative("FirstScene.txt"), suspectsScene1);
        Scene secondScene = new InvestigateScene(new Narrative("SecondScene.txt"), suspectsScene2);
        Scene thirdScene = new InvestigateScene(new Narrative("ThirdScene.txt"), suspectsScene3);
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

        return sceneList;
    }
}
