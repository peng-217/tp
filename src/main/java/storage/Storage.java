//@@author peng-217
package storage;

import scene.Scene;
import scene.SceneList;
import note.NoteList;
import note.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {

    private static final int NUMBER_OF_BLANK_LINE = 1;

    public Storage() {

    }

    public static void saveNote(NoteList notes) {
        File saveDirection =  new File("data");
        saveDirection.mkdir();
        File saveNote = new File(saveDirection,"notes.txt");
        try {
            saveNote.createNewFile();
            FileWriter noteWriter = new FileWriter(saveNote);
            for (int i = 0; i < notes.getSize(); i++) {

                String titleToWrite = notes.getIndexNote(i).getNoteTitle();
                String contentToWrite = notes.getIndexNote(i).getNoteContent();
                noteWriter.write("scene " + notes.getIndexNote(i).getNoteSceneIndex());
                noteWriter.write("\n");
                noteWriter.write(titleToWrite);
                noteWriter.write("\n");
                noteWriter.write(contentToWrite);
                noteWriter.write("\n");
                noteWriter.write("End of this note.");
                for (int j = 0; j < NUMBER_OF_BLANK_LINE; j++) {
                    noteWriter.write("\n");
                }
            }
            noteWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openNoteFromFile(NoteList notes) {
        File saveDirection = new File("data");
        saveDirection.mkdir();
        File saveNote = new File(saveDirection,"notes.txt");
        if (saveNote.exists()) {
            try {
                Scanner scanNote = new Scanner(saveNote);
                while (scanNote.hasNext()) {
                    int sceneIndex = Integer.parseInt(scanNote.nextLine().substring(6));
                    String title = scanNote.nextLine();
                    String content = new String("");
                    String contentPart = scanNote.nextLine();
                    while (!contentPart.equals("End of this note.")) {
                        content += contentPart;
                        contentPart = scanNote.nextLine();
                    }
                    Note savedNote = new Note(content, title, sceneIndex);
                    notes.createNoteFromFile(savedNote,sceneIndex);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
