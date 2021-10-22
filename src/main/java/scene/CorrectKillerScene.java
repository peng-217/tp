package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class CorrectKillerScene extends Scene {
    private SceneTypes sceneType = SceneTypes.CORRECT_KILLER_SCENE;

    public CorrectKillerScene(Narrative narrative, SuspectList suspectList) {
        super(narrative, suspectList);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
