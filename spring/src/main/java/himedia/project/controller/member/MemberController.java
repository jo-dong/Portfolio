package himedia.project.controller.member;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import himedia.project.domain.member.Member;
import himedia.project.service.member.MemberService;
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
		return "redirect:member/login";
	}
	
	@GetMapping("/sign-up")
	public String signUp() {
		return "member/sign-up";
	}
	
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@ResponseBody
	@GetMapping("/user")
    public String user() {
        return "user";
    }

	@ResponseBody 
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
	
}

