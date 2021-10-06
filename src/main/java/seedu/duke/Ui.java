package seedu.duke;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui(InputStream input){
        this.scanner = new Scanner(input);
    }

    public Ui(){
        this(System.in);
    }

    public void printExitMessage(){
        System.out.println("Ok! Hope you enjoy this game! See you soon!");
    }

    public void printSuspects(SuspectList suspects){
        System.out.println("Please choose a suspect that you think is the real murderer from the list:");
        for(int i = 0; i < suspects.getSize(); i++){
            System.out.println((i+1) + "." + suspects.getIndexSuspect(i));
        }

    }

}
