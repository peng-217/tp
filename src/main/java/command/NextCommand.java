package command;

import investigation.Investigation;
import scene.SceneList;
import scene.SceneTypes;
import ui.Ui;

public class NextCommand extends Command {
    private boolean hasCompleted = false;

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        SceneTypes sceneType = sceneList.getCurrentSceneType();
        switch (sceneType) {
        case CORRECT_KILLER_SCENE:
            hasCompleted = true;
            break;
        case TRUTH_SCENE:
            hasCompleted = true;
            sceneList.runCurrentScene();
            break;
        case GUESS_KILLER_SCENE:
            boolean isUserCorrect = investigation.checkSuspectedKiller(sceneList);
            sceneList.setSceneNumberAfterSuspecting(isUserCorrect);
            sceneList.runCurrentScene();
            break;
        default:
            investigation.setSuspectStage();
            sceneList.updateSceneNumber();
            sceneList.runCurrentScene();
        }
    }

    @Override
    public boolean exit() {
        return this.hasCompleted;
    }
}
