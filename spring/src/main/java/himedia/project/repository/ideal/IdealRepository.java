package himedia.project.repository.ideal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	// 회원 통계 - 남자
	// 연령대별
	String male1 = "select count(*) " +
				   "from Member m " +
				   "where m.memberAge like :memberAge " +
				   "and m.gender like 'M' " +
				   "group by m.idx";
	@Query(male1)
	public long getMaleCountByMemberAge(@Param("memberAge") MemberAge memberAge);
	
	// 거주지별
	String male2 = "select count(*) " +
			  	   "from Member m " +
			  	   "where m.region like :region " +
			  	   "and m.gender like 'M' " +
			  	   "group by m.idx";
	@Query(male2)
	public long getMaleCountByRegion(@Param("region") Region region);
	
	// MBTI별
	String male3 = "select count(*) " +
			  	   "from Member m " +
			  	   "where m.mbti like :mbti " +
			  	   "and m.gender like 'M' " +
			  	   "group by m.idx";
	@Query(male3)
	public long getMaleCountByMbti(@Param("mbti") Mbti mbti);
	
	// ------------------------------------------------------------------
	// 회원 통계 - 여자
	// 연령대별
	String female1 = "select count(*) " +
				  	 "from Member m " +
				  	 "where m.memberAge like :memberAge " +
				  	 "and m.gender like 'F' " +
				  	 "group by m.idx";
	@Query(female1)
	public long getFemaleCountByMemberAge(@Param("memberAge") MemberAge memberAge);
	
	// 거주지별
	String female2 = "select count(*) " +
			  	   	 "from Member m " +
			  	     "where m.region like :region " +
			  	   	 "and m.gender like 'F' " +
			  	   	 "group by m.idx";
	@Query(female2)
	public long getFemaleCountByRegion(@Param("region") Region region);
	
	// MBTI별
	String female3 = "select count(*) " +
			  	   	 "from Member m " +
			  	   	 "where m.mbti like :mbti " +
			  	   	 "and m.gender like 'F' " + 
			  	   	 "group by m.idx";
	@Query(female3)
	public long getFemaleCountByMbti(@Param("mbti") Mbti mbti);
	
}
