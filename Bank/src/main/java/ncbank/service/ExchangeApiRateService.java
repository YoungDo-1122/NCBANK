package ncbank.service;

import java.util.List;

import ncbank.dao.ExchangeApiRateDao;
import ncbank.beans.ExchangeApiRateBean;

public class ExchangeApiRateService {

    private ExchangeApiRateDao exchangeApiRatesDao;

    public ExchangeApiRateService() {
        this.exchangeApiRatesDao = new ExchangeApiRateDao();
    }

    public List<ExchangeApiRateBean> getAllExchangeRates() {
        return exchangeApiRatesDao.getAllExchangeRates();
    }
}
