package narrative;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class Narrative {

    String filePath;
    String fileName;

    public Narrative() {
        filePath = "src/main/resources/";
    }

    public Narrative(String fileName) {
        this.fileName = fileName;
        this.filePath = "src/main/resources/";
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
        if(fileName.toLowerCase(Locale.ROOT).contains("scene")) {
            displaySceneNarrative();
        }
        System.out.println(this.getNarrative());
    }
    public void displaySceneNarrative() throws FileNotFoundException {
        String content = this.getNarrative();
        int index = 0;
        while(content.contains("\n")) {
            for(int i=0;i<4 && content.contains("\n");i++) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.print(content.substring(0, 1+content.indexOf("\n")));
                content = content.substring(1+content.indexOf("\n"));
            }
            pressEnterKeyToContinue();
        }
        System.out.print(content);
    }
    public void pressEnterKeyToContinue()
    {
        System.out.println("\nPress Enter key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}