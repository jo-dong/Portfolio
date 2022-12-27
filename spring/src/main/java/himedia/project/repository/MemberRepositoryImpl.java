package himedia.project.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import himedia.project.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {
	
	@Autowired private final EntityManager em;
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		log.info("repo : 저장 완료");
		return member;
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
