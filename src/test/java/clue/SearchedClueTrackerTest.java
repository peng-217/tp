package clue;

import clue.firstscene.FatherInsurance;
import clue.firstscene.FatherTextMessage;
import org.junit.jupiter.api.Test;
import suspect.Suspect;
import suspect.SuspectList;
import ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchedClueTrackerTest {
    @Test
    public void viewSearcherdCLues_Tom_empty() {
        SuspectList suspects = new SuspectList();

        suspects.addSuspect("Father", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();

        suspects.addClueForSuspect("Father", fatherInsurance);
        suspects.addClueForSuspect("Father", fatherTextMessage);

        SearchedClueTracker tracker = new SearchedClueTracker(suspects);
        ArrayList<Clue> actualResult = tracker.searcherdClues("Father");

        assertEquals(0, actualResult.size());
    }

}