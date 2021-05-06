package arlaScreens.be;

public class ScreenCFG {

    private int rowIndex;
    private int colIndex;
    private String imgUrl;
    private User user;

    public ScreenCFG(int rowIndex, int colIndex, String imgUrl, User user) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.imgUrl = imgUrl;
        this.user = user;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
