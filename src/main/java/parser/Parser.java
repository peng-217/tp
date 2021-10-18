package parser;

import command.NextCommand;
import command.Command;
import command.ExitCommand;
import command.HelpCommand;
import command.InvestigateCommand;

public class Parser {
    private static final String HELP = "/help";
    private static final String EXIT = "/exit";
    private static final String NEXT = "/next";
    private static final String SUSPECT_FATHER = "Father";
    private static final String SUSPECT_KEVIN = "Kevin";
    private static final String SUSPECT_WENDY = "Wendy";
    private static final String SUSPECT_LING = "Ling";
    private static final String SUSPECT_ZACK = "Zack";

    private String suspectFromFirstScene(int suspectNumber) {
        if (suspectNumber == 1) {
            return SUSPECT_FATHER;
        }
        return null;
    }

    private String suspectFromSecondScene(int suspectNumber) {
        switch (suspectNumber) {
        case 1:
            return SUSPECT_FATHER;
        case 2:
            return SUSPECT_KEVIN;
        case 3:
            return SUSPECT_WENDY;
        default:
            return null;
        }
    }

    private String suspectFromThirdScene(int suspectNumber) {
        switch (suspectNumber) {
        case 1:
            return SUSPECT_FATHER;
        case 2:
            return SUSPECT_KEVIN;
        case 3:
            return SUSPECT_WENDY;
        case 4:
            return SUSPECT_LING;
        case 5:
            return SUSPECT_ZACK;
        default:
            return null;
        }
    }

    public String getSuspectNameFromIndex(int currentScene, String userInput) {
        int suspectNumber = Integer.parseInt(userInput);
        switch (currentScene) {
        case 0:
            return suspectFromFirstScene(suspectNumber);
        case 1:
            return suspectFromSecondScene(suspectNumber);
        case 2:
            return suspectFromThirdScene(suspectNumber);
        default:
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
