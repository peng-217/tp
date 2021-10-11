package scene;

import narrative.Narrative;
import search.Search;
import suspect.Suspect;
import suspect.SuspectList;

import java.io.FileNotFoundException;

public class Scene {
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
}
