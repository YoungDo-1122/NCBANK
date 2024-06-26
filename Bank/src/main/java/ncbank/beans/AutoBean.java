package ncbank.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class AutoBean {
	private int auto_num;

	@NotNull
	@Size(max = 50)
	private String auto_name;

	@NotNull
	@Size(max = 30)
	private String auto_money;

	@NotNull
	@Size(max = 3)
	private String auto_type;

	@Size(max = 2)
	private String auto_next_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date auto_start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date auto_end;

	@NotNull
	@Size(max = 20)
	private String to_account;

	@NotNull
	@Size(max = 3)
	private String code_organ;

	@NotNull
	@Size(max = 20)
	private String from_account;

	@NotNull
	private int user_num;

	private String code_organ_name;

	public int getAuto_num() {
		return auto_num;
	}

	public void setAuto_num(int auto_num) {
		this.auto_num = auto_num;
	}

	public String getAuto_name() {
		return auto_name;
	}

	public void setAuto_name(String auto_name) {
		this.auto_name = auto_name;
	}

	public String getAuto_money() {
		return auto_money;
	}

	public void setAuto_money(String auto_money) {
		this.auto_money = auto_money;
	}

	public String getAuto_type() {
		return auto_type;
	}

	public void setAuto_type(String auto_type) {
		this.auto_type = auto_type;
	}

	public String getAuto_next_date() {
		return auto_next_date;
	}

	public void setAuto_next_date(String auto_next_date) {
		this.auto_next_date = auto_next_date;
	}

	public Date getAuto_start() {
		return auto_start;
	}

	public void setAuto_start(Date auto_start) {
		this.auto_start = auto_start;
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

	public String getCode_organ() {
		return code_organ;
	}

	public void setCode_organ(String code_organ) {
		this.code_organ = code_organ;
	}

	public String getFrom_account() {
		return from_account;
	}

	public void setFrom_account(String from_account) {
		this.from_account = from_account;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getCode_organ_name() {
		return code_organ_name;
	}

	public void setCode_organ_name(String code_organ_name) {
		this.code_organ_name = code_organ_name;
	}

}
