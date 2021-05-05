package arlaScreens.be;

public class Department {

    int id;
    String name;
    int type;

    public Department(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
