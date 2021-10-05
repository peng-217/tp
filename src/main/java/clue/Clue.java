package clue;

public class Clue {

    //Suspect suspect;
    String clueName = "default name";
    String image = ":)";
    String description = "default description";
    boolean isChecked;

    Clue() {
        isChecked = false;
    }

    public void setChecked() {
        this.isChecked = true;
    }

    @Override
    public String toString() {
        return "------------------------------------------------\n" +
                clueName + System.lineSeparator() +
                image + System.lineSeparator() +
                description + System.lineSeparator();
    }
}
