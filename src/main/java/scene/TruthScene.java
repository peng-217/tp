package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class TruthScene extends Scene {
    private SceneTypes sceneType = SceneTypes.TRUTH_SCENE;

    public TruthScene(Narrative narrative, SuspectList suspectList, SceneTypes sceneType) {
        super(narrative, suspectList, sceneType);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
