package narrative;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Narrative {

    String filePath;
    String fileName;

    public Narrative() {
        filePath = "docs/";
    }

    public void displayNarrative() throws FileNotFoundException {
        File file = new File(filePath + fileName);
        Scanner in = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (in.hasNext()) {
            content.append("\n").append(in.nextLine());
        }
        System.out.println(content);
    }
}
