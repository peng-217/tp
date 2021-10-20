package clue;

import ui.Ui;
import suspect.SuspectList;

import java.util.ArrayList;

public class SearchedClueTracker {
    private Ui ui;
    private SuspectList suspects;

    public SearchedClueTracker(Ui ui, SuspectList suspects) {
        this.ui = ui;
        this.suspects = suspects;
    }
    
    public ArrayList<Clue> searcherdClues(String name) {
        ArrayList<Clue> checkedClues = new ArrayList<>();
        for(Clue clue : suspects.getSuspectAvailableClues(name)) {
            if(clue.isChecked()) {
                checkedClues.add(clue);
            }
        }
        return checkedClues;
    }
}
