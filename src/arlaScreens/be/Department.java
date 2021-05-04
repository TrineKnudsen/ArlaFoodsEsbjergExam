package arlaScreens.be;

public class Department {

    private int id;
    private String depName;

    public Department(int id, String depName) {
        this.id = id;
        this.depName = depName;
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

    @Override
    public String toString() {
        return depName;
    }

}
