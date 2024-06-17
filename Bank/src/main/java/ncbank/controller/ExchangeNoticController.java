package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ncbank.beans.CodeMoneyBean;
import ncbank.beans.ExchangeNoticeBean;
import ncbank.beans.ExchangeRateBean;
import ncbank.beans.UserBean;
import ncbank.service.CodeMoneyService;
import ncbank.service.ExchangeRateService;
import ncbank.utility.DateManager;
import ncbank.utility.EmailManager;
import ncbank.utility.ExchangeRateDTO;

@Controller
@RequestMapping("/exchange")
public class ExchangeNoticController {
	
    @Autowired
    private ExchangeRateService exchangeRateService;
    
    @Autowired
    private CodeMoneyService codeMoneyService;
    
    @Autowired
    private DateManager dateManager;
    
    @Autowired
    private EmailManager emailManager;
    
    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;
    
	/* ==========[환율 알림]========== */
    // 알림을 따로 컨트롤러로 빼야할까? 
    @GetMapping("/notice")
    public String notic(
    		// 1:안내 2:등록 3:변경
    		@RequestParam(value="noticContentIndex", defaultValue="1") int noticContentIndex,
    		@ModelAttribute("ExchangeNoticeBean") ExchangeNoticeBean exchangeNoticeBean,
    		Model model) {
    	System.out.println("ExchangeRateController notice()");
    	
    	// 알림페이지 구분을 위한 index
    	System.out.println("noticContentIndex : " + noticContentIndex);
    	model.addAttribute("noticContentIndex", noticContentIndex);

    	System.out.println("loginUserBean : " + loginUserBean);
    	
    	List<CodeMoneyBean> codeMoneyList = codeMoneyService.getCodeMoneyList();
    	if (null == codeMoneyList || codeMoneyList.isEmpty()) {
    		System.out.println("codeMoneyList null");
    	}
    	model.addAttribute("codeMoneyList", codeMoneyList);
    	
    	if (1 != noticContentIndex) { // 환율 안내 페이지만 비로그인상태를 허용
    		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
    			return "exchange/not_login";
    		}
    	}

    	return "exchange/notice";
    }
    
    @GetMapping("sendNoticeMail")
    // Spring MVC에서 자동으로 HttpServletRequest와 HttpServletResponse 객체를 컨트롤러 메서드에 주입
    public String sendNoticeMail(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("ExchangeController sendNoticeMail()");
    	
    	/* 일반적인 메일 */
    	String rootPath = request.getServletContext().getRealPath("/");
    	String filePath = rootPath + "resources/img/youtube.png";
    	
		emailManager.sendEmail("jcjhjg12@gmail.com", 
				"테스트 메일 제목", "테스트 메일 내용", filePath);
		
		/* jsp 내용 메일 */
        // jsp에 필요한 데이터 세팅
        List<ExchangeRateBean> finalExchangeRateList = exchangeRateService.getFinalExchangeRate();   	
        List<ExchangeRateDTO> rateDtoList = exchangeRateService.convertExchangeDTOList(finalExchangeRateList);
        
        String inquiryDate = dateManager.parseDateToString(rateDtoList.get(0).getCode_date(), "yyyy.MM.dd");
        
        request.setAttribute("ExchangeRateList", rateDtoList);
        request.setAttribute("inquiryDate", inquiryDate);
        
        // 한국 원 정보는 따로 가져감
        ExchangeRateBean beanKRW = exchangeRateService.findFinalExchangeRate("KRW");
        ExchangeRateDTO dtoKRW = exchangeRateService.convertExchangeDTO(beanKRW);
        
        request.setAttribute("beanKRW", dtoKRW);
        
		emailManager.sendJspEmail("jcjhjg12@gmail.com",
				"메일 테스트 제목", "/WEB-INF/views/exchange/sendNoticeMail.jsp", request, response);
		
    	return "exchange/rateInquiry";
    }
}
