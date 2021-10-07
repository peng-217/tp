package seedu.duke;

import java.util.ArrayList;

public class SuspectList {
    private ArrayList<Suspect> suspects;
    private Ui ui;

    public SuspectList(Ui ui) {
        this.ui = ui;
    }

    public Suspect getIndexSuspect(int index) {
        return suspects.get(index);
    }

    public int getSize() {
        return suspects.size();
    }



}
