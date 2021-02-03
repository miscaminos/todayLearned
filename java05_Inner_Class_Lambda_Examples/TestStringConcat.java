package lambda;

public class TestStringConcat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "Hello ";
		String s2 = "My name is";
		int num=1000;
		
		StringConcat concat = (a,b) -> {
			System.out.println(a+b+num);
			//num=100; 람다식 밖의 지역변수는 람다식에서 사용 시 상수가 됨.
		};//인터페이스 자료형 변수에 바로 메소드 구현
		concat.makeString(s1,s2); //인터페이스 자료형 변수로 (위에 구현된) 인터페이스 메소드 호출
		
		StringConcat concat2 = (c,d) -> System.out.println("Today's quote: "+c+d+" Ratattoui!");
		concat2.makeString(s1, s2);
		
		/* HOW LAMBDA WORKS::
		 * 어떻게 객체 생성 없이 람다식은 인터페이스 구현만으로 메소드를 호출할수 있나?
		 * =>람다식의 기능 방식은 익명 내부 클래스를 활용할때와 동일함.
		 * 인터페이스 자료형 변수에 바로 메소드 구현부를 생서해서 대입할 수 있다.
		 * 람다식으로 메소드를 구현해놓고 호출하면, 컴터가 익명클래스를 생성하고 이를 통해 익명클래스 객세가 생성되는것임.
		 * 
		 * 그래서 람다식 내부에서 그밖에서 선언되었던 지역변수에 값을 대입하려면 에러가 발생함. 
		 * => 지역 내부 클래스에서 지역변수가 상수와 같이 다뤄지는 것과 같음. 
		 * 람다식 역시, 익명지역내부클래스가 생성되는것이으로 외부 메소드의 지역 변수를 사용하면 그 변수는 final변수가 된다.
		 * 
		 */
		

		
	
	
	}
	
	

}
