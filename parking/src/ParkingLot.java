import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

// 주차장
public class ParkingLot {
    private List<ParkingSpot> spots = new ArrayList<>();    // 주차 공간
    private final Object lock = new Object();

    public ParkingLot(int totalSpots, int motorcycleSpots, int regularCarSpots, int largeCarSpots, int electricCarSpots) {
        for (int i = 0; i < motorcycleSpots; i++) {
            spots.add(new ParkingSpot(i, 1));
        }
        for (int i = motorcycleSpots; i < motorcycleSpots + regularCarSpots; i++) {
            spots.add(new ParkingSpot(i, 2));
        }
        for (int i = motorcycleSpots + regularCarSpots; i < motorcycleSpots + regularCarSpots + largeCarSpots; i++) {
            spots.add(new ParkingSpot(i, 3));
        }
        for (int i = motorcycleSpots + regularCarSpots + largeCarSpots; i < totalSpots; i++) {
            spots.add(new ParkingSpot(i, 2)); // 전기차는 일반 차량 크기와 동일한 크기를 가짐
        }
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.canPark(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    public void parkVehicle(Vehicle vehicle) {
        synchronized (lock) {
            // 사용 가능한 주차 구역 조회
            ParkingSpot spot = findAvailableSpot(vehicle);
            
            // 사용 가능 구역이 없고 주차 우선권이 있을 경우
            if (spot == null && vehicle.hasPriority()) {
                Vehicle lowestPriorityVehicle = null;
                // 우선순위가 가장 낮은 차량을 조회
                for (ParkingSpot s : spots) {
                    if (s.isOccupied() && !s.getVehicle().hasPriority() &&
                            (lowestPriorityVehicle == null || s.getVehicle().getSize() < lowestPriorityVehicle.getSize())) {
                        lowestPriorityVehicle = s.getVehicle();
                    }
                }

                // 우선순위가 가장 낮은 차량을 밀어내고 주차 공간을 확보
                if (lowestPriorityVehicle != null) {
                    // 우선순위 낮은 주차 구역 조회
                    ParkingSpot lowPrioritySpot = findSpotByVehicle(lowestPriorityVehicle);
                    // 해당 구역 차량 내보내기
                    lowPrioritySpot.removeVehicle();
                    spot = lowPrioritySpot;
                }
            }

            // 주차 공간이 확보되면 차량을 주차한다
            if (spot != null) {
                // 차량을 주차하고, 필요 시 우선 주차권을 가진 차량을 위한 공간을 확보
                spot.parkVehicle(vehicle);
                System.out.println("주차가 가능합니다. 차량이 진입합니다.");
                System.out.println("차량 번호 : " + vehicle.getLicensePlate() + " 주차 구역 번호 : " + spot.getNumber() + "\n");
                Thread thread = new Thread(vehicle);
                thread.start();
            } else {
                System.out.println("사용 가능한 주차 구역이 없습니다. " + vehicle.getLicensePlate());
            }
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        synchronized (lock) {
            ParkingSpot spot = findSpotByVehicle(vehicle);
            if (spot != null) {
                spot.removeVehicle();
                System.out.println("출차 차량 번호 : " + vehicle.getLicensePlate() + " 출차 구역 " + spot.getNumber());
            }
        }
    }

    public ParkingSpot findSpotByVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() && spot.getVehicle().equals(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    public void displayStatus() {
        synchronized (lock) {
            for (ParkingSpot spot : spots) {
                if (spot.isOccupied()) {
                    System.out.println("주차 구역 : " + spot.getNumber() + " 차량 번호 : " + spot.getVehicle().getLicensePlate());
                } else {
                    System.out.println("주차된 차량이 없습니다. 주차구역 : " + spot.getNumber() + " ");
                }
            }
        }
    }
}
