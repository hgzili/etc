package com.dk.apps.etc.domain.etc.dummy;

import java.util.Date;

public class Ticker {
	private Date date;
	private TickerBody ticker;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TickerBody getTicker() {
		return ticker;
	}
	public void setTicker(TickerBody ticker) {
		this.ticker = ticker;
	}
}
