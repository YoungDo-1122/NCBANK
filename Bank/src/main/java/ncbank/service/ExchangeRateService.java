package ncbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbanck.utility.DateManager;
import ncbanck.utility.ExchangeRateUtility;
import ncbank.beans.ExchangeRateBean;
import ncbank.dao.ExchangeRateDAO;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateDAO exchangeRateDAO = null;
    @Autowired
    private ExchangeRateUtility exchangeRateUtils = null;

    public int getExchangeRateCount(String strDate) {
        return exchangeRateDAO.getExchangeRateCount(strDate);
    }

    // 지정 날짜 환율정보를 DB에서 가져옴
    public List<ExchangeRateBean> getExchangeRate(String strDate) {
        System.out.println("getExchangeRate()");
        System.out.println("strDate : " + strDate);
        List<ExchangeRateBean> beanList = exchangeRateDAO.getExchangeRate(strDate);
        System.out.println("beanList : " + beanList);
        if (null == beanList || beanList.isEmpty()) { // DB에 등록된 환율정보가 없으면 크롤링
            beanList = fetchExchangeRate(strDate);
        }

        return beanList;
    }

    // 지정 날짜 환율정보 크롤링 후 DB에 추가
    private List<ExchangeRateBean> fetchExchangeRate(String strDate) {
        System.out.println("fetchExchangeRate()");
        System.out.println("strDate : " + strDate);
        // 여기서 null 을 뱉으면 API에서 제공 불가능한 날짜의 환율
        List<ExchangeRateBean> beanList = exchangeRateUtils.fetchExchangeRateList(strDate);
        System.out.println("beanList : " + beanList);
        if (null == beanList || beanList.isEmpty()) { // 크롤링한 환율정보가 없을경우
            return null;
        }

        for (ExchangeRateBean bean : beanList) { // 환율정보를 DB추가
            exchangeRateDAO.addExchangeRate(bean);
        }
        return beanList;
    }

}
