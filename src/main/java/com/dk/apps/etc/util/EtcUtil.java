package com.dk.apps.etc.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dk.apps.etc.domain.etc.AsksTable;
import com.dk.apps.etc.domain.etc.BidsTable;
import com.dk.apps.etc.domain.etc.TickerTable;
import com.dk.apps.etc.domain.etc.dummy.Depth;
import com.dk.apps.etc.domain.etc.dummy.Ticker;
import com.dk.apps.etc.service.EtcService;

import net.sf.json.JSONObject;

/**
 * 
 * @author Wayne
 * @date 2015-01-16
 */
@SuppressWarnings("static-access")
public class EtcUtil {
	private static Log log = LogFactory.getLog(EtcUtil.class);
	public static String API_DOMAIN = "http://api.chbtc.com";
	private static String currency = "etc_cny";

	/**
	 * 获取行情
	 */
	public static TickerTable getTicker() {
		TickerTable tickerData = new TickerTable();
		try {
			String url = API_DOMAIN+"/data/v1/ticker?currency="+currency;
			JSONObject callback = get(url, "UTF-8");
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
			JSONObject callback = get(url, "UTF-8");
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
			JSONObject callback = get(url, "UTF-8");
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
			String url = API_DOMAIN+"/data/v1/kline?currency="+currency+"&type="+type+"&since="+since.toString();
			JSONObject callback = get(url, "UTF-8");
			return callback;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public static JSONObject get(String urlAll, String charset) {
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
}