package ncbank.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import ncbank.beans.ExchangeRateBean;
import ncbank.service.ExchangeRateService;

public class ExchangeRateInterceptor implements HandlerInterceptor {

	private ExchangeRateService exchangeRateService;

	public ExchangeRateInterceptor(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Bean으로? DTO로 ? or Bean에 통합해서?
		// 여기서 환율크롤링 x, DB의 최신 정보의 환율을 가져와 환율페이지 전역에서 알수있게.
		List<ExchangeRateBean> exchangeRateList;

		request.setAttribute("ExchangeRateList", request);

		return true;
	}

}
