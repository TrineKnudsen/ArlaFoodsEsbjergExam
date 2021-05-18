package arlaScreens.be;

public class DataPoint {

    private String key;
    private int value;

    public DataPoint(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public DataPoint() {}

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
