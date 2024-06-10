package ncbanck.utility;

import java.util.Date;

// ��¿� ������ ���� DTO
public class ExchangeRateDTO {
	// ExchangeRate ���� ä�� ��
	private Date code_date = null; // �Ͻ�
	private String code_money = ""; // ��ȭ �ڵ�
	private float ex_buy = 0.0f; // ���� �� ȯ��
	private float ex_sell = 0.0f; // �Ǹ� �� ȯ��
	private float ex_standard = 0.0f; // �Ÿ� ������
	// CodeMoney ���� ä�� ��
	private String code_money_name = ""; // ��ȭ�ڵ� ��
	
	public Date getCode_date() {
		return code_date;
	}
	public void setCode_date(Date code_date) {
		this.code_date = code_date;
	}
	public String getCode_money() {
		return code_money;
	}
	public void setCode_money(String code_money) {
		this.code_money = code_money;
	}
	public float getEx_buy() {
		return ex_buy;
	}
	public void setEx_buy(float ex_buy) {
		this.ex_buy = ex_buy;
	}
	public float getEx_sell() {
		return ex_sell;
	}
	public void setEx_sell(float ex_sell) {
		this.ex_sell = ex_sell;
	}
	public float getEx_standard() {
		return ex_standard;
	}
	public void setEx_standard(float ex_standard) {
		this.ex_standard = ex_standard;
	}
	
	public String getCode_money_name() {
		return code_money_name;
	}
	public void setCode_money_name(String code_money_name) {
		this.code_money_name = code_money_name;
	}
}
