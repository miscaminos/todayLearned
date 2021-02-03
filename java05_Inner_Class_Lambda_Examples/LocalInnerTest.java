package innerclass;

class Outer{
	int outNum = 100;
	static int sNum = 200;
	
	Runnable getRunnable(int i) {//getRunnable()메소드의 반환형은 Runnable임.
		//Runnable 자료형의 객체를 생성해서 반환해야함. 그래서 지역내부클래스 MyRunnable을 정의함.
		int num = 100;
		
		//지역 내부 클래스::
		class MyRunnable implements Runnable{
			int localNum = 10;
						
			@Override
			public void run() {//Runnable(from java.lang) 인터페이스 구현 시, 반드시 run() 구현해야함.
				//num=200; 지역내부클래스안에서는 외부클래스 메소드의에서 선언된 local변수와 parameter변수가 상수임. 변경불가.
				//i=100;
				System.out.println("i="+i);
				System.out.println("num="+num);
				System.out.println("localNum="+localNum);
				System.out.println("outNum="+outNum+"(외부클래스 인스턴스 변수)");
				System.out.println("Outer.sNum="+Outer.sNum+"(외부클래스 정적 변수)");
			}
		}
		return new MyRunnable();
	}
}

public class LocalInnerTest {

	public static void main(String[] args) {
		
		/*MyRunnable을 사용하려면(e.g.내부에 정의된 run()메소드를 사용하려면) Outer Class의 객체(outer)을 통해
		getRunnable() 메소드 호출을 통해 생성된 객체를 반환받아야함.*/
		Outer outer = new Outer();
		Runnable runner = outer.getRunnable(10); //getRunnable()메소드가 끝나면 local변수들이 사라진다.
		//그값들이 아래 run()메소드에서 계속 사용가능한 이유는 그값들이 상수로 처리된상태도 run()이 진행되기때문.
		runner.run();//반환받은 객체로 run()호출 가능 
	}

}
