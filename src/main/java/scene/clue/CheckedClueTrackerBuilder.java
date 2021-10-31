package scene.clue;

import scene.suspect.SuspectList;
import static scene.suspect.SuspectListBuilder.suspectListBuilder;

import java.io.FileNotFoundException;

public class CheckedClueTrackerBuilder {
    /*private Ui ui;
    private SuspectList suspects;

    public SearchedClueTracker(Ui ui) {
        this.ui = ui;
        this.suspects = new SuspectList(ui);
        buildSuspectList();
    }*/

    public static SuspectList buildClueTracker() {
        SuspectList suspects = new SuspectList();

        try {
            suspectListBuilder("/clueTracker.txt", suspects);
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
