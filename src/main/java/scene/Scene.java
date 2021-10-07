package scene;

import narrative.Narrative;
import suspect.SuspectList;

import java.io.FileNotFoundException;

public class Scene {
    private final Narrative narrative;
    private final SuspectList suspects;

    public Scene(Narrative narrative, SuspectList suspects) {
        this.narrative = narrative;
        this.suspects = suspects;

    }

    public Narrative getNarrative() {
        return narrative;
    }

    public void runScene() throws FileNotFoundException {
        this.narrative.displayNarrative();
    }

    @Override
    public String toString() {
        try {
            return this.narrative.getNarrative() + "\n" + this.suspects.getSuspects().keySet().toString();
        } catch (FileNotFoundException e) {
            System.out.println("Narrative has not been selected!");
            return "Incomplete Scene";
        }
    }
}
