package scene.suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import scene.clue.Clue;
import scene.clue.firstscene.FatherInsurance;
import scene.clue.firstscene.FatherMap;
import scene.clue.firstscene.FatherTextMessage;
import org.junit.jupiter.api.Test;

public class SuspectTest {

    @Test
    public void suspectWithTwoClues() {
        Suspect tom = new Suspect();
        tom.addClue(new FatherInsurance());
        tom.addClue(new FatherTextMessage());
        assertEquals(2, tom.getClues().size());
    }

    @Test
    public void markCluesAsUnavailable() {
        Suspect tom = new Suspect();
        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();
        Clue fatherMap = new FatherMap();
        tom.addClue(fatherInsurance);
        tom.addClue(fatherTextMessage);
        tom.addClue(fatherMap);

        tom.setChecked(fatherInsurance);
        assertEquals(2, tom.getAvailableClues().size());

        tom.setChecked(fatherTextMessage);
        assertEquals(1, tom.getAvailableClues().size());

        tom.setChecked(fatherMap);
        assertEquals(0, tom.getAvailableClues().size());
    }

}
