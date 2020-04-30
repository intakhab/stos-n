package com.hcl.usf.dto;

import java.util.List;

/**
 * 
 * @author Intakhabalam.s@hcl.com
 *
 */
public class CommonDto {
	private String tcId;
	private String runTcId;
	private String tcName;
	private String tcStory;
	private String tcSheetName;
	private String tcRegDate;
	private String tcVersion;
	private String tcRegNote;
	private String tcRunTime = "";
	private String runStatus = "Failed";
	private String browserType = "chrome";
	private String envType = "DEV";
	private String envUrl="";
	private String appUserId;
	private String appUserPass;
	private String dataPath;
	private String reportsPath;
	private String tabId;
	private boolean cronHit = false;
	private String customerId;
	private String departmentId;
	private String reasonFail = "";
	private String settingsId;
	private String stopBatch = "false";
	private boolean enableCron = false;
	private boolean enableCronSeq = false;
	private String cronSeqSheetName = "HealthCheck";
	private String cronSeqClassName = "HealthCheckTest";
	private String cronSeq;
	private boolean enableNode = false;
	private String[] groupList = new String[0];
	private String[] cronList = new String[0];
	private String[] nodeList = new String[0];
	private boolean enableMail = false;
	private String toWhomEmail = "";
	private String host = "";
	private String port = "";
	private String mailUserName = "";
	private String mailPassword = "";
	private String fromMail = "";
	private boolean debugMail = false;
	public String deafultUser = "STOS";
	public String hostName = "Localhost";
	private String groupTestName;
	private String selectedGroupTestName;
	private String groupId;
	private String groupsTcId;
	private String groupsname;
	private String groupname;
	private List<String> groupSuiteList;
	private List<String> groupSuiteTCList;
	private String totalSteps;
	private String passSteps;
	private String failSteps;
	private String leftNodeMenu;
	private String runDuration = "";
	private String jiraTicket = "";
	private String confluenceUrl="";

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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public boolean getEnableMail() {
		return enableMail;
	}

	public void setEnableMail(boolean enableMail) {
		this.enableMail = enableMail;
	}

	public String getToWhomEmail() {
		return toWhomEmail;
	}

