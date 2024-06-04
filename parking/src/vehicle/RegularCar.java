package vehicle;

public class RegularCar extends Vehicle {
    public RegularCar(String licensePlate, boolean hasPriority) {
        super(licensePlate, 2, hasPriority);
    }

    @Override
    public double getParkingRate() {
        return 2.0;
    }
}
