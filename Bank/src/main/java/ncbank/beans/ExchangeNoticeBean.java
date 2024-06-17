package ncbank.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ExchangeNoticeBean {
	
	private int notice_num;
	private float notice_rate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private int user_num;
	private String code_money;
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public float getNotice_rate() {
		return notice_rate;
	}
	public void setNotice_rate(float notice_rate) {
		this.notice_rate = notice_rate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getCode_money() {
		return code_money;
	}
	public void setCode_money(String code_money) {
		this.code_money = code_money;
	}

	
}
