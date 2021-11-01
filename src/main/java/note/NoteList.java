//@@author peng-217

package note;

import java.util.ArrayList;

import exceptions.InvalidNoteException;
import parser.Parser;
import storage.Storage;
import scene.SceneList;
import ui.Ui;

public class NoteList {
    private final ArrayList<Note> notes;
    private final Ui ui;
    private static int defaultTitleCounter = 1;
    private static final String INVALID_NOTE_INDEX_MESSAGE = "The index you entered is not valid! "
            + "Please check again.";
    private static final String INVALID_NOTE_COMMAND_MESSAGE = "The command you entered is not valid! "
            + "Please check again.";
    private static final String INVALID_NOTE_SEARCH_MESSAGE = "Please input a valid search choice!";


    public NoteList(Ui ui) {
        this.ui = ui;
        //storage = new Storage();
        notes = new ArrayList<>();
        Storage.openNoteFromFile(this);
    }

    public int getSize() {
        return notes.size();
    }

    public ArrayList<Note> searchNotesUsingSceneIndex(int searchSceneIndex,NoteList notes) {
        ArrayList<Note> result = new ArrayList<>();
        for (int i = 0; i < notes.getSize(); i++) {
            if (notes.getIndexNote(i).getNoteSceneIndex() == searchSceneIndex) {
                result.add(notes.getIndexNote(i));
            }
        }
        return result;
    }

    public ArrayList<Note> searchNoteUsingTitle(String keyword,NoteList notes) {
        String[] words = stringSplitter(keyword);
        ArrayList<Note> result = new ArrayList<>();
        for (int i = 0; i < notes.getSize(); i++) {
            boolean titleNotContains = false;
            for (int j = 0; j < words.length; j++) {
                if (!notes.getIndexNote(i).getNoteTitle().contains(words[j])) {
                    titleNotContains = true;
                }
            }
            if (!titleNotContains) {
                result.add(notes.getIndexNote(i));
            }
        }
        return result;
    }

