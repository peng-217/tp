package narrative;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Narrative {

    String filePath;
    String fileName;

    public Narrative() {
        filePath = "src/main/resources/";
    }

    public String getNarrative() throws FileNotFoundException {
        File file = new File(filePath + fileName);
        //InputStream file = getClass().getResourceAsStream(filePath + fileName);
        //assert file != null;
        Scanner in = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (in.hasNext()) {
            content.append("\n").append(in.nextLine());
        }
        return content.toString();
    }

    public void displayNarrative() throws FileNotFoundException {
        System.out.println(this.getNarrative());
    }
}
