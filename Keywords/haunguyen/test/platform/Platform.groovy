package haunguyen.test.platform

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.Map

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Platform {

	public static boolean isIOS() {
		return "ios".equals(getDeviceOS().toLowerCase());
	}

	public static boolean isAndroid() {
		return "android".equals(getDeviceOS().toLowerCase());
	}

	public static boolean isWeb() {
		return "web".equals(getDeviceOS().toLowerCase());
	}

	public static String getDeviceOS() {
		ExecutionProperties props = new ExecutionProperties(RunConfiguration.getExecutionProperties());
		String deviceOS = props.getDeviceOS();
		return deviceOS;
	}

	private static class ExecutionProperties {

		Map<String, Object> properties;

		ExecutionProperties(Map<String, Object> properties) {
			this.properties = properties;
		}

		String getDeviceOS() {
			Map<String, Object> drivers = (Map<String, Object>)(this.properties.get("drivers"));
			Map<String, Object> system = (Map<String, Object>)(drivers.get("system"));
			Map<String, String> mobile = (Map<String, String>)(system.get("Mobile"));
			if (mobile == null && system.get("WebUI") != null) {
				return "Web";
			}
			String deviceOS = mobile.get("deviceOS");
			return deviceOS;
		}
	}
}
