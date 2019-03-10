package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.Secret;

import org.aspectj.lang.annotation.Around;

@Aspect
@Order(0)
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     * @throws Throwable 
     */

	@Around("execution(public void edu.sjsu.cmpe275.aop.SecretService.*(..))")
	public void shareSecretAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.printf("Retry aspect prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		Object result = null;
		try {
			result = joinPoint.proceed();
			System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
		} catch (Throwable e) {
			e.printStackTrace();
			try {
				result = joinPoint.proceed();
				System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
			}
			catch(Throwable exception) {
				exception.printStackTrace();
				try {
					result = joinPoint.proceed();
					System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
				}
				catch(Throwable subexception) {
					subexception.printStackTrace();
					System.out.printf("Aborted the executuion of the metohd %s\n", joinPoint.getSignature().getName());
					throw subexception;
				}
			}
			
		}
	}
	
	
	
	  @Around("execution(public * edu.sjsu.cmpe275.aop.SecretService.readSecret(..))") 
	  public Secret readSecretAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
			System.out.printf("Retry aspect prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
			Object result = null;
			try {
				result = joinPoint.proceed();
				System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
			} catch (Throwable e) {
				e.printStackTrace();
				try {
					result = joinPoint.proceed();
					System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
				}
				catch(Throwable exception) {
					exception.printStackTrace();
					try {
						result = joinPoint.proceed();
						System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
					}
					catch(Throwable subexception) {
						subexception.printStackTrace();
						System.out.printf("Aborted the executuion of the metohd %s\n", joinPoint.getSignature().getName());
						throw subexception;
					}
				}
				
			}
			return (Secret)result;
		}
	  
	  
	  
	  @Around("execution(public * edu.sjsu.cmpe275.aop.SecretService.createSecret(..))")
	  public UUID createSecretAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
			System.out.printf("Retry aspect prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
			Object result = null;
			try {
				result = joinPoint.proceed();
				System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
				
			} catch (Throwable e) {
				e.printStackTrace();
				try {
					result = joinPoint.proceed();
					System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
				}
				catch(Throwable exception) {
					exception.printStackTrace();
					try {
						result = joinPoint.proceed();
						System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
					}
					catch(Throwable subexception) {
						subexception.printStackTrace();
						System.out.printf("Aborted the executuion of the metohd %s\n", joinPoint.getSignature().getName());
						throw subexception;
					}
				}
				
			}
			return (UUID) result;
		}
	 
	 
	 
}
