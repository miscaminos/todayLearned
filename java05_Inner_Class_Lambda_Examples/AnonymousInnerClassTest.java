package innerclass;

class Outer2{
	Runnable getRunnable(int i) {
		int num =100;
		
		//익명 내부 클래스:: 지역 내부 클래스와 동일하게 메소드안에서 선언됨.
		//MyRunnable 클래스이름을 빼고 클래스를 바로 생성하는 방법:
		return new Runnable() {//anonymous inner class. Runnable interface생성
			@Override
			public void run() {
				System.out.println(i);
				System.out.println(num);
			}
		};//여기 마지막 ;는 익명내부클래스가 끝났다는것을 알려주기위함.
	}
	
	//인터페이스나 추상클래스형 변수를 선언하고 클래스를 생성해 대입하는 방법:
	Runnable runner = new Runnable() {
		@Override
		public void run() {
			System.out.println("Runnable이 구현된 익명 클래스 변수가 호출했음");
		}
	};
}

public class AnonymousInnerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Outer2 outer2 = new Outer2();
		
		//인터페이스 자료형 변수를 선언+익명내부클래스에서 구현된 메서드를 호출해서 인스턴스를 반환
		Runnable runnable = outer2.getRunnable(10);
		
		//인터페이스의 메소드를 호출
		runnable.run();
		outer2.runner.run();
	}

}
