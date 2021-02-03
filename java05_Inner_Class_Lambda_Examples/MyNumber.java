package lambda;

@FunctionalInterface
public interface MyNumber {
	int getMax(int num1, int num2); //추상 메소드 선언
	//int add(); 이 인터페이스는 람다식으로 구현되었기때문에, 안에 메소드를 2개이상 선언 할 수 없음. 
	//햇갈림을 방지하기위해 @functional interface 라고 위와 같이 labeling을 함.
}
