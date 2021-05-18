package arlaScreens.be;

public class DataPoint {

    private String key;
    private int value;
    String url;

    public DataPoint(String key, int value, String url) {
        this.key = key;
        this.value = value;
        this.url = url;
    }

    public DataPoint() {}

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
