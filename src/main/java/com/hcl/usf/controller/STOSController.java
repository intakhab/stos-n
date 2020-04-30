package com.hcl.usf.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcl.usf.config.Config;
import com.hcl.usf.dto.CommonDto;
import com.hcl.usf.dto.MailDto;
import com.hcl.usf.dto.ReportsDto;
import com.hcl.usf.dto.UserDto;
import com.hcl.usf.service.AlertService;
import com.hcl.usf.service.ConfluenceService;
import com.hcl.usf.service.DownloadReportsService;
import com.hcl.usf.service.EmailService;
import com.hcl.usf.service.JiraClientService;
import com.hcl.usf.service.TCService;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.DBConnection;
import com.hcl.usf.util.DataTableUtils;

/***
 * @author intakhabalam.s@hcl.com
 */
@Controller
@CrossOrigin
public class STOSController {
	static final Logger LOGGER = LogManager.getLogger("STOSController");
	@Autowired
	private TCService tcService;
	@Autowired
	private AlertService alertService;
	@Autowired
	private Config config;
	@Autowired
	private DBConnection dbConnection;
	@Autowired
	private DownloadReportsService downloadReportsService;
	@Autowired
	private JiraClientService jiraClientService;
	@Autowired
	private ConfluenceService confluenceService;
	
	@Autowired
	private EmailService  emailService;

	/***
	 * For Testing purpose
	 * @return {@link String}
	 */
	@RequestMapping("/run")
    @ResponseBody
	public String startedApps() {
		return "OK";
	}
	
	@RequestMapping("/help")
	public String help() {
		return "help";
	}
	
	
	@RequestMapping("/devopsprocess")
    @ResponseBody
	public String devopsprocess(HttpServletRequest request) {
		String tcId=request.getParameter("tcId");
		CommonDto cdto=tcService.loadReportsById(Long.valueOf(tcId));
		String description="Reporting bug agaist this test [ "+ cdto.getTcName()+ " ] , Attached the report file, kindly refer it.";
		String header=config.getEnv().getProperty("jira.issue.header")+"-"+cdto.getTcName();
		//String comments=cdto.getReasonFail();
		File file=Paths.get(cdto.getReportsPath()).toFile();
		String attachmentPath=file.getAbsolutePath();
		//
		String createJiraTicket=request.getParameter("createJiraTicket");
		String jiraComments=request.getParameter("jiraComments");
		if(jiraComments==null || jiraComments.isEmpty()) {
			jiraComments=cdto.getReasonFail();
		}
		if("true".equalsIgnoreCase(createJiraTicket)) {
			String jiraTicket=jiraClientService.createIssueOnJira(config.getEnv().getProperty("jira.product.key"), 1L, header,
					description, jiraComments, attachmentPath);
			cdto.setJiraTicket(jiraTicket);
			
		}
		String confUploadFile=request.getParameter("confUploadFile");
		String confluenceComment=request.getParameter("confuComment");
		if(confluenceComment==null || confluenceComment.isEmpty()) {
			confluenceComment=cdto.getTcStory();
		}
		if("true".equalsIgnoreCase(confUploadFile)) {
               String confluenceUrl= confluenceService.postAttachment(file, confluenceComment);
               cdto.setConfluenceUrl(confluenceUrl);
		}
		
		String isEmail=request.getParameter("emailCheckId");
		String emailCheckText=request.getParameter("emailCheckText");
		if("true".equalsIgnoreCase(isEmail)) {
			cdto.setToWhomEmail(emailCheckText);
			cdto.setPort(config.cDto.getPort());
			cdto.setHost(config.cDto.getHost());
			cdto.setMailUserName(config.cDto.getMailUserName());
			cdto.setMailPassword(config.cDto.getMailPassword());
			cdto.setFromMail(config.cDto.getFromMail());
			emailService.prepareMail(cdto,getMailDto(cdto));
		}
		tcService.saveOrUpdateRuningTC(cdto);
		return "OK";
	}
	/***
	 * 
	 * @param cdto
	 * @return
	 */
	private MailDto getMailDto(CommonDto cdto) {
		MailDto mailDto=new MailDto();
		mailDto.setFileURL(Paths.get(cdto.getReportsPath()).toFile());
		mailDto.setFrom(config.cDto.getFromMail());
		mailDto.setTo(Arrays.asList(cdto.getToWhomEmail().split(",")));
		mailDto.setHtml(true);
		return mailDto;
	}
	
