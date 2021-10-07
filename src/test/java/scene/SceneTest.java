package scene;

import narrative.Narrative;
import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import suspect.SuspectList;


import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SceneTest {

    @Test
    public void getNarrative() {
        Scene scene = new Scene(new Narrative(), new SuspectList(new Ui()));
        assertThrows(FileNotFoundException.class, () -> scene.runScene());
    }

    @Test
    public void toString_InstantiateScene_printNoNarrativeessage() {
        String expectedResult = "Incomplete Scene";
        Scene scene = new Scene(new Narrative(), new SuspectList(new Ui()));
        String result = scene.toString();
        assertEquals(expectedResult, result);
    }
}
