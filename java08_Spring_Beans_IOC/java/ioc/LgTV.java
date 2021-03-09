package ioc;

public class LgTV implements TV {
	
	private AppleSpeaker speaker;
	private int price;
	private String shop;
	
	public void setSpeaker(AppleSpeaker speaker) {
		this.speaker= speaker;
		System.out.println(">>>> setSpeaker() 호출");
	}
	public void setPrice(int price) {
		this.price= price;
		System.out.println(">>>> setPrice() 호출");
	}
	public void setShop(String shop) {
		this.shop= shop;
		System.out.println(">>>> setShop() 호출");
	}
	
	public LgTV() {
		System.out.println(">>>>>>>>LgTV 객체 생성");
	}
	public void initMethod() {
		System.out.println("LgTV: 객체 초기화 작업. 이 클래스에 있는 변수들을 초기화 할 수 있음");
	}
	
	public void destroyMethod() {
		System.out.println("LgTV: 객체 메모리 해제. 삭제되기 전 처리 할 작업");
	}
	
	public void powerOn() {
		// TODO Auto-generated method stub
		System.out.println("LgTV....전원을 켠다.");
		System.out.println("price:"+price);
		System.out.println("purchased from:"+shop);
	}

	public void powerOff() {
		// TODO Auto-generated method stub
		System.out.println("LgTV....전원을 끈다.");
	}

	public void volumeUp() {
		// TODO Auto-generated method stub
		speaker.volumeUp();
	}

	public void volmeDown() {
		// TODO Auto-generated method stub
		speaker.volumeDown();
	}
	
}
