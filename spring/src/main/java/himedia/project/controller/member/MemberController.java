package himedia.project.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.project.domain.member.Member;
import himedia.project.service.member.MemberService;
import himedia.project.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	private final MemberService service;
		
	// 회원가입
	@PostMapping("/sign-up")
	public String register(@Valid @ModelAttribute Member member) {
		service.save(member);
		log.info("controller : 전달 완료");
		return "redirect:/login";
	}
	
	@GetMapping("/sign-up")
	public String signUp() {
		return "member/sign-up";
	}
	
	// 로그인
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
						@RequestParam(value="exception", required=false) String exception,
						Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "member/login";
	}
	
	@PostMapping("/login")
    public String login(@ModelAttribute Member member, 
    					HttpServletRequest request,
    					Model model) {
		// 1) 입력된 ID/PW를 가진 회원이 있는지 확인
        Member loginMember = service.login(member.getMemberId(), member.getMemberPw());
        
        if(loginMember == null) {
        	System.out.println("[null] login member : " + loginMember);
        	System.out.println("[null] member : " + member);
        	
            return "redirect:login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.sessionId, loginMember.getMemberId());

        model.addAttribute("member", loginMember);
        System.out.println("성공 member : " + loginMember);
        return "redirect:/";
    }
	
	// 로그아웃
	@PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "redirect:/";
        }
        session.invalidate();
        return "redirect:/";
    }
	
}