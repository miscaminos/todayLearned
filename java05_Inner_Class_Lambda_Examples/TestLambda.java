package lambda;

interface PrintString{
	void showString(String str);//인터페이스에 선언된 추상 메소드
}

public class TestLambda {
	
	//returnString()메소드의 반환형을 람다식의 인터페이스 형으로 선언해서 여기서 구현한 람다식을 반환할 수 있다.
	public static PrintString returnString() {
		PrintString str = s-> System.out.println(s+" world");
		return str;
	}
	//인터페이스형 변수를 매개변수로 받아서 인터페이스 메소드를 호출하는 메소드
	//여기서 호출되는 인터페이스 메소드는 어디서 구현되었나? 전달된 매개변수에 대입되었던 람다식에서 구현되었음. 
	public static void method1(PrintString x) {
		x.showString("Hello Lamdba ");
	}
	
	//시작!!은 언.제.나. main()에서 부터.
	public static void main(String[] args) {
		
		//람다식을 반환값으로 사용하는 예::
		PrintString ps1 = returnString();//람다식을 대입했던 인터페이스형 변수를 반환하여 새로운 변수ps에 대입함.
		ps1.showString("ps1이 호출한 showString()");//인터페이스형 변수 ps를 통해 인터페이스의 메소드 호출.
		
		//람다식을 매개변수로 사용하는 예::
		PrintString ps2 = a -> System.out.println(a+" Universe!");
		method1(ps1); //ps1은: returnString()에서 반환형으로 람다식이 구현되어 ps1에 대입되었음.
		method1(ps2); //ps2는: ps2라는 인터페이스형 변수에 새로 람다식이 구현되어서 대입되었음.
	}
		
	
		
	

}
