package ncbank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.CrerateTradeBean;
import ncbank.mapper.TradeMapper;

@Repository
public class TradeDao {

    @Autowired
    private TradeMapper tradeMapper;
    
    // Trade 테이블 전체 다 불러오기
    public List<CrerateTradeBean> getTradePlusList(int user_num) {
    	//System.out.println("tradeMapper.getAllTradeList(): "+tradeMapper.getTradePlusList(user_num));
        return tradeMapper.getTradePlusList(user_num);
    }
    
    /*
    public List<TradeBean> getTradeListByDateRange(Date startDate, Date endDate) {
        return tradeMapper.getTradeListByDateRange(startDate, endDate);
    }
    */
    
    
}
