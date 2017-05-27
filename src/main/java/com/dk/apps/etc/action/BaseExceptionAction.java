package com.dk.apps.etc.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("protype")
@Namespace("/exception")
public class BaseExceptionAction extends BaseAction{
	@Action(value = "systemCrash", results = {@Result(name = "success",location = "/exception.ftl")})
	public String systemCrash(){
		return SUCCESS;
	}
	@Action(value = "accountInvalid", results = {@Result(name = "success",location = "/accountInvalid.ftl")})
	public String accountInvalid(){
		return SUCCESS;
	}
}
