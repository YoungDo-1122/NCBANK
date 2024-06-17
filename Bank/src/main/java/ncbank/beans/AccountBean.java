package ncbank.beans;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountBean {
	private String ac_name;
	private String account;
	@Size(max = 4, min = 4)
	@Pattern(regexp = "\\d+", message = "숫자만 입력해야 합니다")
	private String ac_password;
	private Date ac_date;
	private int ac_type;
	@Size(max = 30, min = 1)
	@Pattern(regexp = "\\d+", message = "숫자만 입력해야 합니다")
	private String ac_balance;

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

	public String getAc_balance() {
		return ac_balance;
	}

	public void setAc_balance(String ac_balance) {
		this.ac_balance = ac_balance;
	}
}
