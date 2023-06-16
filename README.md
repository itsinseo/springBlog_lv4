![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=Post%20프로젝트&fontSize=70)

# Spring 개인과제

### 🏁 Goal:  스프링 부트로 로그인 기능이 없는 나만의 블로그 백엔드 서버 만들기

## 서비스 완성 요구사항

1. 아래의 요구사항을 기반으로 Use Case 그려보기
    - 손으로 그려도 됩니다.
    - cf. https://narup.tistory.com/70
2. 전체 게시글 목록 조회 API
    - 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
3. 게시글 작성 API
    - 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기
4. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
      (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
5. 선택한 게시글 수정 API
    - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
6. 선택한 게시글 삭제 API
    - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기

## API 명세서

| Method | URL            | Request                                                                                                                  | Response                                                                                                                                                                                                                                                                                                                                                                                                             |
|:-------|:---------------|:-------------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST   | /api/post      | {<br/>"postName":"postName",<br/>"userName":"userName",<br/>"postContent":"postContent",<br/>"password":"password"<br/>} | {<br/>"id": 4,<br/>"postName": "제목",<br/>"userName": "작성자",<br/>"postContent": "더미2",<br/>"createdAt": "2023-06-16T13:18:43.820994",<br/>"modifiedAt": "2023-06-16T13:18:43.820994"<br/>}                                                                                                                                                                                                                            |
| GET    | /api/posts     | -                                                                                                                        | \[<br/>{<br/>"id": 5,<br/>"postName": "제목",<br/>"userName": "작성자",<br/>"postContent": "수정 최종 테스트",<br/>"createdAt": "2023-06-16T13:18:45.071075",<br/>"modifiedAt": "2023-06-16T13:36:29.43741"<br/>},<br/>{<br/>"id": 4,<br/>"postName": "제목",<br/>"userName": "작성자",<br/>"postContent": "더미2",<br/>"createdAt": "2023-06-16T13:18:43.820994",<br/>"modifiedAt": "2023-06-16T13:18:43.820994"<br/>},<br/>...<br/>\] |
| GET    | /api/post/{id} | -                                                                                                                        | {<br/>"id": 4,<br/>"postName": "제목",<br/>"userName": "작성자",<br/>"postContent": "더미2",<br/>"createdAt": "2023-06-16T13:18:43.820994",<br/>"modifiedAt": "2023-06-16T13:18:43.820994"<br/>}                                                                                                                                                                                                                            |
| PUT    | /api/post/{id} | {<br/>"postName":"postName",<br/>"userName":"userName",<br/>"postContent":"postContent",<br/>"password":"password"<br/>} | {<br/>"id": 4,<br/>"postName": "제목",<br/>"userName": "작성자",<br/>"postContent": "더미2",<br/>"createdAt": "2023-06-16T13:18:43.820994",<br/>"modifiedAt": "2023-06-16T13:18:43.820994"<br/>}                                                                                                                                                                                                                            |
| DELETE | /api/post/{id} | {<br/>"password":"password'<br/>}                                                                                        | {<br/>"success":true<br/>}                                                                                                                                                                                                                                                                                                                                                                                           |

## 프로젝트 진행 중 발생했던 문제와 의문점
https://coding1ki.tistory.com/80