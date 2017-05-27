package com.dk.apps.etc.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dk.apps.etc.domain.AccountInfo;
import com.dk.apps.etc.domain.UserLogin;
import com.dk.apps.etc.service.AdminService;
import com.dk.apps.etc.service.LoginService;
import com.dk.apps.etc.service.etcService;
import com.dk.apps.etc.util.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
@Scope("protype")
@ParentPackage("default")
public class BaseAction extends ActionSupport {
	HttpServletRequest request;

	@Resource(name = "adminService")
	protected AdminService adminService;

	@Resource(name = "loginService")
	protected LoginService loginService;

	@Resource(name = "etcService")
	protected etcService etcService;

	@Action(value = "index", results = { @Result(name = "success", type = "freemarker", location = "/index.ftl") })
	public String index() {
		return SUCCESS;
	}

	//=== multiple Files upload start ===
	//===<input type="file" name="upload">  
	protected List<File> uploads; 
	protected List<String> uploadFileName;
	protected List<String> uploadContentType;

	public List<File> getUpload() {
		return uploads;
	}

	public void setUpload(List<File> uploads) {
		this.uploads = uploads;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	//===multiple File upload end ===
	
	public UserLogin getUserLogin() {
		return (UserLogin) ActionContext.getContext().getSession()
				.get(Constants.USER_LOGIN);
	}

	public AccountInfo getLoginAccountInfo() {
		return this.getUserLogin().getAccountInfo();
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
