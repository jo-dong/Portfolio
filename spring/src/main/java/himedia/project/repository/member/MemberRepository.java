package himedia.project.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
