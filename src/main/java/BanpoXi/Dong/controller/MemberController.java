package BanpoXi.Dong.controller;

import BanpoXi.Dong.domain.Member;
import BanpoXi.Dong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 스프링컨테이너가 이걸 생성해서 들고있다.
    // 컨트롤러 , 이걸 스프링 빈(콩)이라고 함
    //스프링이 관리하게되면 내가직접 new 하지않고, 스프링이 만들어서 씀

    //하나만 만들어놓고 공용으로 쓰면 좋음
    private final MemberService memberService;

    @Autowired//연결시켜주는거야 그래서 와이어드
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    //getmapping : 데이터 조회 시 많이 사용된다.
    public String createForm() {
        return "members/createMemberForm";
    }

    //postMapping : form 같은데다가 데이터를 넣어서 보낼때 많이 씀
    @PostMapping("/members/new")
    public String createUser(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());


        System.out.println(member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    //동일한 url 에 get, post 둘다 사용중인데 둘다 쓸 수 있고, 웹페이지 요청유형에 따라 알아서 잘 호출된


    @GetMapping("/members")
    public String list(Model model) {
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members", memberList);

        return "members/memberList";
    }
}
