package com.dk.apps.etc.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTasks{
	@Resource(name = "etcService")
	protected com.dk.apps.etc.service.etcService etcService;
	
	private static Log log = LogFactory.getLog(SpringTasks.class);
	
	@Scheduled(cron = "0 * * * * *")  
    void clearContactTarget(){  
		etcService.syncTicker();;
    }

	public com.dk.apps.etc.service.etcService getEtcService() {
		return etcService;
	}

	public void setEtcService(com.dk.apps.etc.service.etcService etcService) {
		this.etcService = etcService;
	}
}
