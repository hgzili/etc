package com.dk.apps.etc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk.apps.etc.domain.etc.TickerTable;
import com.dk.apps.etc.service.etcService;

@Transactional
@Service("etcService")
public class etcServiceImpl extends BaseDaoHibernate implements etcService {
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(etcServiceImpl.class);
	
	public void saveOrUpdateTickerTable(TickerTable tickerTable){
		this.getSessionFactory().getCurrentSession().saveOrUpdate(tickerTable);
	}
}
