package scene;

import storage.GameDataFileDecoder;
import storage.GameDataFileManager;

import java.util.ArrayList;

public class SceneList {
    private Scene[] scenes;
    private int currentSceneIndex;
    private static final int STARTING_INDEX_FOR_FILE = 0;
    private static final int CORRECT_KILLER_SCENE_INDEX = 5;
    private static final int WRONG_KILLER_SCENE_INDEX = 6;
    GameDataFileDecoder dataFile;

    public SceneList(Scene[] scenes, GameDataFileDecoder dataFile) {
        this.dataFile = dataFile;
        this.currentSceneIndex = dataFile.getCurrentSceneIndex();
        this.scenes = scenes;
    }

    public void incrementSceneAfterGuessing(boolean killerFound) {
        if (killerFound) {
            this.currentSceneIndex = CORRECT_KILLER_SCENE_INDEX;
        } else {
            this.currentSceneIndex = WRONG_KILLER_SCENE_INDEX;
        }
        dataFile.resetFile(0);
    }

    public Scene getCurrentScene() {
        return this.scenes[currentSceneIndex];
    }

    public void resetCurrentSceneIndex() {
        this.currentSceneIndex = STARTING_INDEX_FOR_FILE;
        dataFile.resetFile(STARTING_INDEX_FOR_FILE);
    }

    public int getCurrentSceneIndex() {
        return this.currentSceneIndex;
    }

    public void incrementSceneNumber() {
        this.currentSceneIndex++;
        dataFile.resetFile(currentSceneIndex);
    }

    public SceneTypes getSceneType() {
        Scene currentScene = getCurrentScene();
        return currentScene.getSceneType();
    }

}
