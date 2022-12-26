package himedia.project.repository;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import himedia.project.domain.Member;

public interface MemberRepository {
	public Member save(Member member);
	public boolean loginCheck(Member member, HttpSession session);
	public Member viewMember(Member member);
	public void logout(HttpSession session);
	public Optional<Member> findByMemberId(String memberId);
}
