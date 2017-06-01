package com.dk.apps.etc.service;

import com.dk.apps.etc.domain.etc.AsksTable;
import com.dk.apps.etc.domain.etc.BidsTable;
import com.dk.apps.etc.domain.etc.TickerTable;

public interface EtcService {
	public void saveOrUpdateTickerTable(TickerTable tickerTable);
	public void syncTicker();
	
	public void syncDepth();
	public void saveOrUpdateAsksTable(AsksTable asksTable);
	public void saveOrUpdateBidsTable(BidsTable bidsTable);
}
