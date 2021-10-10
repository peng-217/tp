package scene;

import narrative.Narrative;
import search.Search;
import suspect.SuspectList;

import java.io.FileNotFoundException;

public class Scene {
    private final Narrative narrative;
    private final Search search;

    public Scene(Narrative narrative, Search search) {
        this.narrative = narrative;
        this.search = search;
        search.setScene(this);
    }

    public Narrative getNarrative() {
        return narrative;
    }

    public Search getSearch() {
        return this.search;
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
                    + this.getSearch().toString();
        } catch (FileNotFoundException e) {
            System.out.println("Narrative has not been selected!");
            return "Incomplete Scene";
        }
    }
}
