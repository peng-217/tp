package command;

import exceptions.InvalidSuspectException;
import investigation.Investigation;
import ui.Ui;

public class InvestigateCommand extends Command {
    private int parsedUserInput;

    public InvestigateCommand(int parsedUserInput) {
        this.parsedUserInput = parsedUserInput;
    }

    @Override
    public void execute(Ui ui, Investigation investigation) throws InvalidSuspectException {
        investigation.investigateScene(this.parsedUserInput);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
