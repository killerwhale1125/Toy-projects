import java.util.Random;

public class Location {

    private static final Random random = new Random();
    double latitude;
    double longitude;

    // 랜덤한 경도 위도 생성
    public static Location generateRandomLocation() {
        double latitude = -90 + (90 - (-90)) * random.nextDouble();
        double longitude = -180 + (180 - (-180)) * random.nextDouble();
        return new Location(latitude, longitude);
    }

    private Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
