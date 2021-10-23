// @@ author peng-217
package note;

import scene.Scene;
import scene.SceneList;
import ui.Ui;
import parser.Parser;

public class Note {
    private int noteSceneIndex;
    private String noteContent;
    private String noteTitle;


    public Note(String inputContent,String inputTitle,int inputIndex) {
        this.noteContent = inputContent;
        this.noteSceneIndex = inputIndex;
        this.noteTitle = inputTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public int getNoteSceneIndex() {
        return noteSceneIndex;
    }

    public String getNoteTitle() { //title must all in uppercase
        return noteTitle;
    }


}
// @@ author peng-217