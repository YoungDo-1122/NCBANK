package ncbank.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/* WebApplicationInitializer : Java 에서 Web 으로 빠져 나가겠다. */
// 서플릿을 서버에 올린다.
public class SpringConfigClass implements WebApplicationInitializer {

	/* 가장 먼저 불리는 함수 - (ServletContext : 서버에 진입하는 매개변수) */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// 프레임워크 환경설정을 위한 서버 클래스 객체 (웹이랑 연결을 담당)
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		// 환경설정 클래스의 구현부가 서버에(서버 저장소 레지스터에) 로드되는 객체 (servletAppContext)
		servletAppContext.register(ServletAppContext.class);

		// 요청 발생시 (리퀘스트 발생시) 요청을 처리하는 서플릿을 DispatcherServlet 으로 설정
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);

		// 부가 설정
		servlet.setLoadOnStartup(1); // 가장 먼저 이 객체를 업로드
		servlet.addMapping("/"); // 루트로 이동해서 시작

		/* ========== ========== */
		// 서버 메모리 확보
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);

		// 확보된 메모리의 객체 로드
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);

		// dispatcher : view(jsp, html 등)컨텐츠와 정적 컨텐츠 파일에 대해서 UTF-8로 인코딩 하는 필터 객체 생성
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");

	} // onStartup

}