package suspect;

import clue.Clue;
import seedu.duke.Ui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class SuspectList {
    protected HashMap<String, Suspect> suspects;
    protected Ui ui;

    public SuspectList(Ui ui) {
        this.ui = ui;
        this.suspects = new HashMap<>();
    }

    public HashMap<String, Suspect> getSuspects() {
        return suspects;
    }

    public void addSuspect(String name, Suspect suspect) {
        suspects.put(name, suspect);
    }

    public void addClueForSuspect(String name, Clue clue) {
        suspects.get(name).addClue(clue);
    }

    public void setClueChecked(String name, Clue clue) {
        suspects.get(name).setChecked(clue);
    }

    public ArrayList<Clue> getSuspectAllClues(String name) {
        return suspects.get(name).getClues();
    }

    public ArrayList<Clue> getSuspectAvailableClues(String name) {
        return suspects.get(name).getAvailableClues();
    }

    public int getNumSuspects() {
        return suspects.size();
    }



}
