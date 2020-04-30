package com.hcl.usf.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hcl.usf.dto.UploadFileResponseDto;
import com.hcl.usf.service.FileStorageService;
import com.hcl.usf.service.ImportTCService;
import com.hcl.usf.service.TCService;

/***
 * @author intakhabalam.s@hcl.com
 */
@RestController
public class RestUploadController {

	private static final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	TCService tcService;
	@Autowired
	Environment env;
	
	@Autowired
	ImportTCService importTCService;
	

	/***
	 * Uploading file
	 * @param file  {@link MultipartFile} filename
	 * @return link {@link UploadFileResponseDto}
	 */
	@PostMapping("/uploadfile")
	public UploadFileResponseDto uploadFile(@RequestParam("file") MultipartFile file) {

		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadfile/")
				.path(fileName).toUriString();

		return new UploadFileResponseDto(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	
	
	@PostMapping("/uploadexcel")
	@ResponseBody
	public String uploadexcel(@RequestParam("file") MultipartFile file) {
			String fileName = fileStorageService.storeFile(file);
			return importTCService.uploadRegisterTCByExcel(fileName);
			
	}
	

	/***
	 * Download file
	 * @param fileName {@link String}
	 * @param request {@link HttpServletRequest}
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/downloadfile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception ex) {
			logger.error("Could not determine file type-"+ex.getMessage());
		}
		// Fall back to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
