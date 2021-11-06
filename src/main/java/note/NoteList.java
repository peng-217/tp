//@@author peng-217

package note;

import java.util.ArrayList;

import exception.DukeCorruptedFileException;
import exception.NoteCorruptedFileException;
import exceptions.InvalidNoteException;
import parser.Parser;
import storage.GameNoteFileManager;
import scene.SceneList;
import ui.Ui;

public class NoteList {
    private final ArrayList<Note> notes;
    private final Ui ui;
    private static GameNoteFileManager noteFile;
    private static int defaultTitleCounter = 1;
    private static final String INVALID_NOTE_INDEX_MESSAGE = "The index you entered is not valid! "
            + "Please check again.";
    private static final String INVALID_NOTE_COMMAND_MESSAGE = "The command you entered is not valid! "
            + "Please check again.";
    private static final String INVALID_NOTE_SEARCH_MESSAGE = "Please input a valid search choice!";
    private static final String NOTE_CORRUPTED_MESSAGE = "The corrupted file has been removed! "
            + "The new file has been created!";


    public NoteList(Ui ui) {
        this.ui = ui;
        //storage = new Storage();
        noteFile = new GameNoteFileManager();
        notes = new ArrayList<>();
        try {
            noteFile.openNoteFromFile(this);
        } catch (NoteCorruptedFileException e) {
            noteFile.forceClearNote();
            ui.printNoteErrorMessage(NOTE_CORRUPTED_MESSAGE);
        }
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
        noteFile.saveNote(this);
        ui.printSaveNoteMessage();
    }

    public void createNoteFromFile(Note newNote, int inputSceneIndex) {
        notes.add(newNote);
        noteFile.saveNote(this);
    }

    public void deleteNote(int index) {
        notes.remove(index);
        noteFile.saveNote(this);
        ui.printDeleteNoteMessage();
    }


    public void deleteAllNotes() {
        notes.removeAll(notes);
        noteFile.forceClearNote();
        ui.printDeleteAllNoteMessage();
    }

    public void processNote(SceneList sceneList, String userChoice) throws InvalidNoteException {
        if (!userChoice.equals("/quit")) {
            switch (userChoice) {
            case "1":
                createNoteProcess(sceneList);
                break;
            case "2":
                openNoteProcess();
                break;
            case "3":
                deleteNoteProcess();
                break;
            default:
                throw new InvalidNoteException(INVALID_NOTE_INDEX_MESSAGE);
            }
        } else {
            ui.printQuitNoteProcess();
        }
    }

    public void createNoteProcess(SceneList sceneList) {
        boolean quitNote = false;
        boolean validNoteTitle = true;
        boolean validNoteContent = true;
        ui.printNoteTitleInstructions();
        String transientTitle = ui.readUserInput();
        String noteTitle = "";
        if ((!transientTitle.equals("") && !transientTitle.equals("\r")) && !transientTitle.equals("/quit")
            && !transientTitle.equals("End of this note.") && !transientTitle.startsWith("scene")) {
            noteTitle = transientTitle;
        } else if (transientTitle.equals("/quit")) {
            quitNote = true;
            ui.printQuitNoteProcess();
        } else if (transientTitle.equals("End of this note.") || transientTitle.startsWith("scene")) {
            validNoteTitle = false;
            ui.printInvalidNoteTitle();
        } else if (transientTitle.equals("") || transientTitle.equals("\r")) {
            noteTitle = "DEFAULT(" + (defaultTitleCounter++) + ")";
        }
        if (!quitNote && validNoteTitle) {
            ui.printNoteTextInstructions();
            String noteContent = ui.readUserInput();
            if (!noteContent.contains("/quit") && !noteContent.equals("End of this note.")
                    && !noteContent.startsWith("scene")) {
                Note newNote = new Note(noteContent, noteTitle, sceneList.getCurrentSceneIndex());
                createNote(newNote);
            } else if (noteContent.equals("End of this note.") || noteContent.startsWith("scene")) {
                ui.printInvalidNoteContent();
            } else {
                ui.printQuitNoteProcess();
            }
        }
    }

