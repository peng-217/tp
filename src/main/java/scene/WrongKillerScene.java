package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class WrongKillerScene extends Scene {
    private SceneTypes sceneType = SceneTypes.WRONG_KILLER_SCENE;

    public WrongKillerScene(Narrative narrative, SuspectList suspectList) {
        super(narrative, suspectList);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
