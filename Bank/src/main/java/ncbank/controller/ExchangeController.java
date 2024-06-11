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

import ncbank.beans.ExchangeRateBean;
import ncbank.service.CodeMoneyService;
import ncbank.service.ExchangeRateService;
import ncbank.utility.DateManager;
import ncbank.utility.ExchangeRateDTO;

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

    /* 환율 조회페이지에서 날짜를 선택한 경우 */
    @GetMapping("/rateInquiryDate")
    // @RequestParam("inquiryDay") : name이 inquiryDay 인 값을 가져온다 (input 의 name 이
    // inquiryDay)
    // @DateTimeFormat(pattern = "yyyyMMdd") : inquiryDay를 Date로 변환하기 위한 포멧 설정
    public String rateInquiryDate(@RequestParam("inquiryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inquiryDate,
            Model model) {
        System.out.println("rateInquiryDate()");

        System.out.println("inquiryDate : " + inquiryDate);
        System.out.println("inquiryDate2 : " + dateManager.parseDateToString(inquiryDate, "yyyyMMdd"));

        List<ExchangeRateDTO> rateDtoList = setupExchangeRateDtoList(
                dateManager.parseDateToString(inquiryDate, "yyyyMMdd"));

        model.addAttribute("ExchangeRateList", rateDtoList);
        model.addAttribute("inquiryDate1", dateManager.parseDateToString(inquiryDate, "yyyy.MM.dd"));
        model.addAttribute("inquiryDate2", dateManager.parseDateToString(inquiryDate, "yyyy-MM-dd"));

        return "exchange/rateInquiry";
    }

    /* 환율 페이지 기본 - 현재 날짜 기준 */
    @GetMapping("/rateInquiry")
    public String rateInquiry(Model model) {
        System.out.println("rateInquiry()");

        List<ExchangeRateDTO> rateDtoList = setupExchangeRateDtoList(dateManager.getCurrentDate("yyyyMMdd"));

        model.addAttribute("ExchangeRateList", rateDtoList);
        model.addAttribute("inquiryDate1", dateManager.getCurrentDate("yyyy.MM.dd"));
        model.addAttribute("inquiryDate2", dateManager.getCurrentDate("yyyy-MM-dd"));

        return "exchange/rateInquiry";
    }

    // 환율정보를 가져와서 출력용 DTO로 만들어 return
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

    // ExchangeRateBean을 ExchangeRateDTO로 변환
    private void convertBeanToDto(ExchangeRateBean bean, ExchangeRateDTO dto) {
        dto.setCode_date(bean.getCode_date());
        dto.setCode_money(bean.getCode_money());
        dto.setEx_buy(bean.getEx_buy());
        dto.setEx_sell(bean.getEx_sell());
        dto.setEx_standard(bean.getEx_standard());
    }

}
