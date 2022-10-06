package spring.core.member;

public class MemberServiceImpl implements MemberService {

    // 실제 할당하는 부분이 인터페이스와 구현체를 동시에 의존하고 있다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
