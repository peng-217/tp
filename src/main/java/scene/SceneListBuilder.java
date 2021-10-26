package scene;

import exceptions.MissingSceneFileException;
import narrative.Narrative;
import storage.GameDataFileDecoder;
import ui.Ui;
import suspect.SuspectList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static suspect.SuspectList.suspectListBuilder;


public class SceneListBuilder {

    public static SceneList buildSceneList(Ui ui, GameDataFileDecoder dataFile) throws MissingSceneFileException {
        Scene[] scenes;
        try {
            scenes = getScenesFromFile("data/scenesWithNarratives.txt");
        } catch (FileNotFoundException e) {
            throw new MissingSceneFileException("Text file containing scene order is missing!");
        }
        return new SceneList(dataFile, scenes);
    }

    private static Scene[] getScenesFromFile(String fileLocation) throws FileNotFoundException {
        File f = new File(fileLocation);
        Scanner sc = new Scanner(f);

        int numOfScenes = sc.nextInt();
        Scene[] scenes = new Scene[numOfScenes];
        sc.nextLine();

        for (int i = 0; i < numOfScenes; i++) {
            String condition = sc.nextLine();
            String narrativeFileLocation = sc.nextLine();
            String sceneTypeInString = sc.nextLine();
            SceneTypes sceneType = Enum.valueOf(SceneTypes.class, sceneTypeInString);
            SuspectList suspectList;
            Scene scene;
            if (condition.equals("")) {
                suspectList = null;
            } else {
                String cluesFileLocation = sc.nextLine();
                suspectList = new SuspectList();
                try {
                    suspectListBuilder(cluesFileLocation, suspectList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            scene = new Scene(new Narrative(narrativeFileLocation), suspectList, sceneType);
            scenes[i] = scene;
        }
        return scenes;
    }
}
