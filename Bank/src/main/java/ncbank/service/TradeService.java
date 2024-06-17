package ncbank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.TradeBean;
import ncbank.dao.TradeDao;

@Service
public class TradeService {

    @Autowired
    private TradeDao tradeDao;

    public List<TradeBean> getAllTradeList() {
        return tradeDao.getAllTradeList();
    }

    public List<TradeBean> getTradeListByDateRange(Date startDate, Date endDate) {
        return tradeDao.getTradeListByDateRange(startDate, endDate);
    }
}
