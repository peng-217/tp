package scene;

import clue.Clue;
import clue.firstscene.FatherInsurance;
import clue.firstscene.FatherPhoneCall;
import clue.firstscene.FatherTextMessage;
import narrative.FirstNarrative;
import narrative.SecondNarrative;
import org.junit.jupiter.api.Test;
import search.Search;
import seedu.duke.Ui;
import suspect.Suspect;
import suspect.SuspectList;

import java.io.FileNotFoundException;

public class SceneListTest {

    @Test
    public void scenesTest() throws FileNotFoundException {
        Ui ui = new Ui();
        SuspectList suspects = new SuspectList(ui);
        suspects.addSuspect("Jerry", new Suspect());
        suspects.addSuspect("Tom", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherMap = new FatherTextMessage();
        suspects.addClueForSuspect("Tom", fatherInsurance);
        suspects.addClueForSuspect("Tom", fatherMap);

        Clue fatherPhoneCall = new FatherPhoneCall();
        suspects.addClueForSuspect("Jerry", fatherPhoneCall);

        Search searchFirstScene = new Search(suspects);
        Scene firstScene = new Scene(new FirstNarrative(), searchFirstScene);

        Search searchSecondScene = new Search(suspects);
        Scene secondScene = new Scene(new SecondNarrative(), searchSecondScene);

        SceneList sceneList = new SceneList();
        sceneList.addScene(firstScene);
        sceneList.addScene(secondScene);

        sceneList.getCurrentScene().runScene();
        sceneList.nextScene();
        System.out.println("------------------------------------------");
        sceneList.getCurrentScene().runScene();
    }
}
