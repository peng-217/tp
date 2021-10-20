package clue;

import clue.firstscene.FatherInsurance;
import clue.firstscene.FatherMap;
import clue.firstscene.FatherPhoneCall;
import clue.firstscene.FatherTextMessage;
import clue.secondscene.*;
import clue.thirdscene.*;
import scene.SceneList;
import suspect.Suspect;
import ui.Ui;
import suspect.SuspectList;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BuildCheckedClueTracker {
    /*private Ui ui;
    private SuspectList suspects;

    public SearchedClueTracker(Ui ui) {
        this.ui = ui;
        this.suspects = new SuspectList(ui);
        buildSuspectList();
    }*/

    public static SuspectList buildClueTracker(Ui ui) {
        SuspectList suspects = new SuspectList(ui);
        suspects.addSuspect("Father", new Suspect());
        suspects.addClueForSuspect("Father", new FatherInsurance());
        suspects.addClueForSuspect("Father", new FatherMap());
        suspects.addClueForSuspect("Father", new FatherPhoneCall());
        suspects.addClueForSuspect("Father", new FatherTextMessage());
        suspects.addClueForSuspect("Father", new FatherDiary());
        suspects.addClueForSuspect("Father", new FatherQuarrel());
        suspects.addClueForSuspect("Father", new FatherResult());
        suspects.addClueForSuspect("Father", new FatherCough());
        suspects.addClueForSuspect("Father", new FatherFinancial());
        suspects.addClueForSuspect("Father", new FatherNewDiary());
        suspects.addClueForSuspect("Father", new FatherTofu());

        suspects.addSuspect("Kevin", new Suspect());
        suspects.addClueForSuspect("Kevin", new KevinBro());
        suspects.addClueForSuspect("Kevin", new KevinGift());
        suspects.addClueForSuspect("Kevin", new KevinAphrodisiac());
        suspects.addClueForSuspect("Kevin", new KevinWhisper());

        suspects.addSuspect("Wendy", new Suspect());
        suspects.addClueForSuspect("Wendy", new WendyTele());
        suspects.addClueForSuspect("Wendy", new WendyActDrunk());
        suspects.addClueForSuspect("Wendy", new WendyRude());

        suspects.addSuspect("Ling", new Suspect());
        suspects.addClueForSuspect("Ling", new LingPowder());
        suspects.addClueForSuspect("Ling", new LingTextMessage());

        suspects.addSuspect("Zack", new Suspect());
        suspects.addClueForSuspect("Zack", new ZackDrink());
        suspects.addClueForSuspect("Zack", new ZackMemo());
        suspects.addClueForSuspect("Zack", new ZackNotif());

        return suspects;
    }

    /*public void viewSearcherdCLues(String name) {
        ArrayList<Clue> availableClues = suspects.getSuspectAvailableClues(name);
        ui.printListOfClues(availableClues);
    }*/
}
