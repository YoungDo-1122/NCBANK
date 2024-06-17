package ncbank.config;

import javax.annotation.Resource;

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

import ncbank.beans.UserBean;
import ncbank.interceptor.TopMenuInterceptor;
import ncbank.mapper.AccountMapper;
import ncbank.mapper.BoardMapper;
import ncbank.mapper.CodeMoneyMapper;
import ncbank.mapper.CodeOrganMapper;
import ncbank.mapper.GroupAccountMapper;
import ncbank.mapper.TopMenuMapper;
import ncbank.mapper.TradeMapper;
import ncbank.mapper.UserMapper;
import ncbank.service.TopMenuService;

// 주소가 요청됨 -> 컨트롤러에서 해당하는 주소를 찾음 (서버에게 컨트롤러가 어디에 있는지 알려줘야 함)
// ServletAppContext : 직접적으로 서버(웹)에 전달해주는 등록소 - 웹까지 뻗어나갈 애들을 등록

@Configuration // Spring MVC 프로젝트 설정
@EnableWebMvc // 어노테이션 셋팅 선언
// Spring이 지정된 패키지의 Component를 검색하고 Bean으로 등록하도록 지시 (Component가 어디에 존재하는지) 
// Service, Repository, Controller 의 상위 어노테이션 이 Component (DAO Service)
@ComponentScan("ncbank.dao") // DAO가 이곳에 존재한다 고 알려줌
@ComponentScan("ncbank.service")
@ComponentScan("ncbank.controller")

// 한번에 등록도 가능.
// @ComponentScan(basePackages = {"kr.co.soldesk.controller", "kr.co.soldesk.service", "kr.co.soldesk.dao"})

@PropertySource("/WEB-INF/properties/db.properties") // 로드할 Property 파일 위치 지정 (서버에 연결)
public class ServletAppContext implements WebMvcConfigurer {

	/* ==========[DB 접속 데이터]========== */

	// @Value : 지정된 Property 파일에서 값을 필드로 주입받음 (lombok 꺼 말고 springframework 꺼 임포트)
	@Value("${db.classname}")
	private String db_classname;

	@Value("${db.url}")
	private String db_url;

	@Value("${db.username}")
	private String db_username;

	@Value("${db.password}")
	private String db_password;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	// CSS, JavaScript, 사진, 영상, 소리, 정적 페이지 등 정적 컨텐츠 파일의 경로 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
		// / : 웹상에서의 루트는 webapp - webapp/resources/ 경로에 정적 컨텐츠가 있으면 알아서 읽음
	}

	// jsp 뷰 컨텐츠 파일의 경로 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp"); // jsp 파일이 해당 경로에 있으면 알아서 읽음
	}

	/* ==========[DB 접속 관리]========== */
	// @Bean : Spring에게 이 메서드가 Bean 정의를 제공한다고 알림
	// 데이터베이스 접속 정보를 관리하는 Bean
	@Bean
	public BasicDataSource dataSource() {
		// BasicDataSource : DB 연결 풀
		BasicDataSource source = new BasicDataSource();
		// Property 파일서 주입된 데이터베이스 연결정보
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);

		return source;
	}

	// 쿼리문과 접속 정보를 관리하는 객체
	// 데이터베이스 연결 풀을 사용하여 MyBatis 'SqlSessionFactory'를 생성하고 이를 Bean으로 등록.
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}

	// 데이터베이스 연결 풀과 쿼리문 실행을 관리하기 위한 객체 (Mapper 관리) | 쿼리 == Mapper

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

	// ������ ������ ���� ��ü(Mapper ����)
	@Bean
	public MapperFactoryBean<TradeMapper> getTradeMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<TradeMapper> factoryBean = new MapperFactoryBean<>(TradeMapper.class);
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
	
	@Bean
	public MapperFactoryBean<GroupAccountMapper> getGroupAccountMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<GroupAccountMapper> factoryBean = new MapperFactoryBean<GroupAccountMapper>(
				GroupAccountMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	/* ==========[Interceptors]========== */
	// WebMvcConfigurer 제공 메소드

	// DB 정보보다 위에 있으면 못읽는 에러가 있을수도 있음 (그럴 시 Value 밑으로)
	@Autowired
	private TopMenuService topMenuService;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);

		// TopMenu 는 상단 메뉴여서 어디서든 요청을하든 변하지 않아야 하는 데이터
		TopMenuInterceptor topMenuIntercepotr = new TopMenuInterceptor(topMenuService, loginUserBean);
		// registry 에 담기는 순간 리퀘스트 영역에 데이터가 올라감
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuIntercepotr);

		// /** : 모든 경로에 대해서 (어디서든 데이터를 끌어다 쓰게 하기 위해)
		reg1.addPathPatterns("/**");
	}

	// Properties 파일을 Bean으로 등록 (아무데서나 사용가능하기 위해)
	@Bean
	public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// Properties Message 위치 지정하여 유효성 검사 소스는 모두 이곳을 읽고 가도록
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setBasenames("/WEB-INF/properties/error_message");
		return res;
	}

}