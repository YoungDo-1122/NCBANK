package ncbank.beans;

import java.util.Date;

public class AccountBean {
	private String ac_name;
	private String account;
	private String ac_password;
	private Date ac_date;
	private int ac_type;
	private int ac_balance;
	
	
	
	//AutoBean
	private int auto_num; //자동이체번호(몇번째 자동이체인지)
	private String auto_balance; //자동이체금액 
	private String auto_type; //월간 주간 0,1
	private String auto_next_date; //요일 or 3일
	private Date auto_end; // 끝나는날짜
	private String auto_status; //활성, 비활성
	private String to_account; //입금계좌
	private String from_account; //출금계좌
	private String code_organ; //입금은행

	
	
	
	public String getAuto_balance() {
		return auto_balance;
	}

	public void setAuto_balance(String auto_balance) {
		this.auto_balance = auto_balance;
	}

	public Date getAuto_end() {
		return auto_end;
	}

	public void setAuto_end(Date auto_end) {
		this.auto_end = auto_end;
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

	public String getAuto_next_date() {
		return auto_next_date;
	}

	public void setAuto_next_date(String auto_next_date) {
		this.auto_next_date = auto_next_date;
	}

	public String getAuto_type() {
		return auto_type;
	}

	public void setAuto_type(String auto_type) {
		this.auto_type = auto_type;
	}

	public String getAuto_status() {
		return auto_status;
	}

	public void setAuto_status(String auto_status) {
		this.auto_status = auto_status;
	}
	
	public int getAuto_num() {
		return auto_num;
	}

	public void setAuto_num(int auto_num) {
		this.auto_num = auto_num;
	}

	
	
	public String getAc_password() {
		return ac_password;
	}

	public void setAc_password(String ac_password) {
		this.ac_password = ac_password;
	}

	public String getAc_name() {
		return ac_name;
	}

	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getAc_date() {
		return ac_date;
	}

	public void setAc_date(Date ac_date) {
		this.ac_date = ac_date;
	}

	public int getAc_type() {
		return ac_type;
	}

	public void setAc_type(int ac_type) {
		this.ac_type = ac_type;
	}

	public int getAc_balance() {
		return ac_balance;
	}

	public void setAc_balance(int ac_balance) {
		this.ac_balance = ac_balance;
	}
}
