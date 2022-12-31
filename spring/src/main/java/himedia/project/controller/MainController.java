package himedia.project.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import himedia.project.domain.member.Member;
import himedia.project.repository.member.MemberRepository;
import himedia.project.session.SessionConst;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(session == null) {
            return "/member/login";
        }

        String memberId = (String)session.getAttribute(SessionConst.sessionId);
        Optional<Member> findMember = memberRepository.findByMemberId(memberId);
        Member member = findMember.orElse(null);

        if(member == null) {
            return "/member/login";
        }

        model.addAttribute("member", member);
        return "main";
    }
	
//	@GetMapping("/logout")
//	public String logout() {
//		
//	}
	
}