    public static String[] stringSplitter(String keywords) {
        String[] words = keywords.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toUpperCase();
        }
        return words;
    }

    public Note getIndexNote(int index) {
        return notes.get(index);
    }

    public void createNote(Note newNote) {
        notes.add(newNote);
        Storage.saveNote(this);
        ui.printSaveNoteMessage();
    }

    public void createNoteFromFile(Note newNote, int inputSceneIndex) {
        notes.add(newNote);
        Storage.saveNote(this);
    }

    public void deleteNote(int index) {
        notes.remove(index);
        Storage.saveNote(this);
        ui.printDeleteNoteMessage();
    }


    public void deleteAllNotes() {
        notes.removeAll(notes);
        Storage.saveNote(this);
        ui.printDeleteAllNoteMessage();
    }

    public void processNote(SceneList sceneList, String userChoice) throws InvalidNoteException {
        switch (userChoice) {
        case "1" :
            createNoteProcess(sceneList);
            break;
        case "2" :
            openNoteProcess();
            break;
        case "3" :
            deleteNoteProcess();
            break;
        default :
            throw new InvalidNoteException(INVALID_NOTE_INDEX_MESSAGE);
        }
    }

    public void createNoteProcess(SceneList sceneList) {
        ui.printNoteTitleInstructions();
        String transientTitle = ui.readUserInput();
        String noteTitle;
        if (!transientTitle.equals(" ")) {
            noteTitle = transientTitle;
        } else {
            noteTitle = "DEFAULT(" + (defaultTitleCounter++) + ")";
        }
        ui.printNoteTextInstructions();
        String noteContent = ui.readUserInput();
        Note newNote = new Note(noteContent, noteTitle, sceneList.getCurrentSceneIndex());
        createNote(newNote);
    }

    public void openNoteProcess() throws InvalidNoteException {
        boolean checkExistence = ui.printOpenNoteMessage(this);
        if (checkExistence) {
            String userInput = ui.readUserInput();
            while (!userInput.equals("")) {
                if (!(userInput.startsWith("search") || userInput.startsWith("open"))) {
                    ui.printNoteErrorMessage(INVALID_NOTE_COMMAND_MESSAGE);
                    userInput = ui.readUserInput();
                } else {
                    break;
                }
            }
            String[] userInputInArray = Parser.parseOpenNoteCommand(userInput.trim());
            if (userInputInArray[0].equals("search") && userInputInArray.length > 1) {
                selectSearchMethod(userInputInArray);
            } else if (userInputInArray[0].equals("search")) {
                while (!userInput.equals("")) {
                    ui.printNoteSearchInstructions();
                    userInput = ui.readUserInput();
                    try {
                        selectSearchMethod(userInput);
                        break;
                    } catch (InvalidNoteException e1) {
                        ui.printNoteErrorMessage(INVALID_NOTE_COMMAND_MESSAGE);
                    } catch (NumberFormatException e2) {
                        ui.printNoteErrorMessage(INVALID_NOTE_INDEX_MESSAGE);
                    }
                }
            } else if (userInput.startsWith("open") && userInputInArray.length == 2) {
                try {
                    openNoteDirectly(userInputInArray[1]);
                } catch (IndexOutOfBoundsException e) {
                    ui.printNoteMissingError(notes.size());
                } catch (NumberFormatException e2) {
                    ui.printNoteErrorMessage(INVALID_NOTE_INDEX_MESSAGE);
                }
            } else if (userInputInArray[0].equals("open") && userInputInArray.length == 1) {
                while (!userInput.equals("")) {
                    try {
                        openNoteDirectly();
                        break;
                    } catch (IndexOutOfBoundsException e1) {
                        ui.printNoteMissingError(notes.size());
                    } catch (NumberFormatException e2) {
                        ui.printNoteErrorMessage(INVALID_NOTE_INDEX_MESSAGE);
                    }
                }
            } else {
                throw new InvalidNoteException(INVALID_NOTE_INDEX_MESSAGE);
            }
        }
    }

    public void selectSearchMethod(String userInput) throws InvalidNoteException {
        while (!userInput.equals("")) {
            if (!userInput.equals("keyword") && !userInput.equals("index")) {
                ui.printNoteErrorMessage(INVALID_NOTE_COMMAND_MESSAGE);
                userInput = ui.readUserInput();
            } else {
                break;
            }
        }
        if (userInput.equals("keyword")) {
            keywordSearch();
        } else if (userInput.equals("index")) {
            indexSearch();
        } else {
            throw new InvalidNoteException(INVALID_NOTE_COMMAND_MESSAGE);
        }
    }

    public void selectSearchMethod(String[] userInputInArray)
            throws InvalidNoteException, NumberFormatException {
        if (userInputInArray[1].equals("keyword")) {
            if (userInputInArray.length == 2) {
                keywordSearch();
            } else {
                keywordSearch(userInputInArray[2]);
            }
        } else if (userInputInArray[1].contains("index")) {
            if (userInputInArray.length == 2) {
                indexSearch();
            } else {
                indexSearch(userInputInArray[2]);
            }
        } else {
            throw new InvalidNoteException(INVALID_NOTE_SEARCH_MESSAGE);
        }
    }

    public void keywordSearch(String userInput) {
        ui.printSelectedNote(this.searchNoteUsingTitle(userInput, this));
    }

    public void keywordSearch() {
        ui.printNoteSearchKeyWordInstructions();
        String keywords = ui.readUserInput();
        ui.printSelectedNote(this.searchNoteUsingTitle(keywords, this));
    }

    public void indexSearch(String userInput) throws NumberFormatException {
        ui.printNoteSearchSceneIndexInstructions();
        int sceneIndex = Integer.parseInt(userInput);
        ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
    }

    public void indexSearch() throws NumberFormatException {
        ui.printNoteSearchSceneIndexInstructions();
        int sceneIndex = Integer.parseInt(ui.readUserInput());
        ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
    }

    public void openNoteDirectly(String index) throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteOpenInstructions();
        // here the index is not scene index, it is the index in the list
        int inputOrderIndex = Integer.parseInt(index);
        if (inputOrderIndex > notes.size()) {
            throw new IndexOutOfBoundsException(INVALID_NOTE_INDEX_MESSAGE);
        }
        ui.printExistingNotes(this, inputOrderIndex);
    }

    public void openNoteDirectly() throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteOpenInstructions();
        //here the index is not scene index, it is the index in the list
        int inputOrderIndex = Integer.parseInt(ui.readUserInput());
        if (inputOrderIndex > notes.size()) {
            throw new IndexOutOfBoundsException(INVALID_NOTE_INDEX_MESSAGE);
        }
        ui.printExistingNotes(this, inputOrderIndex);
    }

    public void deleteNoteProcess() throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteListStarter();
        ui.printAllNotes(this);
        ui.printNoteDeleteInstructions();
        String userInput = ui.readUserInput();
        if (userInput.equals("all")) {
            deleteAllNotes();
        } else {
            int deletedNoteIndex = Integer.parseInt(userInput) - 1;
            this.deleteNote(deletedNoteIndex);
        }
    }
}
