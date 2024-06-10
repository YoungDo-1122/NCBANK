package ncbank.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ncbanck.utility.DateManager;
import ncbanck.utility.ExchangeRateDTO;
import ncbank.beans.ExchangeRateBean;
import ncbank.service.CodeMoneyService;
import ncbank.service.ExchangeRateService;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {
	
	@Autowired
	ExchangeRateService exchangeRateService = null;
	@Autowired
	CodeMoneyService codeMoneyService = null;
	
	@Autowired
	private DateManager dateManager = null;
	
	@GetMapping("/main") 
	public String main() {
		return "exchange/main";
	}
	
	/* ȯ�� ��ȸ���������� ��¥�� ������ ��� */
	@GetMapping("/rateInquiryDate") 
	// @RequestParam("inquiryDay") : name�� inquiryDay �� ���� �����´� (input �� name �� inquiryDay)
	// @DateTimeFormat(pattern = "yyyyMMdd") : inquiryDay�� Date�� ��ȯ�ϱ� ���� ���� ����
	public String rateInquiryDate(@RequestParam("inquiryDate") 
		@DateTimeFormat(pattern = "yyyy-MM-dd")Date inquiryDate, Model model) {
		System.out.println("rateInquiryDate()");
		
		System.out.println("inquiryDate : " + inquiryDate);
		System.out.println("inquiryDate2 : " + dateManager.parseDateToString(inquiryDate, "yyyyMMdd"));
		
		List<ExchangeRateDTO> rateDtoList = setupExchangeRateDtoList(dateManager.parseDateToString(inquiryDate, "yyyyMMdd"));
		
		model.addAttribute("ExchangeRateList", rateDtoList);
		model.addAttribute("inquiryDate1", dateManager.parseDateToString(inquiryDate, "yyyy.MM.dd"));
		model.addAttribute("inquiryDate2", dateManager.parseDateToString(inquiryDate, "yyyy-MM-dd"));
		
		return "exchange/rateInquiry";
	}
	
	/* ȯ�� ������ �⺻ - ���� ��¥ ���� */
	@GetMapping("/rateInquiry") 
	public String rateInquiry(Model model) {
		System.out.println("rateInquiry()");
		
		List<ExchangeRateDTO> rateDtoList = setupExchangeRateDtoList(dateManager.getCurrentDate("yyyyMMdd"));
		
		model.addAttribute("ExchangeRateList", rateDtoList);
		model.addAttribute("inquiryDate1", dateManager.getCurrentDate("yyyy.MM.dd"));
		model.addAttribute("inquiryDate2", dateManager.getCurrentDate("yyyy-MM-dd"));
		
		return "exchange/rateInquiry";
	}
	
	
	// ȯ�������� �����ͼ� ��¿� DTO�� ����� return
	private List<ExchangeRateDTO> setupExchangeRateDtoList(String strDate) {
		System.out.println("setupExchangeRateDtoList()");
		System.out.println("strDate : " + strDate);
		List<ExchangeRateBean> rateBeanList = exchangeRateService.getExchangeRate(strDate);
		if (null == rateBeanList || rateBeanList.isEmpty()) {
			return null;
		}
		
		List<ExchangeRateDTO> rateDtoList = new ArrayList<>();
		
		for (ExchangeRateBean bean : rateBeanList) {
			ExchangeRateDTO dto = new ExchangeRateDTO();
			convertBeanToDto(bean, dto);
			dto.setCode_money_name(codeMoneyService.getCodeMoneyName(bean.getCode_money()));
			rateDtoList.add(dto);
		}
		return rateDtoList;
	}
	
	// ExchangeRateBean�� ExchangeRateDTO�� ��ȯ
	private void convertBeanToDto(ExchangeRateBean bean, ExchangeRateDTO dto) {
	    dto.setCode_date(bean.getCode_date());
	    dto.setCode_money(bean.getCode_money());
	    dto.setEx_buy(bean.getEx_buy());
	    dto.setEx_sell(bean.getEx_sell());
	    dto.setEx_standard(bean.getEx_standard());
	}
	
}
