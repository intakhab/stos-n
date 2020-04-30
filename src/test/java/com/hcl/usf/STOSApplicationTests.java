package com.hcl.usf;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.usf.service.ImportTCService;
import com.hcl.usf.service.JiraClientService;
import com.hcl.usf.service.TestRunFactoryService;
/***
 * 
 * @author intakhabalalam.s@hcl.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class STOSApplicationTests {
	@Autowired
    private TestRunFactoryService service;
	@Autowired
	private JiraClientService jiraClientService;
	@Autowired
	Environment env;
	@Autowired
	ImportTCService  importTCService;
	
	@Test
	public void mockTest() {
		//
		service.run("HomePageOrderingSectionTest");
		//
		/*  
		    DataTableUtils data=new DataTableUtils(env.getProperty("default.ecom.data.path"));
			Map<String, LinkedHashMap<Integer, LinkedHashMap<String, String>>> map=data.geAllSheetData();
			map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
		*/
		
	}	
	@Ignore
	@Test
	public void importRegisterOrder() {
		String fileName="download/RegisterTC_Template.xlsx";
		importTCService.uploadRegisterTCByExcel(fileName);
	}
	//
	@Ignore
	@Test
	public void jiraDeleteTest() {
		jiraClientService.deleteIssue("ECR46-622");
	}	
	
	@Test
	@Ignore
	public void jiraAddTest() {
		String file="C:\\Users\\X3O6026\\eclipse-workspace\\STBuddy\\stos\\reports\\ecom-reports\\Chrome_HomePageOrderingSectionTest_2020.02.14_04-38-39.html";
		jiraClientService.createIssueOnJira("ECR46", 1L, "Testing", "Demo Testing", "Demo Testing...", file);
	}	
}
