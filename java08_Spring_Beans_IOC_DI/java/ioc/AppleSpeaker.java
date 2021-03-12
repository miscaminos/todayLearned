package ioc;
import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker {
	public AppleSpeaker() {
		System.out.println(">>>>>> AppleSpeaker 객체 생성");
	}
	
	public void volumeUp() {
		System.out.println("AppleSpeaker 소리 올린다.");
	}
	public void volumeDown() {
		System.out.println("AppleSpeaker 소리 내린다.");
	}
	
}
