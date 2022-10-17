package spring.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // TreadA : A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // TreadB : B사용자 10000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

        // TreadA : A사용자 주문 금액 조회
        // int price = statefulService1.getPrice();
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userAPrice = " + userAPrice);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService StatefulService() {
            return new StatefulService();
        }

    }

}