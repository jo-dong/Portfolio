package himedia.project.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import himedia.project.domain.Member;
import himedia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MarryServiceImpl implements MarryService {
	
	private final MemberRepository repository;
	
	@Override
	public Member join(Member member) {
		repository.save(member);
		return member;
	}
	
	@Override
	public Member loginCheck(String id, String pw) {
		Member member = repository.findByMemberId(id).get();
		return member;
	}
	
}
