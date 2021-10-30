package command;

import exceptions.InvalidClueException;
import exceptions.InvalidInputException;
import exceptions.InvalidSuspectException;
import scene.SceneList;
import ui.Ui;
import investigation.Investigation;

public abstract class Command {
    public abstract void execute(Ui ui, Investigation investigation, SceneList sceneList) throws InvalidInputException;

    public abstract boolean exit();

}