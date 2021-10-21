package storage;

import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameDataFileDecoder {

    GameDataFileManager dataFile;
    ArrayList<String> lines;
    Ui ui;
    int currentSceneIndex;

    public GameDataFileDecoder(Ui ui, GameDataFileManager dataFile) {
        this.dataFile = dataFile;
        this.ui = ui;
        try {
            dataFile.checkPath();
            this.lines = dataFile.readFile();
            if (this.lines.size() == 0 || !this.lines.get(0).equals("The Great Detective Data File")) {
                resetFile(0);
                this.lines = dataFile.readFile();
                assert lines.size() != 0;
            }
        } catch (IOException e) {
            ui.printFileErrorMessage();
        }
    }

    public int getCurrentSceneIndex() {
        this.currentSceneIndex = Integer.parseInt(this.lines.get(1).substring(19));
        return currentSceneIndex;
    }

    public void resetFile(int index) {
        ArrayList<String> factorySetting = new ArrayList<>();
        factorySetting.add("The Great Detective Data File");
        factorySetting.add("CurrentSceneIndex: " + index);
        try {
            dataFile.rewriteFile(factorySetting);
        } catch (IOException e) {
            ui.printFileErrorMessage();
        }
    }
}
