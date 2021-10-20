package scene;

import clue.firstscene.FatherInsurance;
import clue.firstscene.FatherMap;
import clue.firstscene.FatherPhoneCall;
import clue.firstscene.FatherTextMessage;
import clue.secondscene.FatherDiary;
import clue.secondscene.FatherQuarrel;
import clue.secondscene.FatherResult;
import clue.secondscene.KevinBro;
import clue.secondscene.KevinGift;
import clue.secondscene.WendyTele;
import clue.thirdscene.FatherCough;
import clue.thirdscene.FatherFinancial;
import clue.thirdscene.FatherNewDiary;
import clue.thirdscene.FatherTofu;
import clue.thirdscene.KevinAphrodisiac;
import clue.thirdscene.KevinWhisper;
import clue.thirdscene.LingPowder;
import clue.thirdscene.LingTextMessage;
import clue.thirdscene.WendyActDrunk;
import clue.thirdscene.WendyRude;
import clue.thirdscene.ZackDrink;
import clue.thirdscene.ZackMemo;
import clue.thirdscene.ZackNotif;
import narrative.CorrectEndingNarrative;
import narrative.FirstNarrative;
import narrative.IntroNarrative;
import narrative.SecondNarrative;
import narrative.ThirdNarrative;
import narrative.TruthNarrative;
import narrative.WrongEndingNarrative;
import storage.GameDataFileDecoder;
import ui.Ui;
import suspect.Suspect;
import suspect.SuspectList;

public class SceneListBuilder {
    public static SceneList buildSceneList(Ui ui,GameDataFileDecoder dataFile) {

        SuspectList suspectsScene1 = new SuspectList(ui);
        suspectsScene1.addSuspect("Father", new Suspect());

        suspectsScene1.addClueForSuspect("Father", new FatherInsurance());
        suspectsScene1.addClueForSuspect("Father", new FatherMap());
        suspectsScene1.addClueForSuspect("Father", new FatherPhoneCall());
        suspectsScene1.addClueForSuspect("Father", new FatherTextMessage());

        SuspectList suspectsScene2 = new SuspectList(ui);
        suspectsScene2.addSuspect("Father", new Suspect());
        suspectsScene2.addSuspect("Kevin", new Suspect());
        suspectsScene2.addSuspect("Wendy", new Suspect());

        suspectsScene2.addClueForSuspect("Father", new FatherDiary());
        suspectsScene2.addClueForSuspect("Father", new FatherQuarrel());
        suspectsScene2.addClueForSuspect("Father", new FatherResult());
        suspectsScene2.addClueForSuspect("Kevin", new KevinBro());
        suspectsScene2.addClueForSuspect("Kevin", new KevinGift());
        suspectsScene2.addClueForSuspect("Wendy", new WendyTele());

        SuspectList suspectsScene3 = new SuspectList(ui);
        suspectsScene3.addSuspect("Father", new Suspect());
        suspectsScene3.addSuspect("Kevin", new Suspect());
        suspectsScene3.addSuspect("Wendy", new Suspect());
        suspectsScene3.addSuspect("Ling", new Suspect());
        suspectsScene3.addSuspect("Zack", new Suspect());

        suspectsScene3.addClueForSuspect("Father", new FatherCough());
        suspectsScene3.addClueForSuspect("Father", new FatherFinancial());
        suspectsScene3.addClueForSuspect("Father", new FatherNewDiary());
        suspectsScene3.addClueForSuspect("Father", new FatherTofu());
        suspectsScene3.addClueForSuspect("Kevin", new KevinAphrodisiac());
        suspectsScene3.addClueForSuspect("Kevin", new KevinWhisper());
        suspectsScene3.addClueForSuspect("Wendy", new WendyActDrunk());
        suspectsScene3.addClueForSuspect("Wendy", new WendyRude());
        suspectsScene3.addClueForSuspect("Ling", new LingPowder());
        suspectsScene3.addClueForSuspect("Ling", new LingTextMessage());
        suspectsScene3.addClueForSuspect("Zack", new ZackDrink());
        suspectsScene3.addClueForSuspect("Zack", new ZackMemo());
        suspectsScene3.addClueForSuspect("Zack", new ZackNotif());

        SceneList sceneList = new SceneList(dataFile.getCurrentSceneIndex(),dataFile);
        Scene introScene = new Scene(new IntroNarrative(), null);
        Scene firstScene = new Scene(new FirstNarrative(), suspectsScene1);
        Scene secondScene = new Scene(new SecondNarrative(), suspectsScene2);
        Scene thirdScene = new Scene(new ThirdNarrative(), suspectsScene3);
        Scene correctEndingScene = new Scene(new CorrectEndingNarrative(), null);
        Scene wrongEndingScene = new Scene(new WrongEndingNarrative(), null);
        Scene truthScene = new Scene(new TruthNarrative(), null);

        sceneList.addScene(introScene);
        sceneList.addScene(firstScene);
        sceneList.addScene(secondScene);
        sceneList.addScene(thirdScene);
        sceneList.addScene(correctEndingScene);
        sceneList.addScene(wrongEndingScene);
        sceneList.addScene(truthScene);

        return sceneList;
    }
}
