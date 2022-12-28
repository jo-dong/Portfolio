package himedia.project.service.member;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import himedia.project.domain.member.Member;
import himedia.project.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// [회원가입]
	@Override
	public void save(Member member) {
		member.setMemberPw(bCryptPasswordEncoder.encode(member.getMemberPw()));
		memberRepository.save(member);
		log.info("service : 전달 완료");
	}
	
}
