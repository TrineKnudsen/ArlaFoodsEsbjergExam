package arlaScreens.be;

import java.util.List;

public class ScreenCFG {

    private int rowIndex;
    private int colIndex;
    private String url;
    private List<DataPoint> dataPoints;
    private String fileName;
    private User user;

    public ScreenCFG(int rowIndex, int colIndex, List<DataPoint> dataPoints, String fileName, User user) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.dataPoints = dataPoints;
        this.user = user;
        this.fileName = fileName;
    }

    public ScreenCFG(int rowIndex, int colIndex, String url, String fileName, User user){
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.user = user;
        this.fileName = fileName;
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

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
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