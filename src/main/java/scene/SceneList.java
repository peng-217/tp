package scene;

import storage.GameDataFileDecoder;
import storage.GameDataFileManager;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SceneList {
    private static final Ui ui = new Ui();
    private Scene[] scenes;
    private int currentSceneIndex;
    private static final int STARTING_INDEX_FOR_FILE = 0;
    private static final int INTRODUCTION_SCENE_INDEX = 0;
    private static final int CORRECT_KILLER_SCENE_INDEX = 5;
    private static final int WRONG_KILLER_SCENE_INDEX = 6;
    GameDataFileDecoder dataFile;

    public SceneList(Scene[] scenes, GameDataFileDecoder dataFile) {
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
        dataFile.resetFile(INTRODUCTION_SCENE_INDEX);
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

    public void updateSceneNumber() {
        this.currentSceneIndex++;
        dataFile.resetFile(currentSceneIndex);
    }

    public SceneTypes getCurrentSceneType() {
        Scene currentScene = getCurrentScene();
        return currentScene.getSceneType();
    }

    public void runCurrentScene() {
        Scene currentScene = getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            ui.printFileErrorMessage();
        }
    }

    public void resetAllScenes() {
        resetCurrentSceneIndex();
        runCurrentScene();
    }
}
