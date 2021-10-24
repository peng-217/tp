package exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import investigation.Investigation;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

    @Test
    public void throwInvalidSuspectExceptionTest() {
        Investigation investigation = new Investigation();
        assertThrows(InvalidSuspectException.class, () -> investigation.investigateScene(0));
    }

    @Test
    public void throwInvalidClueExceptionTest() {
        Investigation investigation = new Investigation();
        investigation.investigateScene(1);
        assertThrows(InvalidClueException.class, () -> investigation.investigateScene(10));
    }
}
