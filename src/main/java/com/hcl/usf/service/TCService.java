package com.hcl.usf.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hcl.usf.domain.GroupSuite;
import com.hcl.usf.domain.GroupSuiteTC;
import com.hcl.usf.domain.RegisterTC;
import com.hcl.usf.domain.ReportsTC;
import com.hcl.usf.domain.TCSettings;
import com.hcl.usf.dto.CommonDto;
import com.hcl.usf.repo.GroupSuiteRepo;
import com.hcl.usf.repo.GroupSuiteTCRepo;
import com.hcl.usf.repo.RegisterTCRepo;
import com.hcl.usf.repo.ReportsTCRepo;
import com.hcl.usf.repo.TCSettingsRepo;
import com.hcl.usf.util.AppUtil;

/***
 * @author intakhabalam.s@hcl.com
 * Test Service which serve all crud operation
 */
@Service
public class TCService {

	@Autowired
	private RegisterTCRepo registerTCRepo;
	@Autowired
	private TCSettingsRepo tcSettingsRepo;
	@Autowired
	private ReportsTCRepo reportsTCRepo;
	@Autowired
	private GroupSuiteTCRepo groupSuiteTCRepo;
	@Autowired
	private GroupSuiteRepo groupSuiteRepo;

	/***
	 * @param dto {@link CommonDto}
	 */
	public void saveGroupSuite(CommonDto dto) {
		GroupSuite groupSuite = new GroupSuite();
		groupSuite.setGroupName(dto.getGroupname());
		//
		GroupSuite gt=groupSuiteRepo.findBygroupName(dto.getGroupname());
		if( gt!=null && gt.getId()!=null) {
			groupSuite.setGroupName(gt.getGroupName());
			groupSuite.setId(gt.getId());
			GroupSuiteTC child = new GroupSuiteTC();
			child.setGroupSuite(groupSuite);
			groupSuiteTCRepo.delete(child);
		}
	    groupSuite = groupSuiteRepo.save(groupSuite);
	    if(dto.getSelectedGroupTestName()!=null) {
		 String[] arrys = dto.getSelectedGroupTestName().split(",");
			for (String s : arrys) {
				GroupSuiteTC groupSuiteTC = new GroupSuiteTC();
				groupSuiteTC.setGroupSuite(groupSuite);
				groupSuiteTC.setTcName(s.trim());
				groupSuiteTCRepo.save(groupSuiteTC);
			}
	    }else{
	    	//Presume suite is deleted
	    	if(gt!=null) {
			List<GroupSuiteTC> gtc=groupSuiteTCRepo.findBygroupId(gt.getId());
	    	  groupSuiteTCRepo.deleteAll(gtc);
	    	}
	    	groupSuiteRepo.delete(groupSuite);
	    }
	}

	/***
	 * @return List {@link List}
	 */
	public List<String> getGroupByNameList() {
		List<String> groupList = new ArrayList<>();
		groupSuiteRepo.findAll().forEach(names -> groupList.add(names.getGroupName()));
		return groupList;
	}

	public Long saveReport(ReportsTC reportsTC) {
		return reportsTCRepo.save(reportsTC).getId();
	}
	/***
	 * @param groupId {@link Long}
	 * @return List {@link List}
	 */
	public List<String> getGroupListByGroupId(Long groupId) {
		List<String> groupList = new ArrayList<>();
		groupSuiteTCRepo.findBygroupId(groupId).forEach(names -> {
			groupList.add(String.valueOf(names.getId()));
		});
		return groupList;
	}

	/***
	 * @param groupName {@link String}
	 * @return List {@link List}
	 */
	public List<String> getGroupListByGroupName(String groupName) {
		List<String> groupList = new ArrayList<>();
		groupSuiteTCRepo.findBygroupName(groupName).forEach(names -> {
			groupList.add(names.getTcName());
		});
		return groupList;
	}
	/***
	 * 
	 * @param groupName {@link String}
	 * @return List {@link List}
	 */
	public List<String> getGroupListByGroupNameWithId(String groupName) {
		List<String> groupList = new ArrayList<>();
		groupSuiteTCRepo.findBygroupName(groupName).forEach(names -> {
			groupList.add(names.getId()+"-"+names.getTcName());

		});
		return groupList;
	}

