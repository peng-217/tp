package scene.narrative;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class Narrative {

    String filePath;
    String fileName;

    private static int numLinesToPrint = 0;

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
            promptUserInputNumOfLines();
            clearConsole();
            for (int i = 0; i < numLinesToPrint && content.contains("\n"); i++) {
                System.out.print(content.substring(0, 1 + content.indexOf("\n")));
                content = content.substring(1 + content.indexOf("\n"));
            }
        }
        //clearConsole();
        System.out.println(content + "\n");
    }

    public void promptUserInputNumOfLines() {
        System.out.print("\nEnter number of lines to print for narrative: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        try {
            numLinesToPrint = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer");
            promptUserInputNumOfLines();
        }
    }
}
