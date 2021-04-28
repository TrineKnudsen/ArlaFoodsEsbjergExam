package sample.be;

public class User extends Person {

    public String username;
    public String password;


    public User(int id, int depId, String fullName, boolean isAdmin, String username, String password) {
        super(id, depId, fullName, isAdmin);
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
