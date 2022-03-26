package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //final 은 불변이 아니라 재할당할 수 없도록 한다.
    // 최초 초기화나 상속 이후 변할 수 없는 값.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */

    public Long join(Member member){
        // 같은 이름이 있는 경우에는 안된다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //만약 결과가 이미 존재한다면 (이도 optional 을 사용했기에 가능한 것.if null~ 로 하지 않아도 됨.)
    // 값이 중복되면 발생하는 오류를 발생시킨다.
    // ctrl+ T extract Method
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
       throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers(){
            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
