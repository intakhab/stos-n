package com.hcl.usf.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hcl.usf.common.LoadTemplate;
import com.hcl.usf.dto.DocReportsDto;
import com.hcl.usf.dto.TestNameDto;
import com.hcl.usf.util.AppUtil;

/***
 * @author intakhabalam.s@hcl.com
 *
 */
@Service
public class DownloadReportsService {

	static final Logger LOGGER = LoggerFactory.getLogger(DownloadReportsService.class);

	@Autowired
	Environment env;
	
	/**
	 * Generate Reports
	 */
	public boolean generateReport() {
		createReportsDir();
		DocReportsDto xmlReports = AppUtil.convertXMLToObject(DocReportsDto.class, AppUtil.XML_FILE);
		return saveReports(xmlReports,false);
	}
	/**
	 * Generate Reports
	 */
	public String generateRegReport() {
		createReportsDir();
		DocReportsDto xmlReports = AppUtil.convertXMLToObject(DocReportsDto.class, AppUtil.REG_XML_FILE);
		String temp= saveReportsHealth(xmlReports);
		return temp;
	}	
	
	 /***
     * @param xmlReports  {@link DocReportsDto}
     * @return {@link String}
     */
	private boolean saveReports(DocReportsDto xmlReports,boolean isRegression) {
		LoadTemplate template = new LoadTemplate(env.getProperty("custom.reports.template"));
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("highlevel", getHighLevelReport(xmlReports));
		replacements.put("scriptwise", getScriptWiseReports(xmlReports));
		replacements.put("lowlevel", getLowLevelReports(xmlReports));
		String tdy = String.valueOf(new Date()) + "  ( " + AppUtil.getIPHostName() + " )";
		replacements.put("today", tdy);
		String message = template.getTemplate(replacements);
		return saveReportTemplate(message,isRegression);
	}
	
	 /***
     * @param xmlReports  {@link DocReportsDto}
     * @return {@link String}
     */
	private String saveReportsHealth(DocReportsDto xmlReports) {
		LoadTemplate template = new LoadTemplate(env.getProperty("custom.reports.health.template"));
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("highlevel", getHighLevelReport(xmlReports));
		replacements.put("scriptwise", getScriptWiseReports(xmlReports));
		//replacements.put("lowlevel", getLowLevelReports(xmlReports));
		String tdy = String.valueOf(new Date()) + "  ( " + AppUtil.getIPHostName() + " )";
		replacements.put("today", tdy);
		
		String message= template.getTemplate(replacements);
		 saveReportTemplate(message,true);
		 return message;
	}

