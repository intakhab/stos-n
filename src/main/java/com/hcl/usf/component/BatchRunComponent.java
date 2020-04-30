package com.hcl.usf.component;

import java.time.LocalTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcl.usf.config.Config;
import com.hcl.usf.dto.ResponseDto;
import com.hcl.usf.service.TCService;
import com.hcl.usf.service.TestRunFactoryService;
import com.hcl.usf.util.AppUtil;

@Component
public class BatchRunComponent {
	private static final Logger logger = LogManager.getLogger(BatchRunComponent.class);

	@Autowired
	Config config;
	private String defaultCron = "0 0 0 29 2 ?"; // every 29 of feb Day at midnight
	@Autowired
	private TestRunFactoryService testRunFactoryService;
	@Autowired
	private TCService tcService;

	@Bean
	public String cronJob1() {
		int length = config.cDto.getCronList().length;
		if (length == 1)
			return config.cDto.getCronList()[0];
		else {
			return defaultCron;
		}
	}

	@Bean
	public String cronJob2() {
		int length = config.cDto.getCronList().length;
		if (length == 2)
			return config.cDto.getCronList()[1];
		else {
			return defaultCron;
		}

	}

	@Bean
	public String cronJob3() {
		int length = config.cDto.getCronList().length;
		if (length == 3)
			return config.cDto.getCronList()[2];
		else {
			return defaultCron;
		}

	}
	
	@Bean
	public String cronJobSeq() {
		String cron = config.cDto.getCronSeq();
		if (StringUtils.isNotEmpty(cron))
			return cron;
		else {
			return defaultCron;
		}

	}
	

	@Scheduled(cron = "#{@cronJob1}")
	public void invokecronJob1Run() {
		try {
			if (!config.cDto.isEnableCron()) {
				return;
			}
			final long startTime = System.currentTimeMillis();
			logger.info("=======================================================================");
			logger.info("JOB1 Starting Time [ " + LocalTime.now() + " ]");
			//
			String groupOne = config.cDto.getGroupList()[0];
			List<String> groupnameList = tcService.getGroupListByGroupName(groupOne);
			for (String groupName : groupnameList) {
				commonTestRun(groupName);
			}

			//
			final long endTime = System.currentTimeMillis();
			final double totalTimeTaken = (endTime - startTime) / (double) 1000;
			logger.info("JOB1 Finishing Time [ " + LocalTime.now() + " ] => Total time taken to be completed  [ "
					+ totalTimeTaken + "s ]");
		} catch (Exception e) {
			logger.error("JOB1 Failed " + e.getMessage());

		}

	}

	@Scheduled(cron = "#{@cronJob2}")
	public void invokecronJob2Run() {
		try {
			if (!config.cDto.isEnableCron()) {
				return;
			}
			final long startTime = System.currentTimeMillis();
			logger.info("=======================================================================");
			logger.info("JOB2 Starting Time [ " + LocalTime.now() + " ]");
			//
			String groupTwo = config.cDto.getGroupList()[1];
			callTest(groupTwo);

			final long endTime = System.currentTimeMillis();
			final double totalTimeTaken = (endTime - startTime) / (double) 1000;
			logger.info("JOB2 Finishing Time [ " + LocalTime.now() + " ] => Total time taken to be completed  [ "
					+ totalTimeTaken + "s ]");
		} catch (Exception e) {
			logger.error("JOB2 Failed " + e.getMessage());
		}

	}

	/**
	 * Cron 3
	 */
	@Scheduled(cron = "#{@cronJob3}")
	public void invokecronJob3Run() {
		try {
			if (!config.cDto.isEnableCron()) {
				return;
			}
			final long startTime = System.currentTimeMillis();
			logger.info("=======================================================================");
			logger.info("JOB3 Starting Time [ " + LocalTime.now() + " ]");
			//
			String groupThree = config.cDto.getGroupList()[2];
			callTest(groupThree);
			//
			final long endTime = System.currentTimeMillis();
			final double totalTimeTaken = (endTime - startTime) / (double) 1000;
			logger.info("JOB3 Finishing Time [ " + LocalTime.now() + " ] => Total time taken to be completed  [ "
					+ totalTimeTaken + "s ]");
		} catch (Exception e) {
			logger.error("JOB3 Failed " + e.getMessage());
		}

	}


	/**
	 * @param groupName
	 *            {@link String}
	 */
	private void callTest(String groupName) {
		logger.info("Group name-" + groupName);
		List<String> testList = tcService.getGroupListByGroupNameWithId(groupName);
		for (String test : testList) {
			commonTestRun(test);
		}

	}
	
	
	@Scheduled(cron = "#{@cronJobSeq}")
	public void invokecronSeqRun() {
		try {
			if (!config.cDto.isEnableCronSeq()) {
				return;
			}
			final long startTime = System.currentTimeMillis();
			logger.info("=======================================================================");
			logger.info("JOB SEQ Starting Time [ " + LocalTime.now() + " ]");
			//
			testRunFactoryService.run(config.getEnv().getProperty("healthcheck.test.class"));
			//
			final long endTime = System.currentTimeMillis();
			final double totalTimeTaken = (endTime - startTime) / (double) 1000;
			logger.info("JOB SEQ Finishing Time [ " + LocalTime.now() + " ] => Total time taken to be completed  [ "
					+ totalTimeTaken + "s ]");
		} catch (Exception e) {
			logger.error("JOB SEQ Failed " + e.getMessage());

		}

	}

	/***
	 * @param id
	 *            {@link String}
	 */
	private void commonTestRun(String id) {
		String[] newId = id.split(",");
		AppUtil.TOTAL_TC_COUNT = newId.length;
		AppUtil.testBuilder.setLength(0);
		for (String s : newId) {
			String tcId = s.split("-")[0];
			String tcName = s.split("-")[1];
			logger.info("TC id=>" + tcId);
			logger.info("TC name=>" + tcName);
			ResponseDto res = new ResponseDto();
			res.setTcId(tcId);
			res.setTcName(tcName);
			res = testRunFactoryService.run(res);
		}
	}

}
