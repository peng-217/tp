package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class GuessKillerScene extends Scene {
    private SceneTypes sceneType = SceneTypes.GUESS_KILLER_SCENE;

    public GuessKillerScene(Narrative narrative, SuspectList suspectList) {
        super(narrative, suspectList);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
