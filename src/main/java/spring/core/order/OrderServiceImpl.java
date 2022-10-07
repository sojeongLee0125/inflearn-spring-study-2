package spring.core.order;

import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 구체 클래스에 동시에 의존하여 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 고쳐야 한다 (OCP/DIP 위반)
    // private final DiscountPolicy discountPolicy; : 인터페이스만 의존하면 NPE 발생
    // 누군가가 클라이언트인 OrderServiceImpl에 Disco untPolicy 구현 객체를 대신 생성하고 주입해주어야 한다.
    // 관심사의 분리 듣기

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
