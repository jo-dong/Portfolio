package himedia.project.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
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
	public String register(@Valid @ModelAttribute Member member,
						   BindingResult result,
						   Model model) {
	    
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
        session.setAttribute(SessionConst.sessionId, loginMember.getMemberId());

        model.addAttribute("member", loginMember);
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
	public Map<String, String> regions() {
		Map<String, String> region = new LinkedHashMap<>();
		region.put("SEOUL", "서울");
		region.put("INCHEON", "인천");
		region.put("DAEGU", "대구");
		region.put("GWANGJU", "광주");
		region.put("DAEJEON", "대전");
		region.put("ULSAN", "울산");		
		region.put("BUSAN", "부산");
		region.put("JEJU", "제주");
		return region;
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