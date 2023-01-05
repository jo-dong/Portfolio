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
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(session == null) {
            return "/member/login";
        }
        Member memberSession = (Member)session.getAttribute(SessionConst.sessionId);
        Optional<Member> findMember = memberRepository.findByMemberId(memberSession.getMemberId());
        Member member = findMember.orElse(null);

        if(member == null) {
            return "/member/login";
        }
        
        log.info("멤버 : " + member);
        model.addAttribute("member", member);
        return "main";
    }
	
}
