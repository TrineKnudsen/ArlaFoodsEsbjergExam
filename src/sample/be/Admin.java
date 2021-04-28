package sample.be;

public class Admin extends Person {

    public boolean isAdmin;
    public String username;
    public String password;

    public Admin(int id, int depId, String fullName, boolean isAdmin, String username, String password) {
        super(id, depId, fullName, isAdmin);
        this.username = username;
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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