	/***
	 * @return {@link List}
	 */
	public List<CommonDto> groupsList() {
		List<GroupSuite> grpList = StreamSupport.stream(groupSuiteRepo.findAll().spliterator(), false)
				.collect(Collectors.toList());
		List<CommonDto> cdtoList = new ArrayList<>();
		for (GroupSuite grp : grpList) {
			CommonDto cdto = new CommonDto();
			cdto.setGroupId(String.valueOf(grp.getId()));
			cdto.setGroupname(grp.getGroupName());
			cdtoList.add(cdto);
		}
		return cdtoList;
	}

	/***
	 * 
	 * @param dto
	 */
	public ReportsTC saveOrUpdateRuningTC(CommonDto dto) {
		RegisterTC entity = new RegisterTC();
		ReportsTC childEntity = new ReportsTC();
		if (dto.getRunTcId() != null && !dto.getRunTcId().isEmpty()) {
			childEntity.setId(Long.valueOf(dto.getRunTcId()));
		}
		childEntity.setBrowserName(dto.getBrowserType());
		childEntity.setCustomerNumber(dto.getCustomerId());
		childEntity.setDepartment(dto.getDepartmentId());
		childEntity.setEnvType(dto.getEnvType());
		try {
			int lenght = dto.getEnvUrl().length();
			String url = "";
			if (lenght > 253) {
				url = dto.getEnvUrl().substring(0, 253);
			} else {
				url = dto.getEnvUrl();
			}
			childEntity.setEnvURL(url);
		} catch (Exception e) {

		}
		childEntity.setRunningTime(dto.getTcRunTime());
		childEntity.setReasonFail(dto.getReasonFail());
		childEntity.setRunStatus(dto.getRunStatus());
		childEntity.setReportsPath(dto.getReportsPath());
		childEntity.setTotalSteps(dto.getTotalSteps());
		childEntity.setPassSteps(dto.getPassSteps());
		childEntity.setFailSteps(dto.getFailSteps());
		childEntity.setHostIpName(AppUtil.getIPHostName());
		childEntity.setRunDuration(dto.getRunDuration());
		childEntity.setJiraTicketKey(dto.getJiraTicket());
		childEntity.setConfluenceUrl(dto.getConfluenceUrl());
		entity.setId(Long.valueOf(dto.getTcId()));
		childEntity.setRegisterTC(entity);
		return reportsTCRepo.save(childEntity);
	}

	/****
	 * @return {@link List}
	 */
	public List<CommonDto> loadReportsTC() {
		PageRequest pa = PageRequest.of(0, 100);
		Iterable<ReportsTC> itbl = reportsTCRepo.loadReportsTC(pa);
		List<ReportsTC> arrayList = StreamSupport.stream(itbl.spliterator(), false)
				.sorted((o2, o1) -> o1.getId().compareTo(o2.getId()))
				.collect(Collectors.toList());

		List<CommonDto> list = new ArrayList<>();
		for (ReportsTC reg : arrayList) {
			CommonDto dt = new CommonDto();
			dt.setRunTcId(String.valueOf(reg.getId()));
			dt.setRunStatus(reg.getRunStatus());
			dt.setTcRunTime(reg.getRunningTime());
			dt.setRunDuration(reg.getRunDuration());
			dt.setTcName(reg.getRegisterTC().getTcName());
			dt.setTcId(String.valueOf(reg.getRegisterTC().getId()));
			dt.setEnvType(reg.getEnvType());
			dt.setReportsPath(reg.getReportsPath());
			dt.setBrowserType(AppUtil.capitalizeFirstLetter(reg.getBrowserName()));
			dt.setCustomerId(reg.getCustomerNumber());
			dt.setDepartmentId(reg.getDepartment());
			dt.setReasonFail(reg.getReasonFail());
			dt.setTcStory(reg.getRegisterTC().getTcStory());
			dt.setTcVersion(reg.getRegisterTC().getTcVersion());
			dt.setTotalSteps(reg.getTotalSteps());
			dt.setPassSteps(reg.getPassSteps());
			dt.setFailSteps(reg.getFailSteps());
			dt.setJiraTicket(reg.getJiraTicketKey());
			dt.setHost(reg.getHostIpName());
			dt.setEnvUrl(reg.getEnvURL());
			dt.setConfluenceUrl(reg.getConfluenceUrl());
			list.add(dt);
		}
		return list;
	}
	/****
	 * @return {@link List}
	 */
	public CommonDto loadReportsById(Long id) {
		ReportsTC reg = reportsTCRepo.findById(id).get();
			CommonDto dt = new CommonDto();
			dt.setRunTcId(String.valueOf(reg.getId()));
			dt.setRunStatus(reg.getRunStatus());
			dt.setTcRunTime(reg.getRunningTime());
			dt.setRunDuration(reg.getRunDuration());
			dt.setTcName(reg.getRegisterTC().getTcName());
			dt.setTcId(String.valueOf(reg.getRegisterTC().getId()));
			dt.setEnvType(reg.getEnvType());
			dt.setReportsPath(reg.getReportsPath());
			dt.setBrowserType(AppUtil.capitalizeFirstLetter(reg.getBrowserName()));
			dt.setCustomerId(reg.getCustomerNumber());
			dt.setDepartmentId(reg.getDepartment());
			dt.setReasonFail(reg.getReasonFail());
			dt.setTcStory(reg.getRegisterTC().getTcStory());
			dt.setTcVersion(reg.getRegisterTC().getTcVersion());
			dt.setTotalSteps(reg.getTotalSteps());
			dt.setPassSteps(reg.getPassSteps());
			dt.setFailSteps(reg.getFailSteps());
			dt.setJiraTicket(reg.getJiraTicketKey());
			dt.setConfluenceUrl(reg.getConfluenceUrl());
			dt.setHost(reg.getHostIpName());
			dt.setEnvUrl(reg.getEnvURL());
			//
		return dt;
	}
	
