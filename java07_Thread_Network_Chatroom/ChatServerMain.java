package netTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Room room = new Room();
		try {
			ServerSocket ss= new ServerSocket(3333); //port번호 3333
			System.out.println("chat server start");
			while(true) {
				Socket socket = ss.accept(); //client접속 대기(클라이언트가 접속할때까지 기다린다)
				
				//Server의 socket과 클라이언트 thread가 연결이 되어야하기때문에, ServerMain에서 클라이언트 담당 Thread객체인 ClientJob이 생성됨.
				//ClientJob이 생성되면서 Server의 socket을 받아서 연결함.
				ClientJob cj = new ClientJob(socket,room); //클라이언트 한명 들어오면, 클라이언트 담당 쓰레드 생성
				
				//여기부터 클라이언트 한명씩 
				room.addMember(cj); //채팅방에 추가
				cj.start(); //쓰레드 시작(start()는 Thread 클래스의 메소드이다)
			}
			//ss.close() <-주석처리. 서버socket은 계속 열어둔다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
