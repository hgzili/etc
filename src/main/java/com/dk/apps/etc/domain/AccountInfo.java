package com.dk.apps.etc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dk.apps.etc.service.AdminService;
import com.dk.apps.etc.service.LoginService;

@Entity
@Table(name="accountInfo")
public class AccountInfo extends BaseTable{
	private String account;
	private String password;
	private String email;
	private String role;
	private String openId;
	private Boolean active;
	private Date updateAt;
	private long udateBy;
	private Date lastChangePassword;
	private Date startDate;
	private Date endDate;
	private String weixinUserInfoId;
	@Transient
    private AccountInfo creatorInfo;
	
	public AccountInfo getCreatorInfo(){
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		AdminService adminService = (AdminService) ac.getBean("adminService");
		this.creatorInfo = adminService.getAccountInfoById(this.udateBy);
		return creatorInfo;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public long getUdateBy() {
		return udateBy;
	}
	public void setUdateBy(long udateBy) {
		this.udateBy = udateBy;
	}
	public Date getLastChangePassword() {
		return lastChangePassword;
	}
	public void setLastChangePassword(Date lastChangePassword) {
		this.lastChangePassword = lastChangePassword;
	}

	public void setCreatorInfo(AccountInfo creatorInfo) {
		this.creatorInfo = creatorInfo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getWeixinUserInfoId() {
		return weixinUserInfoId;
	}

	public void setWeixinUserInfoId(String weixinUserInfoId) {
		this.weixinUserInfoId = weixinUserInfoId;
	}

}
