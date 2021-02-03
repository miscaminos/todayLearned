package netTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//import java.net.SocketException;

public class ClientJob extends Thread {//클라이언트 1명 담당 쓰레드 
	private Socket socket; //클라이언트와 연결된 소켓
	private PrintWriter out;
	private BufferedReader in;
	private String id; //클라이언트 아이디
	private Room room;
	
	public ClientJob(Socket socket, Room room) { 
		this.socket = socket;
		this.room = room;
		//클라이언트 1명을 담당하는 쓰레드를 생성할때에 in,out을 담당할 reader/writer역할의 클래스 인스턴스를 생성한다.
		//이 쓰레드가 사용할  socket(main server의 socket), 담당할 클라이언트 id, 활동 할 채팅방도 함께 변수로 initialize된다.
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			id = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String str="", msg="";
		while(true) {
			try {
				str = in.readLine(); //쓰레드(객체)가 담당하는 클라이언트가 보내는 str를 읽는다.
				msg = id+": "+str; //메세지에 ID를 붙혀서 출력
				if(str.startsWith("/stop")) {//담당한 클라이언트가 stop 메세지를 보내면 루프 종료
					break;
				}
				room.sendMsgAll(msg); //완성된 메세지를 채팅방의 sendMsgAll()으로 모든 클라이언트에 전달
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writeMsg(str); //담당 클라이언트 프로그램의 서버읽기 thread 종료를 위해 보냄
		room.delMember(this); //채팅방에서 뺌.
		room.sendMsgAll(id+"님이 나가셨습니다."); //다른 클라이언트에 현재 클라이언트가 나감 메세지를 전송
	}
	
	public void writeMsg(String msg) {//담당 클라이언트 1명에 메세지를 전송
		out.println(msg);
		out.flush();	
	}
}
