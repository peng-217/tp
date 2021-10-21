package command;

import investigation.Investigation;
import ui.Ui;

public class NextCommand extends Command {
    private boolean hasCompleted = false;
    private static final String GUESS_KILLER_SCENE = "guess_killer_scene";
    private static final String FINAL_SCENE = "final_scene";

    @Override
    public void execute(Ui ui, Investigation investigation) {
        String sceneType = investigation.getSceneType();
        switch (sceneType) {
        case FINAL_SCENE:
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
