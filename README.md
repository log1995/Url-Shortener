# Url-Shortener
- 단축 URL 생성 서비스 프로젝트
---
프로젝트 목표
---
- bit.ly와 같은 단축 URL 생성 서비스를 만드는 것이 목표입니다.
- 도메인 주도 설계(DDD)를 기반으로 애플리케이션을 설계합니다.
- 테스트코드를 통해 설계한 API를 검증합니다.

프로젝트 사용기술
---
- java, spring(boot), H2, JPA, Intellij, junit

요구사항
---
1. 단축 URL 생성, 단축 URL 리다이렉션, 단축 URL 요청 횟수를 반환해주는 REST API 구현
2. 각 기능과 제약에 대한 단위테스트 작성

상세 요구사항
---
1. 단축 URL 생성
  - 단축 URL은 10자리의 알파벳 대소문자와 숫자로 이루어진 랜덤한 문자열이다. 
  - 잘못된 URL(http://, https://가 붙지 않은 URL)은 단축 URL 생성 불가.
  - 동일한 단축 URL은 생성되지 않는다.

2. 단축 URL 리다이렉션
  - 단축 URL로 요청을 보내면, DB 에 저장된 해당 단축 URL의 원본 URL로 리다이렉트 시켜준다.

3. 단축 URL 요청 횟수를 반환
  - 해당 단축 URL의 요청 횟수를 반환
