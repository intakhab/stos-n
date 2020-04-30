package com.hcl.usf.service;

import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.TestNG;

import com.hcl.usf.dto.CommonDto;
import com.hcl.usf.dto.ResponseDto;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.TestListener;

@Service
public class TestRunFactoryService {
	private  final static Logger LOG = LogManager.getLogger(TestRunFactoryService.class);
	
	@Autowired
	private TCService tcService;
	/***
	 * 
	 * @param classz
	 */
	private synchronized void initNg(Class<?> classz) {
		LOG.info("Suite starting for :"+classz.getSimpleName());
		TestNG testNG = new TestNG();
		TestListener tLis = new TestListener();
		testNG.addListener(tLis);
		testNG.setUseDefaultListeners(false);
		testNG.setDefaultSuiteName("STOS Suite");
		testNG.setDefaultTestName(classz.getSimpleName());
		testNG.setTestClasses(new Class[] { classz });
		testNG.run();
		LOG.info("Suite finishied");

	}

	/***
	 * 
	 * @param res
	 * @return ResponseDto
	 * 
	 */
	public synchronized ResponseDto run(ResponseDto res) {
		final String testName = res.getTcName();
		try {
			final long startTime = System.currentTimeMillis();
			LOG.info("=======================================================================");
			LOG.info("STOS UI TC ["+testName+"] Starting Time [ " + LocalTime.now() + " ]");
			CommonDto tc=tcService.findByTCName(testName);
			AppUtil.DATE_SHEET_NAME=tc.getTcSheetName();
			AppUtil.TEST_NAME=tc.getTcName();
			AppUtil.WILL_JIRA_TCIKET_CREATE=res.isWillCreateJiraTicket();
			Class<?> clazz= Class.forName("com.hcl.usf.test."+tc.getTcName()).newInstance().getClass();
			initNg(clazz);
			
			final long endTime = System.currentTimeMillis();
			final double totalTimeTaken = (endTime - startTime) / (double) 1000;
			LOG.info("STOS UI TC Finishing Time [ " + LocalTime.now()
					+ " ] => Total time taken to be completed  [ " + totalTimeTaken + " s]");
			
		} catch (Throwable ex) {
			LOG.error("Fatal-" + ex.fillInStackTrace()   +" {} "+ex.getMessage());
			System.err.println("Fatal-" + ex.fillInStackTrace()   +" {} "+ex.getMessage());
		}
		return res;

	}
	
	/***
	 * By Command line this service will run
	 * @param testName
	 */
	public synchronized void run(final String testName) {
		try {
			final long startTime = System.currentTimeMillis();
			LOG.info("=======================================================================");
			LOG.info("STOS Command Line TC ["+testName+"] Starting Time [ " + LocalTime.now() + " ]");
			CommonDto cdto=tcService.findByTCName(testName);
			AppUtil.DATE_SHEET_NAME=cdto.getTcSheetName();
			AppUtil.TEST_NAME=cdto.getTcName();
			Class<?> clazz= Class.forName("com.hcl.usf.test."+cdto.getTcName()).newInstance().getClass();
			initNg(clazz);
			final long endTime = System.currentTimeMillis();
			final double totalTimeTaken = (endTime - startTime) / (double) 1000;
			LOG.info("STOS Finishing Time [ " + LocalTime.now()
					+ " ] => Total time taken to be completed  [ " + totalTimeTaken + "s ]");
			
		} catch (Throwable ex) {
			LOG.error("Fatal-" + ex.fillInStackTrace()   +" {} "+ex.getLocalizedMessage());
			System.err.println("Fatal-" + ex.fillInStackTrace()   +" {} "+ex.getLocalizedMessage());
		}

	}

}
