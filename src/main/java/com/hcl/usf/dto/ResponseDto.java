package com.hcl.usf.dto;
/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class ResponseDto {
	private String tcId;
	private String tcName;
	private String status="Running";
	private String errorCause;
	private String testMethod;
	private boolean willCreateJiraTicket=false;
	
	private int totalTC=0;

	public String getTcId() {
		return tcId;
	}

	public void setTcId(String tcId) {
		this.tcId = tcId;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCause() {
		return errorCause;
	}

	public void setErrorCause(String errorCause) {
		this.errorCause = errorCause;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tcId == null) ? 0 : tcId.hashCode());
		result = prime * result + ((tcName == null) ? 0 : tcName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseDto other = (ResponseDto) obj;
		if (tcId == null) {
			if (other.tcId != null)
				return false;
		} else if (!tcId.equals(other.tcId))
			return false;
		if (tcName == null) {
			if (other.tcName != null)
				return false;
		} else if (!tcName.equals(other.tcName))
			return false;
		return true;
	}

	public int getTotalTC() {
		return totalTC;
	}

	public void setTotalTC(int totalTC) {
		this.totalTC = totalTC;
	}

	public String getTestMethod() {
		return testMethod;
	}

	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}

	public boolean isWillCreateJiraTicket() {
		return willCreateJiraTicket;
	}

	public void setWillCreateJiraTicket(boolean willCreateJiraTicket) {
		this.willCreateJiraTicket = willCreateJiraTicket;
	}
	

	
}