    public void openNoteProcess() throws InvalidNoteException {
        boolean quitNote = false;
        boolean checkExistence = ui.printOpenNoteMessage(this);
        if (checkExistence) {
            String userInput = ui.readUserInput();
            if (userInput.equals("/quit")) {
                quitNote = true;
                ui.printQuitNoteProcess();
            }
            if (!quitNote) {
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
                } else if (userInputInArray[0].equals("/quit")) {
                    ui.printQuitNoteProcess();
                } else {
                    throw new InvalidNoteException(INVALID_NOTE_INDEX_MESSAGE);
                }
            }
        }
    }

    public void selectSearchMethod(String userInput) throws InvalidNoteException {
        boolean quitNote = false;
        if (userInput.equals("/quit")) {
            quitNote = true;
            ui.printQuitNoteProcess();
        }
        while (!userInput.equals("") && quitNote == false) {
            if (!userInput.equals("keyword") && !userInput.equals("index")) {
                ui.printNoteErrorMessage(INVALID_NOTE_COMMAND_MESSAGE);
                userInput = ui.readUserInput();
            } else {
                break;
            }
        }
        if (quitNote == false) {
            if (userInput.equals("keyword")) {
                keywordSearch();
            } else if (userInput.equals("index")) {
                indexSearch();
            } else if (userInput.equals("/quit")) {
                ui.printQuitNoteProcess();
            } else {
                throw new InvalidNoteException(INVALID_NOTE_COMMAND_MESSAGE);
            }
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
        if (!userInput.equals("/quit")) {
            ui.printSelectedNote(this.searchNoteUsingTitle(userInput, this));
        } else {
            ui.printQuitNoteProcess();
        }
    }

    public void keywordSearch() {
        ui.printNoteSearchKeyWordInstructions();
        String keywords = ui.readUserInput();
        if (!keywords.equals("/quit")) {
            ui.printSelectedNote(this.searchNoteUsingTitle(keywords, this));
        } else {
            ui.printQuitNoteProcess();
        }
    }

    public void indexSearch(String userInput) throws NumberFormatException {
        ui.printNoteSearchSceneIndexInstructions();
        if (!userInput.equals("/quit")) {
            int sceneIndex = Integer.parseInt(userInput);
            ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
        } else {
            ui.printQuitNoteProcess();
        }
    }

    public void indexSearch() throws NumberFormatException {
        ui.printNoteSearchSceneIndexInstructions();
        String userInput = ui.readUserInput();
        if (!userInput.equals("/quit")) {
            int sceneIndex = Integer.parseInt(userInput);
            ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
        } else {
            ui.printQuitNoteProcess();
        }
    }

    public void openNoteDirectly(String index) throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteOpenInstructions();
        // here the index is not scene index, it is the index in the list
        if (!index.equals("/quit")) {
            int inputOrderIndex = Integer.parseInt(index);
            if (inputOrderIndex > notes.size()) {
                throw new IndexOutOfBoundsException(INVALID_NOTE_INDEX_MESSAGE);
            }
            ui.printExistingNotes(this, inputOrderIndex);
        } else {
            ui.printQuitNoteProcess();
        }
    }

    public void openNoteDirectly() throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteOpenInstructions();
        String userInput = ui.readUserInput();
        //here the index is not scene index, it is the index in the list
        if (!userInput.equals("/quit")) {
            int inputOrderIndex = Integer.parseInt(userInput);
            if (inputOrderIndex > notes.size()) {
                throw new IndexOutOfBoundsException(INVALID_NOTE_INDEX_MESSAGE);
            }
            ui.printExistingNotes(this, inputOrderIndex);
        } else {
            ui.printQuitNoteProcess();
        }
    }

    public void deleteNoteProcess() throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteListStarter();
        ui.printAllNotes(this);
        ui.printNoteDeleteInstructions();
        String userInput = ui.readUserInput();
        if (userInput.equals("all")) {
            deleteAllNotes();
        } else if (userInput.equals("/quit")) {
            ui.printQuitNoteProcess();
        } else {
            int deletedNoteIndex = Integer.parseInt(userInput) - 1;
            this.deleteNote(deletedNoteIndex);
        }
    }
}
