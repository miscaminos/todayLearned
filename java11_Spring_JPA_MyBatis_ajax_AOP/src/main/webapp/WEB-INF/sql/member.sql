CREATE TABLE member ( 
    id            VARCHAR(10)   NOT NULL, -- 아이디, 중복 안됨, 레코드를 구분하는 컬럼  
    passwd     VARCHAR(20)   NOT NULL, -- 패스워드, 영숫자 조합 
    mname     VARCHAR(20)   NOT NULL, -- 성명, 한글 10자 저장 가능 
    tel           VARCHAR(14)    NULL, -- 전화번호 
    email        VARCHAR(50)   NOT NULL UNIQUE, -- 전자우편 주소, 중복 안됨 
    zipcode     VARCHAR(7)     NULL, -- 우편번호, 101-101 
    address1    VARCHAR(150)  NULL, -- 주소 1 
    address2    VARCHAR(50)   NULL, -- 주소 2 
    job            VARCHAR(20)  NOT NULL, -- 직업 
    mdate        DATE             NOT NULL, -- 가입일     
    fname        VARCHAR(50)  DEFAULT 'member.jpg', -- 회원 사진
    grade         CHAR(1)         DEFAULT 'H', -- 일반회원: H, 정지: Y, 손님:Z 
    PRIMARY KEY (id)               -- 한번 등록된 id는 중복 안됨 
); 

※ 제약 조건 
- NOT NULL: INSERT SQL에서 필수 입력. 
- UNIQUE  : 컬럼에 중복된 값이 올 수 없음. 
- PRIMARY KEY (id): 기본적으로 UNIQUE의 성질을 포함하면서 레코드를 
  구분하는 기준 컬럼의 값. 
  
 
--create 
INSERT INTO member(id, passwd, mname, tel, email, zipcode,  
address1,address2, job, mdate, fname, grade) 
VALUES('user1', '1234', '개발자1', '123-1234', 'email1@mail.com',  
'123-123','인천시', '남동구' ,'A01', sysdate, 'man.jpg', 'H'); 
 
INSERT INTO member(id, passwd, mname, tel, email, zipcode,  
address1,address2, job, mdate, fname, grade) 
VALUES('user2', '1234', '개발자2', '123-1234', 'email2@mail.com',  
'123-123','광명시','남동구' ,'A01', sysdate, 'man.jpg', 'H'); 
 
INSERT INTO member(id, passwd, mname, tel, email, zipcode,  
address1,address2, job, mdate, fname, grade) 
VALUES('user3', '1234', '개발자3', '123-1234', 'email3@mail.com',  
'123-123','용인시','남동구' ,'A01', sysdate, 'myface.jpg', 'H'); 
 
 
--중복 아이디 검사 관련 SQL 
-- 0: 중복 아님, 1: 중복  
SELECT COUNT(id)  
FROM member  
WHERE id='user1'; 
 
 
--이메일 중복 확인
SELECT COUNT(email) as cnt  
FROM member  
WHERE email='email3@mail.com'; 
 
 
--user1 회원 정보 보기 
SELECT id, passwd, mname, tel, email, zipcode, address1, address2, 
    job, mdate, fname, grade 
FROM member  
WHERE id='user1'; 
 
 
--회원 사진 이미지의 수정
UPDATE member 
SET fname='' 
WHERE id='user1'; 
 
 
--패스워드 변경
UPDATE member
SET passwd='1234'
WHERE id='';
 
 
--회원 정보 수정 
UPDATE member  
SET passwd='TEST',
tel='123-123', 
email='email10',
zipcode='TEST',  
    address1='수원', 
    address2='팔달구', 
    job='TEST'
WHERE id = 'user3'; 
 
 
--'user3' 회원 삭제 
DELETE FROM member WHERE id='user3';
 
 
--로그인 관련 SQL 
SELECT COUNT(id) as cnt 
FROM member 
WHERE id = 'user1' AND passwd = '1234';  
 
 
 
--list
SELECT id, mname, tel, email, zipcode, address1, address2,
fname,  r
from(
SELECT id, mname, tel, email, zipcode, address1, address2,
fname, rownum r
from(
SELECT id, mname, tel, email, zipcode, address1, 
address2, fname
FROM member
where mname like '%홍%'
ORDER BY mdate DESC 
)
)where r >= 1 and r <= 5
 