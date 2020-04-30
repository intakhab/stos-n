/*package com.hcl.usf.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.auth.NTLMEngine;
import org.apache.http.impl.auth.NTLMScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.WinHttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class SharepointUitl {

	private final static Logger LOGGER = Logger.getLogger(SharepointUitl.class.getName());

	
	
	public void aut2() {
		 try {

		        HttpParams params = new BasicHttpParams();
		        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		        DefaultHttpClient httpclient = new DefaultHttpClient(params);

		        //Register JCIF NTLMv2 to manage ntlm auth.
		        httpclient.getAuthSchemes().register("ntlm", new AuthSchemeFactory() {
		            @Override
		            public AuthScheme newInstance(HttpParams hp) {
		                return new NTLMScheme(new NTLMEngine());
		            }
		        });

		        //Provide login/password
		        httpclient.getCredentialsProvider().setCredentials(
		                AuthScope.ANY,
		                new NTCredentials([LOGIN], [PASSWORD], "", [DOMAIN]));
		        //Create HTTP PUT Request       
		        HttpPut request = new HttpPut("http://[server]/[site]/[folder]/[fileName]");
		        request.setEntity(new FileEntity([File]));            

		        return httpclient.execute(request);

		    } catch (IOException ex) {
		      //...
		    }
	}
	public static void upload(final File source, final String destination) {
		CloseableHttpClient httpclient = WinHttpClients.createSystem();
		HttpPut httpRequest = new HttpPut(destination);
		httpRequest.setEntity(new FileEntity(new File(source.getPath())));

		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httpRequest);
			EntityUtils.consume(httpResponse.getEntity());

			if (httpResponse.getStatusLine() != null
					&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
				LOGGER.info("" + httpResponse.getStatusLine());
				LOGGER.info("Upload of " + source.getName() + " via HTTP-Client succeeded.");
			} else if (httpResponse.getStatusLine() != null
					&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				LOGGER.info("" + httpResponse.getStatusLine());
			} else {
				LOGGER.info("Uploading " + source.getName() + " failed.");
				LOGGER.info(httpResponse.getStatusLine().getStatusCode() + ": "
						+ httpResponse.getStatusLine().getReasonPhrase());
			}
		} catch (IOException e) {
			LOGGER.info("" + e);
			LOGGER.info(e.getMessage());
		}
		return;
	}
}
*/