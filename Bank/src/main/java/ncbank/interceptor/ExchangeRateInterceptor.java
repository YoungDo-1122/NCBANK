package ncbank.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import ncbank.service.ExchangeRateService;

public class ExchangeRateInterceptor implements HandlerInterceptor {
	
	private ExchangeRateService exchangeRateService = null;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
}
