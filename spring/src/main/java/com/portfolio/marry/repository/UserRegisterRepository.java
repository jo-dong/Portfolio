package com.portfolio.marry.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.marry.domain.UserDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRegisterRepository {

	@Autowired private EntityManager em;
	
	// 회원 가입
	public UserDTO save(UserDTO user) {
		em.persist(user);
		return user;
	}

	// 마이 페이지
	public Optional<UserDTO> myPage() {
		
		return Optional.empty();
	}
	
	

}