	/****
	 * @return {@link List}
	 */
	public List<CommonDto> loadReportsPagination(int min, int max) {
		PageRequest pa = PageRequest.of(min, max);
		Iterable<ReportsTC> itbl = reportsTCRepo.loadReportsTC(pa);
		List<ReportsTC> arrayList = StreamSupport.stream(itbl.spliterator(), false)
				.sorted((o2, o1) -> o1.getId().compareTo(o2.getId()))
				.collect(Collectors.toList());

		List<CommonDto> list = new ArrayList<>();
		for (ReportsTC reg : arrayList) {
			CommonDto dt = new CommonDto();
			dt.setRunTcId(String.valueOf(reg.getId()));
			dt.setRunStatus(reg.getRunStatus());
			dt.setTcRunTime(reg.getRunningTime());
			dt.setTcName(reg.getRegisterTC().getTcName());
			dt.setRunDuration(reg.getRunDuration());
			dt.setTcId(String.valueOf(reg.getRegisterTC().getId()));
			dt.setEnvType(reg.getEnvType());
			dt.setReportsPath(reg.getReportsPath());
			dt.setBrowserType(AppUtil.capitalizeFirstLetter(reg.getBrowserName()));
			dt.setCustomerId(reg.getCustomerNumber());
			dt.setDepartmentId(reg.getDepartment());
			dt.setReasonFail(reg.getReasonFail());
			dt.setTcStory(reg.getRegisterTC().getTcStory());
			dt.setTcVersion(reg.getRegisterTC().getTcVersion());
			dt.setTotalSteps(reg.getTotalSteps());
			dt.setPassSteps(reg.getPassSteps());
			dt.setFailSteps(reg.getFailSteps());
			dt.setJiraTicket(reg.getJiraTicketKey());
			dt.setHost(reg.getHostIpName());
			dt.setConfluenceUrl(reg.getConfluenceUrl());
			dt.setEnvUrl(reg.getEnvURL());

			list.add(dt);
		}
		return list;
	}

	/***
	 * @param dto {@link CommonDto}
	 */
	public void registerTC(CommonDto dto) {
		RegisterTC registerTC = new RegisterTC();
		if (dto.getTcId() != null && !dto.getTcId().isEmpty()) {
			registerTC.setId(Long.valueOf(dto.getTcId()));
		}
		registerTC.setTcName(dto.getTcName());
		registerTC.setTcStory(dto.getTcStory());
		registerTC.setTcSheetName(dto.getTcSheetName());
		registerTC.setTcRegDate(AppUtil.currentTimeDDMMYYYY());
		registerTC.setTcVersion(dto.getTcVersion());
		registerTC.setTcRegNote(dto.getTcRegNote());
		registerTCRepo.save(registerTC);

	}

