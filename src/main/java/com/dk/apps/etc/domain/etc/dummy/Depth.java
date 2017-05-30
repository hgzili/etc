package com.dk.apps.etc.domain.etc.dummy;

import java.util.Date;

public class Depth {
	private Date timestamp;
	private Double[][] asks;
	private Double[][] bids;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Double[][] getAsks() {
		return asks;
	}
	public void setAsks(Double[][] asks) {
		this.asks = asks;
	}
	public Double[][] getBids() {
		return bids;
	}
	public void setBids(Double[][] bids) {
		this.bids = bids;
	}
}
