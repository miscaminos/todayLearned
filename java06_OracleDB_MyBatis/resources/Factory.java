package resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Factory {
	 private static SqlSessionFactory sqlSessionFactory;
	 //변수 초기화 순서: 
	 //1. class 맨위에 선언과 동시에 값을 대입
	 //2. 초기화 block에서 값 대입
	 //3. 생성자에서 값 대입
	 
	 private int m; // 선언
	 //초기화 block
	 {
		 m=10;
	 }
	 
	 	//static 변수 sqlSessionFactory초기화 block임
	    static {
	        try {
//	            String resource = "resources/mybatis_config.xml";
	        	String resource = "resources/config.xml";
	            Reader reader = Resources.getResourceAsReader(resource);
	 
	            if (sqlSessionFactory == null) {
	                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	                
	            }
	        }
	        catch (FileNotFoundException fileNotFoundException) {
	            fileNotFoundException.printStackTrace();
	        }
	        catch (IOException iOException) {
	            iOException.printStackTrace();
	        }
	    }
	    public static SqlSessionFactory getSqlSessionFactory() {
	        return sqlSessionFactory;
	    }
}
