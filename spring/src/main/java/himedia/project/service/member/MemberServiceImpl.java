package himedia.project.service.member;

import java.util.Optional;

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
	}
	
	// [로그인]
	@Override
    public Member login(String memberId, String password) {
		Optional<Member> loginMember = memberRepository.findByMemberId(memberId);
		
		if(bCryptPasswordEncoder.matches(password, loginMember.get().getMemberPw())) {
			// 비밀번호가 일치할 경우
			return loginMember.get();
		}
		else {	// 비밀번호가 불일치 할 경우
			return null;
		}
    }
	
	@Override
	public Member findByMemberId(String memberId) {
		Member member = memberRepository.findByMemberId(memberId).get();
		return member;
	}
	
}
