package ncbank.beans;

import java.util.Date;

public class TransferBean {
	private int trans_num;
	private int trans_type;
	private int trans_amount;
	private int trans_balance;
	private String trans_text;
	private Date trans_date;
	private String to_account;
	private String from_account;
	private String code_organ;

	public int getTrans_num() {
		return trans_num;
	}

	public void setTrans_num(int trans_num) {
		this.trans_num = trans_num;
	}

	public int getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(int trans_type) {
		this.trans_type = trans_type;
	}

	public int getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(int trans_amount) {
		this.trans_amount = trans_amount;
	}

	public int getTrans_balance() {
		return trans_balance;
	}

	public void setTrans_balance(int trans_balance) {
		this.trans_balance = trans_balance;
	}

	public String getTrans_text() {
		return trans_text;
	}

	public void setTrans_text(String trans_text) {
		this.trans_text = trans_text;
	}

	public Date getTrans_date() {
		return trans_date;
	}

	public void setTrans_date(Date trans_date) {
		this.trans_date = trans_date;
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
