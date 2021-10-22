package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class InvestigateScene extends Scene {
    private SceneTypes sceneType = SceneTypes.INVESTIGATE_SCENE;

    public InvestigateScene(Narrative narrative, SuspectList suspectList) {
        super(narrative, suspectList);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}