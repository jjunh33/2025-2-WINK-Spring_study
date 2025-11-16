package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //여러 개의 MemberService 인스턴스를 생성할 필요 X -> 스프링 컨테이너에 등록해서 하나만 사용
    private final MemberService memberService;

    @Autowired //스프링 컨테이너에 있는 MemberService를 주입(Dependency Injection)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //회원 가입 페이지
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //form에서 post로 넘어온 요청을 처리
    public String create(MemberForm form){
        //form에서 받아온 이름으로 Member의 이름을 set
        Member member = new Member();
        member.setName(form.getName());

        //service에 가입
        memberService.join(member);

        //redirect를 이용해서 홈 화면으로 복귀
        return "redirect:/";
    }

    @GetMapping("/members") //회원 조회
    public String list(Model model){
        //등록된 모든 Member를 리스트로 가져와 model에 담고 넘김
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
