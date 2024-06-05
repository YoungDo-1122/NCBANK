package ncbank.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import ncbank.beans.BoardInfoBean;
import ncbank.service.TopMenuService;

// AOP

public class TopMenuInterceptor implements HandlerInterceptor {
	
	// Interceptor는 Autowired 를 막아줘야 한다 => servlet 에 등록시 Autowired 충돌떄문에?
	// @Autowired : Interceptor 처리된 클래스는 Autowired 사용 불가능
	public TopMenuService topMenuService;
	
	public TopMenuInterceptor(TopMenuService _topMenuService) {
		topMenuService = _topMenuService;
	}
	
	// preHandle 무언가 일어나기 전 시점
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList); // 언제 어디서든 request 영역에서 topMenuList를 요청할수 있게 세팅
		return true;
	}
	
	
	
}
