package com.dk.apps.etc.action.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dk.apps.etc.action.BaseAction;
import com.dk.apps.etc.domain.AccountInfo;
import com.dk.apps.etc.util.Constants;
import com.dk.apps.etc.util.Encoder;

@SuppressWarnings("serial")
@Controller
@Scope("protype")
@Namespace("/admin")
public class AccountMaintenance extends BaseAction{
	private Long accountInfoId;
	private AccountInfo accountInfo;
	private String nowDate;
	private String futureDate;
	@Action(value = "accountMaintenance", results = { 
			@Result(name = "success",location = "/admin/accountMaintenance.ftl"),
			@Result(name = "nopermission",location = "/nopermission.ftl") })
	public String accountMaintenance(){
		AccountInfo userLogin = this.getUserLogin().getAccountInfo();
		if(userLogin == null) return "nopermission";
		if(!userLogin.getRole().equals(Constants.ROLE_ADMIN)) return "nopermission";
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance(); 
		nowDate = sf.format(cal.getTime());
		futureDate = "9999-12-31";
		if(accountInfoId!=null) accountInfo = this.adminService.getAccountInfoById(accountInfoId);
		return SUCCESS;
	}
	
	private String account;
	private String password;
	private String email;
	private String role;
	private String providerNo;
	private String providerName;
	private Date startDate;
	private Date endDate;
	@Action(value = "createAccountInfo", results = { 
			@Result(name = "success",type="redirectAction",location = "accountMaintenance.action"), 
			@Result(name = "error",location = "/admin/accountMaintenance.ftl"),
			@Result(name = "nopermission",location = "/nopermission.ftl") })
	public String createAccountInfo() throws Exception{
		if(this.adminService.isExitedAccountInfo(account)){
			addActionError(Constants.ERROR_DUPLICATE_ACCOUNT);
			return ERROR;
		}
		
		Encoder encoder = new Encoder();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccount(account);
		accountInfo.setEmail(email);
		accountInfo.setPassword(encoder.encodePassword(password));
		accountInfo.setRole(role);
		accountInfo.setActive(true);
		accountInfo.setUdateBy(this.getUserLogin().getAccountInfo().getId());
		accountInfo.setUpdateAt(new Date());
		accountInfo.setStartDate(startDate);
		if(endDate!=null){
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
			accountInfo.setEndDate(endDate);
		}
		this.adminService.saveOrUpdateAccountInfo(accountInfo);
		return SUCCESS;
	}
	
	private boolean active;
	@Action(value = "updateAccountInfoActive", results = { 
			@Result(name = "success",type="redirectAction",location = "accountMaintenance.action"), 
			@Result(name = "error",location = "/admin/accountMaintenance.ftl"),
			@Result(name = "nopermission",location = "/nopermission.ftl") })
	public String updateAccountInfoActive() throws Exception{
		if(this.adminService.isExitedAccountInfo(account,accountInfoId)){
			addActionError(Constants.ERROR_DUPLICATE_ACCOUNT);
			return ERROR;
		}
		AccountInfo accountInfo = this.adminService.getAccountInfoById(accountInfoId);
		if(accountInfo==null){
			return "nopermission";
		}
		
		accountInfo.setActive(active);
		accountInfo.setUdateBy(this.getUserLogin().getAccountInfo().getId());
		accountInfo.setUpdateAt(new Date());
		this.adminService.saveOrUpdateAccountInfo(accountInfo);
		return SUCCESS;
	}
	
	@Action(value = "updateAccountInfo", results = { 
			@Result(name = "success",type="redirectAction",location = "accountMaintenance.action"), 
			@Result(name = "error",location = "/admin/accountMaintenance.ftl"),
			@Result(name = "nopermission",location = "/nopermission.ftl")})
	public String updateAccountInfo() throws Exception{
		if(this.adminService.isExitedAccountInfo(account,accountInfoId)){
			addActionError(Constants.ERROR_DUPLICATE_ACCOUNT);
			return ERROR;
		}
		AccountInfo accountInfo = this.adminService.getAccountInfoById(accountInfoId);
		if(accountInfo==null){
			return "nopermission";
		}

		accountInfo.setAccount(account);
		accountInfo.setEmail(email);
		accountInfo.setRole(role);
		accountInfo.setUdateBy(this.getUserLogin().getAccountInfo().getId());
		accountInfo.setUpdateAt(new Date());
		accountInfo.setStartDate(startDate);
		if(endDate!=null){
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
		}
		accountInfo.setEndDate(endDate);
		this.adminService.saveOrUpdateAccountInfo(accountInfo);
		return SUCCESS;
	}
	
	@Action(value = "deleteAccountInfo", results = { 
			@Result(name = "success",type="redirectAction",location = "accountMaintenance.action"), 
			@Result(name = "error",location = "/admin/accountMaintenance.ftl"),
			@Result(name = "nopermission",location = "/nopermission.ftl") })
	public String deleteAccountInfo() throws Exception{
		AccountInfo accountInfo = this.adminService.getAccountInfoById(accountInfoId);
		if(accountInfo==null){
			return "nopermission";
		}
		this.adminService.deleteAccountInfo(accountInfo);
		return SUCCESS;
	}
	
	public List<AccountInfo> getAccountInfoList() {
		return this.adminService.getAccountInfoList();
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

	public Long getAccountInfoId() {
		return accountInfoId;
	}

	public void setAccountInfoId(Long accountInfoId) {
		this.accountInfoId = accountInfoId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProviderNo() {
		return providerNo;
	}

	public void setProviderNo(String providerNo) {
		this.providerNo = providerNo;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
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

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public String getFutureDate() {
		return futureDate;
	}

	public void setFutureDate(String futureDate) {
		this.futureDate = futureDate;
	}

}
