package parser;

public class Parser {
    private static final String HELP = "/help";
    private static final String CLUES = "/clues";
    private static final String SUSPECT = "/suspect";
    private static final String NOTE = "/note";

    private static final String INPUT_SPLITTER = " ";

    private static final int FIRST_INDEX = 0;

    public String parseUserInput(String userInput) {
        String[] userInputSplit = userInput.split(INPUT_SPLITTER);
        System.out.println(userInputSplit[FIRST_INDEX]);
        String userCommand = userInputSplit[FIRST_INDEX];
        return userCommand;
    }

}
