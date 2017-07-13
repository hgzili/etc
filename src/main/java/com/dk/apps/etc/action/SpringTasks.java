package com.dk.apps.etc.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dk.apps.etc.service.EtcService;

@Component
public class SpringTasks{
	@Resource(name = "etcService")
	protected EtcService etcService;
	
	private static Log log = LogFactory.getLog(SpringTasks.class);
	
	@Scheduled(cron = "0 * * * * *")  
    void syncTicker(){  
//		etcService.syncTicker();;
    }
	
	@Scheduled(cron = "0 * * * * *")  
    void syncDepth(){  
//		etcService.syncDepth();
    }
	
	@Scheduled(cron = "50 * * * * *")  
    void syncOrdersNew(){  
//		etcService.syncOrdersNew("1");;
    }
	
	@Scheduled(cron = "30 * * * * *")  
    void syncNetOrders(){  
//		etcService.syncNetOrders();
    }

	public EtcService getEtcService() {
		return etcService;
	}

	public void setEtcService(EtcService etcService) {
		this.etcService = etcService;
	}
}
