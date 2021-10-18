package command;

import investigation.Investigation;
import ui.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation) {
        ui.printListOfCommands();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
