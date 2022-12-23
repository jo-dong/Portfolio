# Day 10 - 12/23(금)

# 오늘 한 일

```
🎈 MySQL과 연동

🎈 sign-up(회원가입) 후 main page로 redirect

🎈 Login 코드
```

---

## 진행 상황

```
🎈 build.gradle 파일에 dependencies
       implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	   implementation 'mysql:mysql-connector-java'
    추가

🎈 application.properties 파일에
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/marry?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    spring.datasource.username=root
    spring.datasource.password=1111
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.database=mysql
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
추가
```

---

## Trouble Shooting

- 문제 발생 1

```html
[application.property]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
<!-- localhost:8080으로 연동을 시도했더니 아래 사진과 같은 Error 발생-->
spring.datasource.url=jdbc:mysql://localhost:8080/marry?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=root spring.datasource.password=1111
spring.jpa.show-sql=true spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect >
```

<!-- image -->

- 해결

```html
[application.property]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
<!-- MySQL의 Port 번호 3306을 적어줘야 연동이 된다 -->
spring.datasource.url=jdbc:mysql://localhost:3306/marry?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=root spring.datasource.password=1111
spring.jpa.show-sql=true spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect >
```

---

- 문제 발생 2

  > save method 실행 시 아래와 같은 Error 발생

  <!-- image 2 -->

- 해결 : id에 auto_increment를 붙여주어 자동 생성되게 한다.

```sql
create table userinfo(
   id bigint,
   userId varchar(255),
   userPw varchar (255),
   userName varchar(255),
   userAge int,
   gender varchar(10),
   phoneNum varchar(15),
   MBTI varchar(4),
   address varchar(255),
   primary key(id)
);
```

```sql
create table userinfo(
   id bigint auto_increment,
   userId varchar(255),
   userPw varchar (255),
   userName varchar(255),
   userAge int,
   gender varchar(10),
   phoneNum varchar(15),
   MBTI varchar(4),
   address varchar(255),
   primary key(id)
);
```

---

## 기타 사항

> DB Table에 들어가있는 row를 삭제할 때는 반드시 id(Key)를 이용해서 Delete를 진행해야함.

Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column. To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.

```sql
delete from userinfo where userName like '강민경'   (X)

                    ↓

delete from userinfo where id in(2,3,4);           (O)

```

> 로그인은 쉽다고 생각했는데 Session, Cookie, 유효성 검사 등 모르는 부분이 너무 많다. 당분간은
> 로그인에 관한 공부를 진행해야 할 듯 하다.