	 /***
     * @param xmlReports  {@link DocReportsDto}
     * @return {@link String}
     */
	private String getHighLevelReport(DocReportsDto xmlReports) {
		int totalSteps = 0;
		int passCount = 0;
		int failCount = 0;
		int passStatus = 0;
		int failStatus=0;
		int totalStatus = 0;
		for (TestNameDto d : xmlReports.getTestname()) {
			if("PASS".equalsIgnoreCase(d.getStatus())) {
			    passCount ++;
			}else if("FAIL".equalsIgnoreCase(d.getStatus())) {
				failCount ++;
			}
			passCount += Integer.valueOf(d.getTotalpass());
			failCount += Integer.valueOf(d.getTotalfail());
			totalSteps = passCount + failCount;
			if("PASS".equalsIgnoreCase(d.getStatus())) {
				passStatus++;
			}else if("FAIL".equalsIgnoreCase(d.getStatus())) {
				failStatus ++;
			}

		}
		totalStatus=passStatus+failStatus;
		StringBuilder sb = new StringBuilder("");
							sb.append("<tr>\r\n" + ""
				+ "					   <td>PASS</td>\r\n" + "	"
				+ "				       <td>" + passStatus +"</td>\r\n" + ""
				+ "					   <td>" + passCount + "</td>\r\n" + ""
				+ "					</tr>\r\n"
				+ "					<tr>\r\n" + "			"
				+ "						<td>FAIL</td>\r\n"
				+ "						<td>" + failStatus + "</td>\r\n" + ""
				+ "						<td>" + failCount + "</td>\r\n" + "	"
				+ "				    </tr>\r\n" + ""
				+ "					<tr>\r\n"
				+ "						<td>TOTAL</td>\r\n" + "	"
				+ "					   <td>" + totalStatus + "</td>\r\n"
				+ "						<td>" + totalSteps + "</td>\r\n" + ""
				+ "					</tr>");
		return sb.toString();
	}
	 /***
     * @param xmlDocReports {@link DocReportsDto}
     * @return {@link String}
     */
	private String getScriptWiseReports(DocReportsDto xmlDocReports) {
		StringBuilder sb = new StringBuilder("");
		int i=1;
		for (TestNameDto d : xmlDocReports.getTestname()) {
			    String status="SKIP";
			    String statusEnv="UP";
			    if("PASS".equalsIgnoreCase(d.getStatus())) {
			    	status="<span style='background-color:green'><b>PASS</b></span>";
			    }else if("FAIL".equalsIgnoreCase(d.getStatus())) {
			    	status="<span style='background-color:red'><b>FAIL</b></span>";
			    }else {
			    	status="<span style='background-color:cyan'><b>SKIP</b></span>";
			    }
			    
			    if("UP".equalsIgnoreCase( d.getEnvstatus())) {
			    	statusEnv="<span style='background-color:green'><b>UP</b></span>";
			    }else if("DOWN".equalsIgnoreCase(d.getEnvstatus())) {
			    	statusEnv="<span style='background-color:red'><b>DOWN</b></span>";
			    }else {
			    	statusEnv="<span style='background-color:CYAN'><b>SKIP</b></span>";
			    }
								sb.append("<tr>\r\n" + ""
					+ "						<td>" + i+ "</td>\r\n"
					+ "						<td>" + d.getTestscenario() + "</td>\r\n"
					+ "						<td>" + d.getBrowser() + "</td>\r\n"
					+ "						<td>" + d.getUrl() + "</td>\r\n"
					+ "						<td>"+ status + "</td>\r\n" + ""
					+ "						<td>" + d.getTotalsteps() + "</td>\r\n"
					+ "						<td>" + d.getTotalpass() + "</td>\r\n"
					+ "						<td>" + d.getTotalfail() + "</td>\r\n"
					+ "						<td >" + statusEnv + "</td>\r\n"
					+ "					</tr>");
								i++;
		}
		return sb.toString();
	}
	 /***
     * @param xmlDocReports  {@link DocReportsDto}
     * @return {@link String}
     */
	private String getLowLevelReports(DocReportsDto xmlDocReports) {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (TestNameDto d : xmlDocReports.getTestname()) {
                
				sb.append("<tr>\r\n" + ""
					+ "<td>" + i + "</td>\r\n" + ""
					+ "<td>"+ d.getTestscenario() + "</td>\r\n" + "						"
					+ "<td>" + d.getBrowser() + "</td>\r\n"
					+ "						<td>\r\n"
					+ "							<table class='table table-bordered table-sm'>\r\n"
					+ "							 <tbody>\r\n" + getLowLevelDescriptIOn(d)
					+ "							</tbody>\r\n" + "							</table>\r\n"
					+ "						</td>\r\n" + "	"
					+ "				</tr>");

			i++;

		}
		return sb.toString();
	}
     /***
      * @param steps
      * @return {@link String}
      */
	private String getLowLevelDescriptIOn(TestNameDto steps) {
		StringBuilder sb = new StringBuilder("");
		 int size = 0;
		 if(steps.getStepsdate()!=null) {
			 size= steps.getStepsdate().getName().length;
		 }
		if(size>0) { 
			for (int k = 0; k < size; k++) {
				 String status="SKIP";
				    if("PASS".equalsIgnoreCase(steps.getStepstatus().getName()[k])) {
				    	status="<span style='color:green'><b>PASS</b></span>";
				    }else if("FAIL".equalsIgnoreCase(steps.getStepstatus().getName()[k])) {
				    	status="<span style='color:red'><b>FAIL</b></span>";
				    }else {
				    	status="<span style='color:cyan'><b>SKIP</b></span>";
				    }
						sb.append("<tr>\r\n"
						+ "	 				<td width='125px'>" + steps.getStepsdate().getName()[k] + "</td>\r\n"
						+ "					<td width='600px'>" + steps.getStepsinfo().getName()[k] + "</td>\r\n"
						+ "					<td width='300px'>" + steps.getStepsdetails().getName()[k] + "</td>\r\n"
						+ "					<td width='40px'>" + status + "</td>\r\n" 
						+ "				</tr>");
			}
		}else {
				sb.append("<tr>\r\n" 
					+ "	 				<td width='120px'>-</td>\r\n"
					+ "					<td  width='600px'>-</td>\r\n"
					+ "					<td width='300px'>-</td>\r\n"
					+ "					<td width='40px'><span style='color:cyan'><b>SKIP</b></span></td>\r\n" 
					+ "				</tr>");
		}
		return sb.toString();
	}

	/**
	 * @param reportTemplate
	 *            {@link String}
	 */
	private boolean saveReportTemplate(String reportTemplate,boolean isRegression) {
		createReportsDir();
		File file=Paths.get(env.getProperty("default.ecom.reports.path") +AppUtil.REPORTS_OUTPUT_NAME).toFile();
		if(isRegression) {
			file=Paths.get(env.getProperty("default.ecom.reports.path") +AppUtil.REG_REPORTS_OUTPUT_NAME).toFile();
		}
		try (PrintWriter reportWriter = new PrintWriter(new BufferedWriter(
				new FileWriter(file)))) {
			reportWriter.println(reportTemplate);
			reportWriter.flush();
			reportWriter.close();
			LOGGER.info("Saving consolidate report(s)=>"+file);
			return true;
		} catch (Exception e) {
			LOGGER.error("Problem saving template", e);
			return false;
		}
	}
	
	private void createReportsDir() {
		if(!Paths.get(env.getProperty("default.ecom.reports.path")).toFile().exists()) {
			LOGGER.info("Directory "+env.getProperty("default.ecom.reports.path") +" is not present, creating it");
			Paths.get(env.getProperty("default.ecom.reports.path")).toFile().mkdir();
		}
	}
}
