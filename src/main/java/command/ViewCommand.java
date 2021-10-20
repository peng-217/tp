package command;

import clue.Clue;
import investigation.Investigation;
import ui.Ui;

import java.util.ArrayList;

public class ViewCommand extends Command {
    private String[] suspects = {"Father", "Kevin", "Wendy", "Ling", "Zack"};

    @Override
    public void execute(Ui ui, Investigation investigation) {
        ui.printViewingCheckedCluesMessage();
        for (String name : suspects) {
            ArrayList<Clue> clues = investigation.getSuspectCheckedClues(name);
            if (clues.isEmpty()) {
                continue;
            }
            System.out.println("<" + name + ">");
            for (Clue clue : clues) {
                System.out.println(clue);
            }
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
