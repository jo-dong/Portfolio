# Day 18 - 01/06(금)

## 오늘 한 일

```
🎈 로그인/게시판 연동 완료

🎈 게시판 작성자 / 댓글 작성자 -> session에서 불러옴

🎈 이상형 찾기 기능 : CheckBox -> Radio 버튼으로 변경

🎈 MBTI / 성별 / 나이(20대, 30대, ...) / 거주지 -> ENUM으로 변경
```

---

## 진행 상황

> IdealController

```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/ideal")
@Slf4j
public class IdealController {

	private final IdealService idealService;

	@ModelAttribute("region")
	public Region[] regions() {
		return Region.values();
	}

	@ModelAttribute("memberAge")
	public MemberAge[] memberAges() {
		return MemberAge.values();
	}

	@ModelAttribute("mbti")
	public Mbti[] mbti() {
		return Mbti.values();
	}

	@ModelAttribute("gender")
	public Gender[] gender() {
		return Gender.values();
	}

	@GetMapping("/check")
	public String check(Model model) {
		model.addAttribute("member", new Member());
		return "ideal/check-ideal";
	}

	@PostMapping("/check")
	public String checkIdeal(@ModelAttribute Member member,
							 HttpServletRequest request,
							 RedirectAttributes redirectAttributes,
							 Model model) {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute(SessionConst.sessionId);

		model.addAttribute("member", loginMember);

		// 로그인 된 회원이 남성일 경우
		if(loginMember.getGender().equals(Gender.M)) {
			List<Member> femaleList = idealService.getIdealMale(member.getMemberAge(),
																member.getRegion(),
																member.getMbti());
			model.addAttribute("list", femaleList);
			return "ideal/result";
		}
		// 로그인 된 회원이 여성일 경우
		List<Member> maleList = idealService.getIdealFemale(member.getMemberAge(),
															member.getRegion(),
															member.getMbti());
		model.addAttribute("list", maleList);
		return "redirect:/ideal/result";
	}

	@GetMapping("/result")
	public String result() {
		return "ideal/result";
	}

	@GetMapping("/statistic")
	public String statistic(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute(SessionConst.sessionId);

		model.addAttribute("member", member);
		return "ideal/statistic";
	}

}
```

> IdealServiceImpl

```java
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class IdealServiceImpl implements IdealService {

	private final IdealRepository idealRepository;

	@Override
	public List<Member> getIdealMale(MemberAge memberAge, Region region, Mbti mbti) {
		List<Member> maleList = idealRepository.getIdealMale(memberAge, region, mbti);
		return maleList;
	}

	@Override
	public List<Member> getIdealFemale(MemberAge memberAge, Region region, Mbti mbti) {
		List<Member> femaleList = idealRepository.getIdealFemale(memberAge, region, mbti);
		return femaleList;
	}

}
```

> IdealRepository

```java
package himedia.project.repository.ideal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import himedia.project.domain.member.Mbti;
import himedia.project.domain.member.Member;
import himedia.project.domain.member.MemberAge;
import himedia.project.domain.member.Region;

public interface IdealRepository extends JpaRepository<Member, Long> {

//	select A.*
//	from member_info A
//	where member_age like 'TWENTY'
//		and mbti like 'ENTJ'
//	    and region like 'INCHEON'
//	    and gender like 'M';

	final String strMale = "select m from Member m " +
						   "where m.memberAge like :memberAge " +
						   "and m.mbti like :mbti " +
						   "and m.region like :region " +
						   "and m.gender like 'F'";

	@Query(strMale)
	public List<Member> getIdealMale(@Param("memberAge") MemberAge memberAge,
									 @Param("region") Region region,
									 @Param("mbti") Mbti mbti);

	final String strFemale = "select m from Member m " +
						     "where m.memberAge like :memberAge " +
						     "and m.mbti like :mbti " +
						     "and m.region like :region " +
						     "and m.gender like 'M'";

	@Query(strFemale)
	public List<Member> getIdealFemale(@Param("memberAge") MemberAge memberAge,
									   @Param("region") Region region,
									   @Param("mbti") Mbti mbti);
}

```

---

## Trouble Shooting

### 📌 문제 발생

> List[Member] femaleList = idealService.getIdealMale(member.getMemberAge(), member.getRegion(), member.getMbti());
>
> method를 실행 후 model.addAttribute("list", femaleList); 로 보냄.
>
> ↓

```html
<tr th:each="list: ${list}">
  <td th:text="${list.memberName}"></td>
  <td th:text="${list.memberAge}"></td>
  <td th:text="${list.gender}"></td>
  <td th:text="${list.mbti}"></td>
  <td th:text="${list.region}"></td>
</tr>
```

> 위의 thymeleaf로 Data를 받았는데 Data가 View에 표현되지 않았다...

### 📌 원인

> 원인을 Thymeleaf 문법과 Controller에서 1시간 가량 찾아 헤맸는데
>
> 진짜 원인은 return "redirect:/ideal/result" 였다.
>
> redirect를 사용하면 model로 보낸 객체가 전달되지 않는다.

### 📌 해결

> Radio 버튼을 클릭 후 전송하면

<!-- Image 1 -->

> 선택한 항목과 일치하는 회원 정보를 출력한다.

<!-- Image 2 -->

---

## 기타 사항

```
🎈 앞으로 할 일 : 회원 통계 / 게시글 & 댓글 작성자만 수정,삭제 가능하도록(시간 되면)
```
