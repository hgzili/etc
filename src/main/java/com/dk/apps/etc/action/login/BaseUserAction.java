package com.dk.apps.etc.action.login;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dk.apps.etc.action.BaseAction;
import com.dk.apps.etc.domain.AccountInfo;
import com.dk.apps.etc.domain.UserLogin;
import com.dk.apps.etc.service.LoginService;
import com.dk.apps.etc.util.Constants;
import com.dk.apps.etc.util.Encoder;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("protype")
@Namespace("/login")
public class BaseUserAction extends BaseAction{
	@Resource(name = "loginService")
	protected LoginService loginService;
	
	private String password;
	private String newPassword;
	private String oldPassword;
	private String errmsg;
	
	@Action(value = "updatePassword", results = { @Result(name = "success",location = "/login/updatePassword.ftl") })  
    public String updatePassword(){
		return SUCCESS;
    }
	
	@Action(value = "doUpdatePassword", results = { 
			@Result(name = "success",type="redirectAction",location = "../index.action"),
			@Result(name = "input",location = "/login/viewUpdatePassword.ftl") })  
    public String doUpdatePassword(){
		try{
			UserLogin userLogin = this.getUserLogin();
			AccountInfo accountInfo = this.loginService.getAccountInfo(userLogin.getAccount(), oldPassword);
			if(accountInfo == null){
				addActionError(Constants.ERROR_MSG3);
			}else{
				Encoder encoder = new Encoder();
//				accountInfo.setPassword(encoder.encodePassword(newPassword));
//				accountInfo.setLastChangePassword(new Date());
				this.adminService.saveOrUpdateAccountInfo(accountInfo);
			}
		}catch(Exception e){
    		addActionError(e.getMessage());
    		return INPUT;    		
    	}	
		return SUCCESS;
    }
	
	@Action(value = "logout", results = { @Result(name = "success", type="redirectAction",location = "viewLogin.action") })  
    public String logout(){
		ActionContext.getContext().getSession().remove(Constants.USER_LOGIN);
		HttpServletRequest request = ServletActionContext.getRequest();
	    request.getSession().invalidate();
		return SUCCESS;
    }

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
}
