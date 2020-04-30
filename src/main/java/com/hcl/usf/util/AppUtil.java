package com.hcl.usf.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.hcl.usf.dto.ReportsDto;
import com.hcl.usf.dto.UserDto;

/***
 * @author intakhabalam.s@hcl.com
 */
public class AppUtil {
	static final Logger LOGGER = LogManager.getLogger("AppUtil");
	static DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static SimpleDateFormat DDMMYYYY = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
	static DateFormat YYYYMMDD_HHMMSS = new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss");
	static SimpleDateFormat MMMDD = new SimpleDateFormat("MMM dd");
	static SimpleDateFormat MMDDYYYYHHMMSS = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	public static final String EMPTY_STR = "";
	public final static String PASSED = "Passed";
	public final static String FAILED = "Failed";
	public final static String RUNNING = "Running";
	public final static String SKIPPED = "skipped";
	public final static String DELIMINATOR = "@@@";
	public static final Long TIMEOUT_SECONDS = 30L;
	public static final Long POOL_SECONDS = 2L;
	private static final String COMMA_SEPERATOR = ",";
	public static final Long MINS = (long) (1000 * 60 * 60);
	public static final String REPORTS_INPUT_NAME = "/ecom-consolidate/STOS_Reports.xml";
	public static final String REPORTS_OUTPUT_NAME = "/ecom-consolidate/STOS_Reports.html";
	public static final String REG_REPORTS_INPUT_NAME = "/ecom-regression/STOS_Reg_Reports.xml";
	public static final String REG_REPORTS_OUTPUT_NAME = "/ecom-regression/STOS_Reg_Reports.html";
	public static final String REPORTS_DIR = System.getProperty("user.dir") + "/reports";
	public static final File XML_FILE = Paths.get(System.getProperty("user.dir") + "/reports"+AppUtil.REPORTS_INPUT_NAME).toFile();
	public static final File REG_XML_FILE = Paths.get(System.getProperty("user.dir") + "/reports"+AppUtil.REG_REPORTS_INPUT_NAME).toFile();
	public static String DATE_SHEET_NAME;
	public static String TEST_NAME;
	public static String CUSTOM_REPORTS_NAME;
	public static int TOTAL_TC_COUNT = 1;
	public static Long REPORT_ID;
	public static StringBuilder testBuilder = new StringBuilder("");
	// Run time get the values from Stos executor class
	public static String textFilePath;
	public static String REPORTS_PATH;
	public static boolean WILL_JIRA_TCIKET_CREATE=false;
	public static boolean WILL_UPLOAD_FILE_TO_CONFULENCE=false;

