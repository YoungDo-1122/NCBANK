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
public class ExchangeController {
    
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
        //System.out.println("Current Date(cuDate): " + cuDate);
        
        // 문자열로 된 날짜를 Date 객체로 파싱
        Date date = cuDate1.parse(cuDate);
        //System.out.println("Current Date(date): " + cuDate1.format(date));
        
        // api 데이터 exchange에 저장된거 가져오기.
        List<ExchangeBean> exchangeAskList = exchangeService.getExchangeList(cuDate);
        // code_bank - code_bank_name 엮은 전체 데이터 가져오기
        List<CodeBankBean> codeBankNameList = exchangeService.getCodeBankName();
        // user_name이랑 account 데이터 가져오기
        List<AccountBean> getAccountList = exchangeService.getAccount(loginUserBean.getUser_num());
       
        
        
        //System.out.println("exchangeAskList: "+exchangeAskList);
        //System.out.println("codeBankNameList: "+codeBankNameList);
        //System.out.println("getAccountList: "+getAccountList);
		
        model.addAttribute("exchangeAskList", exchangeAskList);
        model.addAttribute("codeBankNameList", codeBankNameList);
        model.addAttribute("getAccountList", getAccountList);
        model.addAttribute("loginUserBean.getUser_num()", loginUserBean.getUser_num());
        
        System.out.println("loginUserBean.getUser_num(): "+loginUserBean.getUser_num());
       
       
        return "exchange/exchangeAsk";
    }
	
	@PostMapping("/exchangeAskSuccess")
	public String getExchangeAskSuccess(@ModelAttribute("createExchangeBean") CreateExchangeBean createExchangeBean, Model model) {
	    // 가져온 createExchangeBean Test
	    // System.out.println("createExchangeBeancode_bank_name: " + createExchangeBean.getCode_bank_name());
	    // System.out.println("createExchangeBean.code_bank: " + createExchangeBean.getCode_bank());
	    // System.out.println("createExchangeBean.user_num: " + createExchangeBean.getUser_num());
	    
	    exchangeService.setTradeByExchangeAsk(createExchangeBean);
	    
	    model.addAttribute("createExchangeBean", createExchangeBean);
	    return "exchange/exchangeAskSuccess";
	}
	
	/*
	@GetMapping("/searchPopup")
	public String searchPopup() {
	    return "exchange/searchPopup";
	}
	*/
	
	/*
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
    */
    
	/*
	// AJAX 요청을 처리하는 메소드 추가
	@GetMapping(value = "/searchBank", produces = "application/json")
	@ResponseBody
	public List<CodeBankBean> searchBank(@RequestParam("keyword") String keyword) {
	    try {
	        return exchangeService.searchBankByKeyword(keyword);
	    } catch (Exception e) {
	        e.printStackTrace();  // 로그에 예외 출력
	        return new ArrayList<>();  // 빈 리스트 반환하여 클라이언트에 에러를 전송하지 않음
	    }
	}
    */
    /*
    @GetMapping("/searchBank")
    public String getSearchBank(@ModelAttribute("createExchangeBean") CreateExchangeBean createExchangeBean,
    							Model model) throws ParseException {
        
		
        
        
        // code_bank - code_bank_name 엮은 전체 데이터 가져오기
        List<CodeBankBean> codeBankNameList = exchangeService.getCodeBankName();

        String keyword = "퇴계";
		List<CodeBankBean> searchBankByKeywordList = exchangeService.searchBankByKeyword(keyword);
        
        //System.out.println("exchangeAskList: "+exchangeAskList);
        //System.out.println("codeBankNameList: "+codeBankNameList);
        //System.out.println("getAccountList: "+getAccountList);
		System.out.println("searchBankByKeywordList: "+searchBankByKeywordList);

        model.addAttribute("codeBankNameList", codeBankNameList);

        model.addAttribute("loginUserId", loginUserBean.getUser_num());
        
       
       
        return "exchange/exchangeAsk";
    }
    */
	
    
}
