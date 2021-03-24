package com.study.resort;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.study.cate.CateVO;
import com.study.categrp.CategrpVO;
import com.study.users.UsersVO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/** MyBatis를 사용하기위해 DatabaseConfiguration 클래스가 하는 일:
 * 1. @PropertySource("classpath:/application.properties")를 지정해서
 * 		이 클래스에서 application.properties를 사용한다.
 * 2. Hikari를 사용한 datasource를 생성하기 위한 메소드 선언
 * 3. 설정 파일의 접두사 선언 spring.datasource.hikari.... (application.properties에서 선언했었음)
 * 4. spring mybatis에서 필요한 SqlSessionFactory와 SqlSessionTemplate를
 * 		생성 하기 위한 메소드 선언
 * 5. /src/main/resources/mybatis 폴더의 파일명이 "xml"로 끝나는 파일 매핑
 * 6. MapperScan의 basePackages 선언
 * 
 */
@Configuration
@PropertySource("classpath:/application.properties") //설정파일 위치
@MapperScan(basePackages= {"com.study.*"}) //com.study.categrp/cate/contents/users/etc...모두포함
public class DatabaseConfiguration {
	  @Autowired
	  private ApplicationContext applicationContext;
	  
	  @Bean
	  @ConfigurationProperties(prefix="spring.datasource.hikari") // 설정 파일의 접두사 선언 
	  //application.properties에서 정의한대로 prefix값에 넣어서 access힌다.
	  public HikariConfig hikariConfig() {
	      return new HikariConfig();
	  }
	  
	  //dataSource생성 -> sqlSession생성 -> sqlSessionTemplate생성
	  @Bean
	  public DataSource dataSource() throws Exception{ 
	      DataSource dataSource = new HikariDataSource(hikariConfig());
	      System.out.println(dataSource.toString());  // 정상적으로 연결 되었는지 해시코드로 확인
	      return dataSource;
	  }
	  
	  @Bean
	  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
	      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	      sqlSessionFactoryBean.setDataSource(dataSource);
	      sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));   
	      sqlSessionFactoryBean.setTypeAliases(new Class<?>[] {CategrpVO.class, CateVO.class, UsersVO.class});
	     
	      //TypeAliasPackage 한개인 경우에는 아래와 같이 지정가능하다:
	      //sqlSessionFactoryBean.setTypeAliasesPackage("com.study.categrp");
	      return sqlSessionFactoryBean.getObject();
	  }
	  
	  @Bean
	  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
	      return new SqlSessionTemplate(sqlSessionFactory);
	  }
}
