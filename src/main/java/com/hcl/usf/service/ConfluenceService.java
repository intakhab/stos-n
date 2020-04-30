package com.hcl.usf.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/***
 * @author intakhabalam.s@hcl.com
 */

@Service
public class ConfluenceService{
	private final static Logger LOGGER = Logger.getLogger("ConfluenceService");
	@Autowired
	Environment env;

	/***
	 * This method will post the attachment in confulence with comment
	 * @param f {@link File}
	 * @param comment {@link String} 
	 * @throws AuthenticationException  
	 * @throws Exception 
	 */
	public String postAttachment(File f, String comment) {
		String confURL=env.getProperty("confu.url")+"pages/viewinfo.action?pageId="+env.getProperty("confu.product.key");
		try {
			if (!f.exists() || !f.canRead()) {
				throw new IOException("Could not access file " + f.getAbsolutePath());
			}
			final FileBody bin = new FileBody(f);
			final StringBody cmt = new StringBody(comment, ContentType.TEXT_HTML);
			final HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addPart("file", bin)
					.addPart("comment", cmt)
					.build();
			String URL = env.getProperty("confu.url") + "/rest/api/content/" + env.getProperty("confu.product.key")
					+ "/child/attachment";
			final HttpPost post = new HttpPost(URL);
			LOGGER.info("Confulence URL=" + URL);
			post.setEntity(reqEntity);
			UsernamePasswordCredentials creds = new UsernamePasswordCredentials(env.getProperty("confu.username"),
					env.getProperty("confu.password"));
			// post.addHeader(BasicScheme.authenticate(new
			// UsernamePasswordCredentials("X3O6026", ""), "UTF-8", false));
			post.addHeader(new BasicScheme(StandardCharsets.UTF_8).authenticate(creds, post, null));
			post.addHeader("X-Atlassian-Token", "no-check");
			// System.out.println("executing request " + post.getRequestLine());
			final CloseableHttpClient httpclient = HttpClients.createDefault();
			final CloseableHttpResponse response = httpclient.execute(post);
			// System.out.println(post.getRequestLine());
			LOGGER.info(response.toString());
			if (response.getStatusLine().getStatusCode() == 404) {
				throw new IOException("Status 404 thrown!");
			}
			LOGGER.info("Posting completed at confluence for file ::"+f.getName());
		} catch (Exception ex) {
			LOGGER.error("Posting file at confulence having issue=>" + ex);
		}
		return confURL;
	}

}
