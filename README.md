# 모임 서비스 만들기

주최자가 모임 참여자들의 음식 기호를 고려하여 모임을 이끌어 갈 수 있게 도와주는 서비스

## 사용기술

- Java 17
- Spring Boot 2.7.12
- Spring Data Jpa
- H2

## 실행

> mvn package  
> java -jar -Dspring.profiles.active=local moim.jar

## 기능 목록

- 회원가입 기능
- 로그인 기능
- 내 정보 업데이트 기능
- 모임 주최자로 활동하기 / 모임 참여자로 활동하기
- 내 정보 보기 기능

### 회원 가입 기능

- 회원가입시 모임 주최자 혹은 참여자로 가입할 수 있다.
- 주최자로 가입시 필요한 정보
  - 회원번호, 이름, 생년월일, 성별, 아이디, 비밀번호, 이메일, 소속
- 참여자로 가입시 필요한 정보
  - 회원번호, 이름, 생년월일, 성별, 아이디, 비밀번호, 이메일, 취식을 제한하는 재료, 자기소개
- 회원가입시 입력한 비밀번호가 정책에 부합하는지 확인


### 로그인

- 아이디와 비밀번호를 이용해 로그인
- 다른 API 들을 호출 할 수 있는 인증토큰 발급


### 내 정보 업데이트

- 회원가입시 제출한 정보 수정
- 비밀번호 변경시 비밀번호가 정책에 부합하는지 확인
- 인증토큰 필요


### 모임 주최자/참여자로 활동하기

- 주최자 였던 사람이 참여자로도 활동할 수 있고, 참여자 였던 사람이 주최자로 활동할 수 있음
  - 주최자는 추가 정보를 입력받을 수 있다. 역할은 주죄자를 유지한다.
  - 참여자는 추자 정보를 입력받아 역할을 주최자로 변경할 수 있다.
- 인증토큰 필요


### 내 정보 보기

- 사용자는 자신의 정보를 조회할 수 있음
- 비밀번호는 노출 X
  - 주최자 : 회원번호, 이름, 생년월일, 성별, 아이디, 이메일, 소속 노출
  - 참여자 : 회원번호, 이름, 생년월일, 성별, 아이디, 이메일, 취식을 제한하는 재료, 자기소개 노출


## TODO

모든 기능에 유효성 검증, 테스트 코드 작성 과정이 포함되어 있다.

- [x] 회원가입 기능
  - POST /intsvc/admin/homepage/v1/member
- [x] 로그인 기능
  - POST /intsvc/admin/homepage/v1/login
- [x] 내 정보 업데이트 기능
  - PUT /intsvc/admin/homepage/v1/member/{memberId} 
- [x] 모임 주최자로 활동하기 / 모임 참여자로 활동하기
  - PUT /intsvc/admin/homepage/v1/member/{memberId}/role
- [x] 내 정보 보기 기능
  - GET /intsvc/admin/homepage/v1/member/{memberId}



## 개선사항
- 모임 주최자로도 활동하기 / 모임 참여자로도 활동하기를 구현하다보니 구조 변경의 필요성을 느낌
  - 주최자와 참여자의 역할을 동시에 하는 사람은 Role 을 어떻게 해야 하는가
  - 일단은 해석한 대로 구현함
    - 주최자는 추가 정보를 입력받을 수 있다. 역할은 주최자를 유지한다.
    - 참여자는 추가 정보를 입력받아 역할을 주최자로 변경할 수 있다.
    - 소속을 없애면 주최자를 참여자로 변경할 수 있다.
- 유효성 검증을 어떻게 작성할 것인가
  - spring validation 을 사용할 것인지 값 검증 로직을 직접 구현할 것인지
- dto -> entity, entity -> dto 변환 로직을 service 에 위치할 것인지 dto에 위치할 것인지 고민
  - service 클래스에 위치하는게 좋아보인다
  - entity 에 위치하면 패키지 의존성 방향이 양방향이 되기 때문에 entity 에 위치하는 것은 안좋아 보임
  - dto 에 위치시키는 것도 굳이 dto 에 변환로직을 추가해야 하나 싶다 
- 권한 관련 로직을 인터셉터로 변경하면 코드 중복을 제거할 수 있을 것 같다.

