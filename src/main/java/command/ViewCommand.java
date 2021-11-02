package command;

import scene.clue.Clue;
import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

import java.util.ArrayList;

public class ViewCommand extends Command {
    private final String[] suspects;

    public ViewCommand() {
        suspects = new String[]{"Father", "Kevin", "Wendy", "Ling", "Zack"};
    }

    public ViewCommand(String args) {
        suspects = args.split(" ");
    }

    /**
     * Executes the view command. If there are suspect(s) specified by the user,
     * prints already searched clues relating to them. Else print all clues that
     * have been searched.
     *
     * @param ui            used to communicate with the user.
     * @param investigation contains method to query the already searched clues.
     * @param sceneList     not used in this method but passed in as a standard of
     *                      command execution.
     */
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        ui.printViewingCheckedCluesMessage();
        findSearchedClues(ui, investigation);
    }

    /**
     * Finds the already searched clues for suspects specified by the user.
     * 
     * @param ui used to communicate with the user.
     * @param investigation contains method to query the already searched clues.
     */
    private void findSearchedClues(Ui ui, Investigation investigation) {
        for (String name : suspects) {
            ArrayList<Clue> clues = investigation.getSuspectCheckedClues(name);
            if (clues.isEmpty()) {
                continue;
            }
            ui.printSearchedClues(name, clues);
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
