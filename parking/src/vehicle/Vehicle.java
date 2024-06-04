package vehicle;

public abstract class Vehicle implements Runnable {
    private String licensePlate;    // 번호판
    private int size; // 차량 크기 (1: 오토바이, 2: 자동차, 3: 트럭)
    private boolean hasPriority;    // 우선순위 여부

    public Vehicle(String licensePlate, int size, boolean hasPriority) {
        this.licensePlate = licensePlate;
        this.size = size;
        this.hasPriority = hasPriority;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getSize() {
        return size;
    }

    public boolean hasPriority() {
        return hasPriority;
    }

    public abstract double getParkingRate(); // 주차 요금 계산

    @Override
    public void run() {
        try {
            Thread.sleep(2 * 60 * 60 * 1000); // 2시간 주차
        } catch (InterruptedException e) {
            System.out.println(licensePlate + " was interrupted.");
        }
    }
}
