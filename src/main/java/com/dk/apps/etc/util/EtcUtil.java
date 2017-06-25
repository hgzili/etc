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
	 * 委托下单
	 * tradeType 1买，0卖
	 */
	@Test
	public JSONObject order(String accessKey,String secretKey,String price,String amount,String tradeType){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(secretKey);
			//需加密的请求参数， tradeType=0卖单
			String params = "method=order&accesskey="+accessKey+"&price="+price+"&amount="+amount+"&tradeType="+tradeType+"&currency="+currency;
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"order?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
//			System.out.println("testOrder url: " + url);
			//请求测试
			JSONObject callback = getJSONObject(url, "UTF-8");
			return callback;
//			System.out.println("testOrder 结果: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取消委托
	 */
	@Test
	public JSONObject cancelOrder(String accessKey,String secretKey,String orderId){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(secretKey);
			//需加密的请求参数
			String params = "method=cancelOrder&accesskey="+accessKey + "&id=" + orderId + "&currency="+currency;;
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"cancelOrder?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			//请求测试
			JSONObject callback = getJSONObject(url, "UTF-8");
			return callback;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * (新)获取多个委托买单或卖单，每次请求返回pageSize<=100条记录
	 * 	tradeType 交易类型1/0[buy/sell]
	 * pageIndex 当前页数
	 * pageSize  每页数量
	 */
	@Test
	public static JSONArray getOrders(String accessKey,String secretKey,String tradeType){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(secretKey);	
			//需加密的请求参数
			String params = "method=getOrdersNew&accesskey="+accessKey 
					+ "&tradeType="+tradeType
					+ "&currency="+currency
					+ "&pageIndex=1&pageSize=20";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getOrdersNew?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			//请求测试
			JSONArray callback = getJSONArray(url, "UTF-8");
			return callback;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public static String API_DOMAIN = "http://api.chbtc.com";
	/**
	 * 获取行情
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
	 * 获取深度
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
	 * 获取最近交易记录
	 * since 从指定交易ID后50条数据
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
	 * 获取K线数据
	 * type
			1min : 1分钟
			3min : 3分钟
			5min : 5分钟
			15min : 15分钟
			30min : 30分钟
			1day : 1日
			3day : 3日
			1week : 1周
			1hour : 1小时
			2hour : 2小时
			4hour : 4小时
			6hour : 6小时
			12hour : 12小时
		since	
			从这个时间戳之后的
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