package clue;

import ui.Ui;
import suspect.SuspectList;

import java.util.ArrayList;

public class SearchedClueTracker {

    private SuspectList suspects;

    public SearchedClueTracker(SuspectList suspects) {
        this.suspects = suspects;
    }
    
    public ArrayList<Clue> searcherdClues(String name) {
        assert suspects.getSuspectAvailableClues(name).size() > 0;
        ArrayList<Clue> checkedClues = new ArrayList<>();
        for (Clue clue : suspects.getSuspectAvailableClues(name)) {
            if (clue.isChecked()) {
                checkedClues.add(clue);
            }
        }
        return checkedClues;
    }
}
