package com.dk.apps.etc.util;

import java.util.Iterator;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.conversion.impl.InstantiatingNullHandler;
import com.opensymphony.xwork2.conversion.impl.XWorkConverter;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.NoParameters;
import com.opensymphony.xwork2.ognl.accessor.XWorkMethodAccessor;
import com.opensymphony.xwork2.util.ValueStack;


/**
 * This interceptor sets up the special parameter which name ends with "Id"
 * values on the stack first, most code are copied from
 * com.opensymphony.xwork.interceptor.ParametersInterceptor
 * 
 * @author  
 * @since 2015-08-18
 * @version $Revision: 1.5 $
 */
public class ParametersInterceptor extends AbstractInterceptor {
    
   protected boolean trimFlag=false;
    
    

 
    protected void after(ActionInvocation dispatcher, String result) throws Exception {
    }

    protected void before(ActionInvocation invocation) throws Exception {
        
    }
    
    private void trimValues(String[] values) {
        for (int i = 0; values != null && i < values.length; i++) {
            if (values[i] != null) {
                values[i] = values[i].trim();
            }
        }
    }

    private boolean isSpecialIDParameter(String key) {
        return key.indexOf(".") == -1 && key.endsWith("Id");
    }

    public boolean isTrimFlag() {
        return trimFlag;
    }

    public void setTrimFlag(boolean trimFlag) {
        this.trimFlag = trimFlag;
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (!(invocation.getAction() instanceof NoParameters)) {
            final Map parameters = ActionContext.getContext().getParameters();


            try {
                invocation.getInvocationContext().put(InstantiatingNullHandler.CREATE_NULL_OBJECTS, Boolean.TRUE);
                invocation.getInvocationContext().put(XWorkMethodAccessor.DENY_METHOD_EXECUTION, Boolean.TRUE);
                invocation.getInvocationContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

                if (parameters != null) {
                    final ValueStack stack = ActionContext.getContext().getValueStack();
                    
                    //sets up the special parameter which name ends with "Id" values and does not contain the "." on the stack first.
                    for (Iterator iterator = parameters.entrySet().iterator(); iterator.hasNext();) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = entry.getKey().toString();
                        if(isSpecialIDParameter(key)){
                            stack.setValue(key, entry.getValue());
                        }
                    }
                    
                    //other parameters
                    for (Iterator iterator = parameters.entrySet().iterator(); iterator.hasNext();) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = entry.getKey().toString();
                        if (!isSpecialIDParameter(key)) {
                            if (trimFlag == true && entry.getValue() instanceof String[] ) {
                              //trim all parameter value's space
                                trimValues((String[]) entry.getValue());    
                            }
                             stack.setValue(key, entry.getValue());                            
                        }
                    }                    
                }
            } finally {
                invocation.getInvocationContext().put(InstantiatingNullHandler.CREATE_NULL_OBJECTS, Boolean.FALSE);
                invocation.getInvocationContext().put(XWorkMethodAccessor.DENY_METHOD_EXECUTION, Boolean.FALSE);
                invocation.getInvocationContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.FALSE);
            }
        }
		return invocation.invoke();
	}
}