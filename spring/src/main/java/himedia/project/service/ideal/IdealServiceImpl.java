package himedia.project.service.ideal;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;
import himedia.project.domain.member.MemberAge;
import himedia.project.domain.member.Region;
import himedia.project.repository.ideal.IdealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class IdealServiceImpl implements IdealService {
	
	private final IdealRepository idealRepository;

	@Override
	public List<Member> getIdealMale(MemberAge memberAge, Region region, Mbti mbti) {
		List<Member> maleList = idealRepository.getIdealMale(memberAge, region, mbti);
		log.info("실행완료");
		return maleList;
	}

	@Override
	public List<Member> getIdealFemale(MemberAge memberAge, Region region, Mbti mbti) {
		List<Member> femaleList = idealRepository.getIdealFemale(memberAge, region, mbti);
		log.info("실행완료");
		return femaleList;
	}
	
}
