package search;

import scene.Scene;
import suspect.SuspectList;

public class Search {
    private Scene scene;
    private final SuspectList suspects;

    public Search(SuspectList suspects) {
        this.suspects = suspects;
        this.scene = null;
    }

    public SuspectList getSuspects() {
        return this.suspects;
    }

    public Scene getScene() {
        return this.scene;
    }

    public boolean belongsToScene() {
        return !(this.scene == null);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return this.suspects.getSuspects().toString();
    }
}
