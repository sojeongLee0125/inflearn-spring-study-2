package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    // 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 설정 클래스
    // 한 생성 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        // 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다.
        return new RateDiscountPolicy();
    }
}