	@RequestMapping("/callreports")
	public String callGenericReports(HttpServletResponse response) {
		try {
			downloadReportsService.generateReport();
			final String filePath=Paths.get(config.getEnv().getProperty("default.ecom.reports.path") + AppUtil.REPORTS_OUTPUT_NAME).toString();
			return fileDonwload(filePath, response);
		} catch (Exception e) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			return "redirect:/errorpage?msg=" + errorMessage;
		}
	}
	/***
	 * For testing purpose
	 * @return
	 */
	@GetMapping(value = "/call/api/custom")
    @ResponseBody
	public String healthApps() {
		return "Hello this is my body";
	}
	
	@RequestMapping("/dbrun")
    @ResponseBody
	public String dbRun(HttpServletRequest request) {
		String query=request.getParameter("dbQuery");
		String resut=dbConnection.doUpdate(query);
		if("1".equals(resut)) {
			return "Result updated successfully";
		}else if("0".equals(resut)) {
			return "Result not updated";
		}
		else {
			return resut;
		}
		
	}
	/***
	 * home page, showing reports releated things
	 * @param model {@link ModelMap}
	 * @param request {@link HttpServletRequest}
	 * @return {@link String} home url
	 */
	@RequestMapping("/home")
	public String home(ModelMap model,HttpServletRequest request) {
		List<CommonDto> cList = tcService.loadReportsTC();
		CommonDto tDto = tcService.loadTCSettings();
		String node=(String) request.getAttribute("node");
		if(node==null) {
			request.getSession().setAttribute("node", tDto.getLeftNodeMenu());
		}
		ReportsDto report=new ReportsDto();
		int skip=0,pass=0,fail=0;
		for (final CommonDto cdto : cList) {
			if (cdto.getRunStatus().equalsIgnoreCase("pass")) {
				pass ++;
			} else if (cdto.getRunStatus().equalsIgnoreCase("fail")) {
				fail ++;
			} else {
				skip ++;
			}
		}
		report.setTotalTc(String.valueOf(cList.size()));
		report.setTotalPass(String.valueOf(pass));
		report.setTotalFail(String.valueOf(fail));
		report.setTotalSkip(String.valueOf(skip));
		skip=0;pass=0;fail=0;int total=0;//reset
        int recordsPerPage = 10;
        int noOfRecords = cList.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int pageNo=0,recordsOnPage=10;
		String param=request.getParameter("page");
		if(param==null) {
			pageNo=0;
			recordsOnPage=10;
		}else {
			pageNo=Integer.valueOf(param.split("-")[0]);
			recordsOnPage=Integer.valueOf(param.split("-")[1]);
		}
		List<CommonDto> pageList = tcService.loadReportsPagination(pageNo,recordsOnPage);
		
		StringBuilder sb=new StringBuilder("");
		for (final CommonDto cdto : pageList) {
			sb.append("[").append("'"+cdto.getTcRunTime().substring(0, 5).replaceAll("-", "/")+"'").append(",").append(cdto.getPassSteps()).append(",")
					.append(cdto.getFailSteps()).append(",").append(cdto.getTotalSteps()).append("]").append(",");
		}
		if(pageList!=null && pageList.size()>0) {
		sb.toString().substring(0, sb.toString().length()-1);
		}
		report.setTotalPassSteps(String.valueOf(pass));
		report.setTotalFailSteps(String.valueOf(fail));
		report.setTotalSteps(String.valueOf(total));
		model.addAttribute("pi", report);
		model.addAttribute("bar",sb);
		model.addAttribute("noOfPages", noOfPages);
	    model.addAttribute("totalNoOfRecords", noOfRecords);
		return "home";
	}
	/***
	 * @param test
	 *            {@link CommonDto}
	 * @param model
	 *            {@link ModelMap}
	 * @return {@link String}
	 */
	@RequestMapping("/settings")
	public String setting(@ModelAttribute("infoObj") CommonDto test, ModelMap model) {
		CommonDto tDto = tcService.loadTCSettings();
		if (tDto == null) {
			tDto = new CommonDto();
		}
		
		List<CommonDto> glist = tcService.groupsList();
		String[] gpArr = tDto.getGroupList();
		for (int i = 0; i < gpArr.length; i++) {
			CommonDto gdto = null;
			for (final String s : tDto.getGroupList()) {
				gdto = glist.stream().filter(g -> g.getGroupId().contains(s)).findFirst().orElse(null);
			}
			if (gdto != null) {
				tDto.setGroupname(gdto.getGroupname());
				tDto.setGroupId(gdto.getGroupId());
				gpArr[i] = gdto.getGroupname();
			}
		}
		model.addAttribute("infoObj", tDto);
		model.addAttribute("groupList", glist);
		return "settings";
	}

	/***
	 * Status page
	 * @param model {@link Model}
	 * @return {@link String}
	 */
	@RequestMapping("/registertc")
	public String regtestCase(HttpSession session, ModelMap model) {
		CommonDto ct=new CommonDto();
		model.addAttribute("infoObj", ct);
		model.addAttribute("version",config.getSprintVersions());
		return "registertc";
	}

	/**
	 * @param request {@HttpServletRequest}
	 * @param model {@link ModelMap}
	 * @return {@link String}
	 */
	@RequestMapping("/editregistertc")
	public String editregisterTc(HttpServletRequest request, ModelMap model) {
		String parmId = request.getParameter("id");
		CommonDto cd = tcService.loadRegisteredClassById(parmId);
		model.addAttribute("infoObj", cd);
		model.addAttribute("version",config.getSprintVersions());
		return "editregistertc";
	}

	/**
	 * @param request {@link HttpServletRequest}
	 * @param model {@link ModelMap}
	 * @return {@link String}
	 */
	@RequestMapping("/deleteregistertc")
	public String deleteregisterTc(HttpServletRequest request, ModelMap model) {
		String parmId = request.getParameter("id");
		tcService.deleteById(parmId);
		//also make delete all files
		String xmlPath=AppUtil.REPORTS_DIR+AppUtil.REPORTS_INPUT_NAME;
		String htmlPath=AppUtil.REPORTS_DIR+AppUtil.REPORTS_OUTPUT_NAME;
		File xmlFile = Paths.get(xmlPath).toFile(); // XML file to read and rename it
		File htmlFile=Paths.get(htmlPath).toFile();//html file to read and rename it
		if(xmlFile.exists()) {
			final String currTime=AppUtil.currentTimeYYYYMMDD();
			String newXMLFile=xmlPath+"_"+currTime;
			String newHTMLFile=htmlPath+"_"+currTime;
			AppUtil.moveReplaceFile(xmlFile.toString(),newXMLFile);
			AppUtil.moveReplaceFile(htmlFile.toString(),newHTMLFile);
		}
		return "redirect:/showregisteredtc?msg=Successfully deleted TC";
	}
	/**
	 * @param request {@link HttpServletRequest}
	 * @param model {@link ModelMap}
	 * @return {@link String}
	 */
	@RequestMapping("/deletereports")
	public String deleteReports(HttpServletRequest request, ModelMap model) {
		String parmId = request.getParameter("id");
		String path=request.getParameter("path");
		tcService.deleteReportsById(parmId,path);
		return "redirect:/showreports?msg=Successfully deleted reports";
	}

	/**
	 * @param session {@link HttpSession}
	 * @param model {@link ModelMap}
	 * @return {@link String}
	 */
	@RequestMapping("/showregisteredtc")
	public String showRegisteredTc(ModelMap model) {
		List<CommonDto> cList = tcService.loadRegisteredTC();
		List<CommonDto> glist = tcService.groupsList();
		model.addAttribute("groupList", glist);
		model.addAttribute("infoObj", cList);
		return "showregisteredtc";
	}

	/***
	 * @return {@link String}
	 */
	@RequestMapping("/h2database")
	public String h2database() {
		return "h2database";
	}
	
	@RequestMapping("/apirun")
	public String apiRun() {
		return "apirun";
	}
	/***
	 * @return {@link String}
	 */
	@RequestMapping("/ecomdb")
	public String ecomdb() {
		return "ecomdb";
	}
	
	@RequestMapping("/nodeserver")
	public String nodeserver(HttpServletRequest request,Model model) {
		LOGGER.info("Node server client==> {{nodeserver}}");
	    String server=request.getParameter("call");
	    server=server.concat("/call/nodeclient");
	    model.addAttribute("server", server.trim());
		LOGGER.info("Node server calling {{nodeserver page}}");
		return "nodeserver";
	}
	
	
	@RequestMapping("/call/nodeclient")
	public String callnodeserver(Model model,HttpServletRequest request) {
		LOGGER.info("Call node client ==> {{/call/nodeclient}}");
		String uri=request.getParameter("call");
		LOGGER.info("Call node url ==> "+uri);
		List<CommonDto> cList = tcService.loadRegisteredTC();
		List<CommonDto> glist = tcService.groupsList();
		model.addAttribute("groupList",glist);
		model.addAttribute("infoObj",cList);
		model.addAttribute("hostname",AppUtil.getIPHostName().split("-")[1]);
		model.addAttribute("serverport",config.getEnv().getProperty("server.port"));
		model.addAttribute("nodeurl",uri);
		LOGGER.info("Node node client ==> {{nodeclient page}}");
		return "nodeclient";
	}
	
	
	
	/***
	 * @return {@link String}
	 */
	@RequestMapping("/datatables")
	public String datatables(ModelMap model) {
		DataTableUtils data=new DataTableUtils(config.getEnv().getProperty("default.ecom.data.path"));
		Map<String, LinkedHashMap<Integer, LinkedHashMap<String, String>>> map=data.geAllSheetData();
		/*map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));*/
		model.addAttribute("sheetValueList", map);
		return "datatables";
	}
    /**
     * 
     * @param model {@link ModelMap} 
     * @param request {@link HttpServletRequest} 
     * @return  {@link String} 
     */
	@RequestMapping("/updatedatasheet")
    @ResponseBody
	public String updatedatasheet(ModelMap model,HttpServletRequest request) {
		DataTableUtils data=new DataTableUtils(config.getEnv().getProperty("default.ecom.data.path"));
		String sheetName=request.getParameter("sheetName");
		String rowValue=request.getParameter("rowValueId");
		String colValue=request.getParameter("colValueId");
		String updateValue=request.getParameter("newValueId");
		try {
			data.setData(sheetName, Integer.valueOf(rowValue), Integer.valueOf(colValue), updateValue);
		}catch (Exception e) {
			model.addAttribute("msg", alertService.error("Data not saved... Please close StosData sheet and try aagin"));
			return "ERROR "+e;
		}
		Map<String, LinkedHashMap<Integer, LinkedHashMap<String, String>>> map=data.geAllSheetData();
		model.addAttribute("sheetValueList", map);
		model.addAttribute("msg", alertService.sucess("Data updated successfully"));
		return "OK";
	}
	
	/**
	 * @param request {@link HttpServletRequest}
	 * @return {@link String}
	 */
	@RequestMapping("/reportsdelall")
    @ResponseBody
	public String deleteReportsAll(HttpServletRequest request) {
		String resut=tcService.deleteReportsAll();
		return resut;
	}
	/***
	 * @param test {@link CommonDto}
	 * @param model {@link ModelMap}
	 * @return  {@link String}
	 */
	@RequestMapping("/grouptc")
	public String grouptc(@ModelAttribute("infoObj") CommonDto test, ModelMap model) {
		List<CommonDto> glist = tcService.groupsList();
		List<CommonDto> ctList=tcService.loadRegisteredTC();
		List<String> tcList=new ArrayList<>();
		ctList.forEach(tc -> {
			tcList.add(tc.getTcName());
		});
		model.addAttribute("groupList", glist);
		model.addAttribute("tcListReg", tcList);
		return "grouptc";
	}

	/***
	 * @param test {@link CommonDto}
	 * @param model {@link ModelMap}
	 * @return {@link String}
	 */
	@RequestMapping("/savegroup")
	public String saveGroup(@ModelAttribute("infoObj") CommonDto test, ModelMap model) {
		tcService.saveGroupSuite(test);
		List<CommonDto> glist = tcService.groupsList();
		model.addAttribute("groupList", glist);
		List<CommonDto> ctList=tcService.loadRegisteredTC();
		List<String> selectList = test.getSelectedGroupTestName() != null
				? Arrays.asList(test.getSelectedGroupTestName().split(","))
				: Collections.emptyList();
		List<String> tcList=new ArrayList<>();
		ctList.forEach(tc -> {
			tcList.add(tc.getTcName());
		});
		tcList.removeAll(selectList);
		model.addAttribute("tcListReg", tcList);
		model.addAttribute("tcListSelect", selectList);
		model.addAttribute("msg", alertService.sucess("Information saved successfully"));
		return "grouptc";
	}
	/***
	 * @param test {@link String}
	 * @param model  {@link String}
	 * @param request {@link HttpServletRequest}
	 * @return  {@link String}
	 */
	@RequestMapping("/callgrouptc")
	public String selectGroup(@ModelAttribute("infoObj") CommonDto test,
			ModelMap model,HttpServletRequest request) {
		List<CommonDto> glist = tcService.groupsList();
		model.addAttribute("groupList", glist);
		List<CommonDto> ctList=tcService.loadRegisteredTC();
		String groupName=request.getParameter("groupName");
		test.setGroupname(groupName);
		List<String> selectList = tcService.getGroupListByGroupName(groupName);
		List<String> tcList=new ArrayList<>();
		ctList.forEach(tc -> {
			tcList.add(tc.getTcName());
		});
		tcList.removeAll(selectList);
		model.addAttribute("tcListReg", tcList);
		model.addAttribute("tcListSelect", selectList);
		return "grouptc";
	}
	/***
	 * @param request {@link HttpServletRequest}
	 * @return {@link List}
	 */
	@ResponseBody
	@RequestMapping("/ajaxgrouptc")
	public List<String> callgrouptc(HttpServletRequest request) {
		String groupName=request.getParameter("groupName");
		List<String> selectList = tcService.getGroupListByGroupNameWithId(groupName);
		return selectList;
	}
	/**
	 * @param session {@link HttpSession}
	 * @param model {@link ModelMap}
	 * @return {@link String}
	 */
	@RequestMapping("/reportstable")
	public String reportstable(HttpSession session, ModelMap model) {
		List<CommonDto> cList = tcService.loadReportsTC();
		model.addAttribute("infoObj", cList);
		return "reportstable";
	}
	/***
	 * @param session {@link HttpSession}
	 * @param model {@link  ModelMap}
	 * @return String {@link String}
	 */
	@RequestMapping("/showreports")
	public String showAlllReports(HttpSession session, ModelMap model) {
		List<CommonDto> cList = tcService.loadReportsTC();
		model.addAttribute("infoObj", cList);
		return "showreports";
	}

	/***
	 * @param test {@link CommonDto}
	 * @param model {@link ModelMap}
	 * @return {@link String}
	 */
	@RequestMapping("/savesetting")
	public String savesettings(@ModelAttribute("infoObj") CommonDto test, ModelMap model) {
		tcService.saveUpdateTcSettings(test);
		model.addAttribute("msg", alertService.sucess("Information saved successfully"));
		return "settings";
	}
	/**
	 * This will save user
	 * @param user {@link UserDto}
	 * @param result {@link BindingResult}
	 * @param model {@link Model}
	 * @return {@link String}
	 */

	@RequestMapping("/savetestcase")
	public String saveTestCase(@ModelAttribute("infoObj") CommonDto test, HttpServletRequest request, Model model) {
		try {
			tcService.registerTC(test);
			model.addAttribute("msg", alertService.sucess("TC Information saved successfully"));
			model.addAttribute("infoObj", test);
		} catch (Exception e) {
			return "redirect:/errorpage?msg=Saving problem!!!" + e.getMessage();
		}
		return "redirect:/showregisteredtc?msg="+"TC Information saved successfully";
	}

	
	/***
	 * CronValidator
	 * @param req link {@link HttpServletRequest}
	 * @return {@link Boolean}
	 */
	@RequestMapping("/cronvalidator")
    @ResponseBody
	public boolean cronValidator(HttpServletRequest req) {
		String cronStr=req.getParameter("param");
		if(!CronSequenceGenerator.isValidExpression(cronStr)) {
			return false;
		}
		return true;
	}
	/***
	 * @param filePath {@link String}
	 * @param fileType {@link String}
	 * @param request 
	 *            {@link HttpServletRequest}
	 * @param response
	 *            {@link HttpServletResponse}
	 * @return {@link String}
	 */

	@RequestMapping("/repdownload")
	public String download(HttpServletRequest request, HttpServletResponse response) {
		String fileType = request.getParameter("ftype");
		String filePath = request.getParameter("fpath");
		String filetoDownload = "";
		try {
			switch (fileType) {
			case "html":
				filetoDownload = filePath;
				break;
			case "pdf":
				filetoDownload = filePath;
				break;
			case "sbestp":
				filetoDownload="y";
				filePath = config.getEnv().getProperty("stos.doc")+"/"+"STOS - Technical Design Document.docx";
				break;
			case "dbestp":
				filetoDownload="y";
				filePath = config.getEnv().getProperty("stos.doc")+"/"+"Automation_Best_Practices.docx";
				break;	
			case "hrun":
				filetoDownload="y";
				filePath = config.getEnv().getProperty("stos.doc")+"/"+"How_To_Run_Stos.docx";
				break;
			case "oldnew":
				filetoDownload="y";
				filePath = config.getEnv().getProperty("stos.doc")+"/"+"Old_Automation_Framework_STOS.pptx";
				break;	
			case "toptips":
				filetoDownload="y";
				filePath = config.getEnv().getProperty("stos.doc")+"/"+"Top Tips for Test Automation.docx";
				break;		
			default:
				break;
			}
			if (!filetoDownload.isEmpty()) {
				return fileDonwload(filePath, response);
			} else {
				return "redirect:/errorpage?msg=Error in downloading file";
			}
		} catch (Exception e) {
			return "redirect:/errorpage?msg=Error in downloading file";
		}
	}

	/***
	 * This method will download the file
	 * @param fileDir {@link String}
	 * @param response {@link HttpServletResponse}
	 * @return file {@link String}
	 * @throws IOException {@link IOException}
	 */
	public String fileDonwload(String fileDir, HttpServletResponse response) throws IOException {

		File file = Paths.get(fileDir).toFile();
		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			return "redirect:/errorpage?msg=" + errorMessage;
		}
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			mimeType = "text/html";
		}
		response.setContentType(mimeType);
		//response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
		/**
		 * "Content-Disposition : attachment" will be directly download, may provide
		 * save as popup, based on your browser setting
		 */
		response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", file.getName()));
		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		// Copy bytes from source to destination(outputstream in this example), closes
		// both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
		return null;
	}

}
