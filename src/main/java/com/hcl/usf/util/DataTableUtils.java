package com.hcl.usf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hcl.usf.common.STOSException;
/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
public class DataTableUtils {
	private static final Logger LOG = LogManager.getLogger("DataUtils");
	private static XSSFWorkbook book;
	private XSSFSheet sheet;
	private Map<String, String> map = new HashMap<String, String>(0);
	private DataFormatter df = new DataFormatter();
	private Map<String, LinkedHashMap<Integer, LinkedHashMap<String, String>>> allSheetMap=new LinkedHashMap<String,LinkedHashMap<Integer,LinkedHashMap<String, String>>>(0);
	private List<String> sheetList=new ArrayList<String>(0);
    private String filePath;
	public DataTableUtils(String fp) {
        this.filePath=fp;
		try (FileInputStream file = new FileInputStream(filePath)

		) {
			book = new XSSFWorkbook(file);
			LOG.info("StosData loading...");
		} catch (Exception e) {
			LOG.error("Data loading problem {}"+e.getMessage());
		}
	}

	
	/***
	 * This method will load all excel data
	 * @return Map
	 */
	public Map<String, LinkedHashMap<Integer, LinkedHashMap<String, String>>> geAllSheetData() {
		for (int i = 0; i < getNumberOfSheets(); i++) {
			sheet = book.getSheetAt(i);
			LOG.info(sheet.getSheetName());
		    setSheetName(sheet.getSheetName());
		    int row=noOfRows();
		    LinkedHashMap<Integer, LinkedHashMap<String, String>> singleSheetMap=new LinkedHashMap<Integer, LinkedHashMap<String, String>>();
		    for(int k=1;k<=row;k++) {
		    	singleSheetMap.put(k, getRowMap(k));
		    }
		    allSheetMap.put(sheet.getSheetName(), singleSheetMap);
		    sheetList.add(sheet.getSheetName());
		}
        return allSheetMap;
	}
	
	/***
	 * @param rowVal {@link Integer}
	 */
	public LinkedHashMap<String,String> getRowMap(int rowVal) {
		LinkedHashMap<String,String>  hasm=new LinkedHashMap<String, String>();
		try {
			int colCount = sheet.getRow(0).getLastCellNum();
			Row firstRow = sheet.getRow(0);
			Row currRow = sheet.getRow(rowVal);
			for (int j = 0; j < colCount; j++) {
				hasm.put(df.formatCellValue(firstRow.getCell(j)), df.formatCellValue(currRow.getCell(j)));
			}
		} catch (Exception e) {
			LOG.error(
					"Row number entered exceeds the actual row count of excel data sheet {} " + e.getMessage());
		} finally {
			try {
				book.close();
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
		return hasm;
	}
	/***
	 * @param rowVal {@link Integer}
	 */
	public void getRowData(int rowVal) {
		try {
			int colCount = sheet.getRow(0).getLastCellNum();
			Row firstRow = sheet.getRow(0);
			Row currRow = sheet.getRow(rowVal);
			for (int j = 0; j < colCount; j++) {
				LOG.info(df.formatCellValue(firstRow.getCell(j)) + " = " +df.formatCellValue(currRow.getCell(j)));
				map.put(df.formatCellValue(firstRow.getCell(j)), df.formatCellValue(currRow.getCell(j)));
			}
		} catch (Exception e) {
			LOG.error(
					"Row number entered exceeds the actual row count of excel data sheet {} " + e.getMessage());
		} finally {
			try {
				book.close();
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
	}
	/***
	 * @param rowVal {@link Integer}
	 */
	public void getRowDataForUpload(int rowVal) {
		try {
			int colCount = sheet.getRow(2).getLastCellNum();
			Row firstRow = sheet.getRow(1);
			Row currRow = sheet.getRow(rowVal);
			for (int j = 0; j < colCount; j++) {
				LOG.info(df.formatCellValue(firstRow.getCell(j)) + " = " +df.formatCellValue(currRow.getCell(j)));
				map.put(df.formatCellValue(firstRow.getCell(j)), df.formatCellValue(currRow.getCell(j)));
			}
		} catch (Exception e) {
			LOG.error(
					"Row number entered exceeds the actual row count of excel data sheet {} " + e.getMessage());
		} finally {
			try {
				book.close();
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
	}
	/***
	 * This method will update data into excel
	 * @param sheetName {@link String}
	 * @param row {@link Integer}
	 * @param col {@link Integer}
	 * @param updateValue {@link String}
	 */
	public void setData(String sheetName,int row, int col,String updateValue) {
		try {
			LOG.info("Going to write data into excel, Before writing stos will take backup of StosData sheet");
			String newFileName = AppUtil.currentTimeDDMMYYYY().concat("_").concat(Paths.get(filePath).toFile().getName());
			String newFileLocation = Paths.get(filePath).toAbsolutePath().getParent().toString();
			AppUtil.backupStosDataFile(filePath, newFileLocation.concat(File.separator) + newFileName);
			setSheetName(sheetName);
			Cell cell = null;
			// Update the value of cell
			cell = sheet.getRow(row).getCell(col);
			cell.setCellValue(updateValue);
			try (FileOutputStream outFile = new FileOutputStream(filePath);) {
				 book.write(outFile);
				 outFile.close();
			}
			LOG.info(updateValue +"==> data written successfully in StosData sheet");
		} catch (Exception e) {
			LOG.error("Please check you StosData sheet is opened...if opend then close it and Retry again "+e.getMessage()
			);
			throw new STOSException("Please check you StosData sheet is opened...if opend then close it and Retry again "+e.getMessage()
			);
		} finally {
			try {
				book.close();
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
				
	}
	
    /***
     * @return int not of rows in particular sheet in excel
     */
	public int noOfRows() {
		return sheet.getLastRowNum();
	}

	/**
	 * @param mapData {@link String}
	 * @return {@link String}
	 */
	public String getMapValue(String mapData) {
		return map.get(mapData);
	}

	/***
	 * @param columnName {@link String}
	 * @return {@link String}
	 */
	public String getData(String columnName) {
		return getMapValue(columnName);
	}
    /***
     * @return List<String> total no of sheet in list
     */
	public List<String> getSheetList() {
		return sheetList;
	}

	public void setSheetName(String sheetName) {
		sheet = book.getSheet(sheetName.trim());
	}
   /**
    * @return Integer Total no of sheet in excel
    */
	public int getNumberOfSheets() {
		return book.getNumberOfSheets();
	}
	
	public static void main(String[] args) {
		DataTableUtils dat=new DataTableUtils(System.getProperty("user.dir")+"\\input\\StosData.xlsx");
		dat.setData("DisplaySuggestedSell", 2, 2, "USer 44");

	}
	
}
