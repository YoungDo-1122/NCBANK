package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ncbank.beans.CrerateTradeBean;
import ncbank.beans.UserBean;
import ncbank.service.TradeService;

@Controller
@RequestMapping("/exchange")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    
    @Resource(name="loginUserBean")
    UserBean loginUserBean;
    
    @GetMapping("/exchangeHistory")
    public String getTradeList(
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate,
            Model model)  {
    	
    	/*
        List<TradeBean> tradeList;
        
        if (startDate != null && endDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yy/mm/dd");
            Date start = formatter.parse(startDate);
            Date end = formatter.parse(endDate);
            tradeList = tradeService.getTradeListByDateRange(start, end);
        } else {
            tradeList = tradeService.getAllTradeList();
        }
        */
    	
        //model.addAttribute("tradeList", tradeList);
        //model.addAttribute("startDate", startDate);
        //model.addAttribute("endDate", endDate);
    	
    	List<CrerateTradeBean> tradePlusList = tradeService.getTradePlusList(loginUserBean.getUser_num());
    	System.out.println("tradePlusList: "+tradePlusList);
        model.addAttribute("tradePlusList", tradePlusList);
        model.addAttribute("loginUserBean.getUser_num()", loginUserBean.getUser_num());
        
        return "exchange/exchangeHistory";
    }
}
