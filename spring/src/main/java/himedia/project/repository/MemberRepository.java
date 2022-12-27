package himedia.project.repository;

import java.util.Optional;

import himedia.project.domain.Member;

public interface MemberRepository {
	public Member save(Member member);
	public Optional<Member> findByMemberId(String memberId);
}
