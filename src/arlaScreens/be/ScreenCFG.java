package arlaScreens.be;

import org.apache.poi.ss.usermodel.Workbook;

public class ScreenCFG {

    private int rowIndex;
    private int colIndex;
    private Workbook imgUrl;
    private String fileName;
    private User user;

    public ScreenCFG(int rowIndex, int colIndex, Workbook imgUrl, String fileName, User user) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.imgUrl = imgUrl;
        this.user = user;
        this.fileName = fileName;
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

    public Workbook getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Workbook imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
