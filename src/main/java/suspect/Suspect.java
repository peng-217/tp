package suspect;

import clue.Clue;

import java.util.ArrayList;

public class Suspect {
    protected ArrayList<Clue> clues;

    public Suspect() {
        this.clues = new ArrayList<>();
    }

    public ArrayList<Clue> getClues() {
        return clues;
    }

    public int getNumClues() {
        return clues.size();
    }

    public void addClue(Clue clue) {
        this.clues.add(clue);
    }

    public void setChecked(Clue clue) {
        clue.setChecked();
    }

    public ArrayList<Clue> getAvailableClues() {
        ArrayList<Clue> availableClues = new ArrayList<>();
        for (Clue clue : clues) {
            if (!clue.isChecked()) {
                availableClues.add(clue);
            }
        }
        return availableClues;
    }
}
