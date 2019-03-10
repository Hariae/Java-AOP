/**
 * 
 */
package edu.sjsu.cmpe275.aop;

/**
 * @author charleszhang
 *
 */
public class NotAuthorizedException extends RuntimeException {

	/**
	 *  This file is NOT part of your submission!
	 */
	private static final long serialVersionUID = -2692933807731006155L;

	/**
	 * 
	 */
	public NotAuthorizedException() {
	}

	/**
	 * @param message
	 */
	public NotAuthorizedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NotAuthorizedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NotAuthorizedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
