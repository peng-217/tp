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
            dataFile.setCurrentSceneIndex(INTRODUCTION_SCENE_INDEX);
        } else {
            this.currentSceneIndex = WRONG_KILLER_SCENE_INDEX;
        }
    }

    public Scene getCurrentScene() {
        assert currentSceneIndex <= 7;
        return this.scenes[currentSceneIndex];
    }

    public void resetCurrentSceneIndex() {
        this.currentSceneIndex = STARTING_INDEX_FOR_FILE;
        dataFile.setCurrentSceneIndex(STARTING_INDEX_FOR_FILE);
    }

    public int getCurrentSceneIndex() {
        return this.currentSceneIndex;
    }

    public void updateSceneNumber() {
        this.currentSceneIndex++;
        assert currentSceneIndex <= 7;
        dataFile.setCurrentSceneIndex(currentSceneIndex);
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

    /** Resets the scene index and rerun the scene from the starting scene. */
    public void resetAllScenes() {
        this.resetCurrentSceneIndex();
        dataFile.setCurrentSceneIndex(0);
        runCurrentScene();
    }

    /**
     * Decreases the scene index based on the scene types.
     * Do not allow users to go back to any scene with scene number less than 0.
     */
    private void decreaseSceneNumber() {
        SceneTypes sceneType = getCurrentSceneType();
        switch (sceneType) {
        case INTRODUCTION_SCENE:
            break;
        case WRONG_KILLER_SCENE:
        case CORRECT_KILLER_SCENE:
            this.currentSceneIndex = GUESS_KILLER_SCENE_INDEX;
            dataFile.setCurrentSceneIndex(GUESS_KILLER_SCENE_INDEX);
            break;
        default:
            this.currentSceneIndex--;
            assert this.currentSceneIndex >= 0;
            dataFile.setCurrentSceneIndex(currentSceneIndex);
        }
    }

    /** Go back to the previous scene. */
    public void previousScene() {
        decreaseSceneNumber();
        runCurrentScene();
    }
}
