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
	
	public List<ExchangeRateBean> getAllExchangeRate() {
		return exchangeRateMapper.getAllExchangeRate();
	}
	
	public int getExchangeRateCount(String date) {
		return exchangeRateMapper.getExchangeRateCount(date);
	}
	
	public List<ExchangeRateBean> getExchangeRate(String date) {
		return exchangeRateMapper.getExchangeRate(date);
	}
	
	public void addExchangeRate(ExchangeRateBean exchangeBean) {
		System.out.println("ExchangeRateDAO addExchangeRate()");
		exchangeRateMapper.addExchangeRate(exchangeBean);
	}
	
}
