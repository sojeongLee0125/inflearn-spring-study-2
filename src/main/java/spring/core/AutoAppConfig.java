package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 동일한 이름의 자동 빈 등록과 수동 빈 등록이 존재하면 수동 빈등록이 우선권을 가진다.
    // 그러나 최근 스프링 부트는 수동 빈등록과 자동 빈등록이 충돌하면 오류가 나도록 기본값을 변경했다.
    // spring.main.allow-bean-definition-overriding=true 설정값을 추가하면 오류가 나지 않도록 할 수 있다.
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
