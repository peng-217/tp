package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import org.junit.jupiter.api.Test;
import storage.GameDataFileDecoder;
import ui.Ui;

import java.io.FileNotFoundException;

public class SceneListTest {

    private static final int GUESS_KILLER_SCENE_INDEX = 4;
    private static final int CORRECT_KILLER_SCENE_INDEX = 5;
    private static final int WRONG_KILLER_SCENE_INDEX = 6;

    /*
    @Test
    public void scenesTest() throws FileNotFoundException {
        Ui ui = new Ui();
        GameDataFileDecoder datafile = new GameDataFileDecoder(ui,new GameDataFileManager("GameData.txt"));

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        Scene currentScene = sceneList.getCurrentScene();
        currentScene.runScene();

        sceneList.updateSceneNumber();
        currentScene = sceneList.getCurrentScene();

        System.out.println(currentScene.getSuspectList().getSuspectAvailableClues("Father"));
        System.out.println(currentScene.getSuspectList().toString());
        System.out.println(currentScene.getSuspectList().getAllClues());

        currentScene.getSuspectList().setClueChecked("Father",
                sceneList.getCurrentScene().getSuspectList().getAllAvailableClues().get(0));
        System.out.println(currentScene.getSuspectList().getAllAvailableClues());

        System.out.println("------------------------------------------");
        currentScene.runScene();
    }*/

    @Test
    public void sceneListRunScenesTest()
            throws FileNotFoundException, DukeCorruptedFileException, DukeFileNotFoundException {

        Ui ui = new Ui();
        GameDataFileDecoder datafile = new GameDataFileDecoder("GameData.txt");

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        Scene currentScene = sceneList.getCurrentScene();
        currentScene.runScene();
    }

    @Test
    public void sceneListIndexTest()
            throws FileNotFoundException, DukeCorruptedFileException, DukeFileNotFoundException {

        GameDataFileDecoder datafile = new GameDataFileDecoder("GameData.txt");

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        assertEquals("INTRODUCTION_SCENE", sceneList.getCurrentSceneType().toString());
        assertEquals(0, sceneList.getCurrentSceneIndex());

        sceneList.updateSceneNumber();
        assertEquals("INVESTIGATE_SCENE", sceneList.getCurrentSceneType().toString());
        assertEquals(1, sceneList.getCurrentSceneIndex());
    }

    @Test
    public void sceneAfterGameTest()
            throws FileNotFoundException, DukeCorruptedFileException, DukeFileNotFoundException {

        GameDataFileDecoder datafile = new GameDataFileDecoder("GameData.txt");

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        sceneList.setSceneNumberAfterSuspecting(true);
        assertEquals(CORRECT_KILLER_SCENE_INDEX, sceneList.getCurrentSceneIndex());

        sceneList.previousScene();
        assertEquals(GUESS_KILLER_SCENE_INDEX, sceneList.getCurrentSceneIndex());

        sceneList.setSceneNumberAfterSuspecting(false);
        assertEquals(WRONG_KILLER_SCENE_INDEX, sceneList.getCurrentSceneIndex());

        sceneList.previousScene();
        assertEquals(GUESS_KILLER_SCENE_INDEX, sceneList.getCurrentSceneIndex());
    }

    @Test
    public void previousSceneTest()
            throws FileNotFoundException, DukeCorruptedFileException, DukeFileNotFoundException {

        GameDataFileDecoder datafile = new GameDataFileDecoder("GameData.txt");

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        assertEquals(0, sceneList.getCurrentSceneIndex());

        sceneList.previousScene();
        assertEquals(0, sceneList.getCurrentSceneIndex());

        datafile.setCurrentSceneIndex(1);
        sceneList = SceneListBuilder.buildSceneList(datafile);

        sceneList.previousScene();
        assertEquals(0, sceneList.getCurrentSceneIndex());
    }
}
