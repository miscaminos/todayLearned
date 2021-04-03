package com.study.model;

import lombok.Data;

/* lombok의 @Data 적용
 * 이와 같이 DTO클래스에 lombok을 적용하면, 
 * DTO 변수들의 getter,setter, toString, 기본 constructor 자동생성됨(만들지않아도됨)
 */
@Data
public class NoticeDTO {
  private int    noticeno     ;
  private String title        ;
  private String content      ;
  private String wname        ;
  private String passwd       ;
  private int    cnt          ;
  private String rdate        ;
}
