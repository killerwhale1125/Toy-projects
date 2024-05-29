import java.util.ArrayList;
import java.util.List;

public class TaxiSystem {
    List<Taxi> taxis;
    List<Customer> customers;

    public TaxiSystem() {
        this.taxis = new ArrayList<>();
    }

    public Taxi findNearTaxi(Location customerCurrentLocation) {
        Taxi nearTaxi = null;
        double minDistance = Double.MAX_VALUE;

        for (Taxi taxi : taxis) {
            // 사용 가능할 때
            if(taxi.isAvailable()) {
                double distance = calculateDistance(customerCurrentLocation, taxi.getCurrentLocation());
                if(distance < minDistance) {
                    minDistance = distance;
                    nearTaxi = taxi;
                }
            }
        }
        return nearTaxi;
    }

    public Taxi dispatchTaxi(Customer customer) {
        // 가장 가까운 택시 조회
        Taxi nearTaxi = findNearTaxi(customer.getPickupLocation());
        if (nearTaxi != null) {
            // 택시 상태 변경
            nearTaxi.setAvailable(false);
            nearTaxi.setCurrentLocation(customer.getPickupLocation());
            System.out.println("탑승 택시 번호 : " + nearTaxi.getLicensePlate() + " 탑승 고객 :  " + customer.getName());
            return nearTaxi;
        } else {
            System.out.println("죄송합니다 " + customer.getName() + "고객님 현재 탑승 가능한 택시가 없습니다. ");
            return null;
        }
    }

    private double calculateDistance(Location location1, Location location2) {

        double locLatitude1 = location1.getLatitude();
        double longLongitude1 = location1.getLongitude();
        
        // 택시의 현재 위치
        double locLatitude2 = location2.getLatitude();
        double longLongitude2 = location2.getLongitude();

        // 두 점 사이의 거리를 계산하는 Haversine 공식
        final int R = 6371; // 지구의 반경

        // 위도와 경도의 차이를 라디안으로 변환.
        double latDistance = Math.toRadians(locLatitude2 - locLatitude1);
        double lonDistance = Math.toRadians(longLongitude2 - longLongitude1);

        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(locLatitude1)) * Math.cos(Math.toRadians(locLatitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // 거리를 Km로 반환
    }

    public void updateTaxiStatus(String licensePlate, boolean avaliable) {
        for (Taxi taxi : taxis) {
            if (taxi.getLicensePlate().equals(licensePlate)) {
                taxi.setAvailable(avaliable);
                break;
            }
        }
    }

    // 요금 계산
    public long calculateFare(Location currentLocation, Location destinationLocation, double ratePerKm) {
        // 가격 = (목적지 거리 - 현재위치 거리) * ratePerKm(km당 평균 금액)
        double price = calculateDistance(currentLocation, destinationLocation) * ratePerKm;
        return Math.round(price);
    }

    public void getSystemStatus() {
        StringBuilder sb = new StringBuilder();
        for (Taxi taxi : taxis) {
            String status = "빈차";
            if(!taxi.isAvailable()) {
                status = "탑승중";
            }
            sb.append("택시 번호 : " + taxi.getLicensePlate())
                    .append(" 택시 상태 : " + status)
                    .append("\n");
        }
        System.out.println(sb.toString());
    }

    public void createTaxi() {
        for(int i=0; i<10; i++) {
            Taxi taxi = new Taxi("TAXI" + i, Location.generateRandomLocation(), 1.5);
            taxis.add(taxi);
        }
    }
}
