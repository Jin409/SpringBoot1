package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    
    //스프링 컨테이너에 MemberController 라는 객체를 생성해서 넣어둔다 (controller 라는 어노테이션이 있어서)

//    private final MemberService memberService = new MemberService();
    //스프링 컨테이너에서 받아서 쓰도록 해야 한다.
    // => 굳이 컨트롤러마다 다 따로 할 필요가 없음. 그냥 컨테이너에 등록하는 것이 더 맞음.

    private final MemberService memberService;
//    @Autowired private final MemberService memberService; --> 필드주입

//    @Autowired -> 세터방식. 무조건 PUBLIC 이어야 -> 중간에 바꿔치기 될 수도 있음.
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }

    // 컨테이너의 멤버서비스와 연결시켜주는 역할.
  @Autowired // dependency injection
  // 이것은 생성자주입
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createForm(){
      return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
       Member member = new Member();
       member.setName(form.getName());

       memberService.join(member);
       return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
      List<Member> members = memberService.findMembers();
      model.addAttribute("members",members); //리스트 자체를 모델에 담는다
        return "members/memberList"
    }
}
