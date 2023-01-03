package himedia.project.service.ideal;

import java.util.List;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;

public interface IdealService {
	public Member getIdeal(Member member);
	public List<Member> findByGender(Gender gender);
	public List<Member> findByMemberAge(Integer memberAge);
	public List<Member> findByRegion(List<String> region);
	public List<Member> findByMbti(Mbti mbti);
}
