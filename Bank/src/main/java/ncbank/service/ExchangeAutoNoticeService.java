package ncbank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.ExchangeAutoNoticeBean;
import ncbank.dao.ExchangeAutoNoticeDAO;
import ncbank.utility.DateManager;

@Service
public class ExchangeAutoNoticeService {
	
	@Autowired
	private ExchangeAutoNoticeDAO exchangeAutoNoticeDAO;
	
	@Autowired
	private DateManager dateManager;
	
	/* select */
	public List<ExchangeAutoNoticeBean> getAllExchangeAutoNotice() {
		System.out.println("ExchangeAutoNoticeService getAllExchangeAutoNotice()");
		return exchangeAutoNoticeDAO.getAllExchangeAutoNotice();
	}
	
	public ExchangeAutoNoticeBean getExchangeAutoNotice(int user_num) {
		System.out.println("ExchangeAutoNoticeService getExchangeAutoNotice()");
		return exchangeAutoNoticeDAO.getExchangeAutoNotice(user_num);
	}
	
	public int getAutoNoticeSendState(int user_num) {
		System.out.println("ExchangeAutoNoticeService getAutoNoticeSendState()");
		return exchangeAutoNoticeDAO.getAutoNoticeSendState(user_num);
	}
	
	/* update */
	public void updateExchangeAutoNotice(int send_state, int user_num) {
		System.out.println("ExchangeAutoNoticeService updateExchangeAutoNotice()");
		exchangeAutoNoticeDAO.updateExchangeAutoNotice(send_state,
				dateManager.parseStringToDate(dateManager.getCurrentDate("yyyyMMdd"), "yyyyMMdd"), 
				user_num);
	}
	
	/* insert */
	public void addExchangeAutoNotice(int user_num) {
		System.out.println("ExchangeAutoNoticeService addExchangeAutoNotice()");
		exchangeAutoNoticeDAO.addExchangeAutoNotice(user_num,
				dateManager.parseStringToDate(dateManager.getCurrentDate("yyyyMMdd"), "yyyyMMdd"));
	}
	
	/* delete */
	public void deleteExchangeAutoNotice(int user_num) {
		System.out.println("ExchangeAutoNoticeService deleteExchangeAutoNotice()");
		exchangeAutoNoticeDAO.deleteExchangeAutoNotice(user_num);
	}
	
}
