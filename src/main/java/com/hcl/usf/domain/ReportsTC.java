package com.hcl.usf.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

/***
 * @author intakhabalam.s@hcl.com
 */
@javax.persistence.Entity
@DynamicUpdate
@Table(name = "REPORTS_TC")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReportsTC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CUSTOMER_NUMBER", length = 20)
	private String customerNumber = "";

	@Column(name = "DEPARTMENT", length = 20)
	private String department = "";

	@Column(name = "RUN_STATUS", length = 10)
	private String runStatus = "Failed";

	@Column(name = "RUN_TIME", length = 20)
	private String runningTime = "";

	@Column(name = "RUN_DURATION", length = 20)
	private String runDuration = "";

	@Column(name = "JIRA_TCIKET_KEY", length = 20)
	private String jiraTicketKey = "";

	@Column(name = "CONFLUENCE_URL")
	private String confluenceUrl = "";

	@Column(name = "REASON_FAIL", length = 2000)
	private String reasonFail = "";

	@Column(name = "ENV_TYPE", length = 5)
	private String envType = "DEV";

	@Column(name = "BROWSER_NAME")
	private String browserName = "";

	@Column(name = "TOTAL_STEPS", length = 5)
	private String totalSteps = "";

	@Column(name = "PASS_STEPS", length = 5)
	private String passSteps = "";

	@Column(name = "FAIL_STEPS", length = 5)
	private String failSteps = "";

	@Column(name = "REPORTS_PATH")
	private String reportsPath = "";
	
	@Column(name = "ENV_URL",columnDefinition="text")
	private String envURL = "";

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REGISTER_TC_ID", nullable = false)
	private RegisterTC registerTC;

	@Column(name = "HOST_IP_NAME")
	private String hostIpName = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getTotalSteps() {
		return totalSteps;
	}

	public void setTotalSteps(String totalSteps) {
		this.totalSteps = totalSteps;
	}

	public String getPassSteps() {
		return passSteps;
	}

	public void setPassSteps(String passSteps) {
		this.passSteps = passSteps;
	}

	public String getFailSteps() {
		return failSteps;
	}

	public void setFailSteps(String failSteps) {
		this.failSteps = failSteps;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	public String getReasonFail() {
		return reasonFail;
	}

	public void setReasonFail(String reasonFail) {
		this.reasonFail = reasonFail;
	}

	public String getEnvType() {
		return envType;
	}

	public void setEnvType(String envType) {
		this.envType = envType;
	}

	public String getJiraTicketKey() {
		return jiraTicketKey;
	}

	public void setJiraTicketKey(String jiraTicketKey) {
		this.jiraTicketKey = jiraTicketKey;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getReportsPath() {
		return reportsPath;
	}

	public void setReportsPath(String reportsPath) {
		this.reportsPath = reportsPath;
	}

	public RegisterTC getRegisterTC() {
		return registerTC;
	}

	public void setRegisterTC(RegisterTC registerTC) {
		this.registerTC = registerTC;
	}

	public String getHostIpName() {
		return hostIpName;
	}

	public void setHostIpName(String hostIpName) {
		this.hostIpName = hostIpName;
	}

	public String getRunDuration() {
		return runDuration;
	}

	public void setRunDuration(String runDuration) {
		this.runDuration = runDuration;
	}

	public String getConfluenceUrl() {
		return confluenceUrl;
	}

	public void setConfluenceUrl(String confluenceUrl) {
		this.confluenceUrl = confluenceUrl;
	}

	public String getEnvURL() {
		return envURL;
	}

	public void setEnvURL(String envURL) {
		this.envURL = envURL;
	}

}
