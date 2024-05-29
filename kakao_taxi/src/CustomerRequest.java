
public class CustomerRequest implements Runnable {

    private TaxiSystem system;

    public CustomerRequest(TaxiSystem system) {
        this.system = system;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n" + "========== 택시 배차 중 ==========");
            // 고객 생성
            Customer customer = new Customer("Steve", Location.generateRandomLocation(), Location.generateRandomLocation());

            // 택시 배차
            Taxi taxi = system.dispatchTaxi(customer);

            if (taxi != null) {
                // 요금 계산
                double price = system.calculateFare(customer.getPickupLocation(), customer.getDestination(), 1.5);
                long roundedPrice = Math.round(price);

                System.out.println("택시 [" + taxi.getLicensePlate() + "]가 고객 [" + customer.getName() + "]을 배차합니다.");

                // 택시 상태 업데이트 (도착 후)
                Thread.sleep((long) (10 * roundedPrice / 10)); // 예시로 요금에 비례한 지연 시간 추가
                system.updateTaxiStatus(taxi.getLicensePlate(), true);
                System.out.println("택시" + taxi.getLicensePlate() + "가 " + customer.getName() + "고객의 목적지에 도착했습니다. 운행을 종료합니다.");
                System.out.println("요금 : " + roundedPrice + "원");
            }
        } catch (InterruptedException e) {
            System.out.println("택시 배차 중 인터럽트 발생: " + e.getMessage());
        }
    }
}
