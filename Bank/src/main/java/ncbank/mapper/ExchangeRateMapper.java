package ncbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.ExchangeRateBean;

public interface ExchangeRateMapper {
	
	@Select("select code_date, code_money, ex_buy, ex_sell, ex_standard " + 
			"from exchange " + "order by code_date")
	public List<ExchangeRateBean> getAllExchangeRate();
	
	// �ش��Ͽ� ȯ�������� �����ϴ��� üũ
	@Select("select count(*) "+ "from exchange " + 
			"where code_date = to_date(#{date}, 'yyyyMMdd')")
	public int getExchangeRateCount(String date);
	
	// �ش����� ȯ������ ��������
	@Select("select code_date, ex_buy, ex_sell, ex_standard, code_money "+
			"from exchange " + "where code_date = to_date(#{date}, 'yyyyMMdd')")
	public List<ExchangeRateBean> getExchangeRate(String date);
	
	// ȯ�� ���� �߰�
	@Insert("insert into exchange " + 
			"values(#{code_date}, #{code_money}, #{ex_buy}, #{ex_sell}, #{ex_standard})")
	public void addExchangeRate(ExchangeRateBean exchangeBean);
}
