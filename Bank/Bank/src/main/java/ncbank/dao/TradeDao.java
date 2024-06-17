package ncbank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.TradeBean;
import ncbank.mapper.TradeMapper;

@Repository
public class TradeDao {

    @Autowired
    private TradeMapper tradeMapper;

    public List<TradeBean> getTradeList2(TradeBean tradeBean) {
    	
    		
        	List<TradeBean> tradeList = tradeMapper.getTradeList1(tradeBean);
	    	System.out.println("TradeController");
	    	System.out.println(tradeList);
	    	System.out.println("--------------------");
	    	return tradeList;
	    	
      
    	/*
    	List<TradeBean> tradeList = new ArrayList<TradeBean>();
    	TradeBean tradeBean1 = new TradeBean(); 
    	
    	try {
			// tradeList = tradeMapper.getTradeList1(tradeBean1);
    		tradeBean1.setTrade_num(101);
    		tradeBean1.setTrade_money(100000);
    		tradeBean1.setTrade_rate(1378);
    		tradeBean1.setTrade_type(1);
    		tradeBean1.setCode_bank(1201);
    		tradeBean1.setUser_num("1");
    		tradeBean1.setCode_money("USD");
    		
    		
    		tradeList.add(0, tradeBean1);
    		
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
        return tradeList;
        */
    }
}