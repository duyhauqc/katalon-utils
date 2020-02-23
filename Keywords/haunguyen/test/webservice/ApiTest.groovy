package haunguyen.test.webservice
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import groovy.json.JsonSlurper as JsonSlurper


class ApiTest {

	/**
	 * Get an object from response 
	 * @param requestObject GET request object
	 * @param responseObject Object from response
	 * @return An object with Map type
	 */
	@Keyword
	public static def getResponse(String requestObject, String responseObject) {
		def jsonResponse = WSBuiltInKeywords.sendRequest(findTestObject(requestObject))
		Map textResponse = new JsonSlurper().parseText(jsonResponse.getResponseBodyContent())
		def value = textResponse.get(responseObject)
		return value
	}

	/**
	 * Get a field from respone
	 * @param requestObject GET request object
	 * @param responseObject Object from response
	 * @param objectIndex Index of object from response
	 * @return A field with default datatype
	 */
	@Keyword
	public static def getResponse(String requestObject, String responseObject, int objectIndex) {
		def responseJson = WSBuiltInKeywords.sendRequest(findTestObject(requestObject))
		Map response = new JsonSlurper().parseText(responseJson.getResponseBodyContent())
		def value = response.get(responseObject).get(objectIndex)
		return value
	}
}