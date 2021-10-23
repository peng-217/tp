package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class CorrectKillerScene extends Scene {
    private SceneTypes sceneType = SceneTypes.CORRECT_KILLER_SCENE;

    public CorrectKillerScene(Narrative narrative, SuspectList suspectList, SceneTypes sceneType) {
        super(narrative, suspectList, sceneType);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
