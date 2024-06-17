package ncbank.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ncbank.beans.ExchangeRateBean;
import ncbank.service.ExchangeRateService;
import ncbank.utility.DateManager;
import ncbank.utility.EmailManager;
import ncbank.utility.ExchangeRateDTO;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @Autowired
    private DateManager dateManager;
    
    @Autowired
    private EmailManager emailManager;
    
    @GetMapping("/main")
    public String main() {
        return "exchange/main";
    }

    /* ==========[환율 조회]========== */
    
    /* 환율 조회페이지에서 날짜를 선택한 경우 */
    @GetMapping("/rateInquiryDate")
    // @RequestParam("inquiryDay") : name이 inquiryDay 인 값을 가져온다 (input 의 name 이
    // inquiryDay)
    // @DateTimeFormat(pattern = "yyyyMMdd") : inquiryDay를 Date로 변환하기 위한 포멧 설정
    public String rateInquiryDate(@RequestParam("inquiryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inquiryDate,
            Model model) {
        System.out.println("rateInquiryDate()");
        System.out.println("inquiryDate : " + inquiryDate);
        List<ExchangeRateBean> rateBeanList = exchangeRateService.getExchangeRate(
        		dateManager.parseDateToString(inquiryDate, "yyyyMMdd"));
        List<ExchangeRateDTO> rateDtoList = exchangeRateService.convertExchangeDTOList(rateBeanList);
        
        if (null == rateDtoList) { // 해당날짜의 환율정보가 없음.
        	// 이걸 페이지 전환없이 할려면 ajax? -> 나중에 고민좀
        	return "exchange/not_rateInquiry";
        }
        
        model.addAttribute("ExchangeRateList", rateDtoList);
        model.addAttribute("inquiryDate1", dateManager.parseDateToString(inquiryDate, "yyyy.MM.dd"));
        model.addAttribute("inquiryDate2", dateManager.parseDateToString(inquiryDate, "yyyy-MM-dd"));

        return "exchange/rateInquiry";
    }

    /* 환율 페이지 기본 - 현재 날짜 기준 */
    @GetMapping("/rateInquiry")
    public String rateInquiry(Model model) {
        System.out.println("rateInquiry()");
        
        // 현재날짜의 환율정보 가져옴
        System.out.println("currentDate : " + dateManager.getCurrentDate("yyyyMMdd"));
        String searchDate = dateManager.getCurrentDate("yyyyMMdd");

        // 최근고시 환율 정보
        List<ExchangeRateBean> rateBeanList = exchangeRateService.findFinalExchangeRate();
        // 출력용 DTO로 전환
        List<ExchangeRateDTO> rateDtoList = exchangeRateService.convertExchangeDTOList(rateBeanList);
        
        
        model.addAttribute("ExchangeRateList", rateDtoList);
        model.addAttribute("inquiryDate1", dateManager.changeStringDateFormat(searchDate, "yyyyMMdd", "yyyy.MM.dd"));
        model.addAttribute("inquiryDate2", dateManager.changeStringDateFormat(searchDate, "yyyyMMdd", "yyyy-MM-dd"));

        return "exchange/rateInquiry";
    }
    
    // 시작날짜 ~ 끝날짜 까지의 환율정보를 DB에 추가 - 백 데이터 추가용
    // + 환율 그래프 기간 요청시 ?
    @GetMapping("addRateInquiry_DateRange")
    public String addRateInquiry_DateRange() {
    	
    	exchangeRateService.addRateInquiry_DateRange(
    			dateManager.getMoveDate(dateManager.getCurrentDate("yyyyMMdd"), -6, 0, "yyyyMMdd"), 
    			dateManager.getCurrentDate("yyyyMMdd"));
    	
    	return "exchange/rateInquiry";
    }
    
    @Autowired
    private ResourceLoader resourceLoader;
    
    @GetMapping("sendMail")
    // Spring MVC에서 자동으로 HttpServletRequest와 HttpServletResponse 객체를 컨트롤러 메서드에 주입
    public String sendMail(HttpServletRequest request, HttpServletResponse response) {
    	
    	/* 일반적인 메일 */
    	String rootPath = request.getServletContext().getRealPath("/");
    	String filePath = rootPath + "resources/img/그웬1.jpg";
    	
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
				"메일 테스트 제목", "/WEB-INF/views/exchange/calculator.jsp", request, response);
		
    	return "exchange/rateInquiry";
    }
    
    /* ==========[환율 계산기]========== */
    
    /* 환율 계산기 */
    @GetMapping("/calculator")
    public String calculator(Model model) {
    	System.out.println("ExchangeController calculator()");
    	
    	// DB에 존재하는 환율 데이터중 최종 고시일의 환율 데이터를 가져온다 (크롤링x)
    	List<ExchangeRateBean> finalExchangeRateList = exchangeRateService.getFinalExchangeRate();
    	if (null == finalExchangeRateList) { // 이경우는 거의 없다고 보면됨.
    		System.out.println("finalExchangeRateList is null");
    		return "exchange/calculator";
    	}
    	
    	// 출력용 DTO로 전환
        List<ExchangeRateDTO> rateDtoList = exchangeRateService.convertExchangeDTOList(finalExchangeRateList);
        
        String inquiryDate = dateManager.parseDateToString(rateDtoList.get(0).getCode_date(), "yyyy.MM.dd");
        
        model.addAttribute("ExchangeRateList", rateDtoList);
        model.addAttribute("inquiryDate", inquiryDate);
        
        // 한국 원 정보는 따로 가져감
        ExchangeRateBean beanKRW = exchangeRateService.findFinalExchangeRate("KRW");
        ExchangeRateDTO dtoKRW = exchangeRateService.convertExchangeDTO(beanKRW);
        
        model.addAttribute("beanKRW", dtoKRW);
        
        return "exchange/calculator";
    }
    
    /* ==========[환율 알림]========== */
    @GetMapping("/notice")
    public String notic() {
    	System.out.println("ExchangeController notice()");
    	return "exchange/notice";
    }
    
    @GetMapping("/noticeSetting")
    public String noticeSetting() {
    	System.out.println("ExchangeController noticeSetting()");
    	
    	// 여기서 로그인 검사를 해야함.
    	// 로그인 ㄴㄴ -> 로그인 펭지ㅣ
    	// 로그인 ㅇㅇ -> 알림페이지에서 import jsp 만 변경? or 페이지를 하나 더 연다?
    	
    	return "exchange/noticeSetting";
    }
    
    
    /* ==========[환율 차트]========== */
    @GetMapping("/rateChart")
    public String rateChart() {
    	System.out.println("ExchangeController rateChart()");
    	
    	return "exchange/rateChart";
    }
    
}
