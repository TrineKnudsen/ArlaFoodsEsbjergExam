package arlaScreens.be;

public class ScreenCFG {

    private int rowIndex;
    private int colIndex;
    private String url;
    private String type;
    private Department department;

    public ScreenCFG(int colIndex, int rowIndex, String type, String url, Department department) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.type = type;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department de) {
        this.department = de;
    }
}