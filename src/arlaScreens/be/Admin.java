package arlaScreens.be;

public class Admin extends Department{

    private int id;
    private String username;
    private String password;
    String name = "Admin";

    public Admin(int id, String name, int type, String username, String password) {
        super(id, name, type);
        this.username = username;
        this.password = password;
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
