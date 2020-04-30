package com.hcl.usf.util;
/**
 * @author intakhabalam.s@hcl.com
 */
public class ThreadLocalSession {
	public boolean isHealthCheck=false;
	public String siteRunningStatus="";
	public String runDuration="0";
	public String runStatus="PASS";
	
	public void reset() {
		isHealthCheck=false;
		siteRunningStatus="UP";
		runDuration="0";
		runStatus="PASS";
	}
}
