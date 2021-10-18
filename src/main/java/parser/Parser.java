package parser;

import command.NextCommand;
import command.Command;
import command.ExitCommand;
import command.HelpCommand;
import command.InvestigateCommand;
import exceptions.InvalidInputException;
import exceptions.InvalidSuspectException;

public class Parser {
    private static final String HELP = "/help";
    private static final String CLUES = "/clues";
    private static final String SUSPECT = "/suspect";
    private static final String NOTE = "/note";
    private static final String EXIT = "/exit";
    private static final String NEXT = "/next";

    private static final String INPUT_SPLITTER = " ";

    private static final int COMMAND_INDEX = 0;
    private static final int CLUE_NUMBER_INDEX = 1;

    public String parseUserInput(String userInput) {
        String[] userInputSplit = userInput.split(INPUT_SPLITTER);
        String userCommand = userInputSplit[COMMAND_INDEX];
        return userCommand;
    }

    public int parseClueNumber(String userInput) {
        String[] userInputSplit = userInput.split(INPUT_SPLITTER);
        int clueNumber = Integer.parseInt(userInputSplit[CLUE_NUMBER_INDEX]);
        return clueNumber;
    }

    public String getSuspectNameFromIndex(int currentScene, int userInput) throws InvalidSuspectException {
        if (userInput == 1) {
            return "Father";
        } else if (userInput == 2 & currentScene >= 1) {
            return "Kevin";
        } else if (userInput == 3 & currentScene >= 1) {
            return "Wendy";
        } else if (userInput == 4 & currentScene >= 2) {
            return "Ling";
        } else if (userInput == 5 & currentScene >= 2) {
            return "Zack";
        } else {
            throw new InvalidSuspectException("Input out of range");
        }
    }

    public Command getCommandFromUser(String userInput) throws InvalidInputException {
        switch (userInput) {
        case EXIT:
            return new ExitCommand();
        case HELP:
            return new HelpCommand();
        case NEXT:
            return new NextCommand();
        default:
            validInput(userInput);
            int inputParsedToInt = Integer.parseInt(userInput);
            return new InvestigateCommand(inputParsedToInt);
        }
    }

    private void validInput(String userInput) throws InvalidInputException {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input!");
        }
    }

}
