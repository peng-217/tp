package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class GuessKillerScene extends Scene {
    private SceneTypes sceneType = SceneTypes.GUESS_KILLER_SCENE;

    public GuessKillerScene(Narrative narrative, SuspectList suspectList, SceneTypes sceneType) {
        super(narrative, suspectList, sceneType);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
