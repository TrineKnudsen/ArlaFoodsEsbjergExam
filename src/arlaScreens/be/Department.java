package arlaScreens.be;

public class Department {

    private int id;
    private String depName;
    private ScreenCFG screenCFG;

    public Department(int id, String depName, ScreenCFG screenCFG) {
        this.id = id;
        this.depName = depName;
        this.screenCFG = screenCFG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public ScreenCFG getScreenCFG() {
        return screenCFG;
    }

    public void setScreenCFG(ScreenCFG screenCFG) {
        this.screenCFG = screenCFG;
    }

    @Override
    public String toString() {
        return depName;
    }

}
