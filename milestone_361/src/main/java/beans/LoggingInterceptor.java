package beans;

import java.io.Serializable;

import javax.ejb.Singleton;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Singleton
public class LoggingInterceptor implements Serializable {
	
	
	@AroundInvoke 
	public Object methodInterceptor(InvocationContext ctx) throws Exception 
	{ 
	System.out.println("******* A new method is being called: " +  
	ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "()" + " *******"); 
	return ctx.proceed(); 
	} 


}
