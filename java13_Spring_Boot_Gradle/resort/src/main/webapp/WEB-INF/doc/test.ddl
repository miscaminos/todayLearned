/**********************************/
/* Table Name: test */
/**********************************/
CREATE TABLE test(
		rdate                         		TIMESTAMP(50)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(30)		 NOT NULL
);

COMMENT ON TABLE test is 'test';
COMMENT ON COLUMN test.rdate is 'rdate';
COMMENT ON COLUMN test.name is 'name';


