package clue;

public class Clue {

    //Suspect suspect;
    String clueName = "default name";
    String image = "default image :)";
    String description = "default description";
    boolean isChecked;

    public Clue() {
        isChecked = false;
    }

    public void setChecked() {
        this.isChecked = true;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public String toString() {
        return "------------------------------------------------\n"
                + clueName
                + "\n"
                + image
                + "\n"
                + description
                + "\n";
    }
}
