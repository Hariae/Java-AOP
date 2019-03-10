package edu.sjsu.cmpe275.aop;

import java.util.*;

public class SecretStatsImpl implements SecretStats {
    /***
     * Following is a dummy implementation.
     * You are expected to provide an actual implementation based on the requirements.
     */
	
	//to calculate length of secrets
	public Set<String> secrets = new HashSet<String>();	
	//To track reciever, List of (sharerId+secretId)
	public Map<String, List<String>> secretRecieverTracker = new HashMap<String, List<String>>();
	//To track sharer, number of shares made
	public Map<String, List<String>> secretSharerTracker = new HashMap<String, List<String>>();
	//To track number of unique reads for a secret
	public Map<String, List<String>> secretReadTracker = new HashMap<String, List<String>>();
	// To track secret and owers
	public Map<String, String> secretOwners = new HashMap<String, String>();
	

	//@Override
	public void resetStatsAndSystem() {
		// TODO Auto-generated method stub
		secrets = new HashSet<String>();
		secretRecieverTracker = new HashMap<String, List<String>>();
		secretSharerTracker = new HashMap<String, List<String>>();
		secretReadTracker = new HashMap<String, List<String>>();
		secretOwners = new HashMap<String, String>();	
	}

	//@Override
	public int getLengthOfLongestSecret() {
		// TODO Auto-generated method stub
		int max = Integer.MIN_VALUE;
		for(String entry : secrets){
			if(entry.length() > max) {
				max = entry.length();
			}
		}
		
		return max;
	}

	//@Override
	public String getMostTrustedUser() {
		// TODO Auto-generated method stub
		System.out.println("secretsharetracker: " + secretRecieverTracker);
		int counter = 0; String mostTrustedUser="";
		for(Map.Entry<String, List<String>> entry : secretRecieverTracker.entrySet()) {
			if(counter < entry.getValue().size()) {
				counter = entry.getValue().size();
				mostTrustedUser = entry.getKey();
			}
		}
		return mostTrustedUser;
	}

	//@Override
	public String getWorstSecretKeeper() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> secretKeeperStat = new HashMap<String, Integer>();
		
		System.out.println("secretSharerTracker: " + secretSharerTracker);
		
		for(Map.Entry<String, List<String>> entry : secretRecieverTracker.entrySet()) {
			secretKeeperStat.put(entry.getKey(), entry.getValue().size());
		}
		
		for(Map.Entry<String, List<String>> entry : secretSharerTracker.entrySet()) {
			if(secretKeeperStat.containsKey(entry.getKey())) {
				secretKeeperStat.put(entry.getKey(), secretKeeperStat.get(entry.getKey())-entry.getValue().size());
			}
			else {
				secretKeeperStat.put(entry.getKey(), 0-entry.getValue().size());
			}
			
		}
		
		System.out.println("secretKeeperStat:  " + secretKeeperStat);
		
		int counter = Integer.MAX_VALUE;
		String worstSecretKeeper="";
		for(Map.Entry<String, Integer> entry: secretKeeperStat.entrySet()) {
			if(counter > entry.getValue()) {
				counter = entry.getValue();
				worstSecretKeeper = entry.getKey();
			}
		}
		return worstSecretKeeper;
	}

	//@Override
	public String getBestKnownSecret() {
		// TODO Auto-generated method stub
		int counter = 0;
		String bestKnownSecret = "";
		
		for(Map.Entry<String, List<String>> entry: secretReadTracker.entrySet()) {
			if(counter < entry.getValue().size()) {
				counter = entry.getValue().size();
				bestKnownSecret = entry.getKey();
			}
		}
		
		System.out.println("readtracker: " + secretReadTracker);
		
		
		return bestKnownSecret;
	}
    
}



