package com.dk.apps.etc.domain.etc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dk.apps.etc.domain.BaseTable;


@Entity
@Table(name="asks")
public class AsksTable extends BaseTable{
	private Date date;
    private Double price;
    private Double amount;
    private Date udate;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
}
