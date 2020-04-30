package com.hcl.usf.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Lazy;

/***
 * @author intakhabalam.s@hcl.com
 */
@javax.persistence.Entity
@Table(name = "TC_SETTINGS")
@Lazy(value = false)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class TCSettings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ENV_URL")
	private String envURL = "";

	@Column(name = "ENV_USER_NAME")
	private String envUserName = "";

	@Column(name = "ENV_USER_PASS")
	private String envUserPass = "";

	@Column(name = "BROWSER_TYPE", length = 10)
	private String browserType = "";

	@Column(name = "ENV_TYPE", length = 5)
	private String envType = "";

	@Column(name = "DATA_PATH")
	private String dataPath = "";

	@Column(name = "REPORTS_PATH")
	private String reportsPath = "";

	@Column(name = "MAIL_ENABLE", length = 5)
	private String enableMail = "false";

	@Column(name = "MAIL_TO", length = 1000)
	private String toWhomEmail = "";

	@Column(name = "MAIL_HOST")
	private String host = "";

	@Column(name = "MAIL_PORT")
	private String port = "";

	@Column(name = "MAIL_USERNAME")
	private String mailUserName = "";

	@Column(name = "MAIL_PASSWORD")
	private String mailPassword = "";

	@Column(name = "MAIL_FROM")
	private String fromMail = "";

	@Column(name = "DEBUG_MAIL", length = 5)
	private String debugMail = "false";

	@Column(name = "CRON_ENABLE", length = 5)
	private String enableCron="false";
	
	@Column(name = "CRON_LIST")
	private String cronsList="";
	
	@Column(name = "GROUP_LIST")
	private String groupSuiteList="";
	
	@Column(name = "NODE_ENABLE", length = 5)
	private String enableNode = "false";
	
	@Column(name = "NODE_LIST")
	private String nodeList="";
	
	@Column(name = "CRON_SEQ_ENABLE", length = 5)
	private String enableSeqCron="false";
	
	@Column(name = "CRON_SEQ_RUN")
	private String cronSeq="";
	
	
	public String getEnableSeqCron() {
		return enableSeqCron;
	}

	public void setEnableSeqCron(String enableSeqCron) {
		this.enableSeqCron = enableSeqCron;
	}

	public String getCronSeq() {
		return cronSeq;
	}

	public void setCronSeq(String cronSeq) {
		this.cronSeq = cronSeq;
	}

	public String getEnableNode() {
		return enableNode;
	}

	public void setEnableNode(String enableNode) {
		this.enableNode = enableNode;
	}

	public String getNodeList() {
		return nodeList;
	}

	public void setNodeList(String nodeList) {
		this.nodeList = nodeList;
	}

	public String getGroupSuiteList() {
		return groupSuiteList;
	}

	public void setGroupSuiteList(String groupSuiteList) {
		this.groupSuiteList = groupSuiteList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnvURL() {
		return envURL;
	}

	public void setEnvURL(String envURL) {
		this.envURL = envURL;
	}

	public String getEnvUserName() {
		return envUserName;
	}

	public void setEnvUserName(String envUserName) {
		this.envUserName = envUserName;
	}

	public String getEnvUserPass() {
		return envUserPass;
	}

	public void setEnvUserPass(String envUserPass) {
		this.envUserPass = envUserPass;
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

	public String getEnableMail() {
		return enableMail;
	}

	public void setEnableMail(String enableMail) {
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

	public String getDebugMail() {
		return debugMail;
	}

	public void setDebugMail(String debugMail) {
		this.debugMail = debugMail;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getReportsPath() {
		return reportsPath;
	}

	public void setReportsPath(String reportsPath) {
		this.reportsPath = reportsPath;
	}

	public String getEnableCron() {
		return enableCron;
	}

	public void setEnableCron(String enableCron) {
		this.enableCron = enableCron;
	}

	public String getCronsList() {
		return cronsList;
	}

	public void setCronsList(String cronsList) {
		this.cronsList = cronsList;
	}

}
