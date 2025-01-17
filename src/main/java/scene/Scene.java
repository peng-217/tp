package scene;

import clue.Clue;
import narrative.Narrative;
import suspect.Suspect;
import suspect.SuspectList;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Scene {
    private final Narrative narrative;
    private final SuspectList suspectList;

    public Scene(Narrative narrative, SuspectList suspectList) {
        this.narrative = narrative;
        this.suspectList = suspectList;
    }


    public Narrative getNarrative() {
        return narrative;
    }

    public SuspectList getSuspectList() {
        return suspectList;
    }

    public ArrayList<Clue> getAllAvailableClues() {
        return suspectList.getAllAvailableClues();
    }

    public Suspect investigateSuspect(String name) {
        return suspectList.getSuspects().get(name);
    }

    public void runScene() throws FileNotFoundException {
        this.narrative.displayNarrative();
    }

    @Override
    public String toString() {
        try {
            return this.narrative.getNarrative()
                    + "\n"
                    + "Suspects: "
                    + this.getSuspectList().toString();
        } catch (FileNotFoundException e) {
            System.out.println("Narrative has not been selected!");
            return "Incomplete Scene";
        }
    }

    public abstract SceneTypes getSceneType();
}
