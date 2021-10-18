package command;

import investigation.Investigation;
import ui.Ui;

public class InvestigateCommand extends Command {
    private String userInput;

    public InvestigateCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(Ui ui, Investigation investigation) {
        investigation.investigateScene(userInput);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
