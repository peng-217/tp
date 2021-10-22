package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class TruthScene extends Scene {
    private SceneTypes sceneType = SceneTypes.TRUTH_SCENE;

    public TruthScene(Narrative narrative, SuspectList suspectList) {
        super(narrative, suspectList);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
