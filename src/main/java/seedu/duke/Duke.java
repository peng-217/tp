package seedu.duke;

import clue.FatherInsurance;
import clue.FatherMap;
import clue.FatherPhoneCall;
import clue.FatherTextMessage;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //System.out.println("What is your name?");

        //Scanner in = new Scanner(System.in);
        //System.out.println("Hello " + in.nextLine());

        FatherMap test = new FatherMap();
        System.out.println(test.toString());
    }
}
