package haunguyen.test.mobile

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.regex.Matcher
import java.util.regex.Pattern

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.util.OSUtil
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver

public class MobileTest {


	/**
	 * Close existing application with Id 
	 * @param appId Id of existing application
	 * @throws IOException
	 */
	@Keyword
	public static void closeExistingApp(String appId) throws IOException {
		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		driver.terminateApp(appId)
	}

	/**
	 * Clear data of existing Android application on MacOS and Windows
	 * @param appId Id of existing application
	 * @throws IOException
	 */
	@Keyword
	public static clearAppData(String appId) throws IOException {
		String deviceOS = MobileDriverFactory.getDeviceOS().toLowerCase()

		if (deviceOS == "android") {
			if (OSUtil.isWindows()) {
				try {
					ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "adb", "shell", "pm", "clear", appId)
					pb.start()
				} catch (Exception e) {
					KeywordUtil.logInfo(e.message())
					KeywordUtil.markPassed("Clear app data successfully!")
				}
			} else if (OSUtil.isMac()) {
				try {
					String currentDir = System.getProperty('user.dir')
					ProcessBuilder pb = new ProcessBuilder(currentDir + '/PreScripts/clear_data.sh', appPackage)
					pb.start()
				} catch (Exception e) {
					KeywordUtil.logInfo(e.message())
				}
			}
		}
	}

	/**
	 * Get package name of existing Android application
	 * @return Package name of existing application
	 * @throws IOException
	 */
	@Keyword
	public static String getAppPackage() throws IOException {
		String appPackage = ""
		String deviceOS = MobileDriverFactory.getDeviceOS().toLowerCase()

		if (deviceOS == "android") {
			AppiumDriver driver = MobileDriverFactory.getDriver()
			AndroidDriver androidDriver = (AndroidDriver) driver
			def pageSource = androidDriver.getPageSource()
			String pattern = "package=\"(\\S*)\" "
			Pattern r = Pattern.compile(pattern)
			Matcher m = r.matcher(pageSource)
			if (m.find()){
				appPackage = m.group(1)
			}
		}

		return appPackage
	}


	@Keyword
	public static void swipeFromRightToLeft(float deviceHeightFactor, float deviceWidthFactor) {

		int device_Height = Mobile.getDeviceHeight()

		int device_Width = Mobile.getDeviceWidth()

		int startY = device_Height / 2

		int endY = startY

		int startX = device_Width * 0.30

		int endX = device_Width * 0.70

		Mobile.swipe(endX, endY, startX, startY)

		String starting = endX.toString() + "," + endY.toString()
		String stopping = startX.toString() + "," + startY.toString()

		KeywordUtil.logInfo("starting(" + starting + ")")
		KeywordUtil.logInfo("stopping(" + stopping + ")")
	}
}
