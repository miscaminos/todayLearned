package com.study.cate;

import java.sql.Date;

import lombok.Data;
//@Data를 표기해서 Lombok을 사용한다.
//Lombok은 자동으로 지정된 클래스 변수의 getter,setter,toString()을 생성해준다.
@Data
public class Categrp_Cate_join {
	/** categrp table **/
	private int r_categrpno;
	private String r_name;
	/** cate table **/
	private int cateno;
	private int categrpno;
	private String name;
	private int seqno;
	private String visible;
	private String rdate;
	private int cnt;
	
}
