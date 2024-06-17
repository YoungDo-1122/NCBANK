package ncbank.dao;

import ncbank.beans.ExchangeApiRateBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExchangeApiRateDao {

    private static HttpURLConnection connection;

    public List<ExchangeApiRateBean> getAllExchangeRates() {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        JSONParser parser = new JSONParser();

        String authKey = "orVizHCxNgTCW5vfV4o6cvfWpYEOB7BQ";
        String searchDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String dataType = "AP01";
        List<ExchangeApiRateBean> exchangeRates = new ArrayList<>();

        try {
            URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + authKey + "&searchdate=" + searchDate + "&data=" + dataType);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                JSONArray exchangeRateInfoList = (JSONArray) parser.parse(responseContent.toString());
                for (Object o : exchangeRateInfoList) {
                    JSONObject jsonRate = (JSONObject) o;
                    ExchangeApiRateBean rate = new ExchangeApiRateBean();
                    rate.setCurUnit((String) jsonRate.get("cur_unit"));
                    rate.setCurNm((String) jsonRate.get("cur_nm"));
                    rate.setDealBasR((String) jsonRate.get("deal_bas_r"));
                    rate.setTtb((String) jsonRate.get("ttb"));
                    rate.setTts((String) jsonRate.get("tts"));
                    rate.setBkpr((String) jsonRate.get("bkpr"));
                    rate.setYyEfeeR((String) jsonRate.get("yy_efee_r"));
                    rate.setKftcDealBasR((String) jsonRate.get("kftc_deal_bas_r"));
                    rate.setKftcBkpr((String) jsonRate.get("kftc_bkpr"));
                    exchangeRates.add(rate);
                }
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                throw new IOException(responseContent.toString());
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return exchangeRates;
    }
}
