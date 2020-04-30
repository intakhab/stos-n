package com.hcl.usf.util;
/**
 * @author intakhabalam.s@hcl.com
 */
public class ThreadLocalUtil {
	public static final ThreadLocal<ThreadLocalSession> USERTHREADLOCAL = new ThreadLocal<ThreadLocalSession>();
    
	private ThreadLocalUtil(){
		
	}
	/**
	 * This method sets the ThreadLocalSession object to the thread local.
	 * 
	 * @param session
	 */
	public static void set(ThreadLocalSession session) {
		USERTHREADLOCAL.set(session);
	}

	/**
	 * This method de-scopes the ThreadLocalSession object.
	 */
	public static void unset() {
		USERTHREADLOCAL.get().reset();
		USERTHREADLOCAL.set(null);
		USERTHREADLOCAL.remove();
	}

	/**
	 * This method returns the ThreadLocalSession object under scope.
	 * 
	 * @return util.ThreadLocalSession
	 */
	public static ThreadLocalSession get() {
		return USERTHREADLOCAL.get();
	}

}
