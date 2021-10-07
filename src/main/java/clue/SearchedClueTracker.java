package clue;

import seedu.duke.SuspectList;
import seedu.duke.Ui;

public class SearchedClueTracker {
    private Ui ui;
    private SuspectList suspects;
    private ClueList clues = new ClueList();

    public SearchedClueTracker(Ui ui, SuspectList suspects, Clue Clues) {
        this.ui = ui;
        this.suspects = suspects;
    }
    
    public void viewSearcherdCLues() {
        for(Clue i : clues.getList()) {
            if(i.isChecked) {
                ui.print(i.toString());
            }
        }
    }
}
