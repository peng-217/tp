package command;

import exceptions.InvalidInputException;
import exceptions.InvalidSuspectException;
import investigation.Investigation;
import scene.SceneList;
import scene.SceneTypes;
import ui.Ui;

public class InvestigateCommand extends Command {
    private static final String SUSPECT_FATHER_LOWER = "father";
    private static final String SUSPECT_KEVIN_LOWER = "kevin";
    private static final String SUSPECT_WENDY_LOWER = "wendy";
    private static final String SUSPECT_LING_LOWER = "ling";
    private static final String SUSPECT_ZACK_LOWER = "zack";
    private static final int SUSPECT_FATHER_INDEX = 1;
    private static final int SUSPECT_KEVIN_INDEX = 2;
    private static final int SUSPECT_WENDY_INDEX = 3;
    private static final int SUSPECT_LING_INDEX = 4;
    private static final int SUSPECT_ZACK_INDEX = 5;
    private static final String INVALID_SUSPECT_NAME = "Invalid suspect given!";
    private int suspectIndex;
    private String suspectName;
    private boolean backToSuspectStage = false;
    private static final int WENDY_INDEX = 3;

    public InvestigateCommand(int suspectIndex) {
        this.suspectIndex = suspectIndex;
    }

    public InvestigateCommand(String suspectName) {
        this.suspectName = suspectName;
    }

    private void suspectNameToIndex() throws InvalidInputException {
        switch(suspectName) {
        case SUSPECT_FATHER_LOWER:
            this.suspectIndex = SUSPECT_FATHER_INDEX;
            break;
        case SUSPECT_KEVIN_LOWER:
            this.suspectIndex = SUSPECT_KEVIN_INDEX;
            break;
        case SUSPECT_WENDY_LOWER:
            this.suspectIndex = SUSPECT_WENDY_INDEX;
            break;
        case SUSPECT_LING_LOWER:
            this.suspectIndex = SUSPECT_LING_INDEX;
            break;
        case SUSPECT_ZACK_LOWER:
            this.suspectIndex = SUSPECT_ZACK_INDEX;
            break;
        default:
            throw new InvalidInputException(INVALID_SUSPECT_NAME);
        }
    }

    private void suspectNameGiven() throws InvalidInputException {
        if (this.suspectName != null) {
            suspectNameToIndex();
            this.backToSuspectStage = true;
        } else {
            this.backToSuspectStage = false;
        }
    }

    @Override
//    public void execute(Ui ui, Investigation investigation, SceneList sceneList) throws InvalidSuspectException,
//            InvalidInputException {
//        suspectNameGiven();
//        if (this.backToSuspectStage) {
//            investigation.setSuspectStage();
//        }
//        investigation.investigateScene(this.suspectIndex, sceneList.getCurrentScene());

    public void execute(Ui ui, Investigation investigation, SceneList sceneList) throws InvalidSuspectException, InvalidInputException {
        if (sceneList.getCurrentSceneType() == SceneTypes.GUESS_KILLER_SCENE) {
            boolean isCorrectKiller = (this.suspectIndex == WENDY_INDEX);
            sceneList.setSceneNumberAfterSuspecting(isCorrectKiller);
            sceneList.runCurrentScene();
        } else {
            suspectNameGiven();
            if (this.backToSuspectStage) {
                investigation.setSuspectStage();
            }
            investigation.investigateScene(this.suspectIndex, sceneList.getCurrentScene());
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
