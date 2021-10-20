package command;

import investigation.Investigation;
import ui.Ui;

public class NextCommand extends Command {
    private boolean hasCompleted = false;

    @Override
    public void execute(Ui ui, Investigation investigation) {
        int getNextScene = investigation.getNextSceneFromSceneList();
        if (getNextScene == 0) { // is the last scene
            hasCompleted = true;
            //hasCompleted = investigation.completedGame();
        } else if (getNextScene == 1) { // has finished investigation and needs to vote now
            boolean isCorrect = investigation.isACorrectGuess();
            investigation.getNextSceneFromSceneList(isCorrect);
            investigation.runScenes();
        } else { // neither of the other two
            investigation.runScenes();
        }
    }

    @Override
    public boolean exit() {
        return this.hasCompleted;
    }
}
