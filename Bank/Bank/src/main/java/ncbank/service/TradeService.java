package ncbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.TradeBean;
import ncbank.dao.TradeDao;

@Service
public class TradeService {

    @Autowired
    private TradeDao tradeDao;

    public List<TradeBean> getTradeList3(TradeBean tradeBean) {
    	
    	
    	List<TradeBean> tradeList = tradeDao.getTradeList2(tradeBean);
    	System.out.println("TradeService");
    	System.out.println(tradeList);
    	System.out.println("--------------------");
        return tradeList;
    	
    	/*
    	List<TradeBean> tradeList = new ArrayList<TradeBean>();
		try {
			tradeList = tradeDao.getTradeList2(tradeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return tradeList;
        */
    }
}
