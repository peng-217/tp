package investigation;

import scene.SceneTypes;
import scene.clue.Clue;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import parser.Parser;
import scene.Scene;
import scene.suspect.SuspectList;
import ui.Ui;
import java.util.ArrayList;

public class Investigation {
    private static InvestigationStages stage;
    private static Scene currentScene;
    private static String currentSuspect;
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();
    private final SuspectList clueTracker;
    private static final String WRONG_INDEX_GIVEN = "Sorry please enter index within range";

    public static int numLinesToPrintForNarrative;

    public Investigation(SuspectList clueTracker) {
        this.clueTracker = clueTracker;
        numLinesToPrintForNarrative = 100;
        setSuspectStage();
    }

    public InvestigationStages getStage() {
        return stage;
    }

    public String getCurrentSuspectName() {
        return currentSuspect;
    }

    public void investigateScene(Integer index, Scene scene)
            throws InvalidSuspectException, InvalidClueException {
        switch (stage) {
        case SUSPECT_STAGE:
            currentSuspect = parser.getSuspectNameFromIndex(scene, index);
            setClueStage();
            break;
        case CLUE_STAGE:
            currentScene = scene;
            int suspectNumClues = currentScene.investigateSuspect(currentSuspect).getNumClues();
            if (index > suspectNumClues) {
                throw new InvalidClueException(WRONG_INDEX_GIVEN);
            } else if (index == 0) {
                setSuspectStage();
            } else {
                Clue currentClueInScene = currentScene.investigateSuspect(currentSuspect).getClues().get(index - 1);
                assert clueTracker.getAllClues().contains(currentClueInScene);
                clueTracker.setClueChecked(currentSuspect, currentClueInScene);
                ui.printSelectedClue(currentClueInScene);
            }
            break;
        default:
            ui.printIndexCommand();
        }
    }

    public ArrayList<Clue> getSuspectCheckedClues(String name) {
        return clueTracker.getSuspectCheckedClues(name);
    }

    public void restartGame() {
        setSuspectStage();
    }

    public void setSuspectStage() {
        stage = InvestigationStages.SUSPECT_STAGE;
    }

    private void setClueStage() {
        stage = InvestigationStages.CLUE_STAGE;
    }
}
