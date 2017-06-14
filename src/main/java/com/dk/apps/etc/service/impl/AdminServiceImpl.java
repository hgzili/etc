package com.dk.apps.etc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk.apps.etc.domain.AccountInfo;
import com.dk.apps.etc.service.AdminService;

@SuppressWarnings("unchecked")
@Transactional
@Service("adminService")
public class AdminServiceImpl extends BaseDaoHibernate implements AdminService {
	public AccountInfo getAccountInfoByAccount(String account){
		String sql = "from AccountInfo u where u.account=:account";
		List<AccountInfo> list = this.getSessionFactory().getCurrentSession()
				.createQuery(sql).setString("account",account).list();
		if(list == null || list.size()<=0) return null;
		return (AccountInfo) list.get(0);
	}
	
	public List<AccountInfo> getAccountInfoList(){
		String sql = "from AccountInfo a";
		List<AccountInfo> accountInfoList = this.getSessionFactory().getCurrentSession()
				.createQuery(sql).list();
		return accountInfoList;
	}
	
	public AccountInfo getAccountInfoById(Long id){
		return (AccountInfo) this.getSessionFactory().getCurrentSession().get(AccountInfo.class, id);
	}
	
	public void saveOrUpdateAccountInfo(AccountInfo accountInfo){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(accountInfo);
	}
	
	public void deleteAccountInfo(AccountInfo accountInfo){
		this.getSessionFactory().getCurrentSession().delete(accountInfo);
	}
	
	public boolean isExitedAccountInfo(String account){
		String sql = "from AccountInfo a where a.account=:account";
		List<AccountInfo> accountList = this.getSessionFactory().getCurrentSession().createQuery(sql)
				.setString("account", account).list();
		if(accountList != null && accountList.size() > 0) return true;
		return false;
	}
}
