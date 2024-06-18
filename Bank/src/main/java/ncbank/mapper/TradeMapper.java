package ncbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import ncbank.beans.CrerateTradeBean;

public interface TradeMapper {
	
	// Trade 테이블 전체 다 불러오기
	@Select("select tr.trade_num, tr.trade_date, tr.trade_type, "+
			"tr.code_money, tr.trade_money, tr.trade_rate, "+
			"tr.code_bank, cb.code_bank_tel, cb.code_bank_fax "+
			"from trade tr "+
			"join code_bank cb "+
			"on tr.code_bank = cb.code_bank "+
			"and tr.user_num = #{user_num} "+
			"order by tr.trade_date desc")
    List<CrerateTradeBean> getTradePlusList(int user_num);
    
	/*
    // startDate 와 endDate 
    @Select("<script>" +
            "SELECT trade_num, trade_date, trade_money, trade_rate, trade_type, code_money " +
            "FROM trade " +
            "WHERE trade_date BETWEEN #{startDate} AND #{endDate}" +
            "</script>")
    List<TradeBean> getTradeListByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    */
    
    // 회원번호, 통화코드 별 금액합산
	
    
}
