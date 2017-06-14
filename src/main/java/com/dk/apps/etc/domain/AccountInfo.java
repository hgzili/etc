package com.dk.apps.etc.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="accountInfo")
public class AccountInfo extends BaseTable{
	private String account;
	private String accessKey;
	private String secretKey;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
