package haunguyen.test.webui

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class WebUITest {

	/**
	 * Add a new cookie and refresh the web page
	 * @param name The name of the cookie
	 * @param value The value that the cookie contains
	 * @throws IOException
	 */
	@Keyword
	public static void addCookieValue(String name, String value) throws IOException {
		String browserName = DriverFactory.getExecutedBrowser().getName().toLowerCase()

		if (browserName.contains("chrome" == true) || browserName.contains("firefox" == true) || browserName == "ie_driver") {
			Cookie cookie = new Cookie(name, value)
			WebDriver driver = DriverFactory.getWebDriver()
			driver .manage().addCookie(cookie)
		}

		WebUI.refresh()
	}
}
