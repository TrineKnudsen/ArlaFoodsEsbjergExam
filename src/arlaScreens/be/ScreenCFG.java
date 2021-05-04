package arlaScreens.be;

import javax.swing.text.html.ImageView;

public class ScreenCFG {

    private int rowIndex;
    private int colIndex;
    private String imgUrl;

    public ScreenCFG(int rowIndex, int colIndex, String imgUrl) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.imgUrl = imgUrl;
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

}
