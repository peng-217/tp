package scene;

import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.FileNotFoundException;

public class SceneListTest {

    @Test
    public void scenesTest() throws FileNotFoundException {
        Ui ui = new Ui();
        SceneList sceneList = SceneListBuilder.buildSceneList(ui);
        Scene currentScene = sceneList.getCurrentScene();
        currentScene.runScene();

        sceneList.nextScene();
        currentScene = sceneList.getCurrentScene();

        System.out.println(currentScene.getSuspectList().getSuspectAvailableClues("Father"));
        System.out.println(currentScene.getSuspectList().toString());
        System.out.println(currentScene.getSuspectList().getAllClues());

        currentScene.getSuspectList().setClueChecked("Father",
                sceneList.getCurrentScene().getSuspectList().getAllAvailableClues().get(0));
        System.out.println(currentScene.getSuspectList().getAllAvailableClues());

        System.out.println("------------------------------------------");
        currentScene.runScene();
    }

    @Test
    public void scene2Test() throws FileNotFoundException {
        Ui ui = new Ui();
        SceneList sceneList = SceneListBuilder.buildSceneList(ui);
        Scene currentScene = sceneList.getCurrentScene();
        currentScene.runScene();

        sceneList.nextScene();
        currentScene = sceneList.getCurrentScene();
        System.out.println("------------------------------------------");
        currentScene.runScene();

        sceneList.nextScene();
        currentScene = sceneList.getCurrentScene();
        System.out.println("------------------------------------------");
        currentScene.runScene();
    }
}
