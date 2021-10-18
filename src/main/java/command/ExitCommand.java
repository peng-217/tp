package command;


import investigation.Investigation;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation) {
        ui.printExitMessage();
    }

    @Override
    public boolean exit() {
        return true;
    }
}
