package ncbank.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ncbank.beans.TradeBean;
import ncbank.service.TradeService;

@Controller
@RequestMapping("/exchange")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    
    @GetMapping("/exchangeHistory")
    public String getTradeList(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) throws ParseException {
        
        List<TradeBean> tradeList;
        
        if (startDate != null && endDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yy/mm/dd");
            Date start = formatter.parse(startDate);
            Date end = formatter.parse(endDate);
            tradeList = tradeService.getTradeListByDateRange(start, end);
        } else {
            tradeList = tradeService.getAllTradeList();
        }
        
        model.addAttribute("tradeList", tradeList);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        
        return "exchange/exchangeHistory";
    }
}
