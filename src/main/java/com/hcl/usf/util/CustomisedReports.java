package com.hcl.usf.util;

import static java.util.stream.Collectors.toList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

/***
 * @author intakhabalam.s@hcl.com
 *
 */
public class CustomisedReports implements IReporter {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomisedReports.class);
	private static int count = 0;
	private static final String ROW_TEMPLATE = "<tr class=\"%s\">"
			+ "<td>%s</td>"
			/*+ "<td>%s</td>"*/
			+ "<td>%s</td>"
			+ "<td>%s</td>"
			+ "<td>%s</td>"
			+ "</tr>";
   /**	
    * Generate Reports
    */
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		String reportTemplate = initReportTemplate();
		final String body = suites.stream().flatMap(suiteToResults()).collect(Collectors.joining());
		AppUtil.testBuilder.append(body);
		count++;
		if (AppUtil.TOTAL_TC_COUNT == count) {
			LOGGER.info("Total no product:"+count);
			
			saveReportTemplate(outputDirectory, reportTemplate.replaceFirst("</tbody>",
					String.format("%s</tbody>", AppUtil.testBuilder.toString())));
		}
	}

	/***
	 * 
	 * @return
	 */
	private Function<ISuite, Stream<? extends String>> suiteToResults() {
		return suite -> suite.getResults().entrySet().stream().flatMap(resultsToRows(suite));
	}

	/***
	 * 
	 * @param suite
	 * @return
	 */
	private Function<Map.Entry<String, ISuiteResult>, Stream<? extends String>> resultsToRows(ISuite suite) {
		return e -> {
			ITestContext testContext = e.getValue().getTestContext();
			Set<ITestResult> failedTests = testContext.getFailedTests().getAllResults();
			Set<ITestResult> passedTests = testContext.getPassedTests().getAllResults();
			Set<ITestResult> skippedTests = testContext.getSkippedTests().getAllResults();
			String suiteName = suite.getName();
			
			return Stream.of(failedTests, passedTests, skippedTests)

					.flatMap(results -> generateReportRows(e.getKey(), suiteName, results).stream());
		};
	}

	/***
	 * @param testName
	 * @param suiteName
	 * @param allTestResults
	 * @return
	 */
	private List<String> generateReportRows(String testName, String suiteName, Set<ITestResult> allTestResults) {
		return allTestResults.stream().map(testResultToResultRow(testName, suiteName)).collect(toList());
	}

	/***
	 * @param testName
	 * @param suiteName
	 * @return
	 */
	private Function<ITestResult, String> testResultToResultRow(String testName, String suiteName) {
		return testResult -> {
			switch (testResult.getStatus()) {
			case ITestResult.FAILURE:
				return String.format(ROW_TEMPLATE, "danger", testName, testResult.getName(), "FAILED", TimeUnit.MILLISECONDS
						.toSeconds(Long.valueOf(testResult.getEndMillis() - testResult.getStartMillis())));

			case ITestResult.SUCCESS:
				return String.format(ROW_TEMPLATE, "success", testName, testResult.getName(), "PASSED",
						TimeUnit.MILLISECONDS
								.toSeconds(Long.valueOf(testResult.getEndMillis() - testResult.getStartMillis())));

			case ITestResult.SKIP:
				return String.format(ROW_TEMPLATE, "warning", testName, testResult.getName(), "SKIPPED",
						TimeUnit.MILLISECONDS
						.toSeconds(Long.valueOf(testResult.getEndMillis() - testResult.getStartMillis())));

			default:
				return "";
			}
		};
	}

	/***
	 * 
	 * @return
	 */
	private String initReportTemplate() {
		String template = null;
		byte[] reportTemplate;
		try {
			reportTemplate = Files.readAllBytes(Paths.get("templates/reports-template.html"));
			template = new String(reportTemplate, "UTF-8");
		} catch (IOException e) {
			LOGGER.error("Problem initializing template", e);
		}
		return template;
	}

	/**
	 * 
	 * @param outputDirectory
	 * @param reportTemplate
	 */
	private void saveReportTemplate(String outputDirectory, String reportTemplate) {
		Paths.get(outputDirectory).toFile().mkdir();
		try (PrintWriter reportWriter = new PrintWriter(
				new BufferedWriter(new FileWriter(new File(AppUtil.CUSTOM_REPORTS_NAME))))) {
			reportWriter.println(reportTemplate);
			reportWriter.flush();
			reportWriter.close();
		} catch (IOException e) {
			LOGGER.error("Problem saving template", e);
		}
	}
}
