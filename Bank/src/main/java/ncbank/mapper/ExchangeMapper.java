package ncbank.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.AccountBean;
import ncbank.beans.CodeBankBean;
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
			"code_organ_name, "+
			"code_bank_address, "+
			"code_bank_name "+
			"from code_bank")
	List<CodeBankBean> getCodeBankName();

	
	
	// 검색된 은행 정보를 가져오는 쿼리
	@Select("SELECT code_bank, code_organ_name, code_bank_address, code_bank_name " +
	        "FROM code_bank " +
	        "WHERE code_bank_address LIKE '%' || #{keyword} || '%'")
	List<CodeBankBean> searchBankByKeyword(@Param("keyword") String keyword);
	
}
