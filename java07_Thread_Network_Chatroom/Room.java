package netTest;

//import java.util.ArrayList;
import java.util.Vector;

public class Room {
	private Vector<ClientJob> members;//동기화를 위해 ArrayList->Vector로 변경(ArrayList는 동기화처리 안됨)
	//클라이언트 담당자 쓰레드 객체들을 담을 어레이 리스트 (->동기화를 위해 vecor로 바뀜)
	
	public Room() {
		members = new Vector<>();
	}
	
	public void addMember(ClientJob c){
		members.add(c); //새로운 클라이언트가 들어올때면 클라이언트 담당하는 새로운 쓰레드 객체가 arraylist에 더해짐.
	}
	
	public void delMember(ClientJob c){
		members.remove(c); //1명 클라이언트가 나가면 클라이언트 담당하는 새로운 쓰레드 객체를 arraylist에서 지움.
		
	}
	
	public void sendMsgAll(String msg) {//채팅방에 있는 모든 클라이언트에게 메세지 전송
		for (ClientJob c : members) {
			c.writeMsg(msg);//쓰레드가 자기가 담당하는 클라이언트 1명에게만 메세지 전송
		}
	}
	
}
