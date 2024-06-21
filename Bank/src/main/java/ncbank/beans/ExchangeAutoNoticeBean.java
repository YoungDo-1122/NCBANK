package ncbank.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

// 자동알림을 위한 Bean
public class ExchangeAutoNoticeBean {
	
	// 어떤 알림에 대한 자동알림인지
	private int notice_num;
	// 알림을 보냈는지에 대한 여부 0 : 안보냄 , 1 : 보냄
	private int send_state;
	// 갱신날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date update_date;
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public int getSend_state() {
		return send_state;
	}
	public void setSend_state(int send_state) {
		this.send_state = send_state;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	
}
