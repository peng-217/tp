package seedu.duke;

public class Scene {
    private final Narrative narrative;
    private final Search clueSearch;

    public Scene(Narrative narrative, Search clueSearch) {
        this.narrative = narrative;
        this.clueSearch = clueSearch;
    }

    public Narrative getNarrative() {
        return narrative;
    }

    public Search getClueSearch() {
        return clueSearch;
    }
}
