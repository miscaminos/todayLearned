package com.study.contents;

import lombok.Data;

@Data
public class Contents_UsersVO {
	/** contests table */
	private int			contentsno;
	private int			usersno;
	private	int			cateno;
	private String		title;
	private String		content;
	private	int			cnt;
	private String		rdate;
	private String		word;
	private String		ip;
	private String		file1;
	private	String		thumb1;
	private int			size1;
	/** users table */
	private String		mid;

}
