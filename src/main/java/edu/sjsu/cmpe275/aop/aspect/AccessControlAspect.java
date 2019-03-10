package edu.sjsu.cmpe275.aop.aspect;

import java.util.*;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.NotAuthorizedException;
import edu.sjsu.cmpe275.aop.SecretStatsImpl;
import edu.sjsu.cmpe275.aop.aspect.StatsAspect;


@Aspect
@Order(2)
public class AccessControlAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */
	
	
	/*
	 * @Before("execution(public void edu.sjsu.cmpe275.aop.SecretService.*(..))")
	 * public void dummyAdvice(JoinPoint joinPoint) {
	 * System.out.printf("Access control prior to the executuion of the metohd %s\n"
	 * , joinPoint.getSignature().getName());
	 * 
	 * }
	 */
	
	public Map<UUID, List<String>> userSecrets = new HashMap<UUID, List<String>>();
	public Map<UUID, String> secretCreators = new HashMap<UUID, String>();

	
	@Before("execution(public edu.sjsu.cmpe275.aop.Secret edu.sjsu.cmpe275.aop.SecretService.*(..))")
	public void readSecretAdvice(JoinPoint joinPoint) {
		
		System.out.printf("Access control prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();
		System.out.println(userSecrets.get((UUID)obj[1]) + " " + obj[0].toString());
		
		List<String> secretUsers = userSecrets.get((UUID)obj[1]);
		
		if(!secretUsers.contains(obj[0].toString())) {
			throw new NotAuthorizedException();
		}
		
	}
	
	@AfterReturning(pointcut = "execution(public java.util.UUID edu.sjsu.cmpe275.aop.SecretService.*(..))", returning = "result")
	public void createSecretAdvice(JoinPoint joinPoint, Object result) {
		System.out.printf("Access control post to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();
		
		List<String> secretUsers = new ArrayList<String>();
		secretUsers.add(obj[0].toString());
		
		userSecrets.put((UUID)result, secretUsers);
		secretCreators.put((UUID)result, obj[0].toString());
		System.out.println(userSecrets);
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.SecretService.shareSecret(..))")
	public void shareSecretAdvice(JoinPoint joinPoint) {
		System.out.printf("Access control prior to the executuion of the method %s\n", joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();
		
		List<String> secretUsers = userSecrets.get((UUID)obj[1]);
		
		if(!secretUsers.contains(obj[0])) {
			throw new NotAuthorizedException();
		}
	}
	
	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.SecretService.shareSecret(..))")
	public void shareSecretAfterAdvice(JoinPoint joinPoint) {
		System.out.printf("Access control post to the executuion of the method %s\n", joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();
		
		List<String> secretUsers = userSecrets.get((UUID)obj[1]);
		secretUsers.add(obj[2].toString());
		userSecrets.put((UUID)obj[1], secretUsers);
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.SecretService.unshareSecret(..))")
	public void unshareSecretAdvice(JoinPoint joinPoint) {
		System.out.printf("Access control prior to the executuion of the method %s\n", joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();
		
		if(secretCreators.get(obj[1]) != obj[0].toString()) {
			throw new NotAuthorizedException();
		}
	}
	
	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.SecretService.unshareSecret(..))")
	public void unshareSecretAfterAdvice(JoinPoint joinPoint) {
		System.out.printf("Access control after the executuion of the method %s\n", joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();
		List<String> secretUsers = userSecrets.get((UUID)obj[1]);
		secretUsers.remove(obj[2].toString());
	}
	
	 
	
	
	

}
