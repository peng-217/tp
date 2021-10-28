//@@author peng-217

package note;

import java.util.ArrayList;

import storage.Storage;
import scene.SceneList;
import ui.Ui;

public class NoteList {
    private ArrayList<Note> notes;

    private final Ui ui;
    private static int defaultTitleCounter = 1;

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
        Note noteToDelete = notes.get(index);
        notes.remove(index);
        Storage.saveNote(this);
        ui.printDeleteNoteMessage();
    }


    public void deleteAllNotes() {
        notes.removeAll(notes);
        Storage.saveNote(this);
        ui.printDeleteAllNoteMessage();
    }

    public void processNote(SceneList sceneList, String userChoice) {
        if (userChoice.equals("create")) {
            createNoteProcess(sceneList);
        } else if (userChoice.equals("open")) {
            openNoteProcess();
        } else {
            deleteNoteProcess();
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

    public void openNoteProcess() {
        boolean checkExistance = ui.printOpenNoteMessage(this);
        if (checkExistance) {
            String userInput = ui.readUserInput();
            if (userInput.contains("search")) {
                ui.printNoteSearchInstructions();
                userInput = ui.readUserInput();
                selectSearchMethod(userInput);
            } else {
                openNoteDirectly();
            }
        }
    }

    public void selectSearchMethod(String userInput) {
        if (userInput.contains("keyword")) {
            keywordSearch();
        } else {
            indexSearch();
        }
    }

    public void keywordSearch() {
        ui.printNoteSearchKeyWordInstructions();
        String keywords = ui.readUserInput();
        //System.out.println(keywords);
        ui.printSelectedNote(this.searchNoteUsingTitle(keywords, this));
    }

    public void indexSearch() {
        ui.printNoteSearchSceneIndexInstructions();
        int sceneIndex = Integer.parseInt(ui.readUserInput());
        ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
    }

    public void openNoteDirectly() {
        ui.printNoteOpenInstructions();
        //here the index is not scene index, it is the index in the list
        int inputOrderIndex = Integer.parseInt(ui.readUserInput());
        ui.printExistingNotes(this, inputOrderIndex);
    }

    public void deleteNoteProcess() {
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
