package clue;

import clue.firstscene.FatherMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClueTest {

    @Test
    public void toString_InstantiateClue_printDefaultMessages() {
        String expectedResult = "------------------------------------------------\n"
                +  "                     Map\n"
                + "\nFather's           DNA Testing\n"
                + "company              Agency\n"
                + "   |                    |\n"
                + "   |                    |\n"
                + " 20|                  20|\n"
                + "min|                 min|\n"
                + "   |                    |\n"
                + "   |                    |\n"
                + "Vegetable ____________ Home ____________ Seafood ___________________ Insurance\n"
                + "  Store      5 min      |      5 min      Store         25 min        Company\n"
                + "                        |\n"
                + "                      25|\n"
                + "                     min|\n"
                + "                        |\n"
                + "                        |\n"
                + "                   Money Lender\n";
        Clue fatherMap = new FatherMap();
        String result = fatherMap.toString();
    }
}
