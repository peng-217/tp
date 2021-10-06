package suspect;

import seedu.duke.Ui;

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

    public int getNumSuspects() {
        return suspects.size();
    }



}
