package arlaScreens.be;

public class DataPoint {

    private String columnA;
    private String columnB;
    private String key;
    private int value;

    public DataPoint(String columnA, String columnB, String key, int value) {
        this.key = key;
        this.value = value;
        this.columnA = columnA;
        this.columnB = columnB;
    }

    public DataPoint() {}

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public String getColumnA(){
        return columnA;
    }

    public void setColumnA(String columnA){
        this.columnA = columnA;
    }

    public String getColumnB(){
        return columnB;
    }

    public void setColumnB(String columnB){
        this.columnB = columnB;
    }
}
