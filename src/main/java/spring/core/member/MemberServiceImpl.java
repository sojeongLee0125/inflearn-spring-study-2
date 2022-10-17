package spring.core.member;

public class MemberServiceImpl implements MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 실제 할당하는 부분이 인터페이스와 구현체를 동시에 의존하고 있다.
    private final MemberRepository memberRepository;

    // 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository 를 의존하지 않는다
    // 단지 MemberRepository 인터페이스만 의존
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
