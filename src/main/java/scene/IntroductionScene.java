package scene;

import narrative.Narrative;
import suspect.SuspectList;

public class IntroductionScene extends Scene {
    private SceneTypes sceneType = SceneTypes.INTRODUCTION_SCENE;

    public IntroductionScene(Narrative narrative, SuspectList suspectList, SceneTypes sceneType) {
        super(narrative, suspectList, sceneType);
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
