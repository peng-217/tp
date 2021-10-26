package command;

import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

public class BackCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        investigation.setSuspectStage();
        sceneList.previousScene();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
