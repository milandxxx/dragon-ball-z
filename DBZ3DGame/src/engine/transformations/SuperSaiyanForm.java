package engine.transformations;

public class SuperSaiyanForm {

    private boolean active = false;

    public void transform() {
        active = !active;
    }

    public boolean isTransformed() {
        return active;
    }
}
