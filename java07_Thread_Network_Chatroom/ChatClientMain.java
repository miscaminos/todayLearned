package netTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//여기 2가지 thread가 생성되고 진행된다:: (아래 내용 재확인 필요!!)
//1. Main thread (chatClient thread가 종료되어도 main은 계속 socket이 열려있다.) 
//ServerMain에서 ClientJob thread가 생성+연결되었었고, 대화 종료 시, chatClient thread가 종료되어도 main은 계속 socket이 열려있다.
//2. chatClient thread

class ChatClient extends Thread{ //서버가 보내준 메시지를 받아서 출력
	private BufferedReader in;
	private Socket socket;
	
	public ChatClient(Socket socket, BufferedReader in) {
		this.in = in;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		String str = "";
		while(true) {
			try {
				str = in.readLine(); //BufferedReader를 통해 읽어들여서 str에 대입시킴.
				if(str.startsWith("/stop")) {
					break;
				}
				System.out.println(str);//만약 클라이언트들의 대화 내용을 저장하고 싶다면 여기에서 str을 저장하면됨.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("대화 종료");
		try {
			System.out.println("서버 읽기 쓰레드 종료");
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class ChatClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		try {
			Socket socket = new Socket("192.168.219.177", 3333);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("ID:");
			String id = sc.next();
			out.println(id);
			out.flush();
			ChatClient cc = new ChatClient(socket, in);
			cc.start();
			//start()호출 시, Thread클래스형의 ChatClient의 run()메소드가 호출되고
			//이 run()이 수행되면서 ChatClient는 클라이언트의 콘솔에 쓰여진 문자열을 읽어들여서 출력(모든 클라이언트의 콘솔창에) 
			//이와 동시에 아래 while문으로 client가 쓰는 문자열을 str로 받아서 서버에 전송한다.
			while(true) {
				String str = "";
				str = sc.nextLine(); //메시지 입력
				out.println(str); //메시지 서버 전송
				out.flush(); //버퍼의 내용을 강제 출력하고 비움
				if(str.startsWith("/stop")) {
					break;
				}
			}
			System.out.println("키보드 입력 쓰레드 종료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
