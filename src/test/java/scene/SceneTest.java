package scene;

import narrative.Narrative;
import org.junit.jupiter.api.Test;
import ui.Ui;
import suspect.SuspectList;


import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SceneTest {

    @Test
    public void getNarrative() {
        Scene scene = new IntroductionScene(new Narrative(), new SuspectList(new Ui()), SceneTypes.INTRODUCTION_SCENE);
        assertThrows(FileNotFoundException.class, scene::runScene);
    }

    @Test
    public void toString_InstantiateScene_printNoNarrativeMessage() {
        String expectedResult = "Incomplete Scene";
        Scene scene = new IntroductionScene(new Narrative(), new SuspectList(new Ui()), SceneTypes.INTRODUCTION_SCENE);
        String result = scene.toString();
        assertEquals(expectedResult, result);
    }
}
