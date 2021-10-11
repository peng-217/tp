package clue;

public class Clue {

    //Suspect suspect;
    protected String clueName = "default name";
    protected String image = "default image :)";
    protected String description = "default description";
    protected boolean isChecked;

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
