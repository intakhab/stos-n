package com.hcl.usf.config;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import com.hcl.usf.dto.CommonDto;
import com.hcl.usf.service.ConfluenceService;
import com.hcl.usf.service.DownloadReportsService;
import com.hcl.usf.service.EmailService;
import com.hcl.usf.service.JiraClientService;
import com.hcl.usf.service.TCService;
import com.hcl.usf.util.DBConnection;

@Configuration
@PropertySources({
	@PropertySource("classpath:stos-xpath.properties"),
	@PropertySource("classpath:stos-test.properties")

})
@EnableCaching
public class Config {

	@Autowired
	private TCService tcService;
	@Autowired
	private Environment env;
	public String ipAddress;
	public CommonDto cDto;
	@Autowired
	public EmailService emailService;
	@Autowired
	public DownloadReportsService  downloadReportsService;
	@Autowired
	public JiraClientService jiraClientService;
	@Autowired
	public ConfluenceService confluenceService;
	
	@Autowired
	public DBConnection dbConnection;
	
	@PostConstruct
	void initConfig() {
		cDto = tcService.loadTCSettings();
	}

	public TCService getTcService() {
		return tcService;
	}

	public void setTcService(TCService tcService) {
		this.tcService = tcService;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public List<String> getSprintVersions() {
		return Arrays.asList(env.getProperty("sprint.version").split(","));
	}


}
