package kr.co.soldesk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //설계도(환결설정) 지정
@EnableWebMvc //Annotation 세팅선언
@ComponentScan("kr.co.soldesk.controller") //Controller의 상위어노테이션
public class ServletAppContext implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp"); //view에 갖다놓으면 알아서 읽어줌
	}
	
	//정적 컨텐츠 파일의 경로 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		// /**:모든 요청을 / resources에 매핑하겠다
	    registry.addResourceHandler("/**").addResourceLocations("/resources/"); 
	}


	
	
}
