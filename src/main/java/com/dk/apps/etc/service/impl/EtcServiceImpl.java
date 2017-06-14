package com.dk.apps.etc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk.apps.etc.domain.AccountInfo;
import com.dk.apps.etc.domain.etc.AsksTable;
import com.dk.apps.etc.domain.etc.BidsTable;
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
			Trades trades = (Trades) callback.toBean(jsonObject, Trades.class);
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
		JSONObject callbackBuy = EtcUtil.getOrdersNew(accessKey,secretKey,"1");
		JSONArray buyList = JSONArray.fromObject(callbackBuy);
		for(int i=0; i<buyList.size(); i++){
			JSONObject jsonObject = buyList.getJSONObject(i); 
			OrderBuyTable orderBuy = (OrderBuyTable) callbackBuy.toBean(jsonObject, OrderBuyTable.class);
			saveOrUpdateOrderBuyTable(orderBuy);
		}	
		
		JSONObject callbackSell = EtcUtil.getOrdersNew(accessKey,secretKey,"0");
		JSONArray sellList = JSONArray.fromObject(callbackSell);
		for(int i=0; i<sellList.size(); i++){
			JSONObject jsonObject = sellList.getJSONObject(i); 
			OrderSellTable orderSell = (OrderSellTable) callbackSell.toBean(jsonObject, OrderSellTable.class);
			saveOrUpdateOrderSellTable(orderSell);
		}	
	}
	
	public void saveOrUpdateOrderBuyTable(OrderBuyTable orderBuyTable){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(orderBuyTable);
	}
	
	public void saveOrUpdateOrderSellTable(OrderSellTable orderSellTable){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(orderSellTable);
	}
}
