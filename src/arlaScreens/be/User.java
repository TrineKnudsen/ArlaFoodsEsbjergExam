package arlaScreens.be;

public class User extends Department{


    private ScreenCFG screenCFG;

    public User(int id, String depName, int type, ScreenCFG screenCFG) {
        super(id, depName, type);
        this.screenCFG = screenCFG;
    }

    public ScreenCFG getScreenCFG() {
        return screenCFG;
    }

    public void setScreenCFG(ScreenCFG screenCFG) {
        this.screenCFG = screenCFG;
    }

}
