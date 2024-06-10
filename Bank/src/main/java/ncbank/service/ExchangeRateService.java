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
	
	// ���� ��¥ ȯ�������� DB���� ������
	public List<ExchangeRateBean> getExchangeRate(String strDate) { 
		System.out.println("getExchangeRate()");
		System.out.println("strDate : " + strDate);
		List<ExchangeRateBean> beanList = exchangeRateDAO.getExchangeRate(strDate);
		System.out.println("beanList : " + beanList);
		if (null == beanList  || beanList.isEmpty()) { // DB�� ��ϵ� ȯ�������� ������ ũ�Ѹ�
			beanList = fetchExchangeRate(strDate);
		}
		
		return beanList;
	}
	// ���� ��¥ ȯ������ ũ�Ѹ� �� DB�� �߰�
	private List<ExchangeRateBean> fetchExchangeRate(String strDate) { 
		System.out.println("fetchExchangeRate()");
		System.out.println("strDate : " + strDate);
		// ���⼭ null �� ������ API���� ���� �Ұ����� ��¥�� ȯ��
		List<ExchangeRateBean> beanList = exchangeRateUtils.fetchExchangeRateList(strDate);
		System.out.println("beanList : " + beanList);
		if (null == beanList  || beanList.isEmpty()) { // ũ�Ѹ��� ȯ�������� �������
			return null;
		}
		
		for (ExchangeRateBean bean : beanList) { // ȯ�������� DB�߰�
			exchangeRateDAO.addExchangeRate(bean);
		}
		return beanList;
	}
	
}
