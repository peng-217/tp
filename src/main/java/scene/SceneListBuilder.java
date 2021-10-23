package scene;

import narrative.Narrative;
import storage.GameDataFileDecoder;
import ui.Ui;
import suspect.SuspectList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static suspect.SuspectList.suspectListBuilder;


public class SceneListBuilder {

    public static SceneList buildSceneList(Ui ui, GameDataFileDecoder dataFile) throws FileNotFoundException {
        SceneList sceneList = new SceneList(dataFile.getCurrentSceneIndex(), dataFile);
        addScenes("data/scenesWithNarratives.txt", sceneList, ui);
        return sceneList;
    }

    private static void addScenes(String fileLocation, SceneList sceneList, Ui ui) throws FileNotFoundException {
        File f = new File(fileLocation);
        Scanner sc = new Scanner(f);

        int numOfScenes = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numOfScenes; i++) {
            String condition = sc.nextLine();
            String narrativeFileLocation = sc.nextLine();
            String sceneTypeInString = sc.nextLine();
            SceneTypes sceneType = Enum.valueOf(SceneTypes.class, sceneTypeInString);
            SuspectList suspectList;
            Scene scene;
            if (Objects.equals(condition, "")) {
                suspectList = null;
            } else {
                String cluesFileLocation = sc.nextLine();
                suspectList = new SuspectList(ui);
                try {
                    suspectListBuilder(cluesFileLocation, suspectList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            scene = new Scene(new Narrative(narrativeFileLocation), suspectList, sceneType);
            sceneList.addScene(scene);
        }
    }
}
