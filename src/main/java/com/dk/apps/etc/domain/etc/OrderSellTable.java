package com.dk.apps.etc.domain.etc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dk.apps.etc.domain.BaseTable;



@Entity
@Table(name="orderSell")
public class OrderSellTable extends BaseTable{ 
	private String currency; //交易类型（目前仅支持btc_cny/ltc_cny/eth_cny/eth_btc/etc_cny）
	private Long id; //委托挂单号
//	private Double price; //单价
	private String status; //挂单状态(0：待成交,1：取消,2：交易完成,3：待成交未交易部份)
//	private Double total_amount; //挂单总数量
	private Double trade_amount; //已成交数量
	private Date trade_date; //Unix时间戳
//	private Double trade_money; //已成交总金额
	private Double trade_price; //成交均价
	private String type; //挂单类型 1/0[buy/sell]
//	private Double fees; //交易手续费,卖单的话,显示的是收入货币(如人民币);买单的话,显示的是买入货币(如etc)
	private boolean insertFlag;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

//	public Double getPrice() {
//		return price;
//	}
//	public void setPrice(Double price) {
//		this.price = price;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public Double getTotal_amount() {
//		return total_amount;
//	}
//	public void setTotal_amount(Double total_amount) {
//		this.total_amount = total_amount;
//	}
	public Double getTrade_amount() {
		return trade_amount;
	}
	public void setTrade_amount(Double trade_amount) {
		this.trade_amount = trade_amount;
	}
	public Date getTrade_date() {
		return trade_date;
	}
	public void setTrade_date(Date trade_date) {
		this.trade_date = trade_date;
	}
//	public Double getTrade_money() {
//		return trade_money;
//	}
//	public void setTrade_money(Double trade_money) {
//		this.trade_money = trade_money;
//	}
	public Double getTrade_price() {
		return trade_price;
	}
	public void setTrade_price(Double trade_price) {
		this.trade_price = trade_price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public Double getFees() {
//		return fees;
//	}
//	public void setFees(Double fees) {
//		this.fees = fees;
//	}
	public boolean isInsertFlag() {
		return insertFlag;
	}
	public void setInsertFlag(boolean insertFlag) {
		this.insertFlag = insertFlag;
	}
}
