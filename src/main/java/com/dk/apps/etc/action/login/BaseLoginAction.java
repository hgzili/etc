package com.dk.apps.etc.action.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dk.apps.etc.action.BaseAction;
import com.dk.apps.etc.domain.AccountInfo;
import com.dk.apps.etc.domain.UserLogin;
import com.dk.apps.etc.util.Anonymous;
import com.dk.apps.etc.util.Constants;
import com.opensymphony.xwork2.ActionContext;


@SuppressWarnings("serial")
@Controller
@Scope("protype")
@Namespace("/login")
public class BaseLoginAction extends BaseAction implements Anonymous{
	private String account;
	private String password;
	private String orginalURL;

	private static Log log = LogFactory.getLog(BaseLoginAction.class);

	@Action(value = "viewLogin", results = { @Result(name = "success", location = "/login/viewLogin.ftl") })  
    public String viewLogin(){
    	return SUCCESS;
    }
	
	@Action(value = "login", results = { 
			@Result(name = "success",type="redirect",location = "${orginalURL}"),
			@Result(name = "input",location = "/login/viewLogin.ftl") }) 
	public String doLogin(){
		try{
			AccountInfo accountInfo = this.loginService.getAccountInfo(account, password);
    		UserLogin userLogin = new UserLogin();
    		userLogin.setAccount(accountInfo.getAccount());
    		ActionContext.getContext().getSession().put(Constants.USER_LOGIN,userLogin);
    		
    		if(ActionContext.getContext().getSession().get(Constants.ORIGINAL_URL) != null
				&& !ActionContext.getContext().getSession().get(Constants.ORIGINAL_URL).toString().contains("/login.action")
				&& !ActionContext.getContext().getSession().get(Constants.ORIGINAL_URL).toString().contains("/viewLogin.action")){
    			orginalURL = (String) ActionContext.getContext().getSession().get(Constants.ORIGINAL_URL);
    		}else{
    			orginalURL = "/index.action";
    		}
    	}catch(Exception e){
    		addActionError(e.getMessage());
    		return INPUT;
    	}
    	return SUCCESS;
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

	public String getOrginalURL() {
		return orginalURL;
	}

	public void setOrginalURL(String orginalURL) {
		this.orginalURL = orginalURL;
	}

}
