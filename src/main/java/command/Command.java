package command;

import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import scene.SceneList;
import ui.Ui;
import investigation.Investigation;

public abstract class Command {
    public abstract void execute(Ui ui, Investigation investigation, SceneList sceneList);

    public abstract boolean exit();

}