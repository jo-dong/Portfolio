package himedia.project.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import himedia.project.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
	
	@Autowired private final EntityManager em;
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}
	
	@Override
	public boolean loginCheck(Member member, HttpSession session) {
		return false;
	}
	
	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Member viewMember(Member member) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Member> findByMemberId(String memberId) {
		String str = "select m from member_info m where m.member_id=:member_id";
		List<Member> result = em.createQuery(str, Member.class)
							  .setParameter("member_id", memberId)
							  .getResultList();
		return result.stream().findAny();
	}
	
}
