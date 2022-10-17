package spring.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.core.discount.DiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 구체 클래스에 동시에 의존하여 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 고쳐야 한다 (OCP/DIP 위반)
    // private final DiscountPolicy discountPolicy; : 인터페이스만 의존하면 NPE 발생
    // 누군가가 클라이언트인 OrderServiceImpl에 Disco untPolicy 구현 객체를 대신 생성하고 주입해주어야 한다.

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 설계 변경으로 OrderServiceImpl 은 MemoryMemberRepository, FixDiscountPolicy 를 의존하지 않는다
    // 단지 인터페이스만 의존

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
