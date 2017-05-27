package com.dk.apps.etc.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.dk.apps.etc.domain.UserLogin;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
  
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class LoginInterceptor implements Interceptor {  
  
	public void destroy() {

    }

    public void init() {
    	
    }
    
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        String URL = request.getRequestURL().toString();

        if (needLogin(invocation)) {
        	setupOriginalURL();
            return Action.LOGIN;
        } else {
        	if (!(invocation.getAction() instanceof Anonymous)){ 		
    			UserLogin userLogin = (UserLogin) getSession().get(Constants.USER_LOGIN);
    			if(!(invocation.getAction() instanceof com.dk.apps.etc.action.login.BaseUserAction)){
    				if (userLogin.getAccountInfo() == null) return Action.LOGIN;
    				if (userLogin != null && userLogin.getAccountInfo().getLastChangePassword()==null){ 
						return "changePassword";
    				}
    			}
        	}
        	
			return invocation.invoke();
		}
    } 
    
    private boolean needLogin(ActionInvocation invocation) {
        if (getSession().get(Constants.USER_LOGIN) != null)
            return false;
        if (invocation.getAction() instanceof Anonymous)
            return false;
        HttpServletRequest request = ServletActionContext.getRequest();
        String originalURL = request.getRequestURL().toString();
        if(originalURL.contains("/login/callback.action")){
        	return false;
        }
        return true;
    }
    
	private Map getSession() {
        return ActionContext.getContext().getSession();
    } 
    
	private void setupOriginalURL() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String originalURL = request.getRequestURL() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        getSession().put(Constants.ORIGINAL_URL, originalURL);
    }
}  