create table reply(
   rnum number not null,
   content varchar(500) not null,
   regdate date not null,
   id varchar(10) not null,
   bbsno number(7) not null,
   primary key(rnum),
   foreign key(bbsno)  references bbs(bbsno)
 
);
 
insert into reply(rnum, content, regdate, id, bbsno)
values((select nvl(max(rnum),0)+1 from reply),
'의견입니다.',sysdate,'user1',1
) ;

--list(목록)
select rnum, content, to_char(regdate,'yyyy-mm-dd') regdate, id, bbsno, r
FROM(
select rnum, content, regdate, id, bbsno, rownum r
FROM(
select rnum, content, regdate, id, bbsno
from REPLY
where bbsno = 1
order by rnum DESC
    )
)WHERE r >= 1 and r <= 5;
 
 
--total(목록)
select count(*) from reply
where bbsno = 1;
