package beans;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor implements Serializable {
	
	
	@AroundInvoke 
	public Object methodInterceptor(InvocationContext ctx) throws Exception 
	{ 
	System.out.println("******* A new method is being called: " +  
	ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "()" + " *******"); 
	return ctx.proceed(); 
	} 


}
