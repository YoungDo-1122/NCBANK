package kr.co.soldesk.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SpringConfigClass implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		// 프레임워크 환경설정을 위한 서버 클래스객체
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		// 환경설정 클래스의 구현부가 서버에 로드되는 객체(servletAppContext)
		// -> 서버저장소에 servletAppContext를 등록
		servletAppContext.register(ServletAppContext.class);

		// 요청(request)발생시 요청을 처리하는 서블릿(응답)을 DispatcherServlet 으로 설정
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		// servletContext 서버에 접속하는 매개변수
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispather", dispatcherServlet); // forward 영역

		// 부가설정
		servlet.setLoadOnStartup(1); // 가장먼저(1) 이 객체를 업로드(실행)해라
		servlet.addMapping("/"); // 최초실행시 "/"로 이동 Mapping

		// ---------------------------------------------------------------------------------
		// 서버 메모리 확보
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);

		// 확보된 메모리의 객체 로드
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);

		// dispatcher -> jsp등 모든 정적파일(dispatcher)에 대해서 UTF-8로 인코딩 하는 필터객체생성
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");

	}

}
