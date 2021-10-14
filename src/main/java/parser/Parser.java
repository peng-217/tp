package parser;

import command.NextCommand;
import command.Command;
import command.ExitCommand;
import command.HelpCommand;
import command.InvestigateCommand;

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

    public String getSuspectNameFromIndex(int currentScene, String userInput) {
        try {
            int index = Integer.parseInt(userInput);
            if (index == 1) {
                return "Father";
            } else if (index == 2 & currentScene >= 1) {
                return "Kevin";
            } else if (index == 3 & currentScene >= 1) {
                return "Wendy";
            } else if (index == 4 & currentScene >= 2) {
                return "Ling";
            } else if (index == 5 & currentScene >= 2) {
                return "Zack";
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Command getCommandFromUser(String userInput) {
        Command commandToReturn;
        switch (userInput) {
        case EXIT:
            commandToReturn = new ExitCommand();
            return commandToReturn;
        case HELP:
            commandToReturn = new HelpCommand();
            return commandToReturn;
        case NEXT:
            commandToReturn = new NextCommand();
            return commandToReturn;
        default:
            commandToReturn = new InvestigateCommand(userInput);
            return commandToReturn;
        }
    }

}
