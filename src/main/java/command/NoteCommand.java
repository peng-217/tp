package command;

import investigation.Investigation;
import note.Note;
import note.NoteList;
import scene.SceneList;
import ui.Ui;

public class NoteCommand extends Command {
    public NoteCommand() {

    }

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        investigation.processNote(sceneList);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
