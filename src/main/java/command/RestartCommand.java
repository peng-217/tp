package command;

import investigation.Investigation;
import ui.Ui;

public class RestartCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation) {
        investigation.restartGame();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
