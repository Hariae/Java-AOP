package edu.sjsu.cmpe275.aop.aspect;

import java.util.*;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.Secret;
import edu.sjsu.cmpe275.aop.SecretStatsImpl;

@Aspect
@Order(3)
public class StatsAspect {
	/***
	 * Following is a dummy implementation of this aspect. You are expected to
	 * provide an actual implementation based on the requirements, including
	 * adding/removing advice as needed.
	 */

	@Autowired
	SecretStatsImpl secretStats;
	



	/*
	 * @After("execution(public void edu.sjsu.cmpe275.aop.SecretService.*(..))")
	 * public void dummyAfterAdvice(JoinPoint joinPoint) {
	 * System.out.printf("After the executuion of the metohd %s\n",
	 * joinPoint.getSignature().getName()); // stats.resetStats(); }
	 * 
	 * @Before("execution(public void edu.sjsu.cmpe275.aop.SecretService.*(..))")
	 * public void dummyBeforeAdvice(JoinPoint joinPoint) {
	 * System.out.printf("Doing stats before the executuion of the metohd %s\n",
	 * joinPoint.getSignature().getName()); }
	 */

	@AfterReturning(pointcut = "execution(public java.util.UUID edu.sjsu.cmpe275.aop.SecretService.*(..))", returning = "result")
	public void createSecretAdvice(JoinPoint joinPoint, Object result) {
		System.out.printf("Access control post to the executuion of the metohd %s\n",
				joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();
		secretStats.secrets.add(obj[1].toString());

		// to track owners for a secret
		String ownerId = obj[0].toString();
		String secret = obj[1].toString();
		secretStats.secretOwners.put(secret, ownerId);

		System.out.println(secretStats.secrets);
	}

	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.SecretService.shareSecret(..))")
	public void shareSecretAdvice(JoinPoint joinPoint) {
		System.out.printf("Doing stats after the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();

		String sharerTuple = obj[1].toString() + obj[0].toString();
		String recieverId = obj[2].toString();

		if (recieverId != obj[0].toString()) {
			// Adding (userID+secretID), recieverId to a map
			if (secretStats.secretRecieverTracker.containsKey(recieverId)) {
				List<String> sharerTuples = secretStats.secretRecieverTracker.get(recieverId);
				// to check for unique (userID+secretID)
				if (!sharerTuples.contains(sharerTuple)) {
					sharerTuples.add(sharerTuple);
					secretStats.secretRecieverTracker.put(recieverId, sharerTuples);
				}

			} else {
				List<String> sharerTuples = new ArrayList<String>();
				sharerTuples.add(sharerTuple);
				secretStats.secretRecieverTracker.put(recieverId, sharerTuples);
				System.out.println("secretShareTracker aspect: " + secretStats.secretRecieverTracker);
			}

			// tracking share count
			String sharerId = obj[0].toString();
			String recieverTuple = obj[1].toString() + obj[2].toString();

			if (secretStats.secretSharerTracker.containsKey(sharerId)) {

				List<String> secretRecievers = secretStats.secretSharerTracker.get(sharerId);
				if (!secretRecievers.contains(recieverTuple)) {
					secretRecievers.add(recieverTuple);
					secretStats.secretSharerTracker.put(sharerId, secretRecievers);
				}

			} else {
				List<String> secretRecievers = new ArrayList<String>();
				secretRecievers.add(recieverTuple);
				secretStats.secretSharerTracker.put(sharerId, secretRecievers);
			}

		}

	}

	@AfterReturning(pointcut = "execution(public * edu.sjsu.cmpe275.aop.SecretService.readSecret(..))", returning = "result")
	public void readSecretAdvice(JoinPoint joinPoint, Object result) {
		System.out.printf("Doing stats after the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		Object[] obj = joinPoint.getArgs();

		String readerId = obj[0].toString();
		Secret secret = (Secret) result;
		String secretContent = secret.getContent();

		if (secretStats.secretOwners.get(secretContent) != readerId) {

			if (secretStats.secretReadTracker.containsKey(secretContent)) {
				List<String> readers = secretStats.secretReadTracker.get(secretContent);

				if (!readers.contains(readerId)) {
					readers.add(readerId);
					secretStats.secretReadTracker.put(secretContent, readers);
				}
			} else {
				List<String> readers = new ArrayList<String>();
				readers.add(readerId);
				secretStats.secretReadTracker.put(secretContent, readers);
			}

		}
	}
	

}
