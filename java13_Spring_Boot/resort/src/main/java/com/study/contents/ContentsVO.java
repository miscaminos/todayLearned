package com.study.contents;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class ContentsVO {
	private int 			contentsno     ;
	private int 			cateno         ;
	private int 			usersno        ;
	private String			title          ;
	private String			content        ;
	private int 			cnt            ;
	private String			ip             ;
	private String			passwd         ;
	private String			word           ;
	private String			rdate          ;
	private String			file1          ;
	private String			thumb1         ;
	private int 			size1          ;
	private MultipartFile 	file1MF  	   ;
	private String			size1_label	   ;

}
