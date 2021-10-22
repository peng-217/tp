package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class IntroductionScene extends Scene {
    private SceneTypes sceneType = SceneTypes.INTRODUCTION_SCENE;

    public IntroductionScene(Narrative narrative, SuspectList suspectList) {
        super(narrative, suspectList);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
