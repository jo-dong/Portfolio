package himedia.project.controller.ideal;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Member;
import himedia.project.service.ideal.IdealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ideal")
@Slf4j
public class IdealController {
	
	private final IdealService idealService;
	
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
	
	@ModelAttribute("gender")
	public Gender[] gender() {
		return Gender.values();
	}
	
	@GetMapping("/check")
	public String check(Model model) {
		model.addAttribute("member", new Member());
		return "ideal/check";
	}
	
	// 미완 - Entity(를 List로 짜야하는것인지)랑 Table
	// 체크박스 데이터 자동으로 넘어간다고 하는데 메소드 어케 짜야하는지
	@PostMapping("/check")
	public String checkIdeal(@ModelAttribute Member member,
							 RedirectAttributes redirectAttributes) {
		log.info("member.regions ==> {}", member.getRegion());
		log.info("member.gender ==> {}", member.getGender());
		log.info("member.mbti ==> {}", member.getMbti());
		
		
		
		return "redirect:/ideal/result";
	}
}