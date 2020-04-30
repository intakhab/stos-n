package com.hcl.usf.util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.hateoas.Link;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFReportsGeneration {

	private static final boolean ScreenshotsToAllApplitoolsReport = true;
	//private static final boolean ScreenshotsToAllStepsInExtentReport = false;
	private static final boolean ScreenshotsToAllStepsInPdfReport = false;
	// Set the Font properties
	private final Font TITLE_FONT = new Font(Font.BOLD, 50F, Font.BOLD, Color.DARK_GRAY);
	private final Font FONT_14BW = new Font(Font.BOLD, 14F, Font.BOLD, Color.WHITE);
	private final Font FONT_11BDARK_GRAY = new Font(Font.BOLD, 8F, Font.BOLD, Color.DARK_GRAY);
	private final Font FONT_10BBLACK = new Font(Font.BOLD, 8f, Font.NORMAL, Color.BLACK);
	private final Font FONT_20BDARK_GRAY = new Font(Font.BOLD, 20f, Font.BOLD, Color.DARK_GRAY);
	private final Font FONT_GREEN = new Font(Font.BOLD, 8f, Font.NORMAL, Color.GREEN);
	private final Font FONT_RED = new Font(Font.BOLD, 8f, Font.NORMAL, Color.RED);
	private PdfPTable rhighLeveSummarytable;
	private PdfPTable rsummarytable;
	private PdfPTable rdetailtable;

	/***
	 * 
	 * @param args {@link String}
	 * @throws DocumentException {@link String}
	 * @throws IOException {@link IOException}
	 * @throws ParseException {@link ParseException}
	 */
	public static void main(String[] args) throws DocumentException, IOException, ParseException {
		String reportTextFileName = "ECOM_TestCase.txt";
		String pdfReportFileName = "ECOM_TestCase.pdf";
		new PDFReportsGeneration().generatePDFReport(pdfReportFileName, reportTextFileName);
	}

	/***
	 * @param pdfReportFileName {@link String}
	 * @param txtFileName {@link String}
	 */
	public void generatePDFReport(String pdfReportFileName, String txtFileName)
			{
		try {
			// Step 1 - Create the document with the size
			Document doc = new Document();
			doc.setPageSize(PageSize.A4);
			// Step 2 - Create PDFWriter instance with the created doc
			PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream(pdfReportFileName));

			// Step 3 - Open the document
			// After you use, document.newPage();, it will be ignored if you
			// don't add any content.
			// So, if you need blank page, then add writer.setPageEmpty(false);
			// right after calling newPage()
			doc.open();
			doc.newPage();
			pdf.setPageEmpty(false);
			// Add the client Image
			Image img = Image.getInstance(System.getProperty("user.dir") + "/lib/Logo.png");
			img.scaleAbsolute(75f, 75f);
			img.setAlignment(Image.MIDDLE);
			doc.add(img);
			// Add the client name
			doc.add(Chunk.NEWLINE);
			doc.add(Chunk.NEWLINE);
			Paragraph clientTitle = new Paragraph(new Phrase(getChunk("US Foods", TITLE_FONT)));
			clientTitle.setAlignment(Element.ALIGN_CENTER);
			doc.add(clientTitle);
			doc.add(Chunk.NEWLINE);

			// Add automation report title6g
			Paragraph reportTitle = new Paragraph(
					new Phrase(getChunk("STOS Automation Execution Report", FONT_20BDARK_GRAY)));
			reportTitle.setAlignment(Element.ALIGN_CENTER);
			doc.add(reportTitle);

			// Add automation report date
			Paragraph reportdate = new Paragraph(
					new Phrase(getChunk("Date: " + getCurrentDateAndTime(), FONT_20BDARK_GRAY)));
			reportdate.setAlignment(Element.ALIGN_CENTER);
			doc.add(reportdate);
			doc.add(Chunk.NEWLINE);
			doc.add(Chunk.NEWLINE);

			Chunk highLevelSummaryHeader = getChunk("Automation Execution", FONT_20BDARK_GRAY);
			highLevelSummaryHeader.setUnderline(0.1f, -2f); // 0.1 thick, -2 y-location

			Paragraph highLevelSummaryParagraph = new Paragraph();
			highLevelSummaryParagraph.setAlignment(Element.ALIGN_CENTER);
			highLevelSummaryParagraph.add(new Phrase(highLevelSummaryHeader));
			doc.add(highLevelSummaryParagraph);
			doc.add(Chunk.NEWLINE);

			float[] rhighLevelSummaryCells = { 2.1f, 2.1f, 2.1f };
			String rhighLevelSummaryTableTitle = "High Level Execution Summary";
			String[] rhighLevelSummaryheader = { "Description", "Test Scenario Count", "Test Case Count" };

			rhighLeveSummarytable = createTable(rhighLevelSummaryCells, rhighLevelSummaryheader,
					rhighLevelSummaryTableTitle);
			System.out.println(txtFileName);
			addHighLevelSummaryCells1(txtFileName);
			doc.add(rhighLeveSummarytable);

			// *****************************************************************************************************************************
			// Add a new page
			doc.newPage();
			pdf.setPageEmpty(false);

			Chunk summaryHeader = getChunk("Automated Test Execution - Script Wise Summary", FONT_20BDARK_GRAY);
			summaryHeader.setUnderline(0.1f, -2f); // 0.1 thick, -2 y-location

			Paragraph summaryParagraph = new Paragraph();
			summaryParagraph.setAlignment(Element.ALIGN_CENTER);
			summaryParagraph.add(new Phrase(summaryHeader));
			doc.add(summaryParagraph);
			doc.add(Chunk.NEWLINE);

			float[] rsummaryCells = { 3.0f, 1.2f, 1.2f, 1.8f, 1.2f, 1.2f };
			String rsummaryTableTitle = "Test Scenarios Passed/Failed Summary Details";
			String[] rsummaryheader = { "Test Scenario Name", "Browser/Device", "Status", "Executed Steps",
					"Pass Steps", "Fail Steps" };

			rsummarytable = createTable(rsummaryCells, rsummaryheader, rsummaryTableTitle);
			addSummaryCells(txtFileName);
			doc.add(rsummarytable);

			doc.add(Chunk.NEWLINE);
			doc.setPageSize(PageSize.A3);
			doc.newPage();
			pdf.setPageEmpty(false);

			// *****************************************************************************************************************************
			Chunk detailHeader = getChunk("STOS Automated Test Execution Detail Report", FONT_20BDARK_GRAY);
			detailHeader.setUnderline(0.1f, -2f); // 0.1 thick, -2 y-location

			Paragraph detailParagraph = new Paragraph();
			detailParagraph.setAlignment(Element.ALIGN_CENTER);
			detailParagraph.add(new Phrase(detailHeader));
			doc.add(detailParagraph);
			doc.add(Chunk.NEWLINE);

			detailParagraph.setAlignment(Element.ALIGN_LEFT);
			float[] rdetailCells = { 0.5f, 2.5f, 1.0f, 1.8f, 4.5f, 1.2f, 0.5f, 1.2f };
			String rdetailTableTitle = "Test Scenarios Step By Step Execution Details";
			String[] rdetailheader = { "Step No", "Test Scenario Name", "Browser/Device", "Date",
					"Step Description/Activity", "Test Data", "Status", "Screenshot" };

			rdetailtable = createTable(rdetailCells, rdetailheader, rdetailTableTitle);
			addDetailCells(txtFileName);
			doc.add(rdetailtable);

			doc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("PDF REPORT IS GENERATED");
	}
    /***
     * @param fileName {@link String}
     */
	public void addSummaryCells(String fileName) {

		int reportStepCount = 0;
		int passCount = 0;
		int failCount = 0;
		String stepStatus = "";
		String[] getValue = null;

		String lineData;
		String testCaseName = "";
		String compareTestCase = "";
		String compareBrowser = "";
		String browserName = "";

		Set<String> uniqueTestCaseName = getUniqueTestCases(fileName);
		Iterator<String> iter = uniqueTestCaseName.iterator();

		while (iter.hasNext()) {
			reportStepCount = 0;
			passCount = 0;
			failCount = 0;
			stepStatus = "PASS";
			compareTestCase = (String) iter.next();
			Set<String> uniqueBrowserName = getUniqueBrowserDevice(fileName, compareTestCase, 1);
			Iterator<String> browserNames = uniqueBrowserName.iterator();
			while (browserNames.hasNext()) {
				reportStepCount = 0;
				passCount = 0;
				failCount = 0;
				compareBrowser = browserNames.next();
				try (FileReader inputFile = new FileReader(fileName);
						BufferedReader br = new BufferedReader(inputFile)) {

					while ((lineData = br.readLine()) != null) {
						getValue = lineData.split("@@@@@");
						testCaseName = getValue[0];
						if (testCaseName.equalsIgnoreCase(compareTestCase)) {
							if (reportStepCount == 0) {
							}
							browserName = getValue[1];
							if (browserName.equalsIgnoreCase(compareBrowser)) {
								reportStepCount++;
								stepStatus = getValue[5];
								// System.out.println(lineData);

								if (stepStatus.equalsIgnoreCase("PASS")) {
									passCount++;
								} else {
									failCount++;
								}
							}
						}
					}
					br.close();
				} catch (Exception e) {
					e.fillInStackTrace();
				}
				if (failCount > 0) {
					stepStatus = "FAIL";
				}
				rsummarytable.addCell(createValueCell(String.valueOf(compareTestCase)));
				rsummarytable.addCell(createValueCell(String.valueOf(compareBrowser)));
				rsummarytable.addCell(createValueCell(String.valueOf(stepStatus)));
				rsummarytable.addCell(createValueCell(String.valueOf(reportStepCount)));
				rsummarytable.addCell(createValueCell(String.valueOf(passCount)));
				rsummarytable.addCell(createValueCell(String.valueOf(failCount)));
			}

		}
	}
	/***
     * @param fileName {@link String}
     */
	public void addDetailCells(String fileName) {

		int reportStepCount = 0;
		String lineData;
		String tempTestCaseName = "";
		String printTestCaseName = "";
		String compareTestCase = "";
		String compareBrowser = "";
		int browserCount = 0;
		String deviceCombination;

		Set<String> uniqueTestCaseName = getUniqueTestCases(fileName);
		Iterator<String> iter = uniqueTestCaseName.iterator();

		while (iter.hasNext()) {
			tempTestCaseName = "";
			compareTestCase = (String) iter.next();

			Set<String> uniqueBrowserName = getUniqueBrowserDevice(fileName, compareTestCase, 1);
			Iterator<String> browserNames = uniqueBrowserName.iterator();

			while (browserNames.hasNext()) {

				browserCount = 0;
				compareBrowser = browserNames.next();

				deviceCombination = compareTestCase + "|" + compareBrowser;

				try(FileReader inputFile = new FileReader(fileName);
				BufferedReader br = new BufferedReader(inputFile)){
				reportStepCount = 0;
				while ((lineData = br.readLine()) != null) {
					String[] getValue = lineData.split("@@@@@");
					String testCaseName = getValue[0];
					if (testCaseName.equals(compareTestCase)) {
						String browserName = getValue[1];
						if (browserName.equals(compareBrowser)) {
							browserCount++;

							String DateTime = getValue[2];
							String stepDescription = getValue[3];
							String stepValue = getValue[4];
							String stepStatus = getValue[5];
							String screenshot = getValue[6];

							String year = DateTime.substring(0, 4);
							String month = DateTime.substring(4, 6);
							String date = DateTime.substring(6, 8);

							String hour = DateTime.substring(9, 11);
							String min = DateTime.substring(11, 13);
							String sec = DateTime.substring(13, 15);

							String dispDateTime = month + "/" + date + "/" + year + " - " + hour + ":" + min + ":"
									+ sec;

							try {
								reportStepCount++;
								if (tempTestCaseName.equalsIgnoreCase(deviceCombination)) {
									printTestCaseName = "";
								} else {
									tempTestCaseName = deviceCombination;
									printTestCaseName = deviceCombination.substring(0, deviceCombination.indexOf("|"));
									reportStepCount = 1;
									System.out.println("Print Test Case Name: " + printTestCaseName);
								}
								 
								if (browserCount != 1)
									browserName = "";

								if (stepValue.equals("NO DATA"))
									stepValue = "";

								rdetailtable.addCell(createValueCellLeft(String.valueOf(reportStepCount)));
								rdetailtable.addCell(createValueCellLeft(String.valueOf(printTestCaseName)));
								rdetailtable.addCell(createValueCellLeft(String.valueOf(browserName)));
								rdetailtable.addCell(createValueCell(String.valueOf(dispDateTime)));
								rdetailtable.addCell(createValueCellLeft(String.valueOf(stepDescription)));
								rdetailtable.addCell(createValueCellLeft(String.valueOf(stepValue)));
								if (stepStatus.equalsIgnoreCase("PASS")) {
									rdetailtable.addCell(createPassCell(String.valueOf(stepStatus)));
								} else {
									rdetailtable.addCell(createFailCell(String.valueOf(stepStatus)));
								}

								Image screenshortImg = Image.getInstance(screenshot);
								screenshortImg.scalePercent(1);
								PdfPCell imgCell = new PdfPCell();
								imgCell.addElement(screenshortImg);
								if (stepStatus.equalsIgnoreCase("FAIL")) {
									rdetailtable.addCell(imgCell);
								} else {
									if (lineData.contains("APPLI")) {
										if (ScreenshotsToAllApplitoolsReport) {
											rdetailtable.addCell(imgCell);
										} else {
											rdetailtable.addCell("");
										}
									} else {
										if (ScreenshotsToAllStepsInPdfReport) {
											rdetailtable.addCell(imgCell);
										} else {
											rdetailtable.addCell("");
										}
									}

								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				br.close();
				}catch(Exception e) {
					e.fillInStackTrace();
				}
			}
		}

	}

	/***
	 * @param fileName 
	 */
	public Set<String> getUniqueTestCases(String fileName) {

		try (FileReader inputFile = new FileReader(fileName); BufferedReader br = new BufferedReader(inputFile)) {
			String lineData;
			Set<String> set = new LinkedHashSet<String>();
			while ((lineData = br.readLine()) != null) {

				String[] getArrayValue = lineData.split("@@@@@");
				String getValue = getArrayValue[0];
				set.add(getValue);
			}
			br.close();
			return set;
		} catch (Exception e) {
			e.fillInStackTrace();
			return null;
		}
	}

	public Set<String> getUniqueBrowserDevice(String fileName, String testCaseName, int arrayIndex) {
		try (FileReader inputFile = new FileReader(fileName); BufferedReader br = new BufferedReader(inputFile);) {
			String lineData;
			Set<String> set = new HashSet<String>();

			while ((lineData = br.readLine()) != null) {

				String[] getArrayValue = lineData.split("@@@@@");
				String getValue = getArrayValue[arrayIndex];

				if (testCaseName.equals(getArrayValue[0])) {
					set.add(getValue);
				}
			}
			br.close();
			return set;
		} catch (Exception e) {
			e.fillInStackTrace();
			return null;
		}
	}

	public long getTimeTaken(String startDateTime, String endDateTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(startDateTime);
			d2 = sdf.parse(endDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		// long diffMinutes = diff / (60 * 1000) % 60;
		return diffSeconds;
	}

	public String getCurrentDateAndTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	// create table
	public PdfPTable createTable(float[] columnsSize, String[] header, String tableTitle) throws DocumentException {

		int cellsSize = columnsSize.length;
		// create 6 column table
		PdfPTable table = new PdfPTable(cellsSize);

		// set the width of the table to 100% of page
		table.setWidthPercentage(100);

		// set relative columns width
		table.setWidths(columnsSize);

		// create header cell
		PdfPCell cell = new PdfPCell(new Phrase(tableTitle, FONT_14BW));
		// set Column span "1 cell = 6 cells width"
		cell.setColspan(cellsSize);
		// set style
		headerCellStyle(cell);
		// add to table
		table.addCell(cell);

		for (String hVal : header) {
			table.addCell(createLabelCell(hVal));
		}
		return table;
	}

	// create cells
	public PdfPCell createLabelCell(String text) {
		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, FONT_11BDARK_GRAY));
		// set style
		labelCellStyle(cell);
		return cell;
	}

	// create cells
	public PdfPCell createValueCell(String text) {

		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, FONT_10BBLACK));

		// set style
		valueCellStyle(cell);
		return cell;
	}

	public void headerCellStyle(PdfPCell cell) {

		// alignment
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		// padding
		cell.setPaddingTop(0f);
		cell.setPaddingBottom(7f);

		// background color
		cell.setBackgroundColor(new Color(0, 121, 182));

		// border
		cell.setBorder(1);
		cell.setBorderWidthBottom(2f);

	}

	public void labelCellStyle(PdfPCell cell) {
		// alignment
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		// padding
		cell.setPaddingLeft(3f);
		cell.setPaddingTop(0f);

		// background color
		cell.setBackgroundColor(Color.LIGHT_GRAY);

		// border
		// cell.setBorder(0);
		// cell.setBorderWidthBottom(1);
		cell.setBorderColorBottom(Color.GRAY);

		// height
		cell.setMinimumHeight(18f);
	}

	public void valueCellStyle(PdfPCell cell) {
		// alignment
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setMinimumHeight(18f);
	}

	public Chunk getChunk(String txt, Font font) {
		Chunk chunk = new Chunk(txt);
		chunk.setFont(font);
		return chunk;
	}

	// create pass cells
	public PdfPCell createPassCell(String text) {

		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, FONT_GREEN));

		// set style
		valueCellStyle(cell);
		return cell;
	}

	// create pass cells
	public PdfPCell createFailCell(String text) {
		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, FONT_RED));
		// set style
		valueCellStyle(cell);
		return cell;
	}

	public void addHighLevelSummaryCells(String fileName)  {

		int totalTestCaseCount = 0;
		int totalPassCount = 0;
		int totalFailCount = 0;
		int reportStepCount = 0;
		int failCount = 0;
		String stepStatus = "";
		String[] getValue = null;

		String lineData;
		String testCaseName = "";
		String compareTestCase = "";
		String compareBrowser = "";
		String browserName = "";

		Set<String> uniqueTestCaseName = getUniqueTestCases(fileName);
		Iterator<String> iter = uniqueTestCaseName.iterator();
		totalTestCaseCount = uniqueTestCaseName.size();

		while (iter.hasNext()) {
			reportStepCount = 0;
			failCount = 0;
			stepStatus = "PASS";
			compareTestCase = (String) iter.next();

			Set<String> uniqueBrowserName = getUniqueBrowserDevice(fileName, compareTestCase, 1);
			Iterator<String> browserNames = uniqueBrowserName.iterator();

			while (browserNames.hasNext()) {

				compareBrowser = browserNames.next();

				try(FileReader inputFile = new FileReader(fileName);
				BufferedReader br = new BufferedReader(inputFile);){

				while ((lineData = br.readLine()) != null) {

					getValue = lineData.split("@@@@@");
					testCaseName = getValue[0];

					if (testCaseName.equalsIgnoreCase(compareTestCase)) {
						if (reportStepCount == 0) {
						}
						browserName = getValue[1];

						if (browserName.equalsIgnoreCase(compareBrowser)) {
							reportStepCount++;
							stepStatus = getValue[5];
							// System.out.println(lineData);

							if (stepStatus.equalsIgnoreCase("PASS")) {
							} else {
								failCount++;
							}
						}
					}
				}
				br.close();
				}catch (Exception e) {
					e.fillInStackTrace();
				}
				if (failCount > 0) {
					stepStatus = "FAIL";
					totalFailCount++;
				} else {
					totalPassCount++;
				}
			}
		}
		rhighLeveSummarytable.addCell(createValueCell(String.valueOf(totalTestCaseCount)));
		rhighLeveSummarytable.addCell(createValueCell(String.valueOf(totalPassCount)));
		rhighLeveSummarytable.addCell(createValueCell(String.valueOf(totalFailCount)));
	}

	public void addHighLevelSummaryCells1(String fileName) throws IOException, ParseException {

		int totalPassCount = 0;
		int totalPassStepCount = 0;
		int totalFailStepCount = 0;
		int totalFailCount = 0;
		int reportStepCount = 0;
		int failCount = 0;
		String stepStatus = "";
		String[] getValue = null;
		String lineData;
		String testCaseName = "";
		String compareTestCase = "";
		String compareBrowser = "";
		String browserName = "";

		Set<String> uniqueTestCaseName = getUniqueTestCases(fileName);
		Iterator<String> iter = uniqueTestCaseName.iterator();
		try {
			while (iter.hasNext()) {
				reportStepCount = 0;
				failCount = 0;
				stepStatus = "PASS";
				compareTestCase = (String) iter.next();

				Set<String> uniqueBrowserName = getUniqueBrowserDevice(fileName, compareTestCase, 1);
				Iterator<String> browserNames = uniqueBrowserName.iterator();

				while (browserNames.hasNext()) {

					compareBrowser = browserNames.next();

					try (FileReader inputFile = new FileReader(fileName);
							BufferedReader br = new BufferedReader(inputFile);) {

						while ((lineData = br.readLine()) != null) {
							// System.out.println("Current Line Number : " + lineNumber++);
							getValue = lineData.split("@@@@@");
							testCaseName = getValue[0];
							if (testCaseName.equalsIgnoreCase(compareTestCase)) {
								if (reportStepCount == 0) {
									stepStatus = "";
								}
								browserName = getValue[1];
								if (browserName.equalsIgnoreCase(compareBrowser)) {
									reportStepCount++;
									stepStatus = getValue[5];

								}
							}
						}
						br.close();
					}
					if (failCount > 0) {
						stepStatus = "FAIL";
						totalFailCount++;
					} else {
						totalPassCount++;
					}
				}
			}

			try (FileReader inputFile1 = new FileReader(fileName);
					BufferedReader br1 = new BufferedReader(inputFile1);) {
				while ((lineData = br1.readLine()) != null) {
					if (lineData.contains("PASS")) {
						totalPassStepCount++;
					} else {
						totalFailStepCount++;
					}
				}
				br1.close();
			}
			rhighLeveSummarytable.addCell(createValueCell(String.valueOf("TOTAL")));
			rhighLeveSummarytable.addCell(createValueCell(String.valueOf(totalPassCount + totalFailCount)));
			rhighLeveSummarytable.addCell(createValueCell(String.valueOf((totalPassStepCount + totalFailStepCount))));

			rhighLeveSummarytable.addCell(createValueCell(String.valueOf("PASS")));
			rhighLeveSummarytable.addCell(createValueCell(String.valueOf(totalPassCount)));
			rhighLeveSummarytable.addCell(createValueCell(String.valueOf(totalPassStepCount)));

			rhighLeveSummarytable.addCell(createValueCell(String.valueOf("FAIL")));
			rhighLeveSummarytable.addCell(createValueCell(String.valueOf(totalFailCount)));
			rhighLeveSummarytable.addCell(createValueCell(String.valueOf(totalFailStepCount)));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    /***
     * 
     * @param text {@link String} 
     * @return {@link PdfPCell} 
     */
	// create cells
	public PdfPCell createValueCellLeft(String text) {

		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, FONT_10BBLACK));

		// set style
		valueCellStyle(cell);
		return cell;
	}
    /***
     * 
     * @param cell
     */
	public void valueCellStyleLeft(PdfPCell cell) {
		// alignment
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		// padding
		// cell.setPaddingTop(0f);
		// cell.setPaddingBottom(5f);

		// border
		// cell.setBorder(0);
		// cell.setBorderWidthBottom(0.5f);

		// height
		cell.setMinimumHeight(18f);
	}
}
