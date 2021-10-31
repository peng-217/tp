package scene.narrative;

import investigation.Investigation;

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
        } else {
            System.out.println(this.getNarrative());
        }
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

    private void displaySceneNarrative() throws FileNotFoundException {
        String content = this.getNarrative();
        while (content.contains("\n")) {
            clearConsole();
            content = getNarrativeToPrint(content);
            promptUserEnterKey(content);
        }
        //clearConsole();
        System.out.println(content + "\n");
    }

    private String getNarrativeToPrint(String content) {
        int lines = Investigation.numLinesToPrintForNarrative * 2;
        for (int i = 0; i < lines && content.contains("\n"); i++) {
            System.out.print(content.substring(0, 1 + content.indexOf("\n")));
            content = content.substring(1 + content.indexOf("\n"));
        }
        return content;
    }

    public void promptUserEnterKey(String content) {
        if (content.contains("\n")) {
            System.out.println("Please press the Enter key");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }
}