	/***
	 * @param id {@link String}
	 * @return CommonDto 
	 */
	public CommonDto loadRegisteredClassById(String id) {
		RegisterTC rt = registerTCRepo.findById(Long.valueOf(id)).get();
		CommonDto cd = new CommonDto();
		cd.setTcId(String.valueOf(rt.getId()));
		cd.setTcName(rt.getTcName());
		cd.setTcStory(rt.getTcStory());
		cd.setTcSheetName(rt.getTcSheetName());
		cd.setTcVersion(rt.getTcVersion());
		cd.setTcRegDate(rt.getTcRegDate());
		cd.setTcRegNote(rt.getTcRegNote());
		return cd;
	}
	
    /***
     * 
     * @param tcName
     * @return
     */
	public CommonDto findByTCName(String tcName) {
		RegisterTC rt = registerTCRepo.findByTestName(tcName);
		CommonDto cd = new CommonDto();
		cd.setTcId(String.valueOf(rt.getId()));
		cd.setTcName(rt.getTcName());
		cd.setTcStory(rt.getTcStory());
		cd.setRunTcId(String.valueOf(rt.getId()));// Added Intakhab
		cd.setTcSheetName(rt.getTcSheetName());
		cd.setTcVersion(rt.getTcVersion());
		cd.setTcRegDate(rt.getTcRegDate());
		cd.setTcRegNote(rt.getTcRegNote());
		return cd;
	}
	
	/***
	 * @param {@link String} id
	 * @return {@link RegisterTC} RegisterTC
	 */
	public ReportsTC findReportsById(Long id) {
		return reportsTCRepo.findById(id).get();
	}

	/***
	 * 
	 * @return
	 */
	public List<CommonDto> loadRegisteredTC() {
		List<CommonDto> commonDtoList = new ArrayList<CommonDto>();

		Iterable<RegisterTC> itr = registerTCRepo.findAll();
		for (RegisterTC rt : itr) {
			CommonDto cd = new CommonDto();
			cd.setTcId(String.valueOf(rt.getId()));
			cd.setTcName(rt.getTcName());
			cd.setTcStory(rt.getTcStory());
			cd.setTcSheetName(rt.getTcSheetName());
			cd.setTcVersion(rt.getTcVersion());
			cd.setTcRegDate(rt.getTcRegDate());
			cd.setTcRegNote(rt.getTcRegNote());
			commonDtoList.add(cd);
		}
		return commonDtoList;
	}

	/**
	 * 
	 * @param parmId 
	 */
	public void deleteById(String parmId) {
		registerTCRepo.deleteById(Long.valueOf(parmId));
	}
	/**
	 * 
	 * @param parmId 
	 */
	public void deleteReportsById(String parmId,String path) {
		try {
			FileUtils.forceDelete(Paths.get(path).toFile());
		} catch (IOException e) {
			Paths.get(path).toFile().delete();
		}
		reportsTCRepo.deleteById(Long.valueOf(parmId));
	}
   /***
    * 
    * @param ts  {@link CommonDto}
    * @return {@link TCSettings}
    */
	public TCSettings saveUpdateTcSettings(CommonDto ts) {
		TCSettings tcs = new TCSettings();
		if (ts.getSettingsId() != null && !ts.getSettingsId().isEmpty()) {
			tcs.setId(Long.valueOf(ts.getSettingsId()));
		}
		tcs.setEnvUserName(ts.getAppUserId());
		tcs.setEnvUserPass(ts.getAppUserPass());
		tcs.setBrowserType(ts.getBrowserType());
		tcs.setEnvURL(ts.getEnvUrl());
		tcs.setDataPath(ts.getDataPath());
		tcs.setEnvType(ts.getEnvType());
		tcs.setEnableMail(String.valueOf(ts.getEnableMail()));
		tcs.setDebugMail(String.valueOf(ts.getDebugMail()));
		tcs.setHost(ts.getHost());
		tcs.setPort(ts.getPort());
		tcs.setMailUserName(ts.getMailUserName());
		tcs.setMailPassword(ts.getMailPassword());
		tcs.setFromMail(ts.getFromMail());
		tcs.setToWhomEmail(ts.getToWhomEmail());
		tcs.setReportsPath(ts.getReportsPath());
		tcs.setEnableCron(String.valueOf(ts.isEnableCron()));
		tcs.setCronsList(AppUtil.arrayModification(ts.getCronList()));
		tcs.setGroupSuiteList(AppUtil.arrayModification(ts.getGroupList()));
		tcs.setEnableNode(String.valueOf(ts.isEnableNode()));
		tcs.setNodeList(AppUtil.arrayModification(ts.getNodeList()));
		tcs.setCronSeq(ts.getCronSeq());
		tcs.setEnableSeqCron(String.valueOf(ts.isEnableCronSeq()));
		return tcs = tcSettingsRepo.save(tcs);

	}

