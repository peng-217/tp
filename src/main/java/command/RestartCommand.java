package command;

import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

public class RestartCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        investigation.restartGame();
        sceneList.resetAllScenes();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
