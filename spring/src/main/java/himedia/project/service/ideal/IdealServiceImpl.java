package himedia.project.service.ideal;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;
import himedia.project.repository.ideal.IdealRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IdealServiceImpl implements IdealService {
	
	private final IdealRepository idealRepository;
	
	@Override
	public Member getIdeal(Member member) {
		return idealRepository.findById(member.getIdx()).get();
	}
	
	@Override
	public List<Member> findByGender(Gender gender) {
		return idealRepository.findByGender(gender);
	}
	
	@Override
	public List<Member> findByMemberAge(Integer memberAge) {
		return idealRepository.findByMemberAge(memberAge);
	}
	
	@Override
	public List<Member> findByMbti(Mbti mbti) {
		return idealRepository.findByMbti(mbti);
	}

	@Override
	public List<Member> findByRegion(List<String> region) {
		return idealRepository.findByRegionIn(region);
	}
}
