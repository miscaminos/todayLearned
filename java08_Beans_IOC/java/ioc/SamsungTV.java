package ioc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class SamsungTV implements TV {
	
	//@Autowired: 의존하는 객체 주입 (필드,메소드/setter,생성자 에서 사용) DI객체가 중복될때 Qualifier("") 사용
	//@Qualifier("apple") 여러 변수일때에 Qualifier를 사용해서 다른 이름으로 구분한다 
	//DI가 여러개 있을때 (aka 여러개의 speaker가 있을때에)
	//@Autowired 대신 @Resource로 표기하거나, @Injection을 사용 해도됨
	
	
	@Qualifier("apple")
	private AppleSpeaker speaker;
	@Qualifier("apple2")
	private AppleSpeaker speaker2;
	private int price;
	
	public SamsungTV() {
		System.out.println(">>>>>>>>SamsungTV 객체 생성");
	}
	
	public SamsungTV(AppleSpeaker speaker, int price) {
		System.out.println(">>>>>>>SamsungTV(2) 객체 생성");
		this.speaker = speaker;
		this.price = price;
	}
	
	public void initMethod() {
		System.out.println("SamsungTV: 객체 초기화 작업");
	}
	public void destroyMethod() {
		System.out.println("SamsungTV: 객체 메모리 해제. 삭제되기 전 처리 할 작업");
	}
	
	public void powerOn() {
		// TODO Auto-generated method stub
		System.out.println("SamsungTV....전원을 켠다.(price:"+price+")");

	}

	public void powerOff() {
		// TODO Auto-generated method stub
		System.out.println("SamsungTV....전원을 끈다.");

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
