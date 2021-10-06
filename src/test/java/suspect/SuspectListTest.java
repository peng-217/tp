package suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;

import java.util.Arrays;
import java.util.HashMap;

public class SuspectListTest {

    @Test
    public void listOfSuspects() {
        Ui ui = new Ui();
        SuspectList suspects = new SuspectList(ui);
        suspects.addSuspect("Jerry", new Suspect());
        suspects.addSuspect("Tom", new Suspect());

//        ui.printSuspects(suspects);

        assertEquals("[Tom, Jerry]", suspects.getSuspects().keySet().toString());
    }
}
