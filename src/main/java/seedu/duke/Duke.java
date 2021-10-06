package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    private static Suspect choseSuspect;
    private static Suspect realSuspect;

    public static void initializeGame() {
        ui = new Ui();
        SuspectList suspects = new SuspectList(ui);


    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        String userInput;
        Scanner user_type = new Scanner(System.in);
        userInput = user_type.nextLine();

        while(!userInput.equals("EXIT")){
            //parser.processCommand(userInput);
            //the line above needs parser to be done first, then can be uncommented
            userInput = in.nextLine();
        }


        ui.printExitMessage();
    }
}
