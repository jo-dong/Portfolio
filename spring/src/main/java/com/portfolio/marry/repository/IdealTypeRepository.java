package com.portfolio.marry.repository;

import java.util.List;
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
public class IdealTypeRepository {

	@Autowired private EntityManager em;
	
	// 이상형 검색
	public Optional<UserDTO> findByAge(String age) {
		String str = "";
		em.createQuery(str, UserDTO.class);
		return Optional.empty();
	}
	
	public Optional<UserDTO> findByAddress(String residence) {
		
		return Optional.empty();
	}

	public Optional<UserDTO> findByMbti(String mbti) {
	
	return Optional.empty();
}
	
}
