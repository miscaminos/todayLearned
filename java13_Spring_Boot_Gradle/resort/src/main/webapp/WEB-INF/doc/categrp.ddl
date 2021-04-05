/**********************************/
/* Table Name: 카테고리그룹 */
/**********************************/
CREATE TABLE categrp(
		categrpno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(50)		 NOT NULL,
		seqno                         		NUMBER(7)		 DEFAULT 0 NOT NULL,
		visible                       		CHAR(1)		 DEFAULT 'Y' NOT NULL,
		rdate                         		DATE		 NOT NULL
);

COMMENT ON TABLE categrp is '카테고리그룹';
COMMENT ON COLUMN categrp.categrpno is '카테고리그룹번호';
COMMENT ON COLUMN categrp.name is '이름';
COMMENT ON COLUMN categrp.seqno is '출력순서';
COMMENT ON COLUMN categrp.visible is '출력모드';
COMMENT ON COLUMN categrp.rdate is '그룹생성일';

DROP SEQUENCE categrp_seq;
CREATE SEQUENCE categrp_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- insert
INSERT INTO categrp(categrpno, name, seqno, visible, rdate)
VALUES(categrp_seq.nextval, '국내 여행', 1, 'Y', sysdate);
INSERT INTO categrp(categrpno, name, seqno, visible, rdate)
VALUES(categrp_seq.nextval, '해외 여행', 2, 'Y', sysdate);
INSERT INTO categrp(categrpno, name, seqno, visible, rdate)
VALUES(categrp_seq.nextval, '개발 자료', 3, 'Y', sysdate);
COMMIT;
-- list
SELECT categrpno, name, seqno, visible, rdate
FROM categrp
ORDER BY categrpno ASC;
