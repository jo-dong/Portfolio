package himedia.project.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import himedia.project.domain.Member;
import himedia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MarryServiceImpl implements MarryService {
	
	private final MemberRepository repository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// [회원가입]
	@Override
	public void save(Member member) {
		member.setMemberPw(bCryptPasswordEncoder.encode(member.getMemberPw()));
		repository.save(member);
		log.info("service : 전달 완료");
	}
	
}
