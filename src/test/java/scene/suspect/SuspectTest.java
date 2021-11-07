package scene.suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import scene.clue.Clue;
import scene.clue.firstscene.FatherInsurance;
import scene.clue.firstscene.FatherMap;
import scene.clue.firstscene.FatherTextMessage;
import org.junit.jupiter.api.Test;
import scene.clue.secondscene.FatherDiary;
import scene.clue.secondscene.KevinBro;

import java.util.ArrayList;

public class SuspectTest {

    @Test
    public void suspectWithTwoClues() {
        Suspect tom = new Suspect();
        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();

        tom.addClue(fatherInsurance);
        tom.addClue(fatherTextMessage);
        assertEquals(2, tom.getNumClues());

        ArrayList<Clue> allClues = new ArrayList<>();
        allClues.add(fatherInsurance);
        allClues.add(fatherTextMessage);
        assertEquals(allClues, tom.getClues());
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

    @Test
    public void markClue_ClueNotFound() {
        Suspect father = new Suspect();
        Clue fatherInsurance = new FatherInsurance();
        father.addClue(fatherInsurance);

        Clue kevinBro = new KevinBro();
        father.setChecked(kevinBro);
    }

    @Test
    public void getCheckedCluesTest_TwoClues() {
        Suspect father = new Suspect();
        Clue fatherInsurance = new FatherInsurance();
        Clue fatherDiary = new FatherDiary();

        father.addClue(fatherInsurance);
        father.addClue(fatherDiary);

        father.setChecked(fatherDiary);

        ArrayList<Clue> correctClues = new ArrayList<>();
        correctClues.add(fatherDiary);
        assertEquals(correctClues, father.getCheckedClues());
    }

}
