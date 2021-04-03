package com.study.categrp;

import lombok.Data;

@Data
public class CategrpVO {
	  /** 카테고리 그룹 번호 */
	  private int categrpno;
	  /**  카테고리 이름 */
	  private String name;
	  /** 출력 순서 */
	  private int seqno;
	  /** 출력 모드 */
	  private String visible;
	  /** 등록일 */
	  private String rdate;
}