	/**
	 * This method will load all settings
	 * @return {@link CommonDto}
	 */
	public CommonDto loadTCSettings() {
		Iterable<TCSettings> itr = tcSettingsRepo.findAllSettings();
		for (TCSettings ts : itr) {
			CommonDto tDto = new CommonDto();
			tDto.setSettingsId(String.valueOf(ts.getId()));
			tDto.setAppUserId(ts.getEnvUserName());
			tDto.setAppUserPass(ts.getEnvUserPass());
			tDto.setDataPath(ts.getDataPath());
			tDto.setReportsPath(ts.getReportsPath());
			tDto.setBrowserType(ts.getBrowserType());
			tDto.setEnvType(ts.getEnvType());
			tDto.setEnvUrl(ts.getEnvURL());
			tDto.setEnableMail(Boolean.valueOf(ts.getEnableMail()));
			tDto.setDebugMail(Boolean.valueOf(ts.getDebugMail()));
			tDto.setHost(ts.getHost());
			tDto.setPort(ts.getPort());
			tDto.setMailUserName(ts.getMailUserName());
			tDto.setMailPassword(ts.getMailPassword());
			tDto.setFromMail(ts.getFromMail());
			tDto.setToWhomEmail(ts.getToWhomEmail());
			tDto.setEnableCron(Boolean.valueOf(ts.getEnableCron()));
			tDto.setCronList(ts.getCronsList()!=null?ts.getCronsList().split(","):new String[0]);
			tDto.setGroupList(ts.getGroupSuiteList()!=null?ts.getGroupSuiteList().split(","):new String[0]);
			tDto.setEnableNode(Boolean.valueOf(ts.getEnableNode()));
			tDto.setNodeList(ts.getNodeList()!=null?ts.getNodeList().split(","):new String[0]);//
			String [] arr=ts.getNodeList()!=null?ts.getNodeList().split(","):new String[0];
			tDto.setLeftNodeMenu(createNode(ts.getEnableNode(), arr));
			tDto.setCronSeq(ts.getCronSeq());
			tDto.setEnableCronSeq(Boolean.valueOf(ts.getEnableSeqCron()));
			return tDto;
		}

		return new CommonDto();

	}
	
	/***
	 * <li id="nodeId"><a href="#"><i class="fa fa-cog"></i><span>Node</span>
			     <span class="fa fa-angle-right" style="float: right"></span></a>
				<ul id="menu-academico-sub">
				   <li id="menu-academico-avaliacoes"> <a href="nodeserver"></a></li>
				</ul>
			</li>
	 * @param param
	 * @param arr
	 * @return
	 */
	private String createNode(String param,String arr[]) {
		StringBuilder sb=new StringBuilder("");
		if ("true".equalsIgnoreCase(param) && arr.length > 0) {
			sb.append("<li id=\"nodeId\"><a href=\"#\"><i class=\"fa fa-cog\"></i><span>Node</span>\r\n"
					+ "			     <span class=\"fa fa-angle-right\" style=\"float: right\"></span></a>\r\n"
					+ "				<ul id=\"menu-academico-sub\">");
			int i = 1;
			for (String s : arr) {
				sb.append(
						"<li id=\"menu-academico-avaliacoes\"> <a href='nodeserver?call="+s+"' title='"+s+"'>Child Server " + i + "</a></li>");
				i++;
			}
			sb.append("</ul>\r\n" + "			</li>");
		}
		
		return sb.toString();
	}
	
   /***
    * @return {@link String}
    */
	public String deleteReportsAll() {
		try {
		reportsTCRepo.deleteAll();
		}catch (Exception e) {
			return "error in reports delete";
		}
		return "deleted successfully all reports";
	}
}
