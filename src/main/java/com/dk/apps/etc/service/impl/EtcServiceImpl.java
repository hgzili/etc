package com.dk.apps.etc.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk.apps.etc.domain.etc.AsksTable;
import com.dk.apps.etc.domain.etc.BidsTable;
import com.dk.apps.etc.domain.etc.TickerTable;
import com.dk.apps.etc.domain.etc.dummy.Depth;
import com.dk.apps.etc.service.EtcService;
import com.dk.apps.etc.util.EtcUtil;

@SuppressWarnings("static-access")
@Transactional
@Service("etcService")
public class EtcServiceImpl extends BaseDaoHibernate implements EtcService {
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
		System.out.println(depth.toString());
		Double[][] asks = depth.getAsks();
		Double[][] bids = depth.getBids();
		
		for(int i=0; i<asks.length; i++){
			AsksTable asksTable = new AsksTable();
			asksTable.setDate(depth.getTimestamp());
			asksTable.setPrice(asks[i][0]);
			asksTable.setQty(asks[i][1]);
			asksTable.setUdate(new Date());
			saveOrUpdateAsksTable(asksTable);
		}
		
		for(int i=0; i<bids.length; i++){
			BidsTable bidsTable = new BidsTable();
			bidsTable.setDate(depth.getTimestamp());
			bidsTable.setPrice(bids[i][0]);
			bidsTable.setQty(bids[i][1]);
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
}