	public void setToWhomEmail(String toWhomEmail) {
		this.toWhomEmail = toWhomEmail;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getMailUserName() {
		return mailUserName;
	}

	public String[] getGroupList() {
		return groupList;
	}

	public void setGroupList(String[] groupList) {
		this.groupList = groupList;
	}

	public String[] getCronList() {
		return cronList;
	}

	public void setCronList(String[] cronList) {
		this.cronList = cronList;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public boolean getDebugMail() {
		return debugMail;
	}

	public void setDebugMail(boolean debugMail) {
		this.debugMail = debugMail;
	}

	public String getStopBatch() {
		return stopBatch;
	}

	public void setStopBatch(String stopBatch) {
		this.stopBatch = stopBatch;
	}

	public String getReasonFail() {
		return reasonFail;
	}

	public void setReasonFail(String reasonFail) {
		this.reasonFail = reasonFail;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

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

	public String getTcSheetName() {
		return tcSheetName;
	}

	public void setTcSheetName(String tcSheetName) {
		this.tcSheetName = tcSheetName;
	}

	public String getTcRegDate() {
		return tcRegDate;
	}

	public void setTcRegDate(String tcRegDate) {
		this.tcRegDate = tcRegDate;
	}

	public String getTcVersion() {
		return tcVersion;
	}

	public void setTcVersion(String tcVersion) {
		this.tcVersion = tcVersion;
	}

	public String getTcRunTime() {
		return tcRunTime;
	}

	public void setTcRunTime(String tcRunTime) {
		this.tcRunTime = tcRunTime;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getEnvType() {
		return envType;
	}

	public void setEnvType(String envType) {
		this.envType = envType;
	}

	public String getEnvUrl() {
		return envUrl;
	}

	public void setEnvUrl(String envUrl) {
		this.envUrl = envUrl;
	}

	public String getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}

	public String getAppUserPass() {
		return appUserPass;
	}

	public void setAppUserPass(String appUserPass) {
		this.appUserPass = appUserPass;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getSettingsId() {
		return settingsId;
	}

	public void setSettingsId(String settingsId) {
		this.settingsId = settingsId;
	}

	public String getReportsPath() {
		return reportsPath;
	}

	public void setReportsPath(String reportsPath) {
		this.reportsPath = reportsPath;
	}

	public String getRunTcId() {
		return runTcId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setRunTcId(String runTcId) {
		this.runTcId = runTcId;
	}

	public String getGroupTestName() {
		return groupTestName;
	}

	public void setGroupTestName(String groupTestName) {
		this.groupTestName = groupTestName;
	}

	public String getSelectedGroupTestName() {
		return selectedGroupTestName;
	}

	public void setSelectedGroupTestName(String selectedGroupTestName) {
		this.selectedGroupTestName = selectedGroupTestName;
	}

	public String getGroupsname() {
		return groupsname;
	}

	public void setGroupsname(String groupsname) {
		this.groupsname = groupsname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public List<String> getGroupSuiteList() {
		return groupSuiteList;
	}

	public void setGroupSuiteList(List<String> groupSuiteList) {
		this.groupSuiteList = groupSuiteList;
	}

	public List<String> getGroupSuiteTCList() {
		return groupSuiteTCList;
	}

	public void setGroupSuiteTCList(List<String> groupSuiteTCList) {
		this.groupSuiteTCList = groupSuiteTCList;
	}

	public String getGroupsTcId() {
		return groupsTcId;
	}

	public void setGroupsTcId(String groupsTcId) {
		this.groupsTcId = groupsTcId;
	}

	public boolean isEnableCron() {
		return enableCron;
	}

	public void setEnableCron(boolean enableCron) {
		this.enableCron = enableCron;
	}

	public String getTcStory() {
		return tcStory;
	}

	public void setTcStory(String tcStory) {
		this.tcStory = tcStory;
	}

	public String getTcRegNote() {
		return tcRegNote;
	}

	public void setTcRegNote(String tcRegNote) {
		this.tcRegNote = tcRegNote;
	}

	public boolean isEnableNode() {
		return enableNode;
	}

	public void setEnableNode(boolean enableNode) {
		this.enableNode = enableNode;
	}

	public String[] getNodeList() {
		return nodeList;
	}

	public void setNodeList(String[] nodeList) {
		this.nodeList = nodeList;
	}

	public String getLeftNodeMenu() {
		return leftNodeMenu;
	}

	public void setLeftNodeMenu(String leftNodeMenu) {
		this.leftNodeMenu = leftNodeMenu;
	}

	public boolean isCronHit() {
		return cronHit;
	}

	public void setCronHit(boolean cronHit) {
		this.cronHit = cronHit;
	}

	public boolean isEnableCronSeq() {
		return enableCronSeq;
	}

	public void setEnableCronSeq(boolean enableCronSeq) {
		this.enableCronSeq = enableCronSeq;
	}

	public String getCronSeq() {
		return cronSeq;
	}

	public void setCronSeq(String cronSeq) {
		this.cronSeq = cronSeq;
	}

	public String getCronSeqSheetName() {
		return cronSeqSheetName;
	}

	public void setCronSeqSheetName(String cronSeqSheetName) {
		this.cronSeqSheetName = cronSeqSheetName;
	}

	public String getCronSeqClassName() {
		return cronSeqClassName;
	}

	public void setCronSeqClassName(String cronSeqClassName) {
		this.cronSeqClassName = cronSeqClassName;
	}

	public String getRunDuration() {
		return runDuration;
	}

	public void setRunDuration(String runDuration) {
		this.runDuration = runDuration;
	}

	public String getJiraTicket() {
		return jiraTicket;
	}

	public void setJiraTicket(String jiraTicket) {
		this.jiraTicket = jiraTicket;
	}

	public String getConfluenceUrl() {
		return confluenceUrl;
	}

	public void setConfluenceUrl(String confluenceUrl) {
		this.confluenceUrl = confluenceUrl;
	}

}
