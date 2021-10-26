package note;

import java.util.ArrayList;

import storage.Storage;
import scene.SceneList;
import ui.Ui;


public class NoteList {
    private ArrayList<Note> notes;

    private Ui ui;
    private static int defaultTitleCounter = 1;

    public NoteList(Ui ui) {
        this.ui = ui;
        //storage = new Storage();
        notes = new ArrayList<>();
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
        String[] words = stringSpliter(keyword);
        ArrayList<Note> result = new ArrayList<>();
        for (int i = 0; i < notes.getSize(); i++) {
            boolean titleNotContains = false;
            for (int j = 0; j < words.length; j++) {
                if (!notes.getIndexNote(i).getNoteTitle().contains(words[j])) {
                    titleNotContains = true;
                }
            }
            if (titleNotContains == false) {
                result.add(notes.getIndexNote(i));
            }
        }
        return result;
    }

    public static String[] stringSpliter(String keywords) {
        String[] words = keywords.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toUpperCase();
        }
        return words;
    }

    public Note getIndexNote(int index) {
        return notes.get(index);
    }

    public void createNote(Note newNote, int inputSceneIndex) {
        notes.add(newNote);
        Storage.saveNote(this,inputSceneIndex);
        ui.printSaveNoteMessage();
    }

    public void createNoteFromFile(Note newNote, int inputSceneIndex) {
        notes.add(newNote);
        Storage.saveNote(this,inputSceneIndex);
    }

    public void deleteNote(int index) {
        Note noteToDelete = notes.get(index);
        notes.remove(index);
        Storage.saveNote(this,noteToDelete.getNoteSceneIndex());
        ui.printDeleteNoteMessage();
    }

    public void processNote(SceneList sceneList, String userChoice) {
        if (userChoice.equals("create")) {
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
            createNote(newNote, (sceneList.getCurrentSceneIndex()));
        } else if (userChoice.equals("open")) {
            ui.printNoteTitle(this);
            ui.printNoteOpenSearchInstructions();
            String userInput = ui.readUserInput();
            if (userInput.contains("search")) {
                ui.printNoteSearchInstructions();
                userInput = ui.readUserInput();
                if (userInput.equals("keyword")) {
                    ui.printNoteSearchKeyWordInstructions();
                    String keywords = ui.readUserInput();
                    System.out.println(keywords);
                    ui.printSelectedNote(this.searchNoteUsingTitle(keywords, this));
                } else {
                    ui.printNoteSearchSceneIndexInstructions();
                    int sceneIndex = Integer.parseInt(ui.readUserInput());
                    ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
                }
            } else {
                ui.printNoteOpenInstructions();
                //here the index is not scene index, it is the index in the list
                int inputOrderIndex = Integer.parseInt(ui.readUserInput());
                ui.printExistingNotes(this, inputOrderIndex);
            }
        } else {
            ui.printNoteList();
            ui.printAllNotes(this);
            ui.printNoteDeleteInstructions();
            int deletedNoteIndex = Integer.parseInt(ui.readUserInput()) - 1;
            deleteNote(deletedNoteIndex);
        }
    }
}
