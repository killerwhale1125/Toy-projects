package vehicle;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate, boolean hasPriority) {
        super(licensePlate, 1, hasPriority);
    }

    @Override
    public double getParkingRate() {
        return 1.0;
    }
}
