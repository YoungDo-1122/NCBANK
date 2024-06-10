package ncbanck.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

// @Component �� Spring���� �˷� Bean���� ����ϸ� �̱���ó�� ��밡��
@Component
public class DateManager {
	
	private DateManager() {}
	
	// pattern ex) yyyyMMdd, yyyy.MM.dd, yyyy-MM.dd
	/* ���糯¥ �������� */
	public String getCurrentDate(String pattern) { 
		return new SimpleDateFormat(pattern).format(new Date());
	}
	
	/* String->Date */
	public Date parseStringToDate(String strDate, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			return formatter.parse(strDate);
		} catch (Exception e) {
			System.out.println("parseDate Error");
			e.printStackTrace();
		}
		return null;
	}
	
	/* Date -> String */
	public String parseDateToString(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
}
