package ncbank.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Select;

import ncbank.beans.TradeBean;

public interface TradeMapper {
	
	@Select("SELECT " +
            "trade_num, " +
            "trade_money, " +
            "trade_rate, " +
            "trade_type, " +
            "code_bank, " +
            "user_num, " +
            "code_money " +
            "FROM trade")
    List<TradeBean> getTradeList1(TradeBean tradeBean);
	
}
