package command;

import investigation.Investigation;
import ui.Ui;

public class NextCommand extends Command {
    private boolean hasCompleted = false;

    @Override
    public void execute(Ui ui, Investigation investigation) {
        boolean currentScene = investigation.getNextSceneFromSceneList();
        if (currentScene) {
            hasCompleted = investigation.completedGame();
        } else {
            investigation.runScenes();
        }
    }

    @Override
    public boolean exit() {
        return this.hasCompleted;
    }
}
