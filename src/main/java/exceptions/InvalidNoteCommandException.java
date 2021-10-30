package exceptions;

public class InvalidNoteCommandException extends Exception {
    public InvalidNoteCommandException(String message) {
        super(message);
    }
}
