package himedia.project.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;
import himedia.project.domain.member.MemberAge;
import himedia.project.domain.member.Region;
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
	public String register(@Valid Member member,
						   BindingResult result,
						   Model model) {
	    log.info("member ID -> {}", member.getMemberId());
	    log.info("member AGE -> {}", member.getMemberAge());
	    if (result.hasErrors()) {
	        return "member/sign-up";
	    }
	    
		service.save(member);
		
		log.info("{}", service.findByMemberId(member.getMemberId()));
		log.info("controller : 전달 완료");
		log.info("Region : {}", member.getRegion());
		return "redirect:/";
	}
	
	@GetMapping("/sign-up")
	public String signUp(Member member, Model model) {
		model.addAttribute("member", member);
		return "member/sign-up";
	}
	
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute Member member, 
    					BindingResult result,
    					HttpServletRequest request,
    					Model model) {
		// 입력된 ID/PW를 가진 회원이 있는지 확인
        Member loginMember = service.login(member.getMemberId(), member.getMemberPw());
        
        if(loginMember == null) {
        	log.info("[null] login member : " + loginMember);
        	model.addAttribute("member", loginMember);
            return "redirect:login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.sessionId, loginMember);
        
        Member userInfo = (Member) session.getAttribute(SessionConst.sessionId);
        
        // 로그인 연동 시간 남으면 해보기
//        session.setAttribute("sessionUser", loginMember);
//        
//        Member userInfo = (Member) session.getAttribute("sessionUser");
        
        model.addAttribute("member", userInfo);
//        model.addAttribute("member", loginMember);
        log.info("성공 member : {}", loginMember);
        log.info("maxInactiveInterval : {}", session.getMaxInactiveInterval());
        
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
	
	@ModelAttribute("region")
	public Region[] regions() {
		return Region.values();
	}
	
	@ModelAttribute("memberAge")
	public MemberAge[] memberAges() {
		return MemberAge.values();
	}
	
	@ModelAttribute("mbti")
	public Mbti[] mbti() {
		return Mbti.values();
	}
	
	@ModelAttribute("gender")
	public Gender[] gender() {
		return Gender.values();
	}
	
	
}