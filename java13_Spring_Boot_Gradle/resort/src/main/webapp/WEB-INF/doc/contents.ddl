/**********************************/
/* Table Name: 콘텐츠 */
/**********************************/
CREATE TABLE contents(
		contentsno                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cateno                        		NUMBER(10)		 NULL ,
		usersno                       		NUMBER(10)		 NULL ,
		title                         		VARCHAR2(300)		 NOT NULL,
		content                       		CLOB		 NOT NULL,
		cnt                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		ip                            		VARCHAR2(15)		 NOT NULL,
		passwd                        		VARCHAR2(15)		 NOT NULL,
		word                          		VARCHAR2(300)		 NULL ,
		rdate                         		DATE		 NOT NULL,
		file1                         		VARCHAR2(100)		 NULL ,
		thumb1                        		VARCHAR2(100)		 NULL ,
		size1                         		NUMBER(10)		 DEFAULT 0		 NULL ,
  FOREIGN KEY (cateno) REFERENCES cate (cateno),
  FOREIGN KEY (usersno) REFERENCES users (usersno)
);

COMMENT ON TABLE contents is '콘텐츠';
COMMENT ON COLUMN contents.contentsno is '컨텐츠번호';
COMMENT ON COLUMN contents.cateno is '카테고리번호';
COMMENT ON COLUMN contents.usersno is '회원번호';
COMMENT ON COLUMN contents.title is '제목';
COMMENT ON COLUMN contents.content is '내용';
COMMENT ON COLUMN contents.cnt is '조회수';
COMMENT ON COLUMN contents.ip is 'ip';
COMMENT ON COLUMN contents.passwd is '패스워드';
COMMENT ON COLUMN contents.word is '검색어';
COMMENT ON COLUMN contents.rdate is '등록일';
COMMENT ON COLUMN contents.file1 is '메인이미지';
COMMENT ON COLUMN contents.thumb1 is '메인이미지 Preview';
COMMENT ON COLUMN contents.size1 is '메인이미지 크기';

CREATE SEQUENCE contents_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;     
 
select * from contents;

