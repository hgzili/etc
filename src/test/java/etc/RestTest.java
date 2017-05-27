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
	 * ί���µ�
	 * tradeType 1��0��
	 */
	@Test
	public void testOrder(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);
			//����ܵ���������� tradeType=0����
			String params = "method=order&accesskey="+ACCESS_KEY+"&price=1.11&amount=1.12323&tradeType=0&currency=etc_cny";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"order?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("testOrder url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("testOrder ���: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ȡ��ί��
	 */
	@Test
	public void testCancelOrder(){
		String orderId = "201608011886";
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);
			//����ܵ��������
			String params = "method=cancelOrder&accesskey="+ACCESS_KEY + "&id=" + orderId + "&currency=etc_cny";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"cancelOrder?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("testGetOrder url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("testGetOrder ���: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡί���򵥻�����
	 */
	@Test
	public void testGetOrder(){
		String orderId = "20160817118477640";
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);
			//����ܵ��������
			String params = "method=getOrder&accesskey="+ACCESS_KEY + "&id=" + orderId + "&currency=ltc_cny";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getOrder?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("testGetOrder url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("testGetOrder ���: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ί���򵥻�������ÿ�����󷵻�10����¼
	 */
	@Test
	public void testGetOrders(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);
			//����ܵ��������
			String params = "method=getOrders&accesskey="+ACCESS_KEY + "&tradeType=1&currency=btc_cny&pageIndex=1";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getOrders?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("testGetOrders url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			log.info("testGetOrders ���: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * (��)��ȡ���ί���򵥻�������ÿ�����󷵻�pageSize<=100����¼
	 */
	@Test
	public void testGetOrdersNew(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getOrdersNew&accesskey="+ACCESS_KEY + "&tradeType=1&currency=btc_cny&pageIndex=1&pageSize=1";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getOrdersNew?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("testGetOrdersNew url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			log.info("testGetOrdersNew ���: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * ��getOrdersNew��������ȡ��tradeType�ֶι��ˣ���ͬʱ��ȡ�򵥺�������ÿ�����󷵻�pageSize<=100����¼
	 */
	@Test
	public void getOrdersIgnoreTradeType(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getOrdersIgnoreTradeType&accesskey="+ACCESS_KEY + "&currency=ltc_cny&pageIndex=1&pageSize=1";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getOrdersIgnoreTradeType?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("getOrdersIgnoreTradeType url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			log.info("getOrdersIgnoreTradeType ���: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * ��ȡδ�ɽ��򲿷ݳɽ����򵥺�������ÿ�����󷵻�pageSize<=100����¼
	 */
	@Test
	public void getUnfinishedOrdersIgnoreTradeType(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getUnfinishedOrdersIgnoreTradeType&accesskey="+ACCESS_KEY + "&currency=etc_cny&pageIndex=1&pageSize=20";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getUnfinishedOrdersIgnoreTradeType?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("getUnfinishedOrdersIgnoreTradeType url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			log.info("getUnfinishedOrdersIgnoreTradeType ���: " + callback);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 */
	@Test
	public void testGetAccountInfo(){
        try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getAccountInfo&accesskey="+ACCESS_KEY;
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getAccountInfo?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			log.info("testGetAccountInfo url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			log.info("testGetAccountInfo ���: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ�û���ֵ��ַ
	 */
	@Test
	public void testGetUserAddress(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getUserAddress&accesskey="+ACCESS_KEY+"&currency=etc";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getUserAddress?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getUserAddress url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("getUserAddress ���: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ�û���֤�����ֵ�ַ
	 */
	@Test
	public void testGetWithdrawAddress(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getWithdrawAddress&accesskey="+ACCESS_KEY+"&currency=etc";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getWithdrawAddress?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getWithdrawAddress url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("getWithdrawAddress ���: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ּ�¼
	 */
	@Test
	public void testGetWithdrawRecord(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getWithdrawRecord&accesskey="+ACCESS_KEY+"&currency=etc&pageIndex=1&pageSize=10";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getWithdrawRecord?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getWithdrawRecord url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("getWithdrawRecord ���: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ������ҳ�ֵ��¼
	 */
	@Test
	public void testGetChargeRecord(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getChargeRecord&accesskey="+ACCESS_KEY+"&currency=eth&pageIndex=1&pageSize=10";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getChargeRecord?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getChargeRecord url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("getChargeRecord ���: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ����ҳ�ֵ��¼
	 */
	@Test
	public void testGetCnyChargeRecord(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getCnyChargeRecord&accesskey="+ACCESS_KEY+"&pageIndex=1&pageSize=10";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getCnyChargeRecord?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getCnyChargeRecord url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("getCnyChargeRecord ���: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ��������ּ�¼
	 */
	@Test
	public void testGetCnyWithdrawRecord(){
		try{
			String SECRET_KEY = EncryDigestUtil.digest(this.SECRET_KEY);	
			//����ܵ��������
			String params = "method=getCnyWithdrawRecord&accesskey="+ACCESS_KEY+"&pageIndex=1&pageSize=10";
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"getCnyWithdrawRecord?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis();
			System.out.println("getCnyWithdrawRecord url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("getCnyWithdrawRecord ���: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ���ֲ���
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
			
			//����ܵ��������
			String params = "accesskey=" + ACCESS_KEY + "&amount=" + amount + "&currency=" + currency + "&fees=" + fees
					+ "&itransfer=" + itransfer + "&method=withdraw&receiveAddr=" + addr + "&safePwd=" + PAY_PASS;
			System.out.println("withdraw��������:"+params);
			//����ִ�м���
			String hash = EncryDigestUtil.hmacSign(params, SECRET_KEY);
			//�����ַ
			String url = URL_PREFIX+"withdraw?" + params + "&sign=" + hash + "&reqTime=" + System.currentTimeMillis() ;
			System.out.println("withdraw url: " + url);
			//�������
			String callback = get(url, "UTF-8");
			System.out.println("withdraw ���: " + callback);
			Assert.assertNotSame(callback, "{}");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static String API_DOMAIN = "http://api.chbtc.com";
	
	/**
	 * ���Ի�ȡ����
	 */
	@Test
	public void testTicker() {
		try {
			String currency = "btc_cny";
			// �����ַ
			String url = API_DOMAIN+"/data/v1/ticker?currency="+currency;
			log.info(currency + "-testTicker url: " + url);
			// �������
			String callback = get(url, "UTF-8");
			log.info(currency + "-testTicker ���: " + callback);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * ���Ի�ȡ���
	 */
	@Test
	public void testDepth() {
		try {
			String currency = "btc_cny";
			// �����ַ
			String url = API_DOMAIN+"/data/v1/depth?currency="+currency;
			log.info(currency + "-testDepth url: " + url);
			// �������
			String callback = get(url, "UTF-8");
			log.info(currency + "-testDepth ���: " + callback);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * ���Ի�ȡ������׼�¼
	 */
	@Test
	public void testTrades() {
		try {
			String currency = "btc_cny";
			// �����ַ
			String url = API_DOMAIN+"/data/v1/trades?currency="+currency+"&since=1";
			log.info(currency + "-testTrades url: " + url);
			// �������
			String callback = get(url, "UTF-8");
			log.info(currency + "-testTrades ���: " + callback);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * ���Ի�ȡK������
	 */
	@Test
	public void testKline() {
		try {
			String currency = "btc_cny";
			// �����ַ
			String url = API_DOMAIN+"/data/v1/kline?currency="+currency+"&times=1min";
			log.info(currency + "-testKline url: " + url);
			// �������
			String callback = get(url, "UTF-8");
			log.info(currency + "-testKline ���: " + callback);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param urlAll
	 *            :����ӿ�
	 * @param charset
	 *            :�ַ�����
	 * @return ����json���
	 */
	public String get(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// ģ�������
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
