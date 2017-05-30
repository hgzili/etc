package com.dk.apps.etc.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.dk.apps.etc.domain.etc.TickerTable;
import com.dk.apps.etc.domain.etc.dummy.Depth;
import com.dk.apps.etc.domain.etc.dummy.Ticker;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author Wayne
 * @date 2015-01-16
 */
public class EtcUtil {
	private static Log log = LogFactory.getLog(EtcUtil.class);
	public static String API_DOMAIN = "http://api.chbtc.com";
	private static String currency = "etc_cny";

	@SuppressWarnings("static-access")
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
	@Test
	public static void getDepth() {
		try {
			String url = API_DOMAIN+"/data/v1/depth?currency="+currency;
			JSONObject callback = get(url, "UTF-8");
			JSONArray asks_list = callback.getJSONArray("asks");
			JSONArray bids_list = callback.getJSONArray("bids"); 
//			

			Depth depth = (Depth) callback.toBean(callback, Depth.class);
			Double[][] asks = depth.getAsks();
			Double[][] bids = depth.getBids();
			for(int i=0; i<asks.length; i++){
				Double[] ask = asks[i];
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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