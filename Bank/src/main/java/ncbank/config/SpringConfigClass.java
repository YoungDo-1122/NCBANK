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

/* WebApplicationInitializer : Java ���� Web ���� ���� �����ڴ�. */
// ���ø��� ������ �ø���.
public class SpringConfigClass implements WebApplicationInitializer {

	/* ���� ���� �Ҹ��� �Լ� - (ServletContext : ������ �����ϴ� �Ű�����) */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// �����ӿ�ũ ȯ�漳���� ���� ���� Ŭ���� ��ü (���̶� ������ ���)
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		// ȯ�漳�� Ŭ������ �����ΰ� ������(���� ����� �������Ϳ�) �ε�Ǵ� ��ü (servletAppContext)
		servletAppContext.register(ServletAppContext.class);

		// ��û �߻��� (������Ʈ �߻���) ��û�� ó���ϴ� ���ø��� DispatcherServlet ���� ����
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);

		// �ΰ� ����
		servlet.setLoadOnStartup(1); // ���� ���� �� ��ü�� ���ε�
		servlet.addMapping("/"); // ��Ʈ�� �̵��ؼ� ����

		/* ========== ========== */
		// ���� �޸� Ȯ��
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);

		// Ȯ���� �޸��� ��ü �ε�
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);

		// dispatcher : view(jsp, html ��)�������� ���� ������ ���Ͽ� ���ؼ� UTF-8�� ���ڵ� �ϴ� ���� ��ü ����
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");

	} // onStartup

}