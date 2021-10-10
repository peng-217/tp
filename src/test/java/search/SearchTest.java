package search;

import narrative.Narrative;
import org.junit.jupiter.api.Test;
import scene.Scene;
import seedu.duke.Ui;
import suspect.SuspectList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchTest {
    @Test
    public void getScene() {
        Search search = new Search(new SuspectList(new Ui()));
        assertThrows(NullPointerException.class, () -> search.getScene().toString());
    }

    @Test
    public void toString_InstantiateSearchAndSetScene_printNoNarrativeMessage() {
        String expectedResult = "Incomplete Scene";
        Search search = new Search(new SuspectList(new Ui()));
        Scene scene = new Scene(new Narrative(), search);
        search.setScene(scene);
        String result = search.getScene().toString();
        assertEquals(expectedResult,result);
    }
}
