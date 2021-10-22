package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import command.NoteCommand;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import command.Command;

public class ParserTest {

    // test failed invocation of display method of an Narrative object
    @Test
    public void parserTest() {
        Parser parser = new Parser();
        String suspectFather = parser.getSuspectNameFromIndex(1, 1);
        assertEquals("Father", suspectFather);

        String suspectKevin = parser.getSuspectNameFromIndex(2, 2);
        assertEquals("Kevin", suspectKevin);

        String suspectZack = parser.getSuspectNameFromIndex(3, 5);
        assertEquals("Zack", suspectZack);

        assertThrows(InvalidInputException.class, () -> parser.getCommandFromUser(""));
    }
}
