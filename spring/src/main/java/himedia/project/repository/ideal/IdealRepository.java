package himedia.project.repository.ideal;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.domain.member.Member;

public interface IdealRepository extends JpaRepository<Member, Long> {
	
}
