package ncbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import ncbank.beans.UserBean;

//������Ʈ �۾��� ����� �Ϲ� Bean�� �����ϴ� Ŭ����
@Configuration 
public class RootAppContext {
	
	@Bean("loginUserBean")
	@SessionScope
	public UserBean loginUserBean() {
		return new UserBean();
	}
	
}
