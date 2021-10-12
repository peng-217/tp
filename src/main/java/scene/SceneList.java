package scene;

import java.util.ArrayList;

public class SceneList {
    private ArrayList<Scene> scenes;
    private int currentSceneIndex;

    public SceneList() {
        this.currentSceneIndex = 0;
        this.scenes = new ArrayList<>();
    }

    public void addScene(Scene scene) {
        this.scenes.add(scene);
    }

    // Returns false if unable to move to next scene (last scene)
    public boolean nextScene() {
        if (currentSceneIndex == scenes.size() - 1) {
            return true;
        }
        this.currentSceneIndex++;
        return false;
    }

    public Scene getCurrentScene() {
        return this.scenes.get(currentSceneIndex);
    }

    public int getCurrentSceneIndex() {
        return currentSceneIndex;
    }
}
