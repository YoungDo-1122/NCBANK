package ncbank.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ncbank.interceptor.TopMenuInterceptor;
import ncbank.mapper.AccountMapper;
import ncbank.mapper.BoardMapper;
import ncbank.mapper.CodeMoneyMapper;
import ncbank.mapper.CodeOrganMapper;
import ncbank.mapper.TopMenuMapper;
import ncbank.mapper.UserMapper;
import ncbank.service.TopMenuService;

// �ּҰ� ��û�� -> ��Ʈ�ѷ����� �ش��ϴ� �ּҸ� ã�� (�������� ��Ʈ�ѷ��� ��� �ִ��� �˷���� ��)
// ServletAppContext : ���������� ����(��)�� �������ִ� ��ϼ� - ������ ����� �ֵ��� ���

@Configuration // Spring MVC ������Ʈ ����
@EnableWebMvc // ������̼� ���� ����
// Spring�� ������ ��Ű���� Component�� �˻��ϰ� Bean���� ����ϵ��� ���� (Component�� ��� �����ϴ���) 
// Service, Repository, Controller �� ���� ������̼� �� Component (DAO Service)
@ComponentScan("ncbank.dao") // DAO�� �̰��� �����Ѵ� �� �˷���
@ComponentScan("ncbank.service")
@ComponentScan("ncbank.controller")

// �ѹ��� ��ϵ� ����.
// @ComponentScan(basePackages = {"kr.co.soldesk.controller", "kr.co.soldesk.service", "kr.co.soldesk.dao"})

@PropertySource("/WEB-INF/properties/db.properties") // �ε��� Property ���� ��ġ ���� (������ ����)
public class ServletAppContext implements WebMvcConfigurer {

	/* ==========[DB ���� ������]========== */

	// @Value : ������ Property ���Ͽ��� ���� �ʵ�� ���Թ��� (lombok �� ���� springframework �� ����Ʈ)
	@Value("${db.classname}")
	private String db_classname;

	@Value("${db.url}")
	private String db_url;

	@Value("${db.username}")
	private String db_username;

	@Value("${db.password}")
	private String db_password;

	/* ========== ========== */

	// CSS, JavaScript, ����, ����, �Ҹ�, ���� ������ �� ���� ������ ������ ��� ����
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
		// / : ���󿡼��� ��Ʈ�� webapp - webapp/resources/ ��ο� ���� �������� ������ �˾Ƽ� ����
	}

	// jsp �� ������ ������ ��� ����
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp"); // jsp ������ �ش� ��ο� ������ �˾Ƽ� ����
	}

	/* ==========[DB ���� ����]========== */
	// @Bean : Spring���� �� �޼��尡 Bean ���Ǹ� �����Ѵٰ� �˸�
	// �����ͺ��̽� ���� ������ �����ϴ� Bean
	@Bean
	public BasicDataSource dataSource() {
		// BasicDataSource : DB ���� Ǯ
		BasicDataSource source = new BasicDataSource();
		// Property ���ϼ� ���Ե� �����ͺ��̽� ��������
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);

		return source;
	}

	// �������� ���� ������ �����ϴ� ��ü
	// �����ͺ��̽� ���� Ǯ�� ����Ͽ� MyBatis 'SqlSessionFactory'�� �����ϰ� �̸� Bean���� ���.
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}

	// �����ͺ��̽� ���� Ǯ�� ������ ������ �����ϱ� ���� ��ü (Mapper ����) | ���� == Mapper

	@Bean
	public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<UserMapper>(UserMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<CodeMoneyMapper> getCodeMoneyMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<CodeMoneyMapper> factoryBean = new MapperFactoryBean<CodeMoneyMapper>(CodeMoneyMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<AccountMapper> accountMapper(SqlSessionFactory sqlSessionFactory) {
		MapperFactoryBean<AccountMapper> factoryBean = new MapperFactoryBean<>(AccountMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<CodeOrganMapper> codeOrganMapper(SqlSessionFactory sqlSessionFactory) {
		MapperFactoryBean<CodeOrganMapper> factoryBean = new MapperFactoryBean<>(CodeOrganMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactory);
		return factoryBean;
	}
	/* ==========[Interceptors]========== */
	// WebMvcConfigurer ���� �޼ҵ�

	// DB �������� ���� ������ ���д� ������ �������� ���� (�׷� �� Value ������)
	@Autowired
	private TopMenuService topMenuService;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);

		// TopMenu �� ��� �޴����� ��𼭵� ��û���ϵ� ������ �ʾƾ� �ϴ� ������
		TopMenuInterceptor topMenuIntercepotr = new TopMenuInterceptor(topMenuService);
		// registry �� ���� ���� ������Ʈ ������ �����Ͱ� �ö�
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuIntercepotr);

		// /** : ��� ��ο� ���ؼ� (��𼭵� �����͸� ����� ���� �ϱ� ����)
		reg1.addPathPatterns("/**");
	}

	// Properties ������ Bean���� ��� (�ƹ������� ��밡���ϱ� ����)
	@Bean
	public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// Properties Message ��ġ �����Ͽ� ��ȿ�� �˻� �ҽ��� ��� �̰��� �а� ������
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setBasenames("/WEB-INF/properties/error_message");
		return res;
	}

}
