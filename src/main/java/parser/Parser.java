package parser;

import command.Command;
import command.ExitCommand;
import command.HelpCommand;
import command.InvestigateCommand;
import command.NarrativeLinesCommand;
import command.NextCommand;
import command.NoteCommand;
import command.RestartCommand;
import command.ViewCommand;
import command.BackCommand;
import exceptions.InvalidInputException;
import exceptions.InvalidSuspectException;
import scene.Scene;
import scene.suspect.SuspectList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String HELP = "/help";
    private static final String NOTE = "/note";
    private static final String EXIT = "/exit";
    private static final String NEXT = "/next";
    private static final String VIEW = "/view";
    private static final String BACK = "/back";
    private static final String NARRATIVE_LINES = "/narrative-lines";
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
    private static final String SUSPECT_FATHER_LOWER = "father";
    private static final String SUSPECT_KEVIN_LOWER = "kevin";
    private static final String SUSPECT_WENDY_LOWER = "wendy";
    private static final String SUSPECT_LING_LOWER = "ling";
    private static final String SUSPECT_ZACK_LOWER = "zack";
    private static final String NOTE_CREATE = "1";
    private static final String NOTE_OPEN = "2";
    private static final String NOTE_DELETE = "3";
    private static final String ALPHABET_PATTERN = "^[a-zA-Z]+$";
    private static final String NUMBER_PATTERN = "^[0-9]+$";

    public String getSuspectNameFromIndex(Scene currentScene, int suspectNumber) throws InvalidSuspectException {
        SuspectList currentSceneSuspectList = currentScene.getSuspectList();
        try {
            return currentSceneSuspectList.getSuspectNames()[suspectNumber - 1];
        } catch (InvalidSuspectException e) {
            throw new InvalidSuspectException(INVALID_SUSPECT);
        } catch (ArrayIndexOutOfBoundsException e) {
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
        case BACK:
            return new BackCommand();
        default:
            return useSuspectNameOrIndexForInvestigating(userInput);
        }
    }

    private Command useSuspectNameOrIndexForInvestigating(String userInput) throws InvalidInputException {
        Pattern alphabetPattern = Pattern.compile(ALPHABET_PATTERN);
        Pattern numberPattern = Pattern.compile(NUMBER_PATTERN);
        Matcher alphabetPatternMatcher = alphabetPattern.matcher(userInput);
        Matcher numberPatternMatcher = numberPattern.matcher(userInput);

        boolean numberFound = numberPatternMatcher.find();
        boolean alphabetFound = alphabetPatternMatcher.find();

        if (numberFound) {
            int inputParsedToInt = Integer.parseInt(userInput);
            return new InvestigateCommand(inputParsedToInt);
        } else if (alphabetFound) {
            return parseInputForInvestigateCommand(userInput);
        } else {
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

    private Command parseInputForViewCommand(String argsGiven) throws InvalidInputException {
        if (containInvalidViewArgument(argsGiven)) {
            throw new InvalidInputException(INVALID_INPUT);
        }
        return new ViewCommand(argsGiven);
    }

    private Command parseInputForNoteCommand(String argsGiven) throws InvalidInputException {
        if (containInvalidNoteArgument(argsGiven)) {
            throw new InvalidInputException(INVALID_INPUT);
        }
        return new NoteCommand(argsGiven);
    }

    private Command parseInputForNarrativeLinesCommand(String argsGiven) throws InvalidInputException {
        try {
            int numLines = Integer.parseInt(argsGiven);
            if (numLines < 1) {
                throw new InvalidInputException(INVALID_INPUT);
            }
            return new NarrativeLinesCommand(numLines);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

    private Command parseInputForInvestigateCommand(String suspectName) throws InvalidInputException {
        String suspectNameLowerCase = suspectName.toLowerCase();
        return new InvestigateCommand(suspectNameLowerCase);
    }


    private Command parseInputMultipleArguments(String userInput) throws InvalidInputException {
        String[] userInputArr = userInput.split(INPUT_SPLITTER, 2);
        String commandType = userInputArr[0];
        String argsGiven = userInputArr[1];

        switch (commandType) {
        case NOTE:
            return parseInputForNoteCommand(argsGiven);
        case VIEW:
            return parseInputForViewCommand(argsGiven);
        case INVESTIGATE:
            return useSuspectNameOrIndexForInvestigating(argsGiven);
        case NARRATIVE_LINES:
            return parseInputForNarrativeLinesCommand(argsGiven);
        default:
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

    private boolean containInvalidNoteArgument(String args) {
        String[] argsArr = args.split(INPUT_SPLITTER);
        for (String arg : argsArr) {
            switch (args) {
            case NOTE_CREATE:
                // fallthrough
            case NOTE_OPEN:
                //fallthrough
            case NOTE_DELETE:
                // fallthrough
                break;
            default:
                return true;
            }
        }
        return false;
    }

    private boolean containInvalidViewArgument(String args) {
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

    public boolean validSuspectNameGiven(String suspectedKiller) {
        String suspectedKillerLowerCase = suspectedKiller.toLowerCase();
        switch (suspectedKillerLowerCase) {
        case SUSPECT_WENDY_LOWER:
        case SUSPECT_FATHER_LOWER:
        case SUSPECT_KEVIN_LOWER:
        case SUSPECT_LING_LOWER:
        case SUSPECT_ZACK_LOWER:
            return true;
        default:
            return false;
        }
    }

    public static int parseNoteSceneIndex(String userInput) {
        String[] userInputSplit = userInput.split(INPUT_SPLITTER);
        return Integer.parseInt(userInputSplit[NOTE_SCENE_INDEX]);
    }

    public static String[] parseOpenNoteCommand(String userInput) {
        String[] userInputInArray = userInput.split(" ", 3);
        return userInputInArray;
    }

}


