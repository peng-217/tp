package suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
