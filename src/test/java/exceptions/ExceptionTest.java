package exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import clue.CheckedClueTrackerBuilder;
import investigation.Investigation;
import narrative.Narrative;
import org.junit.jupiter.api.Test;
import parser.Parser;
import scene.Scene;
import scene.SceneList;
import scene.SceneListBuilder;
import scene.SceneTypes;
import storage.GameDataFileDecoder;
import storage.GameDataFileManager;
import suspect.Suspect;
import suspect.SuspectList;
import ui.Ui;

public class ExceptionTest {

    @Test
    public void throwInvalidSuspectExceptionTest() {
        Narrative narrative = new Narrative();
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        Scene scene = new Scene(narrative, suspects, SceneTypes.INVESTIGATE_SCENE);
        SuspectList clueTracker = CheckedClueTrackerBuilder.buildClueTracker();
        Investigation investigation = new Investigation(clueTracker);

        assertThrows(InvalidSuspectException.class, () -> investigation.investigateScene(2, scene));
    }

    @Test
    public void throwInvalidClueExceptionTest() throws MissingSceneFileException {
        Narrative narrative = new Narrative();
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        Scene scene = new Scene(narrative, suspects, SceneTypes.INVESTIGATE_SCENE);
        SuspectList clueTracker = CheckedClueTrackerBuilder.buildClueTracker();
        Investigation investigation = new Investigation(clueTracker);
        investigation.investigateScene(1, scene);
        assertThrows(InvalidClueException.class, () -> investigation.investigateScene(10, scene));
    }
}
