package storage;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;

public class GameDataFileDecoder extends GameFileManager {
    private static final int MAX_SCENE_NUMBER = 3;
    private static final String FACTORY_SETTING = "The Great Detective Data File\nCurrentSceneIndex: ";

    public GameDataFileDecoder(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        super(fileName);
    }

    public void setCurrentSceneIndex(int index) throws DukeFileNotFoundException {
        this.writeFile(FACTORY_SETTING + index);

    }

    public int getCurrentSceneIndex() throws DukeFileNotFoundException {
        int currentSceneIndex = 0;
        try {
            if (isValidFile()) {
                String lines = this.readFile();
                currentSceneIndex = Integer.parseInt(lines.substring(FACTORY_SETTING.length()));
                if (currentSceneIndex <= MAX_SCENE_NUMBER) {
                    return currentSceneIndex;
                }
            }
        } catch (DukeCorruptedFileException e) {
            System.out.println(e.getMessage());
            setCurrentSceneIndex(0);
        }
        setCurrentSceneIndex(0);
        return 0;
    }

    public boolean isValidFile() throws DukeCorruptedFileException, DukeFileNotFoundException {
        String lines = this.readFile();
        return lines.contains("The Great Detective Data File");
    }

}
