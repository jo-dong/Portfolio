package himedia.project.service.ideal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.member.Member;
import himedia.project.repository.ideal.IdealRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IdealServiceImpl implements IdealService {
	
	private final IdealRepository idealRepository;
	
	@Override
	public Member save(Member member) {
		idealRepository.save(member);
		return member;
	}
	
	@Override
	public Member getIdeal(Member member) {
		return idealRepository.findById(member.getIdx()).get();
	}
	
}
