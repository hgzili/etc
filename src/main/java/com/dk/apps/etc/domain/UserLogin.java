package com.dk.apps.etc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dk.apps.etc.service.AdminService;

@Entity
@Table(name="userLogin")
public class UserLogin extends BaseTable{
	private String account;

	@Transient
    private AccountInfo accountInfo;
	
	public AccountInfo getAccountInfo() {
		if(this.account == null) return null;
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		AdminService adminService = (AdminService) ac.getBean("adminService");
		accountInfo = adminService.getAccountInfoByAccount(this.account);
    	return accountInfo;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
