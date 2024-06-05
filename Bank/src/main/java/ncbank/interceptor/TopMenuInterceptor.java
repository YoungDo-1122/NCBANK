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
	
	// Interceptor�� Autowired �� ������� �Ѵ� => servlet �� ��Ͻ� Autowired �浹������?
	// @Autowired : Interceptor ó���� Ŭ������ Autowired ��� �Ұ���
	public TopMenuService topMenuService;
	
	public TopMenuInterceptor(TopMenuService _topMenuService) {
		topMenuService = _topMenuService;
	}
	
	// preHandle ���� �Ͼ�� �� ����
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList); // ���� ��𼭵� request �������� topMenuList�� ��û�Ҽ� �ְ� ����
		return true;
	}
	
	
	
}
