package vehicle;

public class ElectricCar extends Vehicle {
    public ElectricCar(String licensePlate, boolean hasPriority) {
        super(licensePlate, 2, hasPriority);
    }

    @Override
    public double getParkingRate() {
        return 2.5;
    }
}
