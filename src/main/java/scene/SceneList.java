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

    /**
     * We set scene number based on killer being found.
     * @param killerFound is a boolean input.
     *      We update the scene number based on if the killer was found or not.
     */
    public void setSceneNumberAfterSuspecting(boolean killerFound) {
        if (killerFound) {
            this.currentSceneIndex = CORRECT_KILLER_SCENE_INDEX;
        } else {
            this.currentSceneIndex = WRONG_KILLER_SCENE_INDEX;
        }
        dataFile.setCurrentSceneIndex(this.currentSceneIndex);
    }

    public Scene getCurrentScene() {
        assert currentSceneIndex <= 7;
        return this.scenes[currentSceneIndex];
    }

    private void resetToIntroductionScene() {
        this.currentSceneIndex = STARTING_INDEX_FOR_FILE;
        updateDataFileSceneIndex(STARTING_INDEX_FOR_FILE);
    }

    /**
     * We return the current scene index.
     * @return The current scene's index.
     */
    public int getCurrentSceneIndex() {
        return this.currentSceneIndex;
    }

    /**
     * We increase the scene number by 1.
     */
    public void updateSceneNumber() {
        this.currentSceneIndex++;
        assert currentSceneIndex <= 7;
        updateDataFileSceneIndex(currentSceneIndex);
    }

    /**
     * We get the current SceneType.
     */
    public SceneTypes getCurrentSceneType() {
        Scene currentScene = this.getCurrentScene();
        return currentScene.getSceneType();
    }

    /**
     * We run the current scene.
     */
    public void runCurrentScene() {
        Scene currentScene = this.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            ui.printFileErrorMessage();
        }
    }

    /**
     * We reset the scene to the introduction scene.
     * We then run the scene.
     */
    public void resetAllScenes() {
        this.resetToIntroductionScene();
        runCurrentScene();
    }

    private void resetToGuessKillerScene() {
        this.currentSceneIndex = GUESS_KILLER_SCENE_INDEX;
        updateDataFileSceneIndex(GUESS_KILLER_SCENE_INDEX);
    }

    private void goBackOneScene() {
        this.currentSceneIndex--;
        assert this.currentSceneIndex >= 0;
        updateDataFileSceneIndex(currentSceneIndex);
    }

    private void updateDataFileSceneIndex(int sceneIndex) {
        dataFile.setCurrentSceneIndex(sceneIndex);
    }

    /**
     * We decrease the scene number based on the
     * current scene type.
     * If the current scene is the introduction scene,
     * we do not reduce the scene index.
     * If it is either the wrong or correct killer guessed scene,
     * we reset the scene number back to the guess killer scene number.
     * Else we reduce to the introduction scene.
     */
    private void decreaseSceneNumber() {
        // We do not allow users to go back to any scene with
        // scene number less than 0

        SceneTypes sceneType = getCurrentSceneType();
        switch (sceneType) {
        case INTRODUCTION_SCENE:
            break;
        case WRONG_KILLER_SCENE:
            //fallthrough
        case CORRECT_KILLER_SCENE:
            this.resetToGuessKillerScene();
            break;
        default:
            this.goBackOneScene();
        }
    }

    /**
     * We set the current scene number to the previous scene number,
     * and run the current scene.
     */
    public void previousScene() {
        decreaseSceneNumber();
        runCurrentScene();
    }
}
