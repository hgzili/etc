package com.dk.apps.etc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk.apps.etc.domain.AccountInfo;
import com.dk.apps.etc.domain.UserLogin;
import com.dk.apps.etc.service.AdminService;
import com.dk.apps.etc.service.LoginService;
import com.dk.apps.etc.util.Constants;
import com.dk.apps.etc.util.Encoder;

@SuppressWarnings("unchecked")
@Transactional
@Service("loginService")
public class LoginServiceImpl extends BaseDaoHibernate implements LoginService{
	@Resource(name = "adminService")
	private AdminService adminService;
	
	public AccountInfo getAccountInfo(String account, String password) throws Exception{
		AccountInfo accountInfo = this.adminService.getAccountInfoByAccount(account);
		if (accountInfo == null || accountInfo.getActive() == false) throw new Exception(Constants.ERROR_MSG2);
		Encoder encoder = new Encoder();
		String loginPassword = encoder.encodePassword(password);
		if (!accountInfo.getPassword().equals(loginPassword)) throw new Exception(Constants.ERROR_MSG3);
		return accountInfo;
	}
	
	public void saveOrUpdateUserLogin(UserLogin userLogin){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(userLogin);
	}
	
}
