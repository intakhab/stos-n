package com.hcl.usf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hcl.usf.dto.CommonDto;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.DataTableUtils;
/**
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
@Service
public class ImportTCService {
	private static final Logger logger = LoggerFactory.getLogger(ImportTCService.class);

	
	@Autowired
	TCService tcService;
	@Autowired
	Environment env;
	
	public String uploadRegisterTCByExcel(String fileName) {
		try {
			
			DataTableUtils dataTableUtils = new DataTableUtils(fileName);
			dataTableUtils.setSheetName("Sheet1");
			int row = dataTableUtils.noOfRows();
			for (int i = 2; i <=row; i++) {
				CommonDto dto = new CommonDto();
				dataTableUtils.getRowDataForUpload(i);
				dto.setTcName(dataTableUtils.getData("TCName"));
				dto.setTcStory(dataTableUtils.getData("UserStory"));
				dto.setTcSheetName(dataTableUtils.getData("SheetName"));
				dto.setTcRegDate(AppUtil.currentTimeDDMMYYYY());
				dto.setTcVersion(dataTableUtils.getData("Sprint"));
				dto.setTcRegNote(dataTableUtils.getData("Note"));
				try {
					Thread.sleep(1000);
					tcService.registerTC(dto);
				} catch (Exception e) {
					logger.error("Uploading problem :" + e.getMessage());
				}
			}
		} catch (Exception ex) {
			return "ERROR";
		}
		return "OK";
	}
}
