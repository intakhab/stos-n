package com.hcl.usf.common;

/***
 * 
 * @author intakhabalam.s@hcl.com
 * @see Exception 
 */
public class STOSException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    /**
     * 
     * @param message {@link String}
     */
	public STOSException(String message) {
		super(message);
	}
	
	public STOSException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String toString() {
		return "["+ getMessage() +"]";
	}

	
}
