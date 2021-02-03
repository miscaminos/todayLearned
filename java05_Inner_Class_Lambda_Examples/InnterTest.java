package innerclass;
/*
 * 내부 클래스(Inner Class)란?
 * 클래스 내부에 선언한 클래스. 이 클래스와 외부클래스가 밀접한 관계이고, 
 * 외부 클래스 외에 다른 클래스와는 협력할 일이 없는 경우 사용함.
 * 어떤 클래스안에 여러 변수가 있고, 이들 변수 중 일부를 모아 클래스로 표현할때에 사용함.
 * example??
 * 
 */
class OutClass{
	private int outNum=10;
	private static int outSNum=20;
	
	//내부클래스 선언:: inner class(내부 클래스)를  member변수 선언하는것과 동일하게 선언.
	InClass inClass;

	
	public OutClass() { //외부 클래스 생성자. 외부클래스가 생성된 후에 내부 클래스가 생성가능하다.
		inClass = new InClass();
	}
	
	private class InClass{//내부 클래스
		//그냥 access modifier없이 default로 선언하면 외부클래스 외에 다른 클래스에서 내부클래스를 생성/사용할 수 있다. ALERT!!
		//해결:: private으로 지정해야지만 다른 클래스에서 내부 클래스에 접근하는것을 막을 수 있음.
		
		int inNum1=50;
		static final int sInNum=205; 
		//static int sInNum=111; 에러발생!!
		//인스턴스 inner class에서는 static 변수 사용 불가, 단 static final은 사용 가능.
	
		void inTest1() {
			System.out.println("OutClass num="+outNum+"(외부 클래스의 인스턴스 변수)");
			System.out.println("OutClass sNum="+outSNum+"(외부 클래스의 정적 변수)");
			System.out.println("InClass inNum1="+inNum1+"(내부클래스의 변수)");
			System.out.println("InClass의 static final 변수:"+sInNum);
		}
	}
	
	public void usingClass() {//외부 클래스 메소드. 내부클래스에서 정의되 메소드를 호출하는 기능.
		inClass.inTest1();
	}
	
	static class InStaticClass{//static 내부 클래스
		int inNum2=100;
		static int sInNum2=200;
		//static inner class에선 static 변수 사용 가능
		
		void inTest2() {
			System.out.println("InStaticClass inNum="+inNum2+"(내부 클래스의 인스턴스 변수 사용)");
			System.out.println("InStaticClass sNum="+sInNum2+"(내부 클래스의 정적 변수 사용)");
			System.out.println("OutClass sNum="+outSNum+"(외부 클래스의 정적 변수 사용)");
			//System.out.println("OutClass 인스턴스변수 outNum="+outNum); 에러발생. static변수만 사용가능
		}
		
		static void inTest3() {
			System.out.println("OutClass static num="+outSNum+"(외부 클래스의 정적 변수 사용)");
			System.out.println("InStaticClass static sInNum="+sInNum2+"(내부 클래스의 정적 변수 사용)");
			//System.out.println(inNum2); 에러발생! static 메소드에는 static변수만 사용가능
		}
	}
}


public class InnterTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OutClass outClass = new OutClass();
		outClass.usingClass(); //일반적인 인스턴스 내부 클래스 사용방법임. 외부 클래스가 내부클래스 기능을 호출
			
		//내부 클래스가 private이 아닌경우:
		//OutClass.InClass inClass = outClass. new InClass(); 외부클래스의 인스턴스를 통해 문법적으로 내부클래스를 어디서든 사용가능 
		//inClass.inTest1();
		
		System.out.println();
		OutClass.InStaticClass.inTest3();
		//OutClass.InStaticClass.inTest2(); 이렇게는 에러발생!! non-static 메소드 호출 불가능(아래와같이 해결 가능)
		
		System.out.println();
		OutClass.InStaticClass inStaticClass = new OutClass.InStaticClass();
		inStaticClass.inTest2();// 정적내부 클래스 객체를 생성하면, 이 내부 클래스의 member들이 모두 메모리에 올라가기때문에 non-static메소드도 호출가능
		
		
	}

}
