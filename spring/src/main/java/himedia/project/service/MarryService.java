package himedia.project.service;

import himedia.project.domain.Member;

public interface MarryService {
	public Member join(Member member);
	public Member loginCheck(String id, String pw);
}
