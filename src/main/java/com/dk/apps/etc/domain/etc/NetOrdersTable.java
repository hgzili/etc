package com.dk.apps.etc.domain.etc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dk.apps.etc.domain.BaseTable;


@Entity
@Table(name="netOrders")
public class NetOrdersTable extends BaseTable{
	private Long id;
	private Double trade_amount; //已成交数量
	private Date trade_date; //Unix时间戳
	private Double trade_money; //已成交总金额
	private Double trade_price; //成交均价
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Double getTrade_money() {
		return trade_money;
	}
	public void setTrade_money(Double trade_money) {
		this.trade_money = trade_money;
	}
	public Double getTrade_price() {
		return trade_price;
	}
	public void setTrade_price(Double trade_price) {
		this.trade_price = trade_price;
	}
}
