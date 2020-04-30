package com.hcl.usf.dto;

import java.util.Collections;
import java.util.List;

/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class ReportsDto {
	
	private String senariosName="";
	private String status="";
	private  List<String> stepStatus=Collections.emptyList();
	private String tcName="";
	private List<String> runDate=Collections.emptyList();
	private List<String> testData=Collections.emptyList();
	private List<String> steps=Collections.emptyList();
	private  List<String> details=Collections.emptyList();
	private String browser="";
	//
	private String tcId;
	private String totalPass;
	private String totalFail;
	private String totalInfo;
	private String sprint;
	private String userStory;
	//
	private String totalSkip;
	private String totalTc;
	//
	private String totalPassSteps;
	private String totalFailSteps;
	private String totalSteps;
    private String dummyData;
    
    private String url="";
    private String envStatus="";
   
    
	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getTotalPassSteps() {
		return totalPassSteps;
	}
	public void setTotalPassSteps(String totalPassSteps) {
		this.totalPassSteps = totalPassSteps;
	}
	public String getTotalFailSteps() {
		return totalFailSteps;
	}
	public void setTotalFailSteps(String totalFailSteps) {
		this.totalFailSteps = totalFailSteps;
	}
	public String getSprint() {
		return sprint;
	}
	public void setSprint(String sprint) {
		this.sprint = sprint;
	}
	public String getUserStory() {
		return userStory;
	}
	public void setUserStory(String userStory) {
		this.userStory = userStory;
	}
	public String getSenariosName() {
		return senariosName;
	}
	public void setSenariosName(String senariosName) {
		this.senariosName = senariosName;
	}
	public String getTcName() {
		return tcName;
	}
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	public String getTcId() {
		return tcId;
	}
	public void setTcId(String tcId) {
		this.tcId = tcId;
	}
	public String getTotalPass() {
		return totalPass;
	}
	public void setTotalPass(String totalPass) {
		this.totalPass = totalPass;
	}
	public String getTotalFail() {
		return totalFail;
	}
	public void setTotalFail(String totalFail) {
		this.totalFail = totalFail;
	}
	public String getTotalInfo() {
		return totalInfo;
	}
	public void setTotalInfo(String totalInfo) {
		this.totalInfo = totalInfo;
	}
	public String getTotalSteps() {
		return totalSteps;
	}
	public void setTotalSteps(String totalSteps) {
		this.totalSteps = totalSteps;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalSkip() {
		return totalSkip;
	}
	public void setTotalSkip(String totalSkip) {
		this.totalSkip = totalSkip;
	}
	public String getTotalTc() {
		return totalTc;
	}
	public void setTotalTc(String totalTc) {
		this.totalTc = totalTc;
	}
	public String getDummyData() {
		return dummyData;
	}
	public void setDummyData(String dummyData) {
		this.dummyData = dummyData;
	}
	public List<String> getRunDate() {
		return runDate;
	}
	public void setRunDate(List<String> runDate) {
		this.runDate = runDate;
	}
	public List<String> getTestData() {
		return testData;
	}
	public void setTestData(List<String> testData) {
		this.testData = testData;
	}
	public List<String> getSteps() {
		return steps;
	}
	public void setSteps(List<String> steps) {
		this.steps = steps;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public List<String> getStepStatus() {
		return stepStatus;
	}
	public void setStepStatus(List<String> stepStatus) {
		this.stepStatus = stepStatus;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEnvStatus() {
		return envStatus;
	}
	public void setEnvStatus(String envStatus) {
		this.envStatus = envStatus;
	}
}

