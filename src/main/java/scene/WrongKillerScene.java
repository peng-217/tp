package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class WrongKillerScene extends Scene {
    private SceneTypes sceneType = SceneTypes.WRONG_KILLER_SCENE;

    public WrongKillerScene(Narrative narrative, SuspectList suspectList, SceneTypes sceneType) {
        super(narrative, suspectList, sceneType);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