	/***
	 * @param listOfItems {@link List}
	 * @param separator {@link String}
	 * @return {@link String}
	 */
	public static String concatenate(List<String> listOfItems, String separator) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> stit = listOfItems.iterator();
		while (stit.hasNext()) {
			sb.append(stit.next());
			if (stit.hasNext()) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}
    /**
     * @return {@link String}
     */
	public static String getIPHostName() {
		InetAddress ip = null;
		String hostname;
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
			LOGGER.info("Your current IP address : " + ip);
			LOGGER.info("Your current Hostname : " + hostname);
			LOGGER.info("Your current Canonical Address : " + ip.getCanonicalHostName());
		} catch (UnknownHostException e) {
			return EMPTY_STR;
		}
		return ip.getHostAddress() + "-" + ip.getHostName();
	}

	/***
	 * @param apiArrays
	 *            {@link [] String}
	 * @return {@link String}
	 */
	public static String arrayModification(String[] apiArrays) {
		StringBuilder sb = new StringBuilder(EMPTY_STR);
		if (apiArrays.length != 0) {
			for (String s : apiArrays) {
				if (s != null && !s.isEmpty()) {
					sb.append(s).append(COMMA_SEPERATOR);
				}
			}
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 1);
			}
		}
		return sb.toString();
	}

	/***
	 * Write file
	 * 
	 * @param data
	 *            {@link String}
	 * @param filePath
	 *            {@link String}
	 */
	public static void writeFile(String data, String filePath) {
		Path filepath = Paths.get(filePath);
		byte[] bytes = data.getBytes();
		try (OutputStream out = Files.newOutputStream(filepath)) {
			out.write(bytes);
		} catch (Exception e) {
			LOGGER.error("Error writing file", e);

		}
	}

	/**
	 * @param high
	 *            {@link Integer}
	 * @param low
	 *            {@link Integer}
	 * @return {@link Integer}
	 */
	public static int generateRandomNumber(int high, int low) {
		Random r = new Random();
		return r.nextInt(high - low) + low;
	}

	/**
	 * @param input
	 *            {@link String}
	 * @return modified error with fixed length
	 */
	public static String errorText(String input) {
		try {
			String lastText = input.substring(input.length() - 80);
			String firstText = input.substring(0, 170);
			return firstText.concat("XXXX").concat(lastText);
		} catch (Exception e) {
			return input.substring(input.length() - 80);
		}
	}

	/***
	* 
	*/
	public static String[] splitSting(String toSplit) {
		return toSplit.trim().split("\\s*,\\s*");
	}

	/***
	 * Backup of config file
	 * 
	 * @param currFile,String
	 *            newName {@link String}
	 */
	public static void backupStosDataFile(String currFile, String newFile) {
		File file = Paths.get(currFile).toFile();
		if (file.exists()) {
			copyReplaceFile(currFile, newFile);

		}
		LOGGER.info("StosData sheet backup done sucessfully...");

	}

	/**
	 * This method will copy and replace file
	 * 
	 * @param src
	 *            {@link String}
	 * @param destSrc
	 *            {@link String}
	 */
	public static void copyReplaceFile(String src, String destSrc) {
		Path sourcePath = Paths.get(src);
		Path destinationPath = Paths.get(destSrc);
		try {
			Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (FileAlreadyExistsException e) {
			// throw new FileException();
			LOGGER.error("Error: Destination file already exists {} " + e);
			// destination file already exists
		} catch (Exception e) {
			// something else went wrong
			LOGGER.error("Error: Copy file {} - " + e);
		}

	}
	/**
	 * This method will move file to destination dir, if exits then replace it
	 * 
	 * @param src
	 *            {@link String}
	 * @param destSrc
	 *            {@link String}
	 */
	public static void moveReplaceFile(String src, String destSrc) {
		Path sourcePath = Paths.get(src);
		Path destinationPath = Paths.get(destSrc);
		try {
			Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
			LOGGER.info("File Copied successfully..."+destSrc);
		} catch (Exception e) {
			LOGGER.error("Error: File Moving problem {} " + e);
		}
	}

	/***
	 * Will print time
	 * 
	 * @param fileTime
	 *            {@link String}
	 * @return time {@link String}
	 */
	public String printFileTime(FileTime fileTime) {
		try {
			return DDMMYYYY.format(fileTime.toMillis());
		} catch (Exception e) {
			return "" + System.currentTimeMillis();
		}
	}

	/***
	 * @param pathToCsv
	 * @return {@link List}
	 */
	public static List<List<String>> readFilCSV(String pathToCsv) {
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(pathToCsv))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_SEPERATOR);
				records.add(Arrays.asList(values));
			}
			br.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
		}
		return records;
	}

	/***
	 * @return dd-MM-yyyy_HH-mm-ss
	 */
	public static String currentTimeDDMMYYYY() {
		return DDMMYYYY.format(new Date());
	}

	/***
	 * @return yyyy.MM.dd HH-mm-ss
	 */
	public static String currentTimeYYYYMMDD() {
		return YYYYMMDD_HHMMSS.format(new Date());
	}

	/**
	 * Checks if is collection empty.
	 * 
	 * @param collection
	 *            {@link Collection}
	 * @return {@link Boolean}
	 */
	private static boolean isCollectionEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is object empty.
	 * 
	 * @param object
	 *            {@link Object}
	 * @return {@link Boolean}
	 */
	public static boolean isObjectEmpty(Object object) {
		if (object == null)
			return true;
		else if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				return true;
			}
		} else if (object instanceof Collection) {
			return isCollectionEmpty((Collection<?>) object);
		}
		return false;
	}

	/***
	 * 
	 * @param contentToBeAppended
	 *            {@link String}
	 */
	public static void doWrite(String contentToBeAppended) {
		if (textFilePath == null) {
			textFilePath = System.getProperty("user.dir") + "/reports/flat/";
			File file = Paths.get(textFilePath).toFile();
			if (!file.isDirectory()) {
				file.mkdir();
			}
		}
		String fp = textFilePath + "STOS_TestCase_" + currentTimeDDMMYYYY() + ".txt";
		Path path = Paths.get(fp);
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);

			}
		}
		try (FileWriter fw = new FileWriter(fp, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(contentToBeAppended);
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);

		}
	}

	/**
	 * 
	 */
	public static void appendStringToFile(Path file, String s) {
		try (BufferedWriter out = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			out.append(s);
			out.newLine();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	/***
	 * @param finalnumber
	 *            {@link String}
	 * @return {@link String}
	 */
	public static String dateCalculaterValue(String finalnumber) {
		int a = Integer.parseInt(finalnumber);
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		LOGGER.info("Current Date: " + MMMDD.format(cal.getTime()));
		// Number of Days to add
		cal.add(Calendar.DAY_OF_MONTH, a);
		// Displaying the new Date after addition of Days to current date
		String newDate;
		if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
				|| (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
				// Date after adding the days to the current date
				newDate = MMMDD.format(cal.getTime());
			} else {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				// Date after adding the days to the current date
				newDate = MMMDD.format(cal.getTime());
			}
		} else {
			// Date after adding the days to the current date
			newDate = MMMDD.format(cal.getTime());
		}
		return newDate;
	}

	/***
	 * @param fromDate
	 * @return {@link Long}
	 */
	public static long getDateDiff(String fromDate) {
		try {
			LocalDate dateFrom = LocalDate.parse(fromDate, YYYYMMDD);
			LocalDate dateTo = LocalDate.now();
			// Period intervalPeriod = Period.between(dateFrom, dateTo);
			long intervalDays = ChronoUnit.DAYS.between(dateFrom, dateTo);
			return intervalDays;

		} catch (Exception e) {
			return 0;
		}
	}
	
	

	/****
	 * After taking input this method will create users which will save in db and
	 * shown onto UI 
	 * @param userDto {@link UserDto}
	 */
	public static void createXMLReports(ReportsDto r,boolean isRegresion) {
		 String userPath="";
		try {
			LOGGER.info("Is Regression {} "+isRegresion);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			factory.setValidating(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			 userPath = REPORTS_DIR+AppUtil.REPORTS_INPUT_NAME;
			if(isRegresion) {
				userPath = REPORTS_DIR+AppUtil.REG_REPORTS_INPUT_NAME;
			}
			File file = Paths.get(userPath).toFile(); // XML file to read
			if (!file.exists()) {
				createDummyXML(userPath);
			}
			Document document = builder.parse(file);
			Element reports = document.getDocumentElement();
			Element reportsElement = document.createElement("test");
			reportsElement.setAttribute("id", r.getTcId());
			//
			Element testScenario = document.createElement("testscenario");
			Text testScenarioText = document.createTextNode(r.getSenariosName().trim());
			testScenario.appendChild(testScenarioText);
			reportsElement.appendChild(testScenario);
			//
			Element browser = document.createElement("browser");
			Text browserText = document.createTextNode(r.getBrowser().trim());
			browser.appendChild(browserText);
			reportsElement.appendChild(browser);
			//
			Element url = document.createElement("url");
			Text urlText = document.createTextNode(r.getUrl().trim());
			url.appendChild(urlText);
			reportsElement.appendChild(url);
			//
			Element env = document.createElement("envstatus");
			Text envText = document.createTextNode(r.getEnvStatus().trim());
			env.appendChild(envText);
			reportsElement.appendChild(env);
			
			//
			Element status = document.createElement("status");
			Text statusText = document.createTextNode(r.getStatus().trim());
			status.appendChild(statusText);
			reportsElement.appendChild(status);
			//
			Element totalPass = document.createElement("totalpass");
			Text totalPassText = document.createTextNode(r.getTotalPass().trim());
			totalPass.appendChild(totalPassText);
			reportsElement.appendChild(totalPass);
			//
			Element totalFail = document.createElement("totalfail");
			Text totalFailText = document.createTextNode(r.getTotalFail().trim());
			totalFail.appendChild(totalFailText);
			reportsElement.appendChild(totalFail);
			//
			Element totalSteps = document.createElement("totalsteps");
			Text totalStepsText = document.createTextNode(r.getTotalSteps().trim());
			totalSteps.appendChild(totalStepsText);
			reportsElement.appendChild(totalSteps);
			//
			Element date = document.createElement("stepsdate");
			List<String> dateList = r.getRunDate();
			for (String s : dateList) {
				Node nameNode = document.createElement("name");
				reportsElement.appendChild(date).appendChild(nameNode).setTextContent(s);
			}
			//
			Element steps = document.createElement("stepsinfo");
			List<String> stepsList = r.getSteps();
			for (String s : stepsList) {
				Node nameNode = document.createElement("name");
				reportsElement.appendChild(steps).appendChild(nameNode).setTextContent(s);
			}
			//
			Element details = document.createElement("stepsdetails");
			List<String> detailsList = r.getDetails();
			for (String s : detailsList) {
				Node nameNode = document.createElement("name");
				 if(!s.contains("href")) {
					  reportsElement.appendChild(details).appendChild(nameNode).setTextContent(s);
				  }else {
					  reportsElement.appendChild(details).appendChild(nameNode).setTextContent("-");
				  }
			}
			Element stepstatus = document.createElement("stepstatus");
			List<String> stepsStatusList = r.getStepStatus();
			for (String s : stepsStatusList) {
				Node nameNode = document.createElement("name");
				reportsElement.appendChild(stepstatus).appendChild(nameNode).setTextContent(s);
			}

			reports.appendChild(reportsElement);// Parent
			transformDoc(document, file);
			LOGGER.info("XML Reports saved successfully...");
			//
		} catch (Exception e) {
			LOGGER.error("Error: XML Reports  creation problem {} ", e);
			try {
				if (e instanceof SAXParseException) {
					Files.delete(Paths.get(userPath));
					LOGGER.info("File Deleted succesfully as there was problem in file, again calling method ");
					createXMLReports(r, isRegresion);
				}
			} catch (IOException e1) {
				LOGGER.error("File Deleting problem {} ", e1);
			}

		}

	}
	
	/***
	 * @param msg  {@link String}
	 * @return {@link String}
	 */
	public static String getMMDDYYY(long time) {
		return MMDDYYYYHHMMSS.format(time);
	}

	/***
	 * @param document {@link Document}
	 * @param file {@link File}
	 * @throws TransformerException
	 */
	public static void transformDoc(Document document, File file) throws TransformerException {
		TransformerFactory tfact = TransformerFactory.newInstance();
		Transformer tform = tfact.newTransformer();
		tform.setOutputProperty(OutputKeys.INDENT, "yes");
		tform.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
		tform.transform(new DOMSource(document), new StreamResult(file));
	}

	/****
	 * This will create a dummy reports at first time, if reports not available
	 * @param filePath  {@link String}
	 * @param tag {@link String}
	 */
	private static void createDummyXML(String filePath) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("reports");
			doc.appendChild(rootElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(Paths.get(filePath).toFile());
			transformer.transform(source, result);
			LOGGER.info("Dummy XML created for TAG { reports }");

		} catch (Exception e) {
			LOGGER.error("Error: Dummy XML creation problem {} ", e);
		}

	}

	/***
	 * @param str
	 *            {@link String}
	 * @return {@link String}
	 */
	public static String removeSpace(String str) {
		return str.replaceAll("\\s+", "");

	}
	
	/***
	 * 
	 * This method will convert XML to Object from given input file
	 * @param clazz  {@link Class}
	 * @param file  {@link File}
	 * @return class
	 * @throws JAXBException  {@link JAXBException}
	 * @throws FileNotFoundException {@link FileNotFoundException}
	 */
	public static <T> T convertXMLToObject(Class<?> clazz, File file)  {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller um = context.createUnmarshaller();
			@SuppressWarnings("unchecked")
			T unmarshal = (T) um.unmarshal(file);
			LOGGER.info("Data load sucessfully { "+file.getName()+" }");
			return unmarshal;
		} catch (Exception je) {
			LOGGER.error("Error : Interpreting XML response {} ", je.getMessage());
		}
		return null;
	}
	
	/***
	 * This method will give the List of value which present in XML
	 * @param fileName {@link String}
	 * @param tagName {@link String} 
	 * @return list {@link List}
	 */
	public static List<String> getValuesFromXML(File fileName, String tagName) {
		List<String> nonEdiList = new ArrayList<>(0);
		try {
			Document doc = parseDocument(fileName);
			NodeList nodeList = doc.getElementsByTagName(tagName);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				nonEdiList.add(element.getTextContent().trim());

			}
			LOGGER.debug("Load values against  tagname { " + tagName + " } : size [ " + nonEdiList.size() + " ]");
		} catch (Exception e) {
			LOGGER.error("Error {getValuesFromXML} : " + e.getMessage());
		}

		return nonEdiList;
	}
	
	/***
	    * 	
	    * @param fileName
	    * @return {@link Document}
	    * @throws ParserConfigurationException
	    * @throws SAXException
	    * @throws IOException
	    */
	   private static Document parseDocument(File fileName) throws ParserConfigurationException, SAXException, IOException {
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileName);
			doc.getDocumentElement().normalize();
			return doc;
	   }
	
	/***
	 * @param msg  {@link String}
	 * @return {@link String}
	 */
	public static String passFormat(String msg) {
		return "<span class='green'>" + msg + "</span>";
	}
	/***
	 * @param msg  {@link String}
	 * @return {@link String}
	 */
	public static String failFormat(String msg) {
		return "<span class='waves-light red'>" + msg + "</span>";
	}
	/***
	 * @param msg  {@link String}
	 * @return {@link String}
	 */
	public static String errorFormat(String msg) {
		return "<span class='waves-light red lighten-2'>" + msg + "</span>";
	}
	/***
	 * @param msg  {@link String}
	 * @return {@link String}
	 */
	public static String testSkip(String msg) {
		return "<span class='waves-light cyan'>" + msg + "</span>";
	}

	/**
	 * @param str {@link String}
	 * @return {@link String}
	 */
	public static String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);

	}

	/*public static void main(String[] args) {
		File file=new File(System.getProperty("user.dir") + "/" + "download/test.xml");
		//DocReportsDto ddd=	convertXMLToObject(DocReportsDto.class,file);
		List tt=getValuesFromXML(file,"test");
		System.out.println(tt.size());
	}*/

}
