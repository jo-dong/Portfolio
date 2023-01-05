package himedia.project.repository.ideal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import himedia.project.domain.member.Gender;
import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;
import himedia.project.domain.member.MemberAge;
import himedia.project.domain.member.Region;

public interface IdealRepository extends JpaRepository<Member, Long> {
	
//	select A.*
//	from member_info A
//	where member_age like 'TWENTY' 
//		and mbti like 'ENTJ'
//	    and region like 'INCHEON'
//	    and gender like 'M';
	
	final String strMale = "select m from Member m " +
						   "where m.memberAge like :memberAge " +
						   "and m.mbti like :mbti " +
						   "and m.region like :region " +
						   "and m.gender like 'F'";
	
	final String native1 = "select m.member_name, m.member_age, m.gender, m.mbti, m.region " + 
						   "from member_info m " + 
						   "where m.member_age like :memberAge " + 
						   "and m.mbti like :mbti " +
						   "and m.region like :region " +
						   "and m.gender like 'F'";
	
//	@Query(value = native1, nativeQuery = true)
	@Query(strMale)
	public List<Member> getIdealMale(@Param("memberAge") MemberAge memberAge,
									 @Param("region") Region region,
									 @Param("mbti") Mbti mbti);
	
	final String strFemale = "select m from Member m " +
						     "where m.memberAge like :memberAge " +
						     "and m.mbti like :mbti " +
						     "and m.region like :region " +
						     "and m.gender like 'M'";
	
	@Query(strFemale)
	public List<Member> getIdealFemale(@Param("memberAge") MemberAge memberAge,
									   @Param("region") Region region,
									   @Param("mbti") Mbti mbti);
}
