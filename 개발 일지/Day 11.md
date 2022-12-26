# Day 11 - 12/26(월)

## 오늘 한 일

```
🎈 Domain/Controller/Repository가 너무 난잡한 것 같아 다시 정리

🎈 로그인 구현에 관련해 Session/Cookie에 대해 알아봄

🎈 게시판 관련 DB의 charter set을 utf8mb4로 생성하여 후에 게시판 title/content에 이모지(Win + .)를 사용하게 함
```

---

## 진행 상황

> Session/Cookie에 대해 정리해놓은 글과 코드를 보고 참고하여 로그인 코드를 작성하였지만 여전히 잘 모르겠다.. 더 공부가 필요😢
>
> > 출처 : https://catsbi.oopy.io/0c27061c-204c-4fbf-acfd-418bdc855fd8

> 너무 난잡하고 Naming도 마음대로인 것 같아 프로젝트를 재생성하여 아래와 같이 다시 정리

<!-- Image -->

---

## Trouble Shooting

```sql
🎈 코드를 정리하며 회원가입 코드를 다시 작성 후 DB 확인

-> Error는 발생하지 않았지만 DB에 데이터가 저장되지 않음

-> MySQL과의 연동에도 문제가 없었고, DB 저장 코드에도 문제를 발견하지 못함

-> 오늘 수업시간에 Auto Commit을 사용하지 않음으로 바꿨던 것이 생각나서 직접 commit;을 작성했더니 DB에 값이 저장됨을 확인
```

<!-- Image -->

---

## 기타 사항

```
🎈 앞으로 로그인, 체크박스, 게시판 등 남은 작업이 많다.

순서를 잘 정리해서 최대한 효율적으로 작업을 진행해야한다.
```
