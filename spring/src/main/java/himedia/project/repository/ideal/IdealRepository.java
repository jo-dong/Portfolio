package himedia.project.repository.ideal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;

public interface IdealRepository extends JpaRepository<Member, Long> {
	
	List<Member> findByGender(Gender gender);
	List<Member> findByMemberAge(Integer memberAge);
	List<Member> findByMbti(Mbti mbti);
	List<Member> findByRegionIn(List<String> region);
	
//	@Query("select Member m from Member m where m.memberAge= :memberAge")
//	@Transactional
//	public List<Member> findByGender(@Param("memberAge") Integer memberAge);
//	
//	@Query("select Member m from Member m where m.memberAge= :memberAge")
//	@Transactional
//	public List<Member> findByMemberAge(@Param("memberAge") Integer memberAge);
//	
//	@Query("select Member m from Member m where m.mbti= :mbti")
//	@Transactional
//	public List<Member> findByMbti(@Param("mbti") Mbti mbti);
	
//	@Query("select Member m from Member m where m.region in(region)")
//	@Transactional
//	public List<Member> findByRegion(@Param("region") List<String> region);
}
