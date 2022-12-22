package com.portfolio.marry.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

import com.portfolio.marry.domain.UserDTO;

@SpringBootTest
@Transactional
class UserRegisterRepositoryTest {
	
	@Autowired private UserRegisterRepository repo;

	@Test
	void save() {
		UserDTO user = new UserDTO("aaa", "1234", "홍길동", 
								   23, "남성", "01012341234",
								   "ENFP", "서울");
		UserDTO savedUser = repo.save(user);
		
		assertThat(user.getUserName()).isEqualTo(savedUser.getUserName());
	}

}
