package hello.hellospring;

import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 해당 로직을 호출하여 알아서 스프링빈에 등록해준다.
        //해당 객체는 생성자가 있어야 해서 아래에서 빈 생성해주고 넣어줌.
    }

    @Bean
    public MemoryMemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
