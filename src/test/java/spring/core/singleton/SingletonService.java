package spring.core.singleton;

public class SingletonService {

    // static 영역에 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // public 메서드 - 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용 : 항상 같은 인스턴스를 반환
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private 선언 - 외부에서 new 키워드를 사용한 객체 생성을 방지
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
