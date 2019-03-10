 package edu.sjsu.cmpe275.aop;

public interface SecretStats {
	// Please do NOT change this file.

	/**
	 * Reset all the four measurements. For purpose of this lab, it also clears up
	 * all secret objects ever created and their sharing/unsharing as if the system
	 * is starting fresh for any purpose related to the metrics below.
	 */
	void resetStatsAndSystem();

	/**
	 * @return the length of the longest secret by content a user has successfully
	 *         created since the beginning or last reset. If no secrets are created,
	 *         return 0.
	 */
	int getLengthOfLongestSecret();

	/**
	 * If Alice shares a message foo with Bob, the tuple (Alice, foo) is considered
	 * a sharing occurrence with Bob. The most trusted user is determined by the
	 * maximum total number of unique sharing occurrences, each defined by a tuple
	 * of (sharerID, secretID). For each of the sharing, The uniqueness of secrets
	 * are defined their UUIDs; i.e., two secrets with the same content but
	 * different UUIDs are considered different secrets. Unsharing does NOT affect
	 * this stat. If Alice and Bob share the same secret with Carl once each, it's
	 * considered as two total sharing occurrences with Carl. If Alice shares the
	 * same secret he created with Carl five times and later unshares it, it is
	 * still considered one sharing occurrence. Sharing a message with a user
	 * himself does NOT count for the purpose of this stat. If there is a tie,
	 * return the 1st of such users based on alphabetical order of the user ID. Only
	 * successful sharing matters here; if no users has been successfully shared
	 * with any secret, return null.
	 * 
	 * @return the ID of the most trusted user.
	 */
	String getMostTrustedUser();

	/**
	 * The concept of unique sharing occurrences is defined the same as above. The
	 * net sharing balance for a user is the total number of unique sharing
	 * occurrences shared with him minus the total number of unique sharing
	 * occurrences he shared with others. If Alice and Bob share the same message
	 * with Carl three times each, and Carl shares the same message with Doug, Ed,
	 * and Fred, Carl's net sharing balance is 2-3 = -1. Again, sharing/unsharing
	 * with one himself does not count here.
	 * 
	 * @return the ID of the person with the smallest net sharing balance. If there
	 *         is a tie, return the 1st of such users based on alphabetical order of
	 *         the user ID. If no users has been successfully shared with any
	 *         secret, return null.
	 */
	String getWorstSecretKeeper();

	/**
	 * Returns the secret that has been successfully read by the biggest number of
	 * different users, OTHER THAN the creator himself. If the same secret is read
	 * by the same user more than once successfully, it is still considered as one.
	 * If Alice shares a secret with Bob, Bob reads this secret, and later Alice
	 * unshares it from Bob, Bob's read still counts because it was successful.
	 * 
	 * @return the content of the secret that has been shared with the biggest
	 *         number of different users. If no secrets are ever read by users other
	 *         than the creators, return null. If there is a tile, return based on
	 *         the alphabetic order of the secret content.
	 */
	String getBestKnownSecret();

}