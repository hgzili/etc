package com.dk.apps.etc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk.apps.etc.domain.AccountInfo;
import com.dk.apps.etc.domain.etc.AsksTable;
import com.dk.apps.etc.domain.etc.BidsTable;
import com.dk.apps.etc.domain.etc.NetOrdersTable;
import com.dk.apps.etc.domain.etc.OrderBuyTable;
import com.dk.apps.etc.domain.etc.OrderSellTable;
import com.dk.apps.etc.domain.etc.TickerTable;
import com.dk.apps.etc.domain.etc.TradesBuyTable;
import com.dk.apps.etc.domain.etc.TradesSellTable;
import com.dk.apps.etc.domain.etc.dummy.Depth;
import com.dk.apps.etc.domain.etc.dummy.Trades;
import com.dk.apps.etc.service.AdminService;
import com.dk.apps.etc.service.EtcService;
import com.dk.apps.etc.util.EtcUtil;

@SuppressWarnings("static-access")
@Transactional
@Service("etcService")
public class EtcServiceImpl extends BaseDaoHibernate implements EtcService {
	@Resource(name = "adminService")
	private AdminService adminService;
	
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(EtcServiceImpl.class);
	
	public void saveOrUpdateTickerTable(TickerTable tickerTable){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(tickerTable);
	}
	
	public void syncTicker(){
		TickerTable tickerTable = EtcUtil.getTicker();
		if(tickerTable != null) saveOrUpdateTickerTable(tickerTable);
	}
	
	public void syncDepth(){
		JSONObject callback = EtcUtil.getDepth();
		Depth depth = (Depth) callback.toBean(callback, Depth.class);
		Double[][] asks = depth.getAsks();
		Double[][] bids = depth.getBids();
		
		for(int i=0; i<asks.length; i++){
			AsksTable asksTable = new AsksTable();
			asksTable.setDate(depth.getTimestamp());
			asksTable.setPrice(asks[i][0]);
			asksTable.setAmount(asks[i][1]);
			asksTable.setUdate(new Date());
			saveOrUpdateAsksTable(asksTable);
		}
		
		for(int i=0; i<bids.length; i++){
			BidsTable bidsTable = new BidsTable();
			bidsTable.setDate(depth.getTimestamp());
			bidsTable.setPrice(bids[i][0]);
			bidsTable.setAmount(bids[i][1]);
			bidsTable.setUdate(new Date());
			saveOrUpdateBidsTable(bidsTable);
		}
	}
	
	public void saveOrUpdateAsksTable(AsksTable asksTable){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(asksTable);
	}
	
	public void saveOrUpdateBidsTable(BidsTable bidsTable){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(bidsTable);
	}
	
	public void syncTrades(){
		JSONObject callback = EtcUtil.getTrades(null);
		JSONArray list = JSONArray.fromObject(callback);
		for(int i=0; i<list.size(); i++){
			JSONObject jsonObject = list.getJSONObject(i); 
			Trades trades = (Trades) jsonObject.toBean(jsonObject, Trades.class);
			if(trades.getType().equals("buy")){
				TradesBuyTable tradesBuy = new TradesBuyTable();
				tradesBuy.setTid(trades.getTid());
				tradesBuy.setDate(trades.getDate());
				tradesBuy.setPrice(trades.getPrice());
				tradesBuy.setAmount(trades.getAmount());
				tradesBuy.setUdate(new Date());
				saveOrUpdateTradesBuyTable(tradesBuy);
			}else{
				TradesSellTable tradesSell = new TradesSellTable();
				tradesSell.setTid(trades.getTid());
				tradesSell.setDate(trades.getDate());
				tradesSell.setPrice(trades.getPrice());
				tradesSell.setAmount(trades.getAmount());
				tradesSell.setUdate(new Date());
				saveOrUpdateTradesSellTable(tradesSell);
			}
		}		
	}
	
	public void saveOrUpdateTradesBuyTable(TradesBuyTable tradesBuyTable){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(tradesBuyTable);
	}
	
	public void saveOrUpdateTradesSellTable(TradesSellTable tradesSellTable){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(tradesSellTable);
	}
	
	public void syncOrdersNew(String account){
		AccountInfo accountInfo = this.adminService.getAccountInfoByAccount(account);
		String accessKey = accountInfo.getAccessKey();
		String secretKey = accountInfo.getSecretKey();
		JSONArray buyList = EtcUtil.getOrders(accessKey,secretKey,"1");
		JSONUtils.getMorpherRegistry().registerMorpher(new TimestampToDateMorpher());
		for(int i=0; i<buyList.size(); i++){
			OrderBuyTable orderBuy = new OrderBuyTable();
			JSONObject jsonObject = buyList.getJSONObject(i); 
			orderBuy = (OrderBuyTable) JSONObject.toBean(jsonObject, OrderBuyTable.class);
			saveOrUpdateOrderBuyTable(orderBuy);
		}	
		
		JSONArray sellList = EtcUtil.getOrders(accessKey,secretKey,"0");
		for(int i=0; i<sellList.size(); i++){
			OrderSellTable orderSell = new OrderSellTable();
			JSONObject jsonObject = sellList.getJSONObject(i); 
			orderSell = (OrderSellTable) JSONObject.toBean(jsonObject, OrderSellTable.class);
			saveOrUpdateOrderSellTable(orderSell);
		}	
	}
	
	public void saveOrUpdateOrderBuyTable(OrderBuyTable orderBuyTable){
		OrderBuyTable oldData = getOrderBuyTable(orderBuyTable.getId());
		orderBuyTable.setInsertFlag(false);
		if(oldData != null) {
			orderBuyTable.setUuid(oldData.getUuid());
			orderBuyTable.setInsertFlag(oldData.isInsertFlag());
			this.getSessionFactory().getCurrentSession().evict(oldData);
		}
		this.getSessionFactory().getCurrentSession().saveOrUpdate(orderBuyTable);
	}
	
	public void saveOrUpdateOrderSellTable(OrderSellTable orderSellTable){
		OrderSellTable oldData = getOrderSellTable(orderSellTable.getId());
		orderSellTable.setInsertFlag(false);
		if(oldData != null) {
			orderSellTable.setUuid(oldData.getUuid());
			orderSellTable.setInsertFlag(oldData.isInsertFlag());
			this.getSessionFactory().getCurrentSession().evict(oldData);
		}
		this.getSessionFactory().getCurrentSession().saveOrUpdate(orderSellTable);
	}
	
	public OrderBuyTable getOrderBuyTable(Long id){
		String sql = "from OrderBuyTable u where u.id=:id";
		List<OrderBuyTable> list = this.getSessionFactory().getCurrentSession()
				.createQuery(sql).setLong("id",id).list();
		if(list == null || list.size()<=0) return null;
		return (OrderBuyTable) list.get(0);
	}
	public OrderSellTable getOrderSellTable(Long id){
		String sql = "from OrderSellTable u where u.id=:id";
		List<OrderSellTable> list = this.getSessionFactory().getCurrentSession()
				.createQuery(sql).setLong("id",id).list();
		if(list == null || list.size()<=0) return null;
		return (OrderSellTable) list.get(0);	
	}
	
	public void syncNetOrders(){
		String buySql = "from OrderBuyTable u where u.insertFlag=false and u.status = '2'";
		List<OrderBuyTable> buyList = this.getSessionFactory().getCurrentSession()
				.createQuery(buySql).list();
		for(int i=0;i<buyList.size();i++){
			OrderBuyTable orderBuy = buyList.get(i);
			NetOrdersTable netOrdersTable = new NetOrdersTable();
			netOrdersTable.setId(orderBuy.getId());
			netOrdersTable.setTrade_price(orderBuy.getTrade_price());
			netOrdersTable.setTrade_amount(orderBuy.getTrade_amount());
			netOrdersTable.setTrade_date(orderBuy.getTrade_date());
			this.getSessionFactory().getCurrentSession().saveOrUpdate(netOrdersTable);
			orderBuy.setInsertFlag(true);
			this.getSessionFactory().getCurrentSession().saveOrUpdate(orderBuy);
		}
		
		String sql = "from NetOrdersTable u order by trade_price";
		
		String sellSql = "from OrderSellTable u where u.insertFlag=false and u.status = '2'";
		List<OrderSellTable> sellList = this.getSessionFactory().getCurrentSession()
				.createQuery(sellSql).list();
		for(int i=0;i<sellList.size();i++){
			OrderSellTable orderSell = sellList.get(i);
			List<NetOrdersTable> netList = this.getSessionFactory().getCurrentSession()
					.createQuery(sql).list();
			NetOrdersTable netOrdersTable = netList.get(0);
			if(orderSell.getTrade_amount() == netOrdersTable.getTrade_amount()){
				this.getSessionFactory().getCurrentSession().delete(netOrdersTable);
				continue;
			}else if(orderSell.getTrade_amount() > netOrdersTable.getTrade_amount()){
				Double netAmount = orderSell.getTrade_amount() - netOrdersTable.getTrade_amount();
				this.getSessionFactory().getCurrentSession().delete(netOrdersTable);
				while(netAmount > 0){
					netList = this.getSessionFactory().getCurrentSession()
							.createQuery(sql).list();
					netOrdersTable = netList.get(0);
					if(netOrdersTable.getTrade_amount() == netAmount){
						this.getSessionFactory().getCurrentSession().delete(netOrdersTable);
						break;
					}else if(netOrdersTable.getTrade_amount() > netAmount){
						Double tempAmount = netOrdersTable.getTrade_amount() - netAmount;
						netOrdersTable.setTrade_amount(tempAmount);
						this.getSessionFactory().getCurrentSession().saveOrUpdate(netOrdersTable);
						break;
					}else{
						this.getSessionFactory().getCurrentSession().delete(netOrdersTable);
						netAmount = netAmount - netOrdersTable.getTrade_amount();
					}
				}
			}else{
				Double tempAmount = netOrdersTable.getTrade_amount() - orderSell.getTrade_amount();
				netOrdersTable.setTrade_amount(tempAmount);
				this.getSessionFactory().getCurrentSession().saveOrUpdate(netOrdersTable);
			}
			orderSell.setInsertFlag(true);
			this.getSessionFactory().getCurrentSession().saveOrUpdate(orderSell);
		}
	}
	
	public void syncKline1min(Date sinceDate){
		JSONObject callback = EtcUtil.getKline("1min",sinceDate);
		Depth depth = (Depth) callback.toBean(callback, Depth.class);
		Double[][] asks = depth.getAsks();
		Double[][] bids = depth.getBids();
		
		for(int i=0; i<asks.length; i++){
			AsksTable asksTable = new AsksTable();
			asksTable.setDate(depth.getTimestamp());
			asksTable.setPrice(asks[i][0]);
			asksTable.setAmount(asks[i][1]);
			asksTable.setUdate(new Date());
			saveOrUpdateAsksTable(asksTable);
		}
	}
}
