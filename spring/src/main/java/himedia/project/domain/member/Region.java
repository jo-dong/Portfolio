package himedia.project.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Region {
	SEOUL("서울"), INCHEON("인천"), DAEGU("대구"), GWANGJU("광주"), 
	DAEJEON("대전"), ULSAN("울산"), BUSAN("부산"), JEJU("제주");
	
	private final String description;
}
