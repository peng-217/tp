package command;

import ui.Ui;
import investigation.Investigation;

public abstract class Command {
    public abstract void execute(Ui ui, Investigation investigation);

    public abstract boolean exit();

}