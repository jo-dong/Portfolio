package com.portfolio.marry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.marry.domain.UserDTO;
import com.portfolio.marry.service.MarryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MarryUserController {
	
	private final MarryService service;
	
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}
	
//	@PostMapping("/user/login")
//	public String userLogin(@RequestParam String userId,
//							@RequestParam String userPw) {
//		int i = service.login(userId, userPw);
//		return "/user/main";
//	}
	
	@GetMapping("/user/sign-up")
	public String signUp() {
		return "user/sign-up";
	}
	
	@GetMapping("/user/main")
	public String main() {
		return "user/main";
	}
	
	@PostMapping("/user/sign-up")
	public String register(@ModelAttribute UserDTO user) {
		service.join(user);
		return "redirect:main";
	}
	
	@PostMapping("/ideal/check")
	public String check(@RequestParam(value = "age", required = false) String age,
						@RequestParam(value = "residence", required = false) String residence, 
						@RequestParam(value = "mbti", required = false) String mbti) {
		service.search(age, residence, mbti);
		return "/ideal/result";
	}
}
