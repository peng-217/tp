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

    public void nextScene() {
        if(currentSceneIndex == scenes.size() - 1) {
            return;
        }
        this.currentSceneIndex++;
    }

    public Scene getCurrentScene() {
        return this.scenes.get(currentSceneIndex);
    }

}
