package haunguyen.test.mattermost

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.logging.model.TestSuiteLogRecord
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import haunguyen.test.mobile.MobileTest
import haunguyen.test.platform.Platform

import com.kms.katalon.core.logging.TestSuiteXMLLogParser
import org.eclipse.core.runtime.NullProgressMonitor

import internal.GlobalVariable

public class NotificationContent {

	public static String getTestSuiteStatusSummary(String projectName) {
		String reportFolder = RunConfiguration.getReportFolder();

		TestSuiteLogRecord testSuiteLogRecord = new TestSuiteXMLLogParser().readTestSuiteLogFromXMLFiles(reportFolder, new NullProgressMonitor());

		NotificationTemplate template = new NotificationTemplate()

		template.firstRow("Status", testSuiteLogRecord.getSummaryStatus().toString())
				.newRow("Project", projectName + RunConfiguration.getExecutionProfile())
				.newRow("Test Suite", testSuiteLogRecord.getName())
				.newRow("Total Test Cases", testSuiteLogRecord.getTotalTestCases().toString())
				.newRow("Total Passed", testSuiteLogRecord.getTotalPassedTestCases().toString())
				.newRow("Total Failed", testSuiteLogRecord.getTotalFailedTestCases().toString())
				.newRow("Total Error", testSuiteLogRecord.getTotalErrorTestCases().toString())
				.newRow("Total Incomplete", testSuiteLogRecord.getTotalIncompleteTestCases().toString())

		if (Platform.isWeb()) {
			template.newRow("Browser", testSuiteLogRecord.getBrowser())
		} else {
			template.newRow("Device", testSuiteLogRecord.getDeviceName() + " " + Mobile.getDeviceWidth() + "x" + Mobile.getDeviceHeight())
		}

		template.newRow("Host Name", testSuiteLogRecord.getHostName())
				.newRow("Host OS", testSuiteLogRecord.getOs())
				.build()
	}
}
