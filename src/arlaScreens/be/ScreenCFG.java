package arlaScreens.be;

public class ScreenCFG {

    private int rowIndex;
    private int colIndex;
    private String url;
    private String type;
    private User user;

    public ScreenCFG(int colIndex, int rowIndex, String type, String url, User user) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.type = type;
        this.user = user;
        this.url = url;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}