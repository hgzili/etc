package com.dk.apps.etc.service;

import java.util.List;

import com.dk.apps.etc.domain.AccountInfo;

public interface AdminService {
	public AccountInfo getAccountInfoByAccount(String account);
	public AccountInfo getAccountInfoByOpenId(String openId);
	public List<AccountInfo> getAccountInfoList();
	public AccountInfo getAccountInfoById(Long id);
	public void saveOrUpdateAccountInfo(AccountInfo accountInfo);
	public void deleteAccountInfo(AccountInfo accountInfo);
	public boolean isExitedAccountInfo(String account);
	public boolean isExitedAccountInfo(String account,Long excludeId);
}
