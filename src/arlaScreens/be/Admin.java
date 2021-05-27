package arlaScreens.be;

public class Admin {

    private int id;
    private String name;
    private int isAdmin;
    private String username;
    private String password;

    public Admin(int id, String name, int isAdmin, String username, String password) {
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
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

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
