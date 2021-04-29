package arlaScreens.be;

public class Person {

    public int id;
    public int depId;
    public String fullName;
    public boolean isAdmin;

    public Person(int id, int depId, String fullName, boolean isAdmin){
        this.id = id;
        this.depId = depId;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
