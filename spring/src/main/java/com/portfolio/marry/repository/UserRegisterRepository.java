package com.portfolio.marry.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
	
//	// 로그인 - Session, cookie, 유효성 검사, 비밀번호 암호화/복호화 공부 후 진행
//	public Optional<UserDTO> loggingIn(String userId, String userPw) {
//		UserDTO user = new UserDTO();
//		// 입력된 id와 같은 id를 가진 row 조회
//		List<UserDTO> checkId = em.createQuery("select u from userinfo u where u.userId = :userId", UserDTO.class)
//				  .setParameter("userId", user.getUserId()).getResultList();
//		
//		Optional<UserDTO> member = Optional.ofNullable(checkId.get(0));
//		
//		// 입력된 비밀번호와 같은 pw를 가진 row 조회
//		if(member.get().getUserId().equals(userId)) {
//			List<UserDTO> checkPw = em.createQuery("select u from userinfo u where u.userPw = :userPw", UserDTO.class)
//					  .setParameter("userPw", user.getUserPw()).getResultList();
//			
//			UserDTO pwCheck = checkPw.get(0);
//			
//			if(pwCheck.getUserPw().equals(userPw)) {
//				return member;
//			}
//		}
//		return Optional.empty();
//	}

	// 마이 페이지
	public Optional<UserDTO> myPage() {
		
		return Optional.empty();
	}
	
	

}
