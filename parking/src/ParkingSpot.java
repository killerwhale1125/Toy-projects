import vehicle.Vehicle;

public class ParkingSpot {
    private int number;
    private int size; // 주차 공간 크기
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(int number, int size) {
        this.number = number;
        this.size = size;
        this.isOccupied = false;
    }

    public boolean canPark(Vehicle vehicle) {
        // 사용중이 아니며 차량이 사이즈에 적합할 경우
        return !isOccupied && size >= vehicle.getSize();
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public int getSize() {
        return size;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getNumber() {
        return number;
    }
}
