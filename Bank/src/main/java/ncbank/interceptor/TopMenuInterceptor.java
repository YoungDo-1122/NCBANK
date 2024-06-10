package ncbank.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import ncbank.beans.BoardInfoBean;
import ncbank.beans.UserBean;
import ncbank.service.TopMenuService;

/*Controller�� �ƴ� Interceptor���� �������� ��쵵 ����(Service�� �������� , serivce�� �ִ°��� interceptorȭ(servlet�� ���))*/
public class TopMenuInterceptor implements HandlerInterceptor {

//	@Autowired : Interceptor ó���� Ŭ���������� ������̾�带 ����� �� ����
	private TopMenuService topMenuService;
	private UserBean loginUserBean;
	//DIS
	public TopMenuInterceptor(TopMenuService topMenuService,UserBean loginUserBean ) {
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<BoardInfoBean> topMenuList=topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean); //idx�� name
		return true;
	}

}
