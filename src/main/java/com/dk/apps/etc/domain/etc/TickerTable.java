package com.dk.apps.etc.domain.etc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dk.apps.etc.domain.BaseTable;


@Entity
@Table(name="ticker")
public class TickerTable extends BaseTable{
	private Date date;
    private Double high;
    private Double low;
    private Double buy;
    private Double sell;
    private Double last;
    private Double vol;
    private Date udate;
    
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getBuy() {
		return buy;
	}
	public void setBuy(Double buy) {
		this.buy = buy;
	}
	public Double getSell() {
		return sell;
	}
	public void setSell(Double sell) {
		this.sell = sell;
	}
	public Double getLast() {
		return last;
	}
	public void setLast(Double last) {
		this.last = last;
	}
	public Double getVol() {
		return vol;
	}
	public void setVol(Double vol) {
		this.vol = vol;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
}
