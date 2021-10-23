package command;

import investigation.Investigation;
import scene.SceneTypes;
import ui.Ui;

public class NextCommand extends Command {
    private boolean hasCompleted = false;

    @Override
    public void execute(Ui ui, Investigation investigation) {
        SceneTypes sceneType = investigation.getSceneType();
        switch (sceneType) {
        case CORRECT_KILLER_SCENE:
            hasCompleted = true;
            break;
        case TRUTH_SCENE:
            hasCompleted = true;
            investigation.runScenes();
            break;
        case GUESS_KILLER_SCENE:
            investigation.checkSuspectedKiller();
            investigation.runScenes();
            break;
        default:
            investigation.updateScene();
            investigation.runScenes();
        }
    }

    @Override
    public boolean exit() {
        return this.hasCompleted;
    }
}
