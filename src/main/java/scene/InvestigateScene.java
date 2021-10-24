package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class InvestigateScene extends Scene {
    private SceneTypes sceneType = SceneTypes.INVESTIGATE_SCENE;

    public InvestigateScene(Narrative narrative, SuspectList suspectList, SceneTypes sceneType) {
        super(narrative, suspectList, sceneType);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}