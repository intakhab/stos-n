package com.hcl.usf.dto;

/****
 * @author intakhabalam.s@hcl.com
 */
public class TestNameDto {

	private String testscenario;
	private String browser;
	private String url;
	private String totalpass;
	private String totalfail;
	private String totalsteps;
	private String status;
	private DateNameDto stepsdate;
	private StepsDto stepsinfo;
	private DetailsDto stepsdetails;
	private StatusDto stepstatus;
	private String envstatus;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTestscenario() {
		return testscenario;
	}

	public void setTestscenario(String testscenario) {
		this.testscenario = testscenario;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getTotalpass() {
		return totalpass;
	}

	public void setTotalpass(String totalpass) {
		this.totalpass = totalpass;
	}

	public String getTotalfail() {
		return totalfail;
	}

	public void setTotalfail(String totalfail) {
		this.totalfail = totalfail;
	}

	public String getTotalsteps() {
		return totalsteps;
	}

	public void setTotalsteps(String totalsteps) {
		this.totalsteps = totalsteps;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DateNameDto getStepsdate() {
		return stepsdate;
	}

	public void setStepsdate(DateNameDto stepsdate) {
		this.stepsdate = stepsdate;
	}

	public StepsDto getStepsinfo() {
		return stepsinfo;
	}

	public void setStepsinfo(StepsDto stepsinfo) {
		this.stepsinfo = stepsinfo;
	}

	public DetailsDto getStepsdetails() {
		return stepsdetails;
	}

	public void setStepsdetails(DetailsDto stepsdetails) {
		this.stepsdetails = stepsdetails;
	}

	public StatusDto getStepstatus() {
		return stepstatus;
	}

	public void setStepstatus(StatusDto stepstatus) {
		this.stepstatus = stepstatus;
	}

	public String getEnvstatus() {
		return envstatus;
	}

	public void setEnvstatus(String envstatus) {
		this.envstatus = envstatus;
	}

}
