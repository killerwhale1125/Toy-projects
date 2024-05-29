
public class Customer {
    private final String name;    // 이름
    private Location pickupLocation;    // 현재 위치
    private Location destination;   // 목적지

    public Customer(String name, Location pickupLocation, Location destination) {
        this.name = name;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Customer call() {
        return this;
    }
}
