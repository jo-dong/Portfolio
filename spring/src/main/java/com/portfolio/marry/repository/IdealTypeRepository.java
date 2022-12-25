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
public class IdealTypeRepository {

	@Autowired private EntityManager em;
	
	// 이상형 검색
	public Optional<UserDTO> findIdeal(String age) {
		String str = "";
		em.createQuery(str);
		return Optional.empty();
	}
	
}
