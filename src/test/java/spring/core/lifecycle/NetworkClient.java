package spring.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {
    private String url;

    // 스프링 빈 라이프 사이클 : 객체 생성 후 의존관계 주입 => 필요한 데이터 사용할 수 있는 준비 완료
    // 초기화는 준비완료 되었을 때 호출됨.
    // 스프링 컨테이너 생성 -> 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 스프링 종료
    // => 객체의 생성과 초기화는 분리하는 게 좋다.

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);

    }


    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
