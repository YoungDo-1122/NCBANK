package ncbank.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.TradeBean;
import ncbank.mapper.TradeMapper;

@Repository
public class TradeDao {

    @Autowired
    private TradeMapper tradeMapper;

    public List<TradeBean> getAllTradeList() {
        return tradeMapper.getAllTradeList();
    }

    public List<TradeBean> getTradeListByDateRange(Date startDate, Date endDate) {
        return tradeMapper.getTradeListByDateRange(startDate, endDate);
    }
}
