package com.study.resort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

//root-context.xml에서 설정했던 tiles 내용을 이 클래스안에서 설정
//TilesViewResolver 생성 및 layout xml 위치 지정한다.
@Configuration
public class TilesConfiguration {
	 @Bean
	  public TilesConfigurer tilesConfigurer() {
	      final TilesConfigurer configurer = new TilesConfigurer();
	      //해당 경로에 tiles.xml 파일을 넣음
	      configurer.setDefinitions(new String[]{"classpath:/tiles/tiles.xml",
	    		                                 "classpath:/tiles/tiles_cate.xml",
	    		                                 "classpath:/tiles/tiles_users.xml"});
	      configurer.setCheckRefresh(true);
	      return configurer;
	  }
	 
	  @Bean
	  public TilesViewResolver tilesViewResolver() {
	      final TilesViewResolver tilesViewResolver = new TilesViewResolver();
	      tilesViewResolver.setViewClass(TilesView.class);
	      return tilesViewResolver;
	  }
}
