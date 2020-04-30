package com.hcl.usf;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.hcl.usf.common.HTMLTemplate;
import com.hcl.usf.util.FileStorageProperties;

/***
 * @author intakhabalam.s@hcl.com
 */
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
@ComponentScan(basePackages = "com.hcl.usf")
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
	FileStorageProperties.class
})

public class STOSAutomationSuite {
	
	private static String[] args;
	public static AtomicInteger atomic = new AtomicInteger(0);
	
	public static void main(String[] args) {
		HTMLTemplate.startBanner();
		STOSAutomationSuite.args = args;
		SpringApplication.run(STOSAutomationSuite.class, args);
	}
	/***
	 * Re-Starting the application
	 * @param ctx as ConfigurableApplicationContext
	 * @see SpringApplication
	 */
	public static void restart(ConfigurableApplicationContext ctx) {
		// close previous context
		ctx.close();
		// and build new one
		SpringApplication.run(STOSAutomationSuite.class, args);
	}

	 @Bean(name="restTemplate")
		public RestTemplate getRestTemplate() {
			return new RestTemplate();
		}
	
}
