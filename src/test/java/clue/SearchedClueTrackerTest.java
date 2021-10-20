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
        Ui ui = new Ui();
        SuspectList suspects = new SuspectList(ui);

        suspects.addSuspect("Tom", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();

        suspects.addClueForSuspect("Tom", fatherInsurance);
        suspects.addClueForSuspect("Tom", fatherTextMessage);

        SearchedClueTracker tracker = new SearchedClueTracker(ui, suspects);
        ArrayList<Clue> actualResult = tracker.searcherdClues("Tom");

        assertEquals(0, actualResult.size());
    }

}