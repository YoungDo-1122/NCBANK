package ncbank.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.TradeBean;

public interface TradeMapper {

    @Select("SELECT trade_num, trade_date, trade_money, trade_rate, trade_type, code_money FROM trade")
    List<TradeBean> getAllTradeList();

    @Select("<script>" +
            "SELECT trade_num, trade_date, trade_money, trade_rate, trade_type, code_money " +
            "FROM trade " +
            "WHERE trade_date BETWEEN #{startDate} AND #{endDate}" +
            "</script>")
    List<TradeBean> getTradeListByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
