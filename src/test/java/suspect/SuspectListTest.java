package suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import scene.clue.Clue;
import scene.clue.firstscene.FatherInsurance;
import scene.clue.firstscene.FatherMap;
import scene.clue.firstscene.FatherTextMessage;
import org.junit.jupiter.api.Test;

public class SuspectListTest {

    @Test
    public void listOfSuspects() {
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        suspects.addSuspect("Wendy", new Suspect());

        assertEquals("[Father, Wendy]", suspects.getSuspects().keySet().toString());
    }

    @Test
    public void listOfSuspectsWithClues() {
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        suspects.addSuspect("Wendy", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();
        Clue fatherMap = new FatherMap();

        suspects.addClueForSuspect("Father", fatherInsurance);
        suspects.addClueForSuspect("Father", fatherTextMessage);
        suspects.addClueForSuspect("Wendy", fatherMap);

        assertEquals(2, suspects.getSuspectAvailableClues("Father").size());

        assertFalse(suspects.getSuspectAllClues("Father").get(0).isChecked());
        suspects.setClueChecked("Father", fatherInsurance);
        assertTrue(suspects.getSuspectAllClues("Father").get(0).isChecked());

        assertEquals(1, suspects.getSuspectAvailableClues("Father").size());
    }
}
