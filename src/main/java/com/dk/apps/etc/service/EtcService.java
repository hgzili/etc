package com.dk.apps.etc.service;

import com.dk.apps.etc.domain.etc.AsksTable;
import com.dk.apps.etc.domain.etc.BidsTable;
import com.dk.apps.etc.domain.etc.OrderBuyTable;
import com.dk.apps.etc.domain.etc.OrderSellTable;
import com.dk.apps.etc.domain.etc.TickerTable;
import com.dk.apps.etc.domain.etc.TradesBuyTable;
import com.dk.apps.etc.domain.etc.TradesSellTable;

public interface EtcService {
	public void syncTicker();
	public void saveOrUpdateTickerTable(TickerTable tickerTable);
	
	public void syncDepth();
	public void saveOrUpdateAsksTable(AsksTable asksTable);
	public void saveOrUpdateBidsTable(BidsTable bidsTable);
	
	public void syncTrades();
	public void saveOrUpdateTradesBuyTable(TradesBuyTable tradesBuyTable);
	public void saveOrUpdateTradesSellTable(TradesSellTable tradesSellTable);
	
	public void syncOrdersNew(String accountId);
	public void saveOrUpdateOrderBuyTable(OrderBuyTable orderBuyTable);
	public void saveOrUpdateOrderSellTable(OrderSellTable orderSellTable);
	public OrderBuyTable getOrderBuyTable(Long id);
	public OrderSellTable getOrderSellTable(Long id);
	
	public void syncNetOrders();
}
