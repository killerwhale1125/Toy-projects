import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 택시 시스템 객체 생성
        TaxiSystem system = new TaxiSystem();
        Scanner sc = new Scanner(System.in);

        // 택시 생성
        system.createTaxi();

        while(true) {
            // 고객이 요청 시 거리에 따라서 하나의 스레드마다 지연되어 처리되게 구현
            System.out.println("==========택시 자동 배차 시스템==========");
            System.out.println("1.택시 상태 조회");
            System.out.println("2.고객 택시 탑승 요청");
            String str = sc.nextLine();
            System.out.println();
            switch(str) {
                case "1" :
                    system.getSystemStatus();
                    break;
                case "2" :
                    // 스레드 생성하여 요청 처리
                    Thread requestThread = new Thread(new CustomerRequest(system));
                    requestThread.start();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }
}