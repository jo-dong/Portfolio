package himedia.project.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import himedia.project.domain.member.Member;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	@Query("select m from Member m where m.memberId=:memberId and m.memberPw=:memberPw")
    public Optional<Member> login(@Param("memberId") String memberId, 
    							  @Param("memberPw") String memberPw);
	
	public Optional<Member> findByMemberId(String memberId);
}
