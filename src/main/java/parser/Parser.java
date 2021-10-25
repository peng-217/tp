package parser;

import command.Command;
import command.ExitCommand;
import command.HelpCommand;
import command.InvestigateCommand;
import command.NextCommand;
import command.NoteCommand;
import command.RestartCommand;
import command.ViewCommand;
import exceptions.InvalidInputException;
import exceptions.InvalidSuspectException;
import suspect.SuspectNames;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String HELP = "/help";
    private static final String NOTE = "/note";
    private static final String EXIT = "/exit";
    private static final String NEXT = "/next";
    private static final String VIEW = "/view";
    private static final String RESTART = "/restart";
    private static final String SUSPECT_FATHER = "Father";
    private static final String SUSPECT_KEVIN = "Kevin";
    private static final String SUSPECT_WENDY = "Wendy";
    private static final String SUSPECT_LING = "Ling";
    private static final String SUSPECT_ZACK = "Zack";
    private static final String INVALID_SUSPECT = "No suspect with corresponding number.";
    private static final String INPUT_SPLITTER = " ";
    private static final int NOTE_SCENE_INDEX = 1;
    private static final String INVALID_INPUT = "Invalid input!";
    private static final String INVESTIGATE = "/investigate";
    private static final String INVALID_SUSPECT_NAME = "Invalid suspect given!";

    private static final String SUSPECT_FATHER_LOWER = "father";
    private static final String SUSPECT_KEVIN_LOWER = "kevin";
    private static final String SUSPECT_WENDY_LOWER = "wendy";
    private static final String SUSPECT_LING_LOWER = "ling";
    private static final String SUSPECT_ZACK_LOWER = "zack";

    private static final int SUSPECT_FATHER_INDEX = 1;
    private static final int SUSPECT_KEVIN_INDEX = 2;
    private static final int SUSPECT_WENDY_INDEX = 3;
    private static final int SUSPECT_LING_INDEX = 4;
    private static final int SUSPECT_ZACK_INDEX = 5;

    private String suspectFromFirstScene(int suspectNumber) {
        if (suspectNumber == SUSPECT_FATHER_INDEX) {
            return SUSPECT_FATHER;
        }
        throw new InvalidSuspectException(INVALID_SUSPECT);
    }

    private String suspectFromSecondScene(int suspectNumber) {
        switch (suspectNumber) {
        case SUSPECT_FATHER_INDEX:
            return SUSPECT_FATHER;
        case SUSPECT_KEVIN_INDEX:
            return SUSPECT_KEVIN;
        case SUSPECT_WENDY_INDEX:
            return SUSPECT_WENDY;
        default:
            throw new InvalidSuspectException(INVALID_SUSPECT);
        }
    }

    private String suspectFromThirdScene(int suspectNumber) {
        switch (suspectNumber) {
        case SUSPECT_FATHER_INDEX:
            return SUSPECT_FATHER;
        case SUSPECT_KEVIN_INDEX:
            return SUSPECT_KEVIN;
        case SUSPECT_WENDY_INDEX:
            return SUSPECT_WENDY;
        case SUSPECT_LING_INDEX:
            return SUSPECT_LING;
        case SUSPECT_ZACK_INDEX:
            return SUSPECT_ZACK;
        default:
            throw new InvalidSuspectException(INVALID_SUSPECT);
        }
    }


    public String getSuspectNameFromIndex(int currentScene, int suspectNumber) throws InvalidSuspectException {
        switch (currentScene) {
        case 1:
            return suspectFromFirstScene(suspectNumber);
        case 2:
            return suspectFromSecondScene(suspectNumber);
        case 3:
            return suspectFromThirdScene(suspectNumber);
        default:
            throw new InvalidSuspectException(INVALID_SUSPECT);
        }
    }

    public Command getCommandFromUser(String userInput) throws InvalidInputException {
        boolean multipleArgumentsGiven = userInput.contains(INPUT_SPLITTER);
        if (multipleArgumentsGiven) {
            return parseInputMultipleArguments(userInput);
        }

        switch (userInput) {
        case NOTE:
            return new NoteCommand();
        case EXIT:
            return new ExitCommand();
        case HELP:
            return new HelpCommand();
        case NEXT:
            return new NextCommand();
        case VIEW:
            return new ViewCommand();
        case RESTART:
            return new RestartCommand();
        default:
            return differentCommandsForInvestigating(userInput);
        }
    }

    private Command differentCommandsForInvestigating(String userInput) throws InvalidInputException {
        Pattern stringPattern = Pattern.compile("[a-zA-Z]");
        Pattern numberPattern = Pattern.compile("[0-9]");
        Matcher stringPatternMatcher = stringPattern.matcher(userInput);
        Matcher numberPatternMatcher = numberPattern.matcher(userInput);

        boolean numberFound = numberPatternMatcher.find();
        boolean stringFound = stringPatternMatcher.find();

        if (numberFound) {
            int inputParsedToInt = Integer.parseInt(userInput);
            return new InvestigateCommand(inputParsedToInt);
        } else if (stringFound) {
            return parseInputForInvestigateCommand(userInput);
        } else {
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

    private Command parseInputForViewCommand(String argsGiven) throws InvalidInputException {
        if (containInvalidArgument(argsGiven)) {
            throw new InvalidInputException(INVALID_INPUT);
        }
        return new ViewCommand(argsGiven);
    }


    private Command parseInputForInvestigateCommand(String suspectName) throws InvalidInputException {
        String suspectNameLowerCase = suspectName.toLowerCase();
        int suspectIndex = getSuspectIndexFromSuspectName(suspectNameLowerCase);
        return new InvestigateCommand(suspectIndex);
    }

    private int getSuspectIndexFromSuspectName(String suspectName) throws InvalidInputException {
        switch (suspectName) {
        case SUSPECT_FATHER_LOWER:
            return 1;
        case SUSPECT_KEVIN_LOWER:
            return 2;
        case SUSPECT_WENDY_LOWER:
            return 3;
        case SUSPECT_LING_LOWER:
            return 4;
        case SUSPECT_ZACK_LOWER:
            return 5;
        default:
            throw new InvalidInputException(INVALID_SUSPECT_NAME);
        }
    }

    private Command parseInputMultipleArguments(String userInput) throws InvalidInputException {
        String[] userInputArr = userInput.split(INPUT_SPLITTER, 2);
        String commandType = userInputArr[0];
        String argsGiven = userInputArr[1];

        switch (commandType) {
        case VIEW:
            return parseInputForViewCommand(argsGiven);
        case INVESTIGATE:
            return parseInputForInvestigateCommand(argsGiven);
        default:
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

    private boolean containInvalidArgument(String args) {
        String[] argsArr = args.split(INPUT_SPLITTER);
        for (String arg : argsArr) {
            switch (arg) {
            case SUSPECT_FATHER:
                // fallthrough
            case SUSPECT_ZACK:
                // fallthrough
            case SUSPECT_WENDY:
                // fallthrough
            case SUSPECT_KEVIN:
                // fallthrough
            case SUSPECT_LING:
                break;
            default:
                return true;
            }
        }
        return false;
    }


    public static int parseNoteSceneIndex(String userInput) {
        String[] userInputSplit = userInput.split(INPUT_SPLITTER);
        return Integer.parseInt(userInputSplit[NOTE_SCENE_INDEX]);
    }

}


