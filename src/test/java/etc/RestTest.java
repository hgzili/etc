package etc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.dk.apps.etc.util.sign.EncryDigestUtil;

public class RestTest {
	
	private static Logger log = Logger.getLogger(RestTest.class);
	
	public static String ACCESS_KEY = "311f2960-xxxx-xxxx-xxxx-2c4fd17494c7";
	public static String SECRET_KEY = "940e43e1-xxxx-xxxx-xxxx-6cf3cdefb4da";
	public static String URL_PREFIX = "https://trade.chbtc.com/api/";
	public static String PAY_PASS = "xxxxxxx";

	/**
	 * 委托下单
	 * tradeType 1买，0卖
	 */
	@Test
	public void testOrder(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);
			//需加密的请求参数， tradeType=0卖单
			String params = "method=order&accesskey="+ACCESS_KEY+"&price=1.11&amount=1.12323&tradeType=0&currency=etc_cny";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"order?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("testOrder url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("testOrder 结果: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 取消委托
	 */
	@Test
	public void testCancelOrder(){
		String orderId = "201608011886";
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);
			//需加密的请求参数
			String params = "method=cancelOrder&accesskey="+ACCESS_KEY + "&id=" + orderId + "&currency=etc_cny";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"cancelOrder?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("testGetOrder url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("testGetOrder 结果: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取委托买单或卖单
	 */
	@Test
	public void testGetOrder(){
		String orderId = "20160817118477640";
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);
			//需加密的请求参数
			String params = "method=getOrder&accesskey="+ACCESS_KEY + "&id=" + orderId + "&currency=ltc_cny";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getOrder?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("testGetOrder url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("testGetOrder 结果: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取多个委托买单或卖单，每次请求返回10条记录
	 */
	@Test
	public void testGetOrders(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);
			//需加密的请求参数
			String params = "method=getOrders&accesskey="+ACCESS_KEY + "&tradeType=1&currency=btc_cny&pageIndex=1";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getOrders?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("testGetOrders url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			log.info("testGetOrders 结果: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * (新)获取多个委托买单或卖单，每次请求返回pageSize<=100条记录
	 */
	@Test
	public void testGetOrdersNew(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getOrdersNew&accesskey="+ACCESS_KEY + "&tradeType=1&currency=btc_cny&pageIndex=1&pageSize=1";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getOrdersNew?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("testGetOrdersNew url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			log.info("testGetOrdersNew 结果: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 与getOrdersNew的区别是取消tradeType字段过滤，可同时获取买单和卖单，每次请求返回pageSize<=100条记录
	 */
	@Test
	public void getOrdersIgnoreTradeType(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getOrdersIgnoreTradeType&accesskey="+ACCESS_KEY + "&currency=ltc_cny&pageIndex=1&pageSize=1";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getOrdersIgnoreTradeType?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("getOrdersIgnoreTradeType url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			log.info("getOrdersIgnoreTradeType 结果: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 获取未成交或部份成交的买单和卖单，每次请求返回pageSize<=100条记录
	 */
	@Test
	public void getUnfinishedOrdersIgnoreTradeType(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getUnfinishedOrdersIgnoreTradeType&accesskey="+ACCESS_KEY + "&currency=etc_cny&pageIndex=1&pageSize=20";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getUnfinishedOrdersIgnoreTradeType?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("getUnfinishedOrdersIgnoreTradeType url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			log.info("getUnfinishedOrdersIgnoreTradeType 结果: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取用户信息
	 */
	@Test
	public void testGetAccountInfo(){
        try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getAccountInfo&accesskey="+ACCESS_KEY;
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getAccountInfo?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("testGetAccountInfo url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			log.info("testGetAccountInfo 结果: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取用户充值地址
	 */
	@Test
	public void testGetUserAddress(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getUserAddress&accesskey="+ACCESS_KEY+"&currency=etc";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getUserAddress?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getUserAddress url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("getUserAddress 结果: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取用户认证的提现地址
	 */
	@Test
	public void testGetWithdrawAddress(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getWithdrawAddress&accesskey="+ACCESS_KEY+"&currency=etc";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getWithdrawAddress?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getWithdrawAddress url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("getWithdrawAddress 结果: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取提现记录
	 */
	@Test
	public void testGetWithdrawRecord(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getWithdrawRecord&accesskey="+ACCESS_KEY+"&currency=etc&pageIndex=1&pageSize=10";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getWithdrawRecord?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getWithdrawRecord url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("getWithdrawRecord 结果: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取虚拟货币充值记录
	 */
	@Test
	public void testGetChargeRecord(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getChargeRecord&accesskey="+ACCESS_KEY+"&currency=eth&pageIndex=1&pageSize=10";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getChargeRecord?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getChargeRecord url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("getChargeRecord 结果: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取人民币充值记录
	 */
	@Test
	public void testGetCnyChargeRecord(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getCnyChargeRecord&accesskey="+ACCESS_KEY+"&pageIndex=1&pageSize=10";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getCnyChargeRecord?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getCnyChargeRecord url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("getCnyChargeRecord 结果: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取人民币提现记录
	 */
	@Test
	public void testGetCnyWithdrawRecord(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//需加密的请求参数
			String params = "method=getCnyWithdrawRecord&accesskey="+ACCESS_KEY+"&pageIndex=1&pageSize=10";
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"getCnyWithdrawRecord?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getCnyWithdrawRecord url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("getCnyWithdrawRecord 结果: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 提现操作
	 */
	@Test
	public void withdraw(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			
			String addr = "143GwqgnjNi5DqGv4xzwqeGTi7BG9uaxxx";
			String fees = "0.0003";
			String currency = "btc";
			String amount = "0.0004";
			String itransfer = "0";	 
			
			//需加密的请求参数
			String params = "accesskey=" + ACCESS_KEY + "&amount=" + amount + "&currency=" + currency + "&fees=" + fees
					+ "&itransfer=" + itransfer + "&method=withdraw&receiveAddr=" + addr + "&safePwd=" + PAY_PASS;
			System.out.println("withdraw加密明文:"+params);
			//参数执行加密
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//请求地址
			String url = URL_PREFIX+"withdraw?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis() ;
			System.out.println("withdraw url: " + url);
			//请求测试
			String callback = get(url, "UTF-8");
			System.out.println("withdraw 结果: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static String API_DOMAIN = "http://api.chbtc.com";
	
	/**
	 * 测试获取行情
	 */
	@Test
	public void testTicker() {
		try {
			String currency = "btc_cny";
			// 请求地址
			String url = API_DOMAIN+"/data/v1/ticker?currency="+currency;
			log.info(currency + "-testTicker url: " + url);
			// 请求测试
			String callback = get(url, "UTF-8");
			log.info(currency + "-testTicker 结果: " + callback);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 测试获取深度
	 */
	@Test
	public void testDepth() {
		try {
			String currency = "btc_cny";
			// 请求地址
			String url = API_DOMAIN+"/data/v1/depth?currency="+currency;
			log.info(currency + "-testDepth url: " + url);
			// 请求测试
			String callback = get(url, "UTF-8");
			log.info(currency + "-testDepth 结果: " + callback);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 测试获取最近交易记录
	 */
	@Test
	public void testTrades() {
		try {
			String currency = "btc_cny";
			// 请求地址
			String url = API_DOMAIN+"/data/v1/trades?currency="+currency+"&since=1";
			log.info(currency + "-testTrades url: " + url);
			// 请求测试
			String callback = get(url, "UTF-8");
			log.info(currency + "-testTrades 结果: " + callback);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 测试获取K线数据
	 */
	@Test
	public void testKline() {
		try {
			String currency = "btc_cny";
			// 请求地址
			String url = API_DOMAIN+"/data/v1/kline?currency="+currency+"&times=1min";
			log.info(currency + "-testKline url: " + url);
			// 请求测试
			String callback = get(url, "UTF-8");
			log.info(currency + "-testKline 结果: " + callback);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param urlAll
	 *            :请求接口
	 * @param charset
	 *            :字符编码
	 * @return 返回json结果
	 */
	public String get(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
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
			result = sbf.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
