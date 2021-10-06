package suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import clue.Clue;
import clue.FatherInsurance;
import clue.FatherMap;
import clue.FatherTextMessage;
import org.junit.jupiter.api.Test;
import seedu.duke.Ui;

public class SuspectListTest {

    @Test
    public void listOfSuspects() {
        Ui ui = new Ui();
        SuspectList suspects = new SuspectList(ui);
        suspects.addSuspect("Jerry", new Suspect());
        suspects.addSuspect("Tom", new Suspect());

        assertEquals("[Tom, Jerry]", suspects.getSuspects().keySet().toString());
    }

    @Test
    public void listOfSuspectsWithClues() {
        Ui ui = new Ui();
        SuspectList suspects = new SuspectList(ui);
        suspects.addSuspect("Jerry", new Suspect());
        suspects.addSuspect("Tom", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();
        Clue fatherMap = new FatherMap();

        suspects.addClueForSuspect("Tom", fatherInsurance);
        suspects.addClueForSuspect("Tom", fatherTextMessage);
        suspects.addClueForSuspect("Jerry", fatherMap);

        assertEquals(2, suspects.getSuspectAvailableClues("Tom").size());

        assertFalse(suspects.getSuspectAllClues("Tom").get(0).isChecked());
        suspects.setClueChecked("Tom", fatherInsurance);
        assertTrue(suspects.getSuspectAllClues("Tom").get(0).isChecked());

        assertEquals(1, suspects.getSuspectAvailableClues("Tom").size());

//        System.out.println(suspects.getSuspects().get("Jerry").getClues().get(0).isChecked());
    }
}
