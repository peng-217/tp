package clue;

public class Clue {

    protected String clueName = "default name";
    protected String image = "default image :)";
    protected String description = "default description";
    protected boolean isChecked;

    public Clue() {
        isChecked = false;
    }

    public Clue(String clueName, String image, String description) {
        this.clueName = clueName;
        this.image = image;
        this.description = description;
        isChecked = false;
    }

    public void setChecked() {
        this.isChecked = true;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getClueName() {
        return clueName;
    }

    @Override
    public String toString() {
        assert !clueName.equals("default name");
        assert !image.equals("default image :)");
        assert !description.equals("default description");
        return "------------------------------------------------\n"
                + clueName
                + "\n"
                + image
                + description;
    }
}
