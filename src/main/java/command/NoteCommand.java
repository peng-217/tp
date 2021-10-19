package command;

import investigation.Investigation;
import note.Note;
import note.NoteList;
import ui.Ui;

public class NoteCommand extends Command {
    public NoteCommand() {

    }

    @Override
    public void execute(Ui ui, Investigation investigation) {
        investigation.processNote();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
