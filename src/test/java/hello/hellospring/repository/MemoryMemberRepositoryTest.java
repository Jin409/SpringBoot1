package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 하나 끝날 때마다 레포지토리를 깔끔하게 지워주는 코드

    @AfterEach //하나하나 끝날 때마다 실행되는
    public void afterEach(){
        repository.clearStore();

    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
//        System.out.println("result="+(result==member));
//        assertEquals(member,result); // 둘이 똑같은지 확인할 수 있음. 앞이 기대하는 것, 뒤가 실제 (actual)
//        Assertions.assertEquals(member,null); // 오류남. 실제 값과 기대하는 값이 일치하지 않음.

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
//        assertThat(result).isEqualTo(member2);
    }
    
    @Test
    public void findAll(){
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

}
