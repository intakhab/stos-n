package com.hcl.usf.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hcl.usf.pages.CCALMAMigrationPage;
import com.hcl.usf.util.AppUtil;
import com.hcl.usf.util.CommonElement;

public class CCALMAMigartionTest extends CommonElement {

       private static final String STORY_ECR46_296 = "ECR46_296 CCA MLM Option Changes";
       
       private static final String STORY_ECR46_397 = "ECR_46_397 CCA changes for LMA Migration Upload-Testing";
       
       private static final String STORY_ECR46_500 = "ECR46_500 CCA changes for LMA Migration Report";
       
       private static final String STORY_ECR46_823 = "ECR46_823 CCA update eUser screen changes";
       
       private static final String STORY_ECR46_760 = "ECR_46_760 CCA LMA Migration Report Behavior with > 60,000 records";
       
       
       private  CCALMAMigrationPage page =null; 
       
       
       @BeforeMethod
       public void beforeMethod() {
              page = PageFactory.initElements(driver, CCALMAMigrationPage.class);
       }
       
       @Test(enabled = true)
       public void migrationMLMNewOptionsPage() {
              try {
                     int row = 1; // MLMNewOptionsTest
                     String testSenarios=selectRowAndReturnSenarios(row);
                     if ("CCAMigrationMLMNewOptionsTest".equalsIgnoreCase(testSenarios)
                                  && findData("Run").equalsIgnoreCase("yes")) {
                            startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_296,testSenarios); // Row required you may change row as per your data
                           page.validateMLMNewOptions(cdto.getCustomerId(), cdto.getDepartmentId());
                           cdto.setRunStatus(AppUtil.PASSED);
                           commonInfoLog(testSenarios + " scenario completed");
                     } else {
                           skipTestCase(testSenarios,STORY_ECR46_296);
                     }
              } catch (Throwable ex) {
                     commonErrorUpdate(ex);
              } finally {
                     signOut();
                     updateTC(cdto);
              }
       }
       
       @Test(enabled = true)
       public void migrationuploadPage() {
              try {
                     int row = 2; // MigrationUploadTest
                     String testSenarios=selectRowAndReturnSenarios(row);
                     if ("CCAMigrationUploadTest".equalsIgnoreCase(testSenarios)
                                  && findData("Run").equalsIgnoreCase("yes")) {
                            startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_397,testSenarios); // Row required you may change row as per your data
                           page.validateCCALMAMigrationUploadPage(cdto.getCustomerId(), cdto.getDepartmentId());
                           cdto.setRunStatus(AppUtil.PASSED);
                           commonInfoLog(testSenarios + " scenario completed");
                     } else {
                           skipTestCase(testSenarios,STORY_ECR46_397);
                     }
              } catch (Throwable ex) {
                     commonErrorUpdate(ex);
              } finally {
                     signOut();
                     updateTC(cdto);
              }
       }
       
       
       @Test(enabled = true)
       public void migrationreportPage() {
              try {
                     int row = 3; // CCAMigrationReportTest
                     String testSenarios=selectRowAndReturnSenarios(row);
                     if ("CCAMigrationReportTest".equalsIgnoreCase(testSenarios)
                                  && findData("Run").equalsIgnoreCase("yes")) {
                            startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_500,testSenarios); // Row required you may change row as per your data
                           page.validateCCALMAMigrationReportPage(cdto.getCustomerId(), cdto.getDepartmentId());
                           cdto.setRunStatus(AppUtil.PASSED);
                           commonInfoLog(testSenarios + " scenario completed");
                     } else {
                           skipTestCase(testSenarios,STORY_ECR46_500);
                     }
              } catch (Throwable ex) {
                     commonErrorUpdate(ex);
              } finally {
                     signOut();
                     updateTC(cdto);
              }
       }
       
       @Test(enabled = true)
       public void userscreenchangesPage() {
              try {
                     int row = 4; // CCAUserscreenchangesTest
                     String testSenarios=selectRowAndReturnSenarios(row);
                     if ("CCAUserScreenChangesTest".equalsIgnoreCase(testSenarios)
                                  && findData("Run").equalsIgnoreCase("yes")) {
                            startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_823,testSenarios); // Row required you may change row as per your data
                           page.validateCCAUserscreenchanges(cdto.getCustomerId(), cdto.getDepartmentId());
                           cdto.setRunStatus(AppUtil.PASSED);
                           commonInfoLog(testSenarios + " scenario completed");
                     } else {
                           skipTestCase(testSenarios,STORY_ECR46_823);
                     }
              } catch (Throwable ex) {
                     commonErrorUpdate(ex);
              } finally {
                     signOut();
                     updateTC(cdto);
              }
       }
       
       @Test(enabled = true)
       public void  RecordsOptionsPage() {
              try {
                     int row = 5; // CCAUserscreenchangesTest
                     String testSenarios=selectRowAndReturnSenarios(row);
                     if ("CCARecordsOptionsTest".equalsIgnoreCase(testSenarios)
                                  && findData("Run").equalsIgnoreCase("yes")) {
                            startTestWithDescription(AppUtil.TEST_NAME,STORY_ECR46_760,testSenarios); // Row required you may change row as per your data
                           page.validateCCARecordsOptions(cdto.getCustomerId(), cdto.getDepartmentId());
                           cdto.setRunStatus(AppUtil.PASSED);
                           commonInfoLog(testSenarios + " scenario completed");
                     } else {
                           skipTestCase(testSenarios,STORY_ECR46_760);
                     }
              } catch (Throwable ex) {
                     commonErrorUpdate(ex);
              } finally {
                     signOut();
                     updateTC(cdto);
              }
       }
}
