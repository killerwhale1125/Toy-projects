import vehicle.*;

import java.util.Random;

public class ParkingManagement {
    private ParkingLot parkingLot;

    public ParkingManagement(int totalSpots, int motorcycleSpots, int regularCarSpots, int largeCarSpots, int electricCarSpots) {
        this.parkingLot = new ParkingLot(totalSpots, motorcycleSpots, regularCarSpots, largeCarSpots, electricCarSpots);
    }

    public void startSimulation() {
        Random random = new Random();
        String[] licensePlates = new String[50];
        // 번호판 생성
        for (int i = 0; i < 50; i++) {
            licensePlates[i] = "차량번호" + (i + 1);
        }

        for (String licensePlate : licensePlates) {
            int vehicleType = random.nextInt(4);
            boolean hasPriority = random.nextBoolean();
            Vehicle vehicle;
            switch (vehicleType) {
                case 0:
                    vehicle = new Motorcycle(licensePlate, hasPriority);
                    break;
                case 1:
                    vehicle = new RegularCar(licensePlate, hasPriority);
                    break;
                case 2:
                    vehicle = new LargeCar(licensePlate, hasPriority);
                    break;
                case 3:
                    vehicle = new ElectricCar(licensePlate, hasPriority);
                    break;
                default:
                    vehicle = new RegularCar(licensePlate, hasPriority);
            }
            parkingLot.parkVehicle(vehicle);
        }
    }

    public void displayParkingLotStatus() {
        parkingLot.displayStatus();
    }

    public static void main(String[] args) {
        // 주차장의 차량 종류 별 구역 사이즈 지정
        ParkingManagement system = new ParkingManagement(30, 5, 10, 10, 5);
        system.startSimulation();
        system.displayParkingLotStatus();
    }
}
