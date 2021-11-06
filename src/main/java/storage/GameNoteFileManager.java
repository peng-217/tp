//@@author peng-217

package storage;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import exception.NoteCorruptedFileException;
import note.NoteList;
import note.Note;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GameNoteFileManager {

    private static final String NOTE_CORRUPTED_MESSAGE = "The note data file is corrupted!"
            + " A new note data file will be created. ";
    private static GameFileManager noteFile;

    public GameNoteFileManager() {
        try {
            new File("data").mkdir();
            new File("notes.txt").createNewFile();
            noteFile = new GameFileManager("notes.txt");
        } catch (DukeFileNotFoundException | DukeCorruptedFileException | IOException e) {
            forceClearNote();
        }
    }

    public void saveNote(NoteList notes) {
        File saveDirectory =  new File("data");
        saveDirectory.mkdir();
        try {
            new File(saveDirectory,"notes.txt").createNewFile();
            for (int i = 0; i < notes.getSize(); i++) {
                String titleToWrite = notes.getIndexNote(i).getNoteTitle();
                String contentToWrite = notes.getIndexNote(i).getNoteContent();
//                noteWriter.write("scene " + notes.getIndexNote(i).getNoteSceneIndex());
//                noteWriter.write("\n");
//                noteWriter.write(titleToWrite);
//                noteWriter.write("\n");
//                noteWriter.write(contentToWrite);
//                noteWriter.write("\n");
//                noteWriter.write("End of this note.");
                StringBuilder lines = new StringBuilder();
                lines.append("scene ").append(notes.getIndexNote(i).getNoteSceneIndex()).append("\n");
                lines.append(titleToWrite).append("\n");
                lines.append(contentToWrite).append("\n");
                lines.append("End of this note.").append("\n");
                noteFile.writeFile(lines.toString());
            }
        } catch (IOException | DukeFileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void openNoteFromFile(NoteList notes) throws NoteCorruptedFileException {
        new File("data").mkdir();
        try {
            new File("notes.txt").createNewFile();
            GameFileScanner lines = new GameFileScanner(noteFile);

            while (lines.hasNext()) {
                //String nextLine = scanNote.nextLine();
                String nextLine = lines.nextLine();

                if (!nextLine.startsWith("scene")) {
                    System.out.println(nextLine);
                    throw new NoteCorruptedFileException(NOTE_CORRUPTED_MESSAGE);
                }
                int sceneIndex = Integer.parseInt(nextLine.substring(6));
                String title = lines.nextLine();
                String content = new String("");
                String contentPart = lines.nextLine();
                //int emptyLineCounter = 0;
                boolean missEndFlag = false;
                while (!contentPart.equals("End of this note.")) {
                    content += contentPart;
                    contentPart = lines.nextLine();
                    if (contentPart.equals("")) {
                        missEndFlag = true;
                        break;
                    }
                }
                if (missEndFlag) {
                    throw new NoteCorruptedFileException(NOTE_CORRUPTED_MESSAGE);
                }
                Note savedNote = new Note(content, title, sceneIndex);
                notes.createNoteFromFile(savedNote, sceneIndex);
            }
        } catch (IOException | DukeFileNotFoundException | DukeCorruptedFileException e) {
            throw new NoteCorruptedFileException("cannot open note from file");
        }
    }



    public void forceClearNote() {
        try {
            new File("data").mkdir();
            new File("notes.txt").createNewFile();
            noteFile.writeFile("");
        } catch (DukeFileNotFoundException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("The note data file is corrputed! A new note data file will be created. ");
//        new File("data").mkdir();
//        new File("notes.txt").createNewFile();
//        File saveNote = new File(saveDirectory,"notes.txt");
//        try {
//            FileWriter noteWriter = new FileWriter(saveNote);
//            noteWriter.write("");
//            noteWriter.flush();
//            noteWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("The corrupted file has been removed! The new file has been created!");
//        }
    }
}
