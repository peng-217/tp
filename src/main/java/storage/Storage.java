//@@author peng-217

package storage;

import exception.NoteCorruptedFileException;
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
    private static final String NOTE_CORRUPTED_MESSAGE = "The note data file is corrupted!"
            + " A new note data file will be created. ";
    public Storage() {

    }

    public static void saveNote(NoteList notes) {
        File saveDirectory =  new File("data");
        saveDirectory.mkdir();
        File saveNote = new File(saveDirectory,"notes.txt");
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

    public static void openNoteFromFile(NoteList notes) throws NoteCorruptedFileException {
        File saveDirectory = new File("data");
        saveDirectory.mkdir();
        File saveNote = new File(saveDirectory,"notes.txt");
        if (saveNote.exists()) {
            try {
                Scanner scanNote = new Scanner(saveNote);
                while (scanNote.hasNext()) {
                    String nextLine = scanNote.nextLine();
                    if (!nextLine.startsWith("scene")) {
                        throw new NoteCorruptedFileException(NOTE_CORRUPTED_MESSAGE);
                    }
                    int sceneIndex = Integer.parseInt(nextLine.substring(6));
                    String title = scanNote.nextLine();
                    String content = new String("");
                    String contentPart = scanNote.nextLine();
                    //int emptyLineCounter = 0;
                    boolean missEndFlag = false;
                    while (!contentPart.equals("End of this note.")) {
                        content += contentPart;
                        contentPart = scanNote.nextLine();
                        if (contentPart.equals("")) {
                            missEndFlag = true;
                            break;
                        }
                    }
                    if (missEndFlag == true) {
                        throw new NoteCorruptedFileException(NOTE_CORRUPTED_MESSAGE);
                    }
                    Note savedNote = new Note(content, title, sceneIndex);
                    notes.createNoteFromFile(savedNote,sceneIndex);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static void forceClearNote() {
        File saveDirectory = new File("data");
        saveDirectory.mkdir();
        File saveNote = new File(saveDirectory,"notes.txt");
        try {
            if (!saveNote.exists()) {
                saveNote.createNewFile();
            }
            FileWriter noteWriter = new FileWriter(saveNote);
            noteWriter.write("");
            noteWriter.flush();
            noteWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The corrupted file has been removed! The new file has been created!");
        }
    }
}
