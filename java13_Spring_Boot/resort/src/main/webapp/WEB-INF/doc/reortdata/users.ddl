/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE users(
		usersno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		id                            		VARCHAR2(20)		 NOT NULL,
		passwd                        		VARCHAR2(60)		 NOT NULL,
		name                         		VARCHAR2(20)		 NOT NULL,
		tel                           		VARCHAR2(14)		 NOT NULL,
		zipcode                       		VARCHAR2(5)		 NULL ,
		address1                      		VARCHAR2(100)		 NULL ,
		address2                      		VARCHAR2(50)		 NULL ,
		rdate                         		DATE		 NOT NULL
);

COMMENT ON TABLE users is '회원';
COMMENT ON COLUMN users.usersno is '회원번호';
COMMENT ON COLUMN users.id is '아이디';
COMMENT ON COLUMN users.passwd is '패스워드';
COMMENT ON COLUMN users.name is '성명';
COMMENT ON COLUMN users.tel is '전화번호';
COMMENT ON COLUMN users.zipcode is '우편번호';
COMMENT ON COLUMN users.address1 is '주소1';
COMMENT ON COLUMN users.address2 is '주소2';
COMMENT ON COLUMN users.rdate is '가입일';

DROP SEQUENCE users_seq;
CREATE SEQUENCE users_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;    
                   -- 다시 1부터 생성되는 것을 방지
-- id 중복 확인
SELECT COUNT(id) as cnt
FROM users
WHERE id='user1';
 
-- 등록
-- 회원 관리용 계정, Q/A 용 계정
INSERT INTO users(usersno, id, passwd, name, tel, zipcode, address1, address2, rdate)
VALUES (users_seq.nextval, 'qnaadmin', '1234', 'QNA관리자', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
INSERT INTO users(usersno, id, passwd, name, tel, zipcode, address1, address2, rdate)
VALUES (users_seq.nextval, 'crm', '1234', '고객관리자', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
-- 개인 회원 테스트 계정
INSERT INTO users(usersno, id, passwd, name, tel, zipcode, address1, address2, rdate)
VALUES (users_seq.nextval, 'user1', '1234', '왕눈이', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
INSERT INTO users(usersno, id, passwd, name, tel, zipcode, address1, address2, rdate)
VALUES (users_seq.nextval, 'user2', '1234', '아로미', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
INSERT INTO users(usersno, id, passwd, name, tel, zipcode, address1, address2, rdate)
VALUES (users_seq.nextval, 'user3', '1234', '투투투', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
-- 그룹별 공유 회원 기준
INSERT INTO users(usersno, id, passwd, name, tel, zipcode, address1, address2, rdate)
VALUES (users_seq.nextval, 'team1', '1234', '개발팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
INSERT INTO users(usersno, id, passwd, name, tel, zipcode, address1, address2, rdate)
VALUES (users_seq.nextval, 'team2', '1234', '웹퍼블리셔팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
INSERT INTO users(usersno, id, passwd, name, tel, zipcode, address1, address2, rdate)
VALUES (users_seq.nextval, 'team3', '1234', '디자인팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
COMMIT;
-- 목록
-- 검색을 하지 않는 경우, 전체 목록 출력
SELECT usersno, id, passwd, name, tel, zipcode, address1, address2, rdate
FROM users
ORDER BY usersno ASC;
 
-- 조회
-- user1 사원 정보 보기
SELECT usersno, id, passwd, name, tel, zipcode, address1, address2, rdate
FROM users
WHERE usersno = 1;
-- 수정
UPDATE users 
SET name='아로미', tel='111-1111-1111', zipcode='00000',
      address1='경기도', address2='파주시'
WHERE usersno=1;
COMMIT;
 
-- 삭제
-- 모두 삭제
DELETE FROM users;
-- 특정 회원 삭제
DELETE FROM users
WHERE usersno=15;
COMMIT;
-- 패스워드 변경
-- 패스워드 검사
SELECT COUNT(usersno) as cnt
FROM users
WHERE usersno=1 AND passwd='1234';
-- 패스워드 수정
UPDATE users
SET passwd='0000'
WHERE usersno=1;
COMMIT;
-- 로그인
SELECT COUNT(usersno) as cnt
FROM users
WHERE id='user1' AND passwd='1234';
