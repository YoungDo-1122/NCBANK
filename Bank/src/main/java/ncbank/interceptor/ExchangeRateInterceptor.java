package ncbank.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import ncbank.beans.CodeMoneyBean;
import ncbank.beans.ExchangeRateBean;
import ncbank.service.CodeMoneyService;
import ncbank.service.ExchangeRateService;
import ncbank.utility.ExchangeRateDTO;

public class ExchangeRateInterceptor implements HandlerInterceptor {
	
	private ExchangeRateService exchangeRateService;
    
	public ExchangeRateInterceptor(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("ExchangeRateInterceptor preHandle()");
		
		// DB에 존재하는 환율 데이터중 최종 고시일의 환율 데이터를 가져온다 (크롤링x)
    	List<ExchangeRateBean> finalExchangeRateList = exchangeRateService.getFinalExchangeRate();
    	if (null == finalExchangeRateList) { // 이경우는 거의 없다고 보면됨. -> 없으면 크롤링
    		System.out.println("finalExchangeRateList is null");
    		finalExchangeRateList = exchangeRateService.findFinalExchangeRate();
    	}
    	
    	// 출력용 DTO로 전환
        List<ExchangeRateDTO> rateDtoList = exchangeRateService.convertExchangeDTOList(finalExchangeRateList);
        
        if (null == rateDtoList) {
			System.out.println("ExchangeRateInterceptor()");
			System.out.println("rateDtoList is null");
			return true;
		}
        
		// 최종환율고시를 리퀘스트영역에 등록
		request.setAttribute("FinalExchangeRateList", rateDtoList);

		System.out.println("setAttribute");
		System.out.println("========== ==========");
		
		return true;
	}

	
}
