package suspect;

import scene.clue.Clue;

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
        int index = clues.indexOf(clue);
        try {
            clues.get(index).setChecked();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to find clue to set checked");
        }
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

    public ArrayList<Clue> getCheckedClues() {
        ArrayList<Clue> checkedClues = new ArrayList<>();
        for (Clue clue : clues) {
            if (clue.isChecked()) {
                checkedClues.add(clue);
            }
        }
        return checkedClues;
    }
}
