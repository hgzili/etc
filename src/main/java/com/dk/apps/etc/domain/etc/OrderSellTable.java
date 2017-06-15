package com.dk.apps.etc.domain.etc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dk.apps.etc.domain.BaseTable;



@Entity
@Table(name="orderSell")
public class OrderSellTable extends BaseTable{ 
	private String currency; //�������ͣ�Ŀǰ��֧��btc_cny/ltc_cny/eth_cny/eth_btc/etc_cny��
	private Long id; //ί�йҵ���
	private Double price; //����
	private int status; //�ҵ�״̬(0�����ɽ�,1��ȡ��,2���������,3�����ɽ�δ���ײ���)
	private Double total_amount; //�ҵ�������
	private Double trade_amount; //�ѳɽ�����
	private Date trade_date; //Unixʱ���
	private Double trade_money; //�ѳɽ��ܽ��
	private Double trade_price; //�ɽ�����
	private String type; //�ҵ����� 1/0[buy/sell]
	private Double fees; //����������,�����Ļ�,��ʾ�����������(�������);�򵥵Ļ�,��ʾ�����������(��etc)
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

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
}
