package com.dk.apps.etc.service;

import java.util.Date;

import com.dk.apps.etc.domain.AccountInfo;

public interface LoginService {
	public AccountInfo getAccountInfo(String account, String password) throws Exception;
}
