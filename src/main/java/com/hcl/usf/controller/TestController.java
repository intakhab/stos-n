package com.hcl.usf.controller;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.usf.domain.ReportsTC;
import com.hcl.usf.dto.ResponseDto;
import com.hcl.usf.service.TCService;
import com.hcl.usf.service.TestRunFactoryService;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.MultipartUtility;
/***
 * @author intakhabalam.s@hcl.com
 */
@RestController
@CrossOrigin
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
	@Autowired
	private TestRunFactoryService testRunFactoryService;
	@Autowired
	private TCService tcService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	Environment env;

	/***
	 * 
	 * @param id
	 *            {@value String}
	 * @param tcName
	 *            {@value String}
	 */
	/*@GetMapping("/run/{id}")
	public ResponseDto runTest(@PathVariable String id) {
		String[] newId = id.split(",");
		Set<String> testIdSet = new LinkedHashSet<String>(Arrays.asList(newId));
		// Reseting
		AppUtil.TOTAL_TC_COUNT = newId.length;
		AppUtil.testBuilder.setLength(0);
		//
		for (String s : testIdSet) {
			String tcId = s.split("-")[0];
			String tcName = s.split("-")[1];
			logger.info("TC id=>" + tcId);
			logger.info("TC name=>" + tcName);
			ResponseDto res = new ResponseDto();
			res.setTcId(tcId);
			res.setTcName(tcName);
			res = testRunFactoryService.run(res);
		}

		return null;
	}*/
	/***
	 * 
	 * @param id
	 *            {@value String}
	 * @param tcName
	 *            {@value String}
	 */
	@GetMapping("/run/{id}")
	public ResponseDto runTest(@PathVariable String id,HttpServletRequest request) {
		String[] newId = id.split(",");
		String jira=request.getParameter("jiraId");
		String[] jiraId=jira.split(",");
		Set<String> testIdSet = new LinkedHashSet<String>(Arrays.asList(newId));
		Set<String> jiraIdSet = new LinkedHashSet<String>(Arrays.asList(jiraId));
		// Reseting
		AppUtil.TOTAL_TC_COUNT = newId.length;
		AppUtil.testBuilder.setLength(0);
		//
		for (String s : testIdSet) {
			String tcId = s.split("-")[0];
			String tcName = s.split("-")[1];
			boolean willCreateJiraTicket=false;
			for(String jid:jiraIdSet) {
				if(tcName.trim().equalsIgnoreCase(jid.trim())) {
					willCreateJiraTicket=true;
				}
			}
			logger.info("TC id=>" + tcId);
			logger.info("TC name=>" + tcName);
			ResponseDto response = new ResponseDto();
			response.setTcId(tcId);
			response.setTcName(tcName);
			response.setWillCreateJiraTicket(willCreateJiraTicket);
			response = testRunFactoryService.run(response);
		}

		return null;
	}

	/***
	 * 
	 * @param id
	 *            {@value String}
	 * @param tcName
	 *            {@value String}
	 * @return {@value ResponseDto}
	 */
	@GetMapping("/run/{id}/{hostnameNport}")
	public ResponseDto runNodeTest(@PathVariable String id, @PathVariable String hostnameNport) {

		String[] newId = id.split(",");
		Set<String> testIdSet = new LinkedHashSet<String>(Arrays.asList(newId));
		// Reseting
		AppUtil.TOTAL_TC_COUNT = newId.length;
		AppUtil.testBuilder.setLength(0);
		//
		for (String s : testIdSet) {
			String tcId = s.split("-")[0];
			String tcName = s.split("-")[1];
			logger.info("TC id=>" + tcId);
			logger.info("TC name=>" + tcName);
			ResponseDto res = new ResponseDto();
			res.setTcId(tcId);
			res.setTcName(tcName);
			res = testRunFactoryService.run(res);
			callServicesNodeServer(hostnameNport);
		}

		return null;
	}

	/***
	 * This method will call service node server
	 * 
	 * @param hostnameNport
	 *            {@link String}
	 */
	private void callServicesNodeServer(String hostnameNport) {
		logger.info("Calling Node server");
		ObjectMapper mapper = new ObjectMapper();
		String[] spl = hostnameNport.split("-");
		String uri = "http://" + spl[0] + ":" + spl[1];
		String url = uri + "/uploadfile";
		logger.info("Node server URL-" + url);
		String jsonInString2;
		try {
			ReportsTC reportsTC = tcService.findReportsById(AppUtil.REPORT_ID);
			jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reportsTC);
			logger.info("Reports Json Str" + jsonInString2);
			callServer(url, jsonInString2, uri);
		} catch (Exception e) {
			logger.error("Exception callServicesNodeServer{} " + e.getMessage());
		}

	}

	/**
	 * @param requestURL
	 *            {@link String}
	 * @param jsonInString
	 *            {@link String}
	 * @param uri
	 *            {@link String}
	 */
	private void callServer(String requestURL, String jsonInString, String uri) {
		logger.info("Calling Server");
		logger.info("Path-" + AppUtil.REPORTS_PATH);
		String charset = "UTF-8";
		File uploadFile1 = new File(AppUtil.REPORTS_PATH);
		// String requestURL = "http://localhost:6100/uploadfile";
		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset);

			multipart.addHeaderField("User-Agent", "EcomHtml");
			multipart.addHeaderField("Test-Header", "Header-Value");
			multipart.addFormField("keywords", "Java,upload,Spring");
			multipart.addFilePart("file", uploadFile1);
			List<String> response = multipart.finish();
			logger.info("SERVER REPLIED:");
			for (String line : response) {
				logger.info(line);
			}
			postReports(jsonInString, uri);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	private void postReports(String strPost, String uri) {
		logger.info("Posting reports to master node");
		String url = uri + "/call/postreports";
		String result = restTemplate.postForObject(url, strPost, String.class);
		logger.info("Posting result-" + result);

	}
	

     /**
      * 
      * @param strObj
      * @return
      */
	@PostMapping("/call/postreports")
	public String postReports(@RequestBody String strObj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ReportsTC reportsTC = mapper.readValue(strObj, ReportsTC.class);
			tcService.saveReport(reportsTC);
			logger.info("Posting result saved");

		} catch (Exception e) {
			logger.error("Posting result error" + e.getMessage());
		}
		return "OK";
	}

	
	 /**
     * 
     * @param strObj
     * @return
     */
	@PostMapping("/call/poststatus")
	public String postStataus(@RequestBody String strObj) {
		String status="fail";
		try {
			ObjectMapper mapper = new ObjectMapper();
			status = mapper.readValue(strObj, String.class);
			logger.info("Posting status "+status);

		} catch (Exception e) {
			logger.error("Posting result error" + e.getMessage());
		}
		return status;
	}
	/***
	 * @param id {@link String}
	 * @return {@link String} status
	 */
	@GetMapping("/statusfind")
	public String statusFind(@PathVariable String id) {
		
		return null;
	}

}
