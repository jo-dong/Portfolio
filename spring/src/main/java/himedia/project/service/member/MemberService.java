package himedia.project.service.member;

import himedia.project.domain.member.Member;

public interface MemberService {
	public void save(Member member);
	public Member login(String memberId, String password);
	public Member findByMemberId(String memberId);
}
