
public class Taxi {

    private final String licensePlate; // 번호판 (필수)
    private boolean isAvailable; // 빈차 (필수) -> true 탑승가능 false 탑승 불가능
    private Location currentLocation; // 현재 위치 (필수)
    private double ratePerKm; // km 당 평균 금액

    // 필수 생성자가 많아 Builder 객체 사용 X
    public Taxi(String licensePlate, Location location, double ratePerKm) {
        this.licensePlate = licensePlate;
        this.currentLocation = location;
        this.ratePerKm = ratePerKm;
        this.isAvailable = true;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }
}
