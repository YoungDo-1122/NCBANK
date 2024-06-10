package kr.co.soldesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ncbank.beans.TradeBean;
import ncbank.service.TradeService;

@Controller
public class TradeController {

    @Autowired
    private TradeService tradeService;
    
    @GetMapping("/exchange/exchangeHistory")
    public String getTradeList(TradeBean tradeBean ,Model model) {
    	
		/* Controller 에서 처리하면서 테스트 하는게 편함.
		 * try {
		 * 
		 * } catch (Exception e) { // TODO: handle exception }
		 */
    	
    	List<TradeBean> tradeList = tradeService.getTradeList3(tradeBean);
        System.out.println("TradeController");
    	System.out.println(tradeList);
    	System.out.println("--------------------");
        model.addAttribute("tradeList", tradeList);
        return "exchange/exchangeHistory";
    }
}
