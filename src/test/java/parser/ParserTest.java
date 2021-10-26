package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import clue.CheckedClueTrackerBuilder;
import exceptions.InvalidInputException;
import exceptions.MissingSceneFileException;
import investigation.Investigation;
import org.junit.jupiter.api.Test;
import scene.Scene;
import scene.SceneList;
import scene.SceneListBuilder;
import storage.GameDataFileDecoder;
import storage.GameDataFileManager;
import suspect.SuspectList;
import ui.Ui;

public class ParserTest {

    // test failed invocation of display method of an Narrative object
    @Test
    public void parserTest() throws MissingSceneFileException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        GameDataFileDecoder dataFile = new GameDataFileDecoder(ui, new GameDataFileManager("GameData.txt"));
        SceneList sceneList = SceneListBuilder.buildSceneList(ui, dataFile);

        sceneList.updateSceneNumber();
        String suspectFather = parser.getSuspectNameFromIndex(sceneList.getCurrentScene(), 1);
        assertEquals("Father", suspectFather);

        sceneList.updateSceneNumber();
        String suspectKevin = parser.getSuspectNameFromIndex(sceneList.getCurrentScene(), 2);
        assertEquals("Kevin", suspectKevin);

        sceneList.updateSceneNumber();
        String suspectZack = parser.getSuspectNameFromIndex(sceneList.getCurrentScene(), 5);
        assertEquals("Zack", suspectZack);

        assertThrows(InvalidInputException.class, () -> parser.getCommandFromUser(""));
    }
}
