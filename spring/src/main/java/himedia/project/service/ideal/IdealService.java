package himedia.project.service.ideal;

import java.util.List;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;
import himedia.project.domain.member.MemberAge;
import himedia.project.domain.member.Region;

public interface IdealService {
	public List<Member> getIdealMale(MemberAge memberAge,
									 Region region,
									 Mbti mbti);
	public List<Member> getIdealFemale(MemberAge memberAge,
									   Region region,
									   Mbti mbti);
}
