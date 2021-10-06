package environment;

import java.util.ArrayList;

public class Suspect {
    protected int totalClues;
    protected ArrayList<String> clues;

    public Suspect(int totalClues) {
        this.totalClues = totalClues;
        this.clues = new ArrayList<>();
    }

    public ArrayList<String> getClues() {
        return clues;
    }

    public void addClue(String clue) {
        this.clues.add(clue);
    }

    public ArrayList<String> getAvailableClues() {
        ArrayList<String> availableClues = new ArrayList<>();
        for (String clue : clues) {
            if (!clue.isEmpty()) { // TODO: replace with clue.isChecked in future
                availableClues.add(clue);
            }
        }
        return availableClues;
    }
}
