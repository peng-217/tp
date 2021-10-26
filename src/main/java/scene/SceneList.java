package scene;

import storage.GameDataFileDecoder;
import ui.Ui;
import java.io.FileNotFoundException;

public class SceneList {
    private static final Ui ui = new Ui();
    private Scene[] scenes;
    private int currentSceneIndex;
    private static final int STARTING_INDEX_FOR_FILE = 0;
    private static final int INTRODUCTION_SCENE_INDEX = 0;
    private static final int GUESS_KILLER_SCENE_INDEX = 4;
    private static final int CORRECT_KILLER_SCENE_INDEX = 5;
    private static final int WRONG_KILLER_SCENE_INDEX = 6;
    GameDataFileDecoder dataFile;

    public SceneList(GameDataFileDecoder dataFile, Scene... scenes) {
        this.dataFile = dataFile;
        this.currentSceneIndex = dataFile.getCurrentSceneIndex();
        this.scenes = scenes;
    }

    public void setSceneNumberAfterSuspecting(boolean killerFound) {
        if (killerFound) {
            this.currentSceneIndex = CORRECT_KILLER_SCENE_INDEX;
        } else {
            this.currentSceneIndex = WRONG_KILLER_SCENE_INDEX;
        }
        dataFile.setFile(INTRODUCTION_SCENE_INDEX);
    }

    public Scene getCurrentScene() {
        assert currentSceneIndex <= 6;
        return this.scenes[currentSceneIndex];
    }

    public void resetCurrentSceneIndex() {
        this.currentSceneIndex = STARTING_INDEX_FOR_FILE;
        dataFile.setFile(STARTING_INDEX_FOR_FILE);
    }

    public int getCurrentSceneIndex() {
        return this.currentSceneIndex;
    }

    public void updateSceneNumber() {
        this.currentSceneIndex++;
        assert currentSceneIndex <= 6;
        dataFile.setFile(currentSceneIndex);
    }

    public SceneTypes getCurrentSceneType() {
        Scene currentScene = this.getCurrentScene();
        return currentScene.getSceneType();
    }

    public void runCurrentScene() {
        Scene currentScene = this.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            ui.printFileErrorMessage();
        }
    }

    public void resetAllScenes() {
        this.resetCurrentSceneIndex();
        runCurrentScene();
    }

    private void decreaseSceneNumber() {
        // We do not allow users to go back to any scene with
        // scene number less than 0

        SceneTypes sceneType = getCurrentSceneType();
        switch (sceneType) {
        case INTRODUCTION_SCENE:
            break;
        case WRONG_KILLER_SCENE:
        case CORRECT_KILLER_SCENE:
            this.currentSceneIndex = GUESS_KILLER_SCENE_INDEX;
            dataFile.setFile(GUESS_KILLER_SCENE_INDEX);
            break;
        default:
            this.currentSceneIndex--;
            assert this.currentSceneIndex >= 0;
            dataFile.setFile(currentSceneIndex);
        }

    }

    public void previousScene() {
        decreaseSceneNumber();
        runCurrentScene();
    }
}
