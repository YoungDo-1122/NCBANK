package ncbanck.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.ExchangeRateBean;

@Repository // Bean���� ���
public class ExchangeRateUtility {

	/* ��û URL */
	private final String requestURL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON";
	/* ��û ���� */
	// ����Ű
	private final String authKey = "V8mETPuk3L60CYJjpfWW3nssE0ZbRgiY"; // ���� ���� Ű�� ��ü
	// �˻� ��û APIŸ�� : AP01 - ȯ��, AP02 - ����ݸ�, AP03 - �����ݸ�
	private final String dataType = "AP01";
	
	@Autowired
	private DateManager dateManager = null;
	
	// �˻� ��û ��¥ : 20150101 (DEFAULT)������
	public List<ExchangeRateBean> fetchExchangeRateList(String searchDate) {

		HttpURLConnection con = null;
		List<ExchangeRateBean> beanList = null;
		
		System.out.println("ExchangeRateUtility - fetchExchangeRateList()");
		System.out.println("searchDate : " + searchDate);
		Date date = dateManager.parseStringToDate(searchDate, "yyyyMMdd");
		if (null == date) {
			System.out.println("searchDate null");
			return null;
		}
		
		try {
			// ��û URL ����
			String strUrl = buildRequestURL(searchDate);
			System.out.println("strUrl : " + strUrl);
			URL url = new URL(strUrl);
			con = (HttpURLConnection) url.openConnection();

			// ��û �ʱ� ����
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000); // ����õ� �ð� - 5��
			con.setReadTimeout(5000); // ������ ��û �ð� - 5��

			// HTTP ���� �����ڵ� ������ (���� 300 �̻��� �����ڵ�� ���з� ����)
			int responseCode = con.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			BufferedReader br = null;

			if (responseCode > 299) { // HTTP ����
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8));
				System.err.println("HTTP ���� ����. ���� �ڵ�: " + responseCode);
				return null;
			} else { // ����
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
				System.out.println("HTTP ���� ����");
			}

			String inputLine;
			StringBuilder responseContent = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				responseContent.append(inputLine);
			}
			br.close();

			if (0 >= responseContent.length()) { // ���� ���� ������ - ���� �������� �ʴ� ��¥������ ��û
				System.out.println("�ش� ��¥�� ���� ȯ�� �����͸� ã�� �� �����ϴ�.");
				return null;
			}

			beanList = new ArrayList<ExchangeRateBean>();
			// �ش糯¥�� ���� �޷�, �� �� ��� ��ȭ ������ ������� -> array
			JSONArray resultArray = new JSONArray(responseContent.toString());

			for (int i = 0; i < resultArray.length(); i++) {
				JSONObject resultObject = resultArray.getJSONObject(i);
				ExchangeRateBean bean = new ExchangeRateBean();

				// RESULT - ��ȸ ��� (1: ����, 2: DATA �ڵ� ����, 3: �����ڵ� ����, 4: �������� Ƚ�� ����)
				int result = resultObject.getInt("result");
				System.out.println("��ȸ ���: " + result);

				if (1 == result) { 
					System.out.println("1 : ���� ����");
					
					setupExchangeRateBean(bean, resultObject, searchDate);
					
					System.out.println(bean.getCode_money() + " | " + bean.getCode_date());
					beanList.add(bean);
					System.out.println("add");

				} else if (2 == result) {
					System.out.println("2 : DATA �ڵ� ����");
				} else if (3 == result) {
					System.out.println("3 : �����ڵ� ����");
				} else if (4 == result) {
					System.out.println("4 : �������� Ƚ�� ����");
				} else {
					System.out.println("�� �� ���� �����ڵ�");
				}
				
				System.out.println("==========");
				
			} // for

		} catch (MalformedURLException e) {
			System.err.println("�߸��� URL ����: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("����� ����: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		
		return beanList;
	}
	
	private String buildRequestURL(String searchDate) {
		return requestURL + "?authkey=" + authKey + "&searchdate=" + searchDate + "&data=" + dataType;
	}
	
	private void setupExchangeRateBean(ExchangeRateBean bean, JSONObject resultObject, String searchDate)
			throws ParseException {
		/* get���� �����ö� �빮�ڸ� ������.
		{"result":1,"cur_unit":"AED","ttb":"371.3","tts":"378.81",
		"deal_bas_r":"375.06","bkpr":"375","yy_efee_r":"0","ten_dd_efee_r":"0",
		"kftc_bkpr":"375","kftc_deal_bas_r":"375.06","cur_nm":"�ƶ����̸�Ʈ ����"}
		*/
		
		// �Ͻ� 
		bean.setCode_date(dateManager.parseStringToDate(searchDate, "yyyyMMdd"));
		
		// cur_unit - ��ȭ �ڵ�
		String curUnit = resultObject.getString("cur_unit").replace("(100)", "");
		bean.setCode_money(curUnit);

		// cur_nm ����/��ȭ��
		String curNm = resultObject.getString("cur_nm");
		
		// float ġȯ�ϴµ� , ������ ������ 
		// 1. replace([��������], [�ٲܹ���]) - �ܼ� �ĸ��� ���� �� | 2. NumberFormat - ���ڴٷ�� ����
		NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
		
		// ttb �۱� ������
		String ttb = resultObject.getString("ttb").replace(",", "");
		bean.setEx_buy(format.parse(ttb).floatValue());
		
		// tts �۱� ������
		String tts = resultObject.getString("tts");
		bean.setEx_sell(format.parse(tts).floatValue());

		// deal_bas_r �Ÿ� ������
		String dealBasR = resultObject.getString("deal_bas_r".toLowerCase());
		bean.setEx_standard(format.parse(dealBasR).floatValue());

		/* ���� ������� �ʴµ� ���� �����͵� */
		// bkpr ��ΰ���
		String bkpr = resultObject.getString("bkpr");

		// yy_efee_r ��ȯ������
		String yyEfeeR = resultObject.getString("yy_efee_r");

		// ten_dd_efee_r 10�� ȯ���η���
		String tenDdEfeeR = resultObject.getString("ten_dd_efee_r");
		
		// kftc_bkpr ����ܱ�ȯ�߰� ��ΰ���
		String kftcBkpr = resultObject.getString("kftc_bkpr");
		
		// kftc_deal_bas_r ����ܱ�ȯ�߰� �Ÿű�����
		String kftcDealBasR = resultObject.getString("kftc_deal_bas_r");
		
		// Ȯ�ο� ���
		System.out.println("��ȭ�ڵ�: " + curUnit);
		System.out.println("����/��ȭ��: " + curNm);
		System.out.println("����ȯ(�۱�) �����Ƕ�: " + ttb);
		System.out.println("����ȯ(�۱�) �����Ƕ�: " + tts);
		System.out.println("�Ÿ� ������: " + dealBasR);
		System.out.println("��ΰ���: " + bkpr);
		System.out.println("��ȯ������: " + yyEfeeR);
		System.out.println("10��ȯ������: " + tenDdEfeeR);
		System.out.println("����ܱ�ȯ�߰� ��ΰ���: " + kftcBkpr);
		System.out.println("����ܱ�ȯ�߰� �Ÿű�����: " + kftcDealBasR);
	}
	
	
}
