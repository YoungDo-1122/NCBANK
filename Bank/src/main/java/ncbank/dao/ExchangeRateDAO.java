package ncbank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.ExchangeRateBean;
import ncbank.mapper.ExchangeRateMapper;

@Repository
public class ExchangeRateDAO {

    @Autowired
    private ExchangeRateMapper exchangeRateMapper = null;

    /* Select */

    // DB에 저장되어 있는 데이터중 가장 최신의 환율 데이터 가져오기
    public List<ExchangeRateBean> getFinalExchangeRate() {
    	return exchangeRateMapper.getFinalExchangeRate();
    }
    // 최신 환율 데이터중 원하는 동화의 데이터
    public ExchangeRateBean findFinalExchangeRate(String code_money) {
    	return exchangeRateMapper.findFinalExchangeRate(code_money);
    }
    
    // 지정한 날짜의 환율정보 가져오기
    public List<ExchangeRateBean> getExchangeRate(String date) {
        return exchangeRateMapper.getExchangeRate(date);
    }
    
   
    /* insert */
    public void addExchangeRate(ExchangeRateBean exchangeBean) {
    	System.out.println("ExchangeRateDAO addExchangeRate()");
        exchangeRateMapper.addExchangeRate(exchangeBean);
    }

}
