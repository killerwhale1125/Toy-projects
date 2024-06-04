package vehicle;

public class LargeCar extends Vehicle {
    public LargeCar(String licensePlate, boolean hasPriority) {
        super(licensePlate, 3, hasPriority);
    }

    @Override
    public double getParkingRate() {
        return 3.0;
    }
}
