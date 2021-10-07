package clue;

import suspect.Suspect;

import java.util.ArrayList;

public class ClueList {
    private ArrayList<Clue> clues = new ArrayList();

    public void addCLue(Clue clue) {
        clues.add(clue);
    }

    public int getSize() {
        return clues.size();
    }

    public ArrayList<Clue> getList() {
        return clues;
    }
}

