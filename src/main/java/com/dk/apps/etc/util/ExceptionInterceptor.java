package com.dk.apps.etc.util;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * a simple exception handler interceptor, catch any uncaught exceptions, log
 * and return to global "exception" result.
 * 
 * @author  
 * @since  
 * @version $Revision: 1.3 $
 */
public class ExceptionInterceptor implements Interceptor {
    private static final Log log = LogFactory.getLog(ExceptionInterceptor.class);
    public static final String PCEXCEPTION = "pcexception";
    public static final String MOBILEEXCEPTION = "mobileexception";

    public void destroy() {
        // TODO Auto-generated method stub
 
    }

    public void init() {
        // TODO Auto-generated method stub

    }

    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            return invocation.invoke();
        } catch (Exception e) {
        	//if (e instanceof RuntimeException)
        	Date date = new Date();
        	String errorMsgCode = String.valueOf(date.getTime());
            String message = "Caught exception while invoking action: " + invocation.getAction();
            log.error(message, e);
            log.info("Error Message Code:"+errorMsgCode);
            HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
            log.info("Session info:"+request.getSession());
            log.info("Value stack info when exception:" + getParameters(request));
            if(invocation.getAction().equals("/mobile/")){
            	return MOBILEEXCEPTION;
            }
            return PCEXCEPTION;
        }
    }

    private String getParameters(HttpServletRequest request) {
        String result = "\n";
        try {
            Map map = request.getParameterMap();
            if (map != null) {
                Iterator itr = map.keySet().iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    String value = "";
                    Object values[] = (Object[]) map.get(key);
                    for (int i = 0; values != null && i < values.length; i++) {
                        value += (values[i] != null) ? values[i].toString() : null;
                    }
                    result += key + "=" + value + "\n";
                }
            }
        } catch (Exception ex) {
            log.error("Exception when getParameters:", ex);
        }
        return result;
    }
}