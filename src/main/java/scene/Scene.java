package scene;

import clue.Clue;
import narrative.Narrative;
import suspect.Suspect;
import suspect.SuspectList;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Scene {
    private final Narrative narrative;
    private final SuspectList suspectList;
    private final SceneTypes sceneType;

    public Scene(Narrative narrative, SuspectList suspectList, SceneTypes sceneType) {
        this.narrative = narrative;
        this.suspectList = suspectList;
        this.sceneType = sceneType;
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

    public SceneTypes getSceneType() {
        return this.sceneType;
    }
}
