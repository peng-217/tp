package command;

import investigation.Investigation;
import ui.Ui;

public class NextCommand extends Command {
    private boolean hasCompleted = false;
    private static final String GUESS_KILLER_SCENE = "guess_killer_scene";
    private static final String CORRECT_KILLER_SCENE = "correct_killer_scene";
    private static final String WRONG_KILLER_SCENE = "WRONG_killer_scene";
    private static final String TRUTH_SCENE = "truth_scene";

    @Override
    public void execute(Ui ui, Investigation investigation) {
        String sceneType = investigation.getSceneType();
        switch (sceneType) {
        case CORRECT_KILLER_SCENE:
            hasCompleted = true;
            investigation.runScenes();
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
