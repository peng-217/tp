package suspect;

import clue.Clue;
import ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SuspectList {
    protected LinkedHashMap<String, Suspect> suspects;
    protected Ui ui;

    public SuspectList(Ui ui) {
        this.ui = ui;
        this.suspects = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Suspect> getSuspects() {
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

    public int getClueIndex(String suspectName, String clueName) {
        ArrayList<Clue> clues = this.getSuspectAllClues(suspectName);
        for (int i = 0; i < clues.size(); i++) {
            if (clues.get(i).getClueName().equals(clueName)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Clue> getSuspectAvailableClues(String name) {
        return suspects.get(name).getAvailableClues();
    }

    public ArrayList<Clue> getSuspectCheckedClues(String name) {
        return suspects.get(name).getCheckedClues();
    }

    public ArrayList<Clue> getAllClues() {
        ArrayList<Clue> clues = new ArrayList<>();
        for (Map.Entry<String, Suspect> suspectEntry : suspects.entrySet()) {
            clues.addAll(0, suspectEntry.getValue().getClues());
        }
        return clues;
    }

    public ArrayList<Clue> getAllAvailableClues() {
        ArrayList<Clue> clues = new ArrayList<>();
        for (Map.Entry<String, Suspect> suspectEntry : suspects.entrySet()) {
            clues.addAll(0, suspectEntry.getValue().getAvailableClues());
        }
        return clues;
    }

    public int getNumSuspects() {
        return suspects.size();
    }

    @Override
    public String toString() {
        return String.valueOf(suspects.keySet());
    }

    public int getClueIndex(String suspectName, String clueName) {
        ArrayList<Clue> clues = this.getSuspectAllClues(suspectName);
        for (int i = 0; i < clues.size(); i++) {
            if (clues.get(i).getClueName().equals(clueName)) {
                return i;
            }
        }
        return -1;
    }
}
