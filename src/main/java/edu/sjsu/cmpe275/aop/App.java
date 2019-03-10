package edu.sjsu.cmpe275.aop;

import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of your submission.
         */

    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        SecretService secretService = (SecretService) ctx.getBean("secretService");
        SecretStats stats = (SecretStats) ctx.getBean("secretStats");

        try {
			
			
			  UUID secret = secretService.createSecret("Alice", "My little secret");
			  UUID secret1 = secretService.createSecret("Alice", "My little secret1");
			  
			  secretService.shareSecret("Alice",secret, "Bob");
			  secretService.shareSecret("Alice",secret1, "Bob");
			  secretService.shareSecret("Bob",secret, "Alex");
			  secretService.shareSecret("Bob",secret, "Jim");
			  secretService.readSecret("Bob", secret);
			  secretService.readSecret("Jim", secret);
			  secretService.readSecret("Bob", secret1);
			  secretService.unshareSecret("Alice",
			  secret, "Bob");
			 
			 
			
			/*Use case*/
			
			
			
			/*
			 * UUID secret = secretService.createSecret("Alice", "My little secret");
			 * secretService.readSecret("Alice", secret);secretService.readSecret("Alice",
			 * secret);
			 * 
			 * secretService.shareSecret("Alice",secret, "Bob");
			 * secretService.readSecret("Bob", secret);secretService.readSecret("Bob",
			 * secret); secretService.shareSecret("Alice",secret, "Carl");
			 * secretService.shareSecret("Alice",secret, "Carl");
			 * secretService.shareSecret("Alice",secret, "Carl");
			 * secretService.shareSecret("Bob",secret, "Carl");
			 * secretService.shareSecret("Bob",secret, "Carl");
			 * secretService.shareSecret("Bob",secret, "Carl");
			 * secretService.readSecret("Carl", secret);secretService.readSecret("Carl",
			 * secret);secretService.readSecret("Carl", secret);
			 * secretService.shareSecret("Carl",secret, "Ed");
			 * secretService.shareSecret("Carl",secret, "Boyce");
			 * secretService.shareSecret("Carl",secret, "Jones");
			 * secretService.readSecret("Ed", secret);secretService.readSecret("Jones",
			 * secret);
			 */
			  
			 
        	/*Use case 2*/
        	
			/*
			 * UUID secret = secretService.createSecret("Alice", "My little secret"); UUID
			 * secret1 = secretService.createSecret("Alice", "My little secret");
			 * 
			 * secretService.shareSecret("Alice",secret, "Bob");
			 * secretService.shareSecret("Alice",secret1, "Bob");
			 * secretService.shareSecret("Bob",secret, "Saumya");
			 * secretService.shareSecret("Saumya",secret, "Bob");
			 * secretService.shareSecret("Bob",secret, "Bob");
			 */
        	
			
			System.out.println("Longest " + stats.getLengthOfLongestSecret());
			
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Best known secret: " + stats.getBestKnownSecret());
        System.out.println("Worst secret keeper: " + stats.getWorstSecretKeeper());
        System.out.println("Most trusted user: " + stats.getMostTrustedUser());
        stats.resetStatsAndSystem();
        ctx.close();
    }
}
