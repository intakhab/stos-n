package com.hcl.usf.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hcl.usf.util.AppUtil;

/**
 * @author intakhabalam.s@hcl.com
 * @see LoadTemplate
 */
public class LoadTemplate {
	private final Logger logger = LogManager.getLogger(LoadTemplate.class);
	private String templateId;
	private String template;
	private Map<String, String> replacementParams;
	
    /***
     * Parameterize construction
     * @param templateId {@link String}
     * return {@link LoadTemplate}
     */
	public LoadTemplate(String templateId) {
		this.templateId = templateId;
		try {
			this.template = loadTemplate(templateId);
		} catch (Exception e) {
			this.template = "";
		}
	}
	
	
    /***
     * @see LoadTemplate
     * @param templateId
     * @return {@link String}
     * @throws Exception
     */
	private String loadTemplate(String templateId) throws Exception {/*
		File file = Paths.get(templateId).toFile();
		createEmailTemplate(file);//check if templates are available or not
		String content = "";
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			throw new Exception("Could not read template with ID = " + templateId);
		}
		return content;
	 */
		File file = Paths.get("templates/" + templateId).toFile();
		if(!file.exists()) {
			createEmailTemplate(file);
		}
		String content = AppUtil.EMPTY_STR;
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			throw new Exception("Could not read template with ID = " + templateId);
		}
		return content;
	
	}
	/***
	 * @param file {@link File}
	 */
	private void createEmailTemplate(File file) {
		if(!file.isDirectory()) {

	        try {
	        	Path path = Paths.get("templates");
	        	logger.info("Template Folder is not available creating folder :: "+path.toString());
				Files.createDirectories(path);
				AppUtil.writeFile(HTMLTemplate.emailTemplate(),file.getPath());
				
			} catch (IOException e) {
			}

 		}
	}
	
	
    /***
     * @param replacements {@link Map}
     * @return {@link String}
     */
	public String getTemplate(Map<String, String> replacements) {
		String cTemplate = this.getTemplate();

		if (!cTemplate.isEmpty()) {
			for (Map.Entry<String, String> entry : replacements.entrySet()) {
				cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
			}
		}
		
		return cTemplate;
	}

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the replacementParams
	 */
	public Map<String, String> getReplacementParams() {
		return replacementParams;
	}

	/**
	 * @param replacementParams
	 *            the replacementParams to set
	 */
	public void setReplacementParams(Map<String, String> replacementParams) {
		this.replacementParams = replacementParams;
	}

}
