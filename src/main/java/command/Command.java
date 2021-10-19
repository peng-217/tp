package command;

import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import ui.Ui;
import investigation.Investigation;

public abstract class Command {
    public abstract void execute(Ui ui, Investigation investigation);

    public abstract boolean exit();

}