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

import java.util.Objects;

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

    private String suspectFromFirstScene(int suspectNumber) {
        if (suspectNumber == 1) {
            return SUSPECT_FATHER;
        }
        throw new InvalidSuspectException(INVALID_SUSPECT);
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
            throw new InvalidSuspectException(INVALID_SUSPECT);
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
        boolean isNotOneWord = userInput.contains(" ");
        if (isNotOneWord) {
            return parseInputForViewCommand(userInput);
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
            validInput(userInput);
            int inputParsedToInt = Integer.parseInt(userInput);
            return new InvestigateCommand(inputParsedToInt);
        }
    }

    private Command parseInputForViewCommand(String userInput) throws InvalidInputException {
        String[] userInputArr = userInput.split(INPUT_SPLITTER,2);
        System.out.println(userInputArr[0]);
        if (!Objects.equals(userInputArr[0], VIEW)) {
            throw new InvalidInputException(INVALID_INPUT);
        }
        if (containInvalidArgument(userInputArr[1])) {
            throw new InvalidInputException(INVALID_INPUT);
        }
        return new ViewCommand(userInputArr[1]);
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

    private void validInput(String userInput) throws InvalidInputException {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

}


