package clue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClueTest {

    @Test
    public void toString_InstantiateClue_printDefaultMessages() {
        String expectedResult = "------------------------------------------------\n"
                + "default name\n"
                + "default image :)\n"
                + "default description\n";
        Clue clue = new Clue();
        String result = clue.toString();
        assertEquals(expectedResult, result);
    }
}
