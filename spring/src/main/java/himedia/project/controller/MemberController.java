package himedia.project.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.project.domain.Member;
import himedia.project.service.MarryService;
import himedia.project.service.SessionManager;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	@Autowired private final MarryService service;
	@Autowired private final SessionManager sessionManager;
		
	// 회원가입
	@PostMapping("/sign-up")
	public String register(@ModelAttribute Member member) {
		service.join(member);
		return "redirect:main";
	}
	
	@GetMapping("/sign-up")
	public String signUp() {
		return "member/sign-up";
	}
	
	// 로그인
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		Member member = (Member)sessionManager.getSession(request);
		
		if(member == null)
			return "main";
		
		model.addAttribute("member", member);
		return "login-main";
	}
	
	@PostMapping("/loginCheck")
	public String loginCheck(@Valid @ModelAttribute Member member,
									BindingResult bindingResult,
									HttpServletResponse response) {
		if(bindingResult.hasErrors()) {
			return "member/login";
		}
		
		Member loginMember = service.loginCheck(member.getMemberId(), member.getMemberPw());
		
		if(loginMember == null) {
			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "member/login";
		}
		
		Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getMemberId()));
		response.addCookie(idCookie);
		
		// 세션 생성 및 회원정보 보관
		sessionManager.createSession(loginMember, response);
		
		return "redirect:main";
	}
	
	// 로그아웃 처리
	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		sessionManager.expire(request);
		return "redirect:main";
	}
	
}

