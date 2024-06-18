package ncbank.mapper;


import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.AccountBean;
import ncbank.beans.CodeBankBean;
import ncbank.beans.CreateExchangeBean;
import ncbank.beans.ExchangeBean;

public interface ExchangeMapper {
	
	// api 데이터 exchange에 저장된거 가져오기.
	 @Select("select "+
            "code_date, "+
            "code_money, "+
            "ex_standard "+
            "from exchange "+
            "where to_char(code_date, 'YY/MM/DD') = #{cuDate}")
	    List<ExchangeBean> getExchangeList(@Param("cuDate") String cuDate);
	
	 @Select("SELECT "+
				"ac.user_num, "+
				"ac.account, "+
				"ac.ac_password "+
				"FROM account ac "+
				"JOIN login lg "+
				"ON ac.user_num = lg.user_num "+
				"WHERE ac.user_num = #{user_num} "+
				"order by ac.user_num ")
		List<AccountBean> getAccount(@Param("user_num") int user_num);
	 
	@Select("select "+
			"code_bank, "+
			"code_organ_name, "+
			"code_bank_name, "+
			"code_bank_address "+
			"from code_bank")
	List<CodeBankBean> getCodeBankName();
	
	// ExchangeAsk에서 얻어낸 정보를 Trade 테이블에 집어넣기
    @Insert("INSERT into trade "+
    		"(trade_num, trade_date, trade_money, trade_rate, trade_type, "+
    		"trade_reservation_date, code_bank, user_num, code_money) values "+
    		"(trade_num_seq.nextval, to_date(sysdate,'yy/mm/dd'), #{trade_money}, #{trade_rate}, "+
    		"'매입', #{trade_reservation_date}, #{code_bank}, #{user_num}, #{code_money} )")
    
    void  setTradeByExchangeAsk(CreateExchangeBean createExchangeBean);

	
	/*
	// 검색된 은행 정보를 가져오는 쿼리
	@Select("SELECT code_bank, code_organ_name, code_bank_address, code_bank_name " +
	        "FROM code_bank " +
	        "WHERE code_bank_address LIKE '%' || #{keyword} || '%'")
	List<CodeBankBean> searchBankByKeyword(@Param("keyword") String keyword);
	*/
	
}
