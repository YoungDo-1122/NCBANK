package ncbank.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExchangeRates {

    private static HttpURLConnection connection;
    

    public static JSONArray getAllExchangeRates() {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        JSONParser parser = new JSONParser();

        String authKey = "orVizHCxNgTCW5vfV4o6cvfWpYEOB7BQ";
        String searchDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //String searchDate = dateFormat.format(new Date(100, 4, 8));
        String dataType = "AP01";
        JSONArray exchangeRateInfoList = null;

        try {
            // Request URL
            URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + authKey + "&searchdate=" + searchDate + "&data=" + dataType);
            connection = (HttpURLConnection) url.openConnection();

            // Request �ʱ� ����
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            // API ȣ��
            if (status == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    exchangeRateInfoList = (JSONArray) parser.parse(line);
                    responseContent.append(line);
                }
                reader.close();
            } else {
                // �������� ���
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return exchangeRateInfoList;
    }

    public static void main(String[] args) {
    	 
        JSONArray exchangeRates = getAllExchangeRates();
        System.out.println("���� : " + exchangeRates.size());
        if (exchangeRates != null) {
            for (Object o : exchangeRates) {
                JSONObject exchangeRateInfo = (JSONObject) o;
               
                System.out.println("��ȭ ����: " + exchangeRateInfo.get("cur_unit"));
                System.out.println("����/��ȭ��: " + exchangeRateInfo.get("cur_nm"));
                System.out.println("�Ÿ� ������: " + exchangeRateInfo.get("deal_bas_r"));
                System.out.println("����ȯ(�۱�)\r\n"
                		+ "�����Ƕ�: " + exchangeRateInfo.get("ttb"));
                System.out.println("����ȯ(�۱�)\r\n"
                		+ "�����Ƕ�: " + exchangeRateInfo.get("tts"));
                System.out.println("��ΰ���: " + exchangeRateInfo.get("bkpr"));
                System.out.println("��ȯ������: " + exchangeRateInfo.get("yy_efee_r"));
                System.out.println("����ܱ�ȯ�߰�\r\n"
                		+ "�Ÿű�����: " + exchangeRateInfo.get("kftc_deal_bas_r"));
                System.out.println("����ܱ�ȯ�߰�\r\n"
                		+ "��ΰ���: " + exchangeRateInfo.get("kftc_bkpr"));
                System.out.println("===================================");
            }
        } else {
            System.out.println("ȯ�� ������ �������� ���߽��ϴ�.");
        }
    }
}
