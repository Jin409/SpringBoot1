package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberservice;
    //테스트 코드의 경우에는 한국어로 적어도 무방하다. (가독성을 위해서)
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberservice = new MemberService(memberRepository);
    }

    @AfterEach //하나하나 끝날 때마다 실행되는
    public void afterEach(){
        memberRepository.clearStore();

    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberservice.join(member);

        //then
        Member findMember = memberservice.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberservice.join(member1);
        //cmd option v
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberservice.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//        memberservice.join(member2);
//        fail(); // 여기까지 오면 fail 인 것.
//        }
//        catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미  존재하는 회원입니다.123");
//        }//예외가 발생해야 한다.


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}