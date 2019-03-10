package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ValidationAspect {
	/***
	 * Following is a dummy implementation of this aspect. You are expected to
	 * provide an actual implementation based on the requirements, including
	 * adding/removing advices as needed.
	 */
	@Order(0)
	@Before("execution(public * edu.sjsu.cmpe275.aop.SecretService.*(..))")
	public void shareSecretAdvice(JoinPoint joinPoint) {
		System.out.printf("Doing validation prior to the executuion of the metohd %s\n",
				joinPoint.getSignature().getName());
		Object[] obj  = joinPoint.getArgs();
		System.out.println("Objects: " + obj);
		for(int i=0;i<obj.length;i++) {
			if(obj[i] == null) {
				throw new IllegalArgumentException();
			}
		}
	}

	@Order(1)
	@Before("execution(public * edu.sjsu.cmpe275.aop.SecretService.createSecret(..))")
	public void createSecretAdvice(JoinPoint joinPoint) {
		System.out.printf("Doing validation prior to the executuion of the metohd %s\n",
				joinPoint.getSignature().getName());
		Object[] objects = joinPoint.getArgs();
		/*
		 * String userId = objects[0].toString(); String secretContent =
		 * objects[1].toString();
		 */
		if (objects[0] == null || objects[1] == null) {
			throw new IllegalArgumentException();
		}

		if (objects[1].toString().length() > 100) {
			throw new IllegalArgumentException();
		}
	}
	/*
	 * @Before("execution(public * edu.sjsu.cmpe275.aop.SecretService.readSecret(..))"
	 * ) public void readSecretAdvice(JoinPoint joinPoint) { System.out.
	 * printf("Doing validation prior to the executuion of the metohd %s\n",
	 * joinPoint.getSignature().getName()); Object[] obj = joinPoint.getArgs();
	 * for(int i=0;i<obj.length;i++) { if(obj[i] == null) { throw new
	 * IllegalArgumentException(); } } }
	 */
	
	

}
