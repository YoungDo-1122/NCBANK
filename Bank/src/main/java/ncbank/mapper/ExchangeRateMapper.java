package ncbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.ExchangeRateBean;

public interface ExchangeRateMapper {

	/* Select */

    // DB에 저장되어 있는 데이터중 가장 최신의 환율 데이터 가져오기
    @Select("select * from exchange "
    		+ "where code_date = (select max(code_date) from exchange)")
    public List<ExchangeRateBean> getFinalExchangeRate();
    // 최신 환율 데이터중 원하는 동화의 데이터
    @Select("select * from exchange "
    		+ "where code_date = (select max(code_date) from exchange) "
    		+ "and code_money = #{code_money}")
    public ExchangeRateBean findFinalExchangeRate(String code_money);
    

    // 지정한 날짜의 환율정보 가져오기
    @Select("select code_date, ex_buy, ex_sell, ex_standard, code_money " +
            "from exchange " + "where code_date = to_date(#{date}, 'yyyyMMdd')")
    public List<ExchangeRateBean> getExchangeRate(String date);
    
    
    // 선택한 통화의 매매 기준율 가져오기. - 필요한가? -> 아직 DAO에는 추가 안함
    @Select("select code_money, ex_standard " + "from exchange " 
    		+ "where code_date = to_date(#{date}, 'yyyyMMdd') and code_money = #{code_money}")
    public ExchangeRateBean getDealingBasicRate(String date, String code_money);
    
    
    
    /* insert */
    // 환율 정보 추가 - fk제약조건에 맞춰서
    @Insert("insert into exchange(code_date, code_money, ex_buy, ex_sell, ex_standard) " 
    		+ "values(#{code_date}, (select code_money from code_money where code_money = #{code_money}) "
            + ", #{ex_buy}, #{ex_sell}, #{ex_standard})")
    public void addExchangeRate(ExchangeRateBean exchangeBean);
    
}
