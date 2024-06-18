package ncbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.CrerateTradeBean;
import ncbank.dao.TradeDao;

@Service
public class TradeService {

    @Autowired
    private TradeDao tradeDao;

    // Trade 테이블 전체 다 불러오기
    public List<CrerateTradeBean> getTradePlusList(int user_num) {
    	//System.out.println("tradeDao.getAllTradeList(): "+tradeDao.getTradePlusList(user_num));
        return tradeDao.getTradePlusList(user_num);
    }
    
    /*
    public List<TradeBean> getTradeListByDateRange(Date startDate, Date endDate) {
        return tradeDao.getTradeListByDateRange(startDate, endDate);
    }
    */
    
    
}
