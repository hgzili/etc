package etc;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.dk.apps.etc.domain.etc.TickerTable;
import com.dk.apps.etc.service.EtcService;
import com.dk.apps.etc.util.EtcUtil;

@ContextConfiguration(locations={"spring.xml"})
public class etcTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private EtcService etcService; 

	@Test
	public void testGetTicker() {
//		EtcUtil.getKline("1min",new Date());
//		etcService.syncDepth();
//		etcService.syncOrdersNew("1");
		TickerTable tickerTable = EtcUtil.getTicker();
//		etcService.saveOrUpdateTickerTable(tickerTable);
	}
}
