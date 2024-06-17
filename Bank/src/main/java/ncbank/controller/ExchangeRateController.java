package ncbank.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ncbank.beans.AccountBean;
import ncbank.beans.CodeBankBean;
import ncbank.beans.CreateExchangeBean;
import ncbank.beans.ExchangeBean;
import ncbank.beans.UserBean;
import ncbank.service.ExchangeService;

@Controller
@RequestMapping("/exchange")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @Autowired
    private DateManager dateManager;
    
    @Autowired
    private ExchangeService exchangeService;
    
    @Resource(name="loginUserBean")
    UserBean loginUserBean;
    
    
	@GetMapping("/exchangeAsk")
    public String getExchangeList(@ModelAttribute("createExchangeBean") CreateExchangeBean createExchangeBean,
    							Model model) throws ParseException {
        
		// 현재 날짜를 나타내는 Date 객체 생성
        Date d = new Date();
        // 날짜를 특정 형식으로 포맷하기 위한 SimpleDateFormat 객체 생성
        SimpleDateFormat cuDate1 = new SimpleDateFormat("yy/mm/dd");
        // 현재 날짜를 나타내는 Calendar 객체 생성
        Calendar c = Calendar.getInstance();
        // 연, 월, 일 값 추출
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;  // Calendar.MONTH에 1을 더함
        int day = c.get(Calendar.DAY_OF_MONTH);
        // 연, 월, 일을 두 자리수 형식으로 포맷팅하여 문자열로 변환
        String cuDate = String.format("%02d/%02d/%02d", year % 100, month, day);
        System.out.println("Current Date(cuDate): " + cuDate);
        // 문자열로 된 날짜를 Date 객체로 파싱
        Date date = cuDate1.parse(cuDate);
        System.out.println("Current Date(date): " + cuDate1.format(date));
        
        // api 데이터 exchange에 저장된거 가져오기.
        List<ExchangeBean> exchangeAskList = exchangeService.getExchangeList(cuDate);
        // code_bank - code_bank_name 엮은 전체 데이터 가져오기
        List<CodeBankBean> codeBankNameList = exchangeService.getCodeBankName();
        // user_name이랑 account 데이터 가져오기
        List<AccountBean> getAccountList = exchangeService.getAccount(loginUserBean.getUser_num());
       
        
        
        //System.out.println("exchangeAskList: "+exchangeAskList);
        System.out.println("codeBankNameList: "+codeBankNameList);
        //System.out.println("getAccountList: "+getAccountList);
		
        model.addAttribute("exchangeAskList", exchangeAskList);
        model.addAttribute("codeBankNameList", codeBankNameList);
        model.addAttribute("getAccountList", getAccountList);
        model.addAttribute("loginUserId", loginUserBean.getUser_num());
        
        System.out.println("loginUserBean.getUser_num(): "+loginUserBean.getUser_num());
       
       
        return "exchange/exchangeAsk";
    }
	
	@PostMapping("/exchangeAskSuccess")
	public String getExchangeAskSuccess(@ModelAttribute("createExchangeBean") CreateExchangeBean createExchangeBean, Model model) {
	    // createExchangeBean에 설정된 값을 출력하거나 처리할 수 있습니다.
	    System.out.println("Selected Bank Name: " + createExchangeBean.getCode_bank_name());
	    model.addAttribute("createExchangeBean", createExchangeBean);
	    return "exchange/exchangeAskSuccess";
	}
	
	/*
	@GetMapping("/searchPopup")
	public String searchPopup() {
	    return "exchange/searchPopup";
	}
	*/

	@GetMapping(value = "/searchBank", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<?> searchBank(@RequestParam("keyword") String keyword) {
        try {
            List<CodeBankBean> results = exchangeService.searchBankByKeyword(keyword);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("검색 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public String notic(
    		// 1:안내 2:등록 3:변경
    		@RequestParam(value="noticContentIndex", defaultValue="1") int noticContentIndex,
    		Model model) {
    	System.out.println("ExchangeController notice()");
    	
    	// 알림페이지 구분을 위한 index
    	model.addAttribute("noticContentIndex", noticContentIndex);
    	System.out.println("noticContentIndex : " + noticContentIndex);
    	
    	// 여기서 로그인 검사를 해야함.
    	// 로그인 ㄴㄴ -> 로그인 펭지ㅣ
    	// 로그인 ㅇㅇ -> 알림페이지에서 import jsp 만 변경? or 페이지를 하나 더 연다?
    	
    	return "exchange/notice";
    }
    
    @GetMapping("sendNoticeMail")
    // Spring MVC에서 자동으로 HttpServletRequest와 HttpServletResponse 객체를 컨트롤러 메서드에 주입
    public String sendNoticeMail(HttpServletRequest request, HttpServletResponse response) {
    	
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
				"메일 테스트 제목", "/WEB-INF/views/exchange/sendNoticeMail.jsp", request, response);
		
    	return "exchange/rateInquiry";
    }
    
    /* ==========[환율 차트]========== */
    @GetMapping("/rateChart")
    public String rateChart() {
    	System.out.println("ExchangeController rateChart()");
    	
    	return "exchange/rateChart";
    }
    */
	
    
}
