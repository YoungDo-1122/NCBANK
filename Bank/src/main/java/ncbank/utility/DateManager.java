package ncbank.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

// @Component 로 Spring한테 알려 Bean으로 등록하면 싱글톤처럼 사용가능
@Component
public class DateManager {

    private DateManager() {
    }

    // pattern ex) yyyyMMdd, yyyy.MM.dd, yyyy-MM.dd
    /* 현재날짜 가져오기 */
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
