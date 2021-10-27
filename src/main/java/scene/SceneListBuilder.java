package scene;

import exceptions.MissingSceneFileException;
import scene.narrative.Narrative;
import storage.GameDataFileDecoder;
import scene.suspect.SuspectList;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import static scene.suspect.SuspectList.suspectListBuilder;


public class SceneListBuilder {

    public static SceneList buildSceneList(GameDataFileDecoder dataFile) throws MissingSceneFileException {
        Scene[] scenes;
        try {
            scenes = getScenesFromFile("/scenesWithNarratives.txt");

        } catch (FileNotFoundException e) {
            throw new MissingSceneFileException("Text file containing scene order is missing!");
        }
        return new SceneList(dataFile, scenes);
    }

    private static Scene[] getScenesFromFile(String fileLocation) throws FileNotFoundException {
        InputStream f = SceneListBuilder.class.getResourceAsStream(fileLocation);
        //File f = new File(fileLocation);
        if (f == null) {
            throw new FileNotFoundException();
        }
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
            Narrative narrative = new Narrative(narrativeFileLocation);
            scene = new Scene(narrative, suspectList, sceneType);
            scenes[i] = scene;
        }
        return scenes;
    }
}
