package scene.suspect;

import scene.clue.Clue;
import scene.SceneListBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class SuspectList {
    protected LinkedHashMap<String, Suspect> suspects;
    protected final String[] suspectsNames = new String[]{"Father", "Kevin", "Wendy", "Ling", "Zack"};

    public SuspectList() {
        this.suspects = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Suspect> getSuspects() {
        return suspects;
    }

    public void addSuspect(String name, Suspect suspect) {
        suspects.put(name, suspect);
    }

    public void addClueForSuspect(String name, Clue clue) {
        assert suspects.containsKey(name);
        suspects.get(name).addClue(clue);
    }

    public void setClueChecked(String name, Clue clueInScene) {
        int indexInClueTracker = this.getClueIndex(name, clueInScene.getClueName());
        Clue clueInTracker = this.getSuspectAllClues(name).get(indexInClueTracker);
        assert Arrays.asList(suspectsNames).contains(name);
        this.suspects.get(name).setChecked(clueInTracker);
    }

    public ArrayList<Clue> getSuspectAllClues(String name) {
        assert Arrays.asList(suspectsNames).contains(name);
        return suspects.get(name).getClues();
    }

    public ArrayList<Clue> getSuspectAvailableClues(String name) {
        assert Arrays.asList(suspectsNames).contains(name);
        return suspects.get(name).getAvailableClues();
    }

    public ArrayList<Clue> getSuspectCheckedClues(String name) {
        assert Arrays.asList(suspectsNames).contains(name);
        return suspects.get(name).getCheckedClues();
    }

    public ArrayList<Clue> getAllClues() {
        ArrayList<Clue> clues = new ArrayList<>();
        for (Map.Entry<String, Suspect> suspectEntry : suspects.entrySet()) {
            clues.addAll(0, suspectEntry.getValue().getClues());
        }
        return clues;
    }

    public ArrayList<Clue> getAllAvailableClues() {
        ArrayList<Clue> clues = new ArrayList<>();
        for (Map.Entry<String, Suspect> suspectEntry : suspects.entrySet()) {
            clues.addAll(0, suspectEntry.getValue().getAvailableClues());
        }
        return clues;
    }

    public int getNumSuspects() {
        return suspects.size();
    }

    public String[] getSuspectNames() {
        String[] suspectNames = new String[getNumSuspects()];
        for (int i = 0; i < getNumSuspects(); i++) {
            suspectNames[i] = (String) suspects.keySet().toArray()[i];
        }
        return suspectNames;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < getNumSuspects(); i++) {
            toReturn.append(i + 1).append(". ").append((String) suspects.keySet().toArray()[i]).append("\n");
        }
        return toReturn.toString();
    }

    public int getClueIndex(String suspectName, String clueName) {
        ArrayList<Clue> clues = this.getSuspectAllClues(suspectName);
        for (int i = 0; i < clues.size(); i++) {
            if (clues.get(i).getClueName().equals(clueName)) {
                return i;
            }
        }
        return -1;
    }

    public static void suspectListBuilder(String fileLocation, SuspectList suspectList) throws FileNotFoundException {
        //File f = new File(fileLocation);
        //System.out.println(fileLocation);
        InputStream f = SceneListBuilder.class.getResourceAsStream(fileLocation);
        if (f == null) {
            throw new FileNotFoundException();
        }
        Scanner sc = new Scanner(f);

        int numOfSuspect = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numOfSuspect; i++) {
            String suspectName = sc.nextLine();
            Suspect suspect = new Suspect();
            suspectList.addSuspect(suspectName, suspect);
        }

        int numOfClues = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numOfClues; i++) {
            int count = 0;
            String suspect = "";
            StringBuilder name = new StringBuilder();
            StringBuilder image = new StringBuilder();
            StringBuilder description = new StringBuilder();
            String phrase = sc.nextLine();
            while (!phrase.equals("**")) {
                if (phrase.equals("*")) {
                    count += 1;
                } else if (count == 0) {
                    suspect = phrase;
                } else if (count == 1) {
                    name.append(phrase);
                } else if (count == 2) {
                    image.append(phrase).append("\n");
                } else if (count == 3) {
                    description.append(phrase).append("\n");
                }
                phrase = sc.nextLine();
            }
            Clue clueToAdd = new Clue(name.toString(), image.toString(), description.toString());
            suspectList.addClueForSuspect(suspect, clueToAdd);
        }
        sc.close();
    }
}
