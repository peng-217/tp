package command;

import exceptions.InvalidNoteException;
import investigation.Investigation;
import note.NoteList;
import scene.SceneList;
import ui.Ui;

public class NoteCommand extends Command {
    private String userChoice;
    static NoteList notes = new NoteList(new Ui());


    public NoteCommand(String command) {
        this.userChoice = command;
    }

    public NoteCommand() {
        userChoice = null;
    }

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        if (userChoice == null) {
            ui.printNoteInstructions();
            userChoice = ui.readUserInput();
        }
        try {
            notes.processNote(sceneList, userChoice);
        } catch (InvalidNoteException e1) {
            ui.printNoteErrorMessage(e1.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException e2) {
            ui.printNoteDeleteErrorMessage();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
