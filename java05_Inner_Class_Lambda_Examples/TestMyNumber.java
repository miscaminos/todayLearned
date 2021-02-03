package lambda;

public class TestMyNumber  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyNumber max = (x,y) ->(x >= y) ? x:y; //lambda식을 인터페이스형 변수 max에 대입함.
		System.out.println(max.getMax(10, 20)); //인터페이스형 변수 max로 인터페이스에서 선언했고, 윗줄에서 정의한 메소드를 호출함.
		
	}


}
