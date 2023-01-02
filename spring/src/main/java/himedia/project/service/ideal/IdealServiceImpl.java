package himedia.project.service.ideal;

<<<<<<< HEAD
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
=======
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

>>>>>>> f235dcb242764c01c6b88382bdf22d8104db2f61
import himedia.project.domain.member.Member;
import himedia.project.repository.ideal.IdealRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IdealServiceImpl implements IdealService {
	
	private final IdealRepository idealRepository;
	
	@Override
<<<<<<< HEAD
=======
	public Member save(Member member) {
		idealRepository.save(member);
		return member;
	}
	
	@Override
>>>>>>> f235dcb242764c01c6b88382bdf22d8104db2f61
	public Member getIdeal(Member member) {
		return idealRepository.findById(member.getIdx()).get();
	}
	
<<<<<<< HEAD
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
=======
>>>>>>> f235dcb242764c01c6b88382bdf22d8104db2f61
}
