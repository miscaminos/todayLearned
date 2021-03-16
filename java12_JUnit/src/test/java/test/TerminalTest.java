package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**TerminalTest.java
 * 
 *  1) New>JUnit Test Case를 생성
 *  2) "Class under test"항목에 src/main/java/model에서 test하려는 클래스를 지정한다. 
 *  	(for this case, src/main/java/model/test/Terminal.java)
 *  3) Before, AfterClass 등 필요한 method stubs 생성을 위해 check한다.
 *  4) JUnit Test실행시, 동작 순서: 
 *  	BeforeClass->Before->Test1->After->Before->Test2->After->...->AfterClass
 *  참고: import error발생시, Maven>Update Project 진행 try.
 **/
public class TerminalTest {
	
	//Test하려는 클래스 객체
	private static Terminal term;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		term = new Terminal();
		term.netConnect();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		term.netDisConnect();
	}

	@Before
	public void setUp() throws Exception {
		term.logon("user", "1234");
	}

	@After
	public void tearDown() throws Exception {
		term.logoff();
	}
	
	@Test
	public void terminalConnected() throws Exception{
		assertTrue(term.isLogon());
		System.out.println("===logon test===");
	}
	
	@Test
	public void getReturnMsg() throws Exception{
		term.setMsg("hello");
		assertEquals("hello",term.getReturnMsg());
		System.out.println("===Msg test===");
	}
	



}
