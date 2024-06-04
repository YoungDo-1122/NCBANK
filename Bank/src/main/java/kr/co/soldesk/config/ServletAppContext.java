package kr.co.soldesk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //���赵(ȯ�ἳ��) ����
@EnableWebMvc //Annotation ���ü���
@ComponentScan("kr.co.soldesk.controller") //Controller�� ����������̼�
public class ServletAppContext implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp"); //view�� ���ٳ����� �˾Ƽ� �о���
	}
	
	//���� ������ ������ ��� ����
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		// /**:��� ��û�� / resources�� �����ϰڴ�
	    registry.addResourceHandler("/**").addResourceLocations("/resources/"); 
	}


	
	
}
