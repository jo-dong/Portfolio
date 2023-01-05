package himedia.project.controller.ideal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;
import himedia.project.domain.member.MemberAge;
import himedia.project.domain.member.Region;
import himedia.project.service.ideal.IdealService;
import himedia.project.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ideal")
@Slf4j
public class IdealController {
	
	private final IdealService idealService;
	
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
	
	@GetMapping("/check")
	public String check(Model model) {
		model.addAttribute("member", new Member());
		return "ideal/check-ideal";
	}
	
	@PostMapping("/check")
	public String checkIdeal(@ModelAttribute Member member,
							 HttpServletRequest request,
							 RedirectAttributes redirectAttributes,
							 Model model) {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute(SessionConst.sessionId);
		
		model.addAttribute("member", loginMember);
		
		log.info("member -> {}", loginMember);
		
		// 로그인 된 회원이 남성일 경우
		if(loginMember.getGender().equals(Gender.M)) {
			List<Member> femaleList = idealService.getIdealMale(member.getMemberAge(), 
																member.getRegion(), 
																member.getMbti());
			model.addAttribute("list", femaleList);
			return "ideal/result";
		}
		// 로그인 된 회원이 여성일 경우
		List<Member> maleList = idealService.getIdealFemale(member.getMemberAge(),
															member.getRegion(),
															member.getMbti());
		model.addAttribute("list", maleList);
		return "ideal/result";
	}
	
	@GetMapping("/result")
	public String result() {
		return "ideal/result";
	}
	
	@GetMapping("/statistic")
	public String statistic(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute(SessionConst.sessionId);
		
		model.addAttribute("member", member);
		return "ideal/statistic";
	}
	
	
}