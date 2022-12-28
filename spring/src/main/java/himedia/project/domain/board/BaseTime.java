package himedia.project.domain.board;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/**
 * 시간 도메인
 * 
 * @MappedSuperclass
 * : 부모 클래스를 Entity로 선언하면 테이블이 별도로 생성
 * 		-> MappedSuperclass를 선언하면 테이블이 생성되지 않고 상속 가능
 * 
 * @EntityListeners
 * : 상태를 관찰하는 리스너 생성 / main class에 @EnableJpaAuditing 필요
 * 		-> insert = createDate / update = updateDate
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {
	
	@CreatedDate
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(name = "update_date")
	private LocalDateTime updateDate;
}
