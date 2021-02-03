package board.member;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MemService service = new MemServiceImpl();
//		service.addMember(new Scanner(System.in));
		MemberService service = new MemberService();
		service.addMember(new Member("oi99","2222","Orth","op@gmail.com"));
		
		ArrayList<Member> list = service.getMembers();
		System.out.println(list);
	}

}
