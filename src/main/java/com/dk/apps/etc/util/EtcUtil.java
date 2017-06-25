package com.dk.apps.etc.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.dk.apps.etc.domain.etc.TickerTable;
import com.dk.apps.etc.domain.etc.dummy.Ticker;
import com.dk.apps.etc.util.sign.EncryDigestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author Wayne
 * @date 2015-01-16
 */
@SuppressWarnings("static-access")
public class EtcUtil {
	private static Log log = LogFactory.getLog(EtcUtil.class);
	private static String currency = "etc_cny";

	public static String URL_PREFIX = "https://trade.chbtc.com/api/";
	
	/**
	 * ί���µ�
	 * tradeType 1��0��
	 */
	@Test
	public JSONObject order(String accessKey,String secretKey,String price,String amount,String tradeType){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(secretKey);
			//����ܵ���������� tradeType=0����
			String params = "method=order&accesskey="+accessKey+"&price="+price+"&amount="+amount+"&tradeType="+tradeType+"&currency="+currency;
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"order?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
//			System.out.println("testOrder url: " + url);
			//�������
			JSONObject callback = getJSONObject(url, "UTF-8");
			return callback;
//			System.out.println("testOrder ���: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ȡ��ί��
	 */
	@Test
	public JSONObject cancelOrder(String accessKey,String secretKey,String orderId){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(secretKey);
			//����ܵ��������
			String params = "method=cancelOrder&accesskey="+accessKey + "&id=" + orderId + "&currency="+currency;;
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"cancelOrder?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			//�������
			JSONObject callback = getJSONObject(url, "UTF-8");
			return callback;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * (��)��ȡ���ί���򵥻�������ÿ�����󷵻�pageSize<=100����¼
	 * 	tradeType ��������1/0[buy/sell]
	 * pageIndex ��ǰҳ��
	 * pageSize  ÿҳ����
	 */
	@Test
	public static JSONArray getOrders(String accessKey,String secretKey,String tradeType){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(secretKey);	
			//����ܵ��������
			String params = "method=getOrdersNew&accesskey="+accessKey 
					+ "&tradeType="+tradeType
					+ "&currency="+currency
					+ "&pageIndex=1&pageSize=20";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getOrdersNew?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			//�������
			JSONArray callback = getJSONArray(url, "UTF-8");
			return callback;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public static String API_DOMAIN = "http://api.chbtc.com";
	/**
	 * ��ȡ����
	 */
	public static TickerTable getTicker() {
		TickerTable tickerData = new TickerTable();
		try {
			String url = API_DOMAIN+"/data/v1/ticker?currency="+currency;
			JSONObject callback = getJSONObject(url, "UTF-8");
			Ticker ticker = (Ticker) callback.toBean(callback, Ticker.class);
			tickerData.setDate(ticker.getDate());
			tickerData.setBuy(ticker.getTicker().getBuy());
			tickerData.setHigh(ticker.getTicker().getHigh());
			tickerData.setLast(ticker.getTicker().getLast());
			tickerData.setLow(ticker.getTicker().getLow());
			tickerData.setSell(ticker.getTicker().getSell());
			tickerData.setVol(ticker.getTicker().getVol());
			tickerData.setUdate(new Date());
			return tickerData;
		} catch (Exception ex) {
			log.error("etc getTicker error: " + ex);
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��ȡ���
	 */
	public static JSONObject getDepth() {
		try {
			String url = API_DOMAIN+"/data/v1/depth?currency="+currency;
			JSONObject callback = getJSONObject(url, "UTF-8");
			return callback;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��ȡ������׼�¼
	 * since ��ָ������ID��50������
	 */
	public static JSONObject getTrades(String tid) {
		try {
			String url = API_DOMAIN+"/data/v1/trades?currency="+currency;
			if(tid != null) url = url +"&since="+tid;
			JSONObject callback = getJSONObject(url, "UTF-8");
			return callback;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * ��ȡK������
	 * type
			1min : 1����
			3min : 3����
			5min : 5����
			15min : 15����
			30min : 30����
			1day : 1��
			3day : 3��
			1week : 1��
			1hour : 1Сʱ
			2hour : 2Сʱ
			4hour : 4Сʱ
			6hour : 6Сʱ
			12hour : 12Сʱ
		since	
			�����ʱ���֮���
	 */
	public static JSONObject getKline(String type,Date since) {
		try {
			String url = API_DOMAIN+"/data/v1/kline?currency="+currency+"&type="+type+"&since="+since.getTime();
			JSONObject callback = getJSONObject(url, "UTF-8");
			return callback;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public static JSONObject getJSONObject(String urlAll, String charset) {
		BufferedReader reader = null;
		JSONObject result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = JSONObject.fromObject(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static JSONArray getJSONArray(String urlAll, String charset) {
		BufferedReader reader = null;
		JSONArray result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = JSONArray.fromObject(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}