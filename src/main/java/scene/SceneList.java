package scene;

import storage.GameDataFileDecoder;
import storage.GameDataFileManager;

import java.util.ArrayList;

public class SceneList {
    private ArrayList<Scene> scenes;
    private int currentSceneIndex;
    GameDataFileDecoder dataFile;

    public SceneList(int index, GameDataFileDecoder dataFile) {
        this.dataFile = dataFile;
        this.currentSceneIndex = index;
        this.scenes = new ArrayList<>();
    }

    public void addScene(Scene scene) {
        this.scenes.add(scene);
    }
    
    public int isLastScene() {
        if (currentSceneIndex == scenes.size() - 1) {
            return 0;
        } else if (currentSceneIndex == 3) {
            return 1;
        } else if (currentSceneIndex == 4 | currentSceneIndex == 5) {
            this.currentSceneIndex = 6;
            return 2;
        }
        this.currentSceneIndex++;
        dataFile.resetFile(currentSceneIndex);
        return 2;
    }

    public void incrementSeceneAfterGuessing(boolean killerFound) {
        if (killerFound) {
            this.currentSceneIndex += 1;
        } else {
            this.currentSceneIndex += 2;
        }
        dataFile.resetFile(currentSceneIndex);
    }

    public Scene getCurrentScene() {

        return this.scenes.get(currentSceneIndex);
    }

    public int getCurrentSceneIndex() {
        return this.currentSceneIndex;
    }

    public void incrementSceneNumber() {
        this.currentSceneIndex++;
    }

    public String getSceneType() {
        int sceneNumber = getCurrentSceneIndex();
        if (sceneNumber == 0) {
            return "initial_scene";
        } else if (sceneNumber < 3) {
            return "next_scene";
        } else if (sceneNumber == 3) {
            return "guess_killer_scene";
        } else {
            return "final_scene";
        }
    }

}
