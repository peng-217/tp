package environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;


public class SuspectTest {

    @Test
    public void suspectWithTwoClues() {
       Suspect tom = new Suspect(3);
       tom.addClue("aaa");
       tom.addClue("bbb");
       assertEquals(Arrays.asList("aaa", "bbb"), tom.getClues());
    }

    @Test
    public void listOfSuspects() {
        HashMap<String, Suspect> suspects = new HashMap<>();
        suspects.put("Jerry", new Suspect(4));
        suspects.put("Tom", new Suspect(3));

        assertEquals(3, suspects.get("Tom").totalClues);
        assertEquals(4, suspects.get("Jerry").totalClues);
    }

}
