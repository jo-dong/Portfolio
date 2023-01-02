package himedia.project.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender {
	M("남성"), F("여성");
	
	private final String description;
}
