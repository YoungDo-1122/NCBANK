package ncbank.beans;

import java.util.Date;

public class AutoBean {
	private int auto_num;
	private Date auto_next_date;
	private int auto_type;
	private Date auto_end;
	private int auto_status;
	private String to_account;
	private String from_account;
	private String code_organ;

	public int getAuto_num() {
		return auto_num;
	}

	public void setAuto_num(int auto_num) {
		this.auto_num = auto_num;
	}

	public Date getAuto_next_date() {
		return auto_next_date;
	}

	public void setAuto_next_date(Date auto_next_date) {
		this.auto_next_date = auto_next_date;
	}

	public int getAuto_type() {
		return auto_type;
	}

	public void setAuto_type(int auto_type) {
		this.auto_type = auto_type;
	}

	public Date getAuto_end() {
		return auto_end;
	}

	public void setAuto_end(Date auto_end) {
		this.auto_end = auto_end;
	}

	public int getAuto_status() {
		return auto_status;
	}

	public void setAuto_status(int auto_status) {
		this.auto_status = auto_status;
	}

	public String getTo_account() {
		return to_account;
	}

	public void setTo_account(String to_account) {
		this.to_account = to_account;
	}

	public String getFrom_account() {
		return from_account;
	}

	public void setFrom_account(String from_account) {
		this.from_account = from_account;
	}

	public String getCode_organ() {
		return code_organ;
	}

	public void setCode_organ(String code_organ) {
		this.code_organ = code_organ;
	}
}
