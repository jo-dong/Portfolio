package himedia.project.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberAge {
	TWENTY("20대"), THIRTY("30대"),
	FOURTY("40대"), FIFTY("50대");
	
	private final String description;
}
