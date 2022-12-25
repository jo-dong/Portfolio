package com.portfolio.marry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.marry.domain.UserDTO;
import com.portfolio.marry.repository.IdealTypeRepository;
import com.portfolio.marry.repository.UserRegisterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarryService {
	
	@Autowired private final UserRegisterRepository userRepository;
	@Autowired private final IdealTypeRepository idealRepository;
	
	// 회원 가입
	public UserDTO join(UserDTO user) {
		userRepository.save(user);
		return user;
	}
	
	// 로그인
	public void login(String userId, String userPw) {
		userRepository.loggingIn(userId, userPw);
	}
	
	// 이상형 검색
	public void search(String age, String residence, String mbti) {
		idealRepository.findIdeal(age);
	}
	
}
