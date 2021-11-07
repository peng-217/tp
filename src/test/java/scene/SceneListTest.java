package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import org.junit.jupiter.api.Test;
import storage.GameDataFileDecoder;
import ui.Ui;

import java.io.FileNotFoundException;

public class SceneListTest {

    @Test
    public void scene2Test() throws FileNotFoundException, DukeCorruptedFileException, DukeFileNotFoundException {
        Ui ui = new Ui();
        GameDataFileDecoder datafile = new GameDataFileDecoder("GameData.txt");

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        Scene currentScene = sceneList.getCurrentScene();
        currentScene.runScene();

        currentScene = sceneList.getCurrentScene();
        System.out.println("------------------------------------------");
        currentScene.runScene();

        currentScene = sceneList.getCurrentScene();
        System.out.println("------------------------------------------");
        currentScene.runScene();
    }

    @Test
    public void testSceneNumber() throws FileNotFoundException, DukeCorruptedFileException, DukeFileNotFoundException {
        Ui ui = new Ui();
        GameDataFileDecoder datafile = new GameDataFileDecoder("GameData.txt");

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        int introductionSceneNumber = 0;
        assertEquals(introductionSceneNumber, sceneList.getCurrentSceneIndex());

        assertEquals(SceneTypes.INTRODUCTION_SCENE, sceneList.getCurrentSceneType());

        sceneList.previousScene();
        assertEquals(introductionSceneNumber, sceneList.getCurrentSceneIndex());

        sceneList.setSceneNumberAfterSuspecting(true);
        int killerGuessedCorrectlySceneNumber = 5;
        assertEquals(killerGuessedCorrectlySceneNumber, sceneList.getCurrentSceneIndex());

    }
}
