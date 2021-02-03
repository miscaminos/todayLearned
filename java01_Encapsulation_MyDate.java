package com.encore.encapsulation;
/*
 *   2021_0104_w
 *   
 * Encapsulation 작성하는 패턴
 * 1. Field -  private 지정
 * 2. setter()/getter() - public 지정
 * 3. setter() 메소드 구현부 첫라인에서(필드 초기화 하기 직전에)제어문을 달아서 타당한 값만 할당 되도록 한다.
 */
public class java01_Encapsulation_MyDate{
	private int month;//0
	private int day;//33
	
	public int getMonth() {
		return month;
	}
	//값을 주입하는 기능...	
	public void setMonth(int month) {
		if(month>=1 && month<=12) {
			this.month = month;
		}else {//필드초기화...
			System.out.println("Invalid Month Value!!");
			System.exit(0);//정상종료..-1(음수) 비정상종료
		}	
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		switch(month) {//setMonth() 를 통한 초기화가 이미 되어져 있는 상태이다...0
			case 2://2월달인 경우라면
				if(day>=1 && day<=28) {
					this.day = day;
				}else {
					System.out.println("Invalid Day!!");
					System.exit(0);
				}
			break;
			
			case 4:
			case 6:
			case 9:
			case 11:
				if(day>=1 && day<=30) {
					this.day = day;
				}else {
					System.out.println("Invalid Day!!");
					System.exit(0);
				}
			break;
			
			default:
				if(day>=1 && day<=31) {
					this.day = day;//5
				}else {
					System.out.println("Invalid Day!!");					
				}					
		}//switch		
	}//setDay	
}//class










