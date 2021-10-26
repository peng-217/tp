package narrative;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class Narrative {

    String filePath;
    String fileName;

    public Narrative() {
        filePath = "";
    }

    public Narrative(String fileName) {
        this.fileName = fileName;
        this.filePath = "";
    }

    public String getNarrative() throws FileNotFoundException {
        //File file = new File(filePath + fileName);
        //System.out.println(filePath + fileName);
        InputStream file = getClass().getResourceAsStream(filePath + fileName);
        if (file == null) {
            throw new FileNotFoundException();
        }
        Scanner in = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (in.hasNext()) {
            content.append("\n").append(in.nextLine());
        }
        return content.toString();
    }

    public void displayNarrative() throws FileNotFoundException {
        if (fileName.toLowerCase(Locale.ROOT).contains("scene")) {
            displaySceneNarrative();
        }
        System.out.println(this.getNarrative());
    }

    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displaySceneNarrative() throws FileNotFoundException {
        String content = this.getNarrative();
        while (content.contains("\n")) {
            clearConsole();
            for (int i = 0; i < 4 && content.contains("\n"); i ++) {
                System.out.print(content.substring(0, 1 + content.indexOf("\n")));
                content = content.substring(1 + content.indexOf("\n"));
            }
            if (content.contains("\n")) {
                pressEnterKeyToContinue();
            }
        }
        clearConsole();
        System.out.print(content + "\n");
        pressEnterKeyToContinue();
        clearConsole();
    }
    public void pressEnterKeyToContinue() {
        System.out.println("\nPress Enter key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}