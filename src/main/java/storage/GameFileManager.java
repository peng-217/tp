package storage;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;

public class GameFileManager extends EncryptedFile {

    private static FileDecoder decoder;
    private static FileEncoder encoder;

    public GameFileManager(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        super(fileName);
        decoder = new FileDecoder(fileName);
        encoder = new FileEncoder(fileName);
    }

    public void writeFile(String lines) throws DukeFileNotFoundException {
        encoder.encodeFile(lines);
    }

    public String readFile() throws DukeCorruptedFileException, DukeFileNotFoundException {
        return decoder.decodeFile();
    }
}

