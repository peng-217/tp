package command;

import exceptions.InvalidSuspectException;
import investigation.Investigation;
import scene.SceneList;
import scene.SceneTypes;
import ui.Ui;

public class InvestigateCommand extends Command {
    private int parsedUserInput;
    private static final int WENDY_INDEX = 3;

    public InvestigateCommand(int parsedUserInput) {
        this.parsedUserInput = parsedUserInput;
    }

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) throws InvalidSuspectException {
        if (sceneList.getCurrentSceneType() == SceneTypes.GUESS_KILLER_SCENE) {
            boolean isCorrectKiller = (this.parsedUserInput == WENDY_INDEX);
            sceneList.setSceneNumberAfterSuspecting(isCorrectKiller);
            sceneList.runCurrentScene();
        } else {
            investigation.investigateScene(this.parsedUserInput, sceneList.getCurrentScene());
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
