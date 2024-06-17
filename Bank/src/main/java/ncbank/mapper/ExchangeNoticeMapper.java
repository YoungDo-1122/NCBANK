package ncbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.ExchangeNoticeBean;

public interface ExchangeNoticeMapper {
	
	@Select("")
	public List<ExchangeNoticeBean> getAllExchangeRateNotice();
	
	@Insert("")
	public void addExchangeRateNotice(ExchangeNoticeBean noticeBean);
	
}
