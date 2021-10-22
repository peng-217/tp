package clue;

import suspect.SuspectList;
import static suspect.SuspectList.suspectListBuilder;
import ui.Ui;

import java.io.FileNotFoundException;

public class CheckedClueTrackerBuilder {
    /*private Ui ui;
    private SuspectList suspects;

    public SearchedClueTracker(Ui ui) {
        this.ui = ui;
        this.suspects = new SuspectList(ui);
        buildSuspectList();
    }*/

    public static SuspectList buildClueTracker(Ui ui) {
        SuspectList suspects = new SuspectList(ui);

        try {
            suspectListBuilder("data/clueTracker.txt", suspects);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return suspects;
    }

    /*public void viewSearcherdCLues(String name) {
        ArrayList<Clue> availableClues = suspects.getSuspectAvailableClues(name);
        ui.printListOfClues(availableClues);
    }*/
}
