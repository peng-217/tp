package note;

import java.util.ArrayList;

import Storage.Storage;
import scene.Scene;
import scene.SceneList;
import ui.Ui;
import parser.Parser;

public class NoteList {
    private ArrayList<Note> notes;
    //private Storage storage;
    private Ui ui;

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
        for(int i = 0; i < notes.getSize(); i++) {
            if(notes.getIndexNote(i).getNoteSceneIndex() == searchSceneIndex) {
                result.add(notes.getIndexNote(i));
            }
        }
        return result;
    }

    public ArrayList<Note> searchNoteUsingTitle(String keyword,NoteList notes) {
        String[] words = stringSpliter(keyword);
        ArrayList<Note> result = new ArrayList<>();
        boolean titleNotContains = false;
        //String[] words = keywords.split("\\s+");
        for(int i = 0; i < notes.getSize(); i++) {
            for(int j = 0; j < words.length; j++) {
                if(!notes.getIndexNote(i).getNoteTitle().contains(words[j])) {
                    titleNotContains = true;
                }
            }
            if(titleNotContains == false) {
                result.add(notes.getIndexNote(i));
            }
        }
        return result;
    }

    public static String[] stringSpliter(String keywords) {
        String[] words = keywords.split(" ");
        for(int i = 0; i < words.length; i++){
            words[i] = words[i].toUpperCase();
        }
        return words;
    }

    public Note getIndexNote(int Index) {
        return notes.get(Index);
    }

    public void createNote(Note newNote) {
        notes.add(newNote);
        ui.printSaveNoteMessage();
        Storage.saveNote(this);
    }


}
