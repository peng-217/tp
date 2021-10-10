package Parser;

public class Parser {
    private final static String HELP = "/help";
    private final static String CLUES = "/clues";
    private final static String SUSPECT = "/suspect";
    private final static String NOTE = "/note";

    private final static String INPUT_SPLITTER = " ";

    private final static int FIRST_INDEX = 0;

    public String parseUserInput(String userInput) {
        String[] userInputSplit = userInput.split(INPUT_SPLITTER);
        System.out.println(userInputSplit[FIRST_INDEX]);
        String userCommand = userInputSplit[FIRST_INDEX];
        return userCommand;
    }

}
