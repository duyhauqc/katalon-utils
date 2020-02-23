package haunguyen.test.mattermost

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.junit.After

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.logging.model.TestSuiteLogRecord
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.logging.TestSuiteXMLLogParser
import org.eclipse.core.runtime.NullProgressMonitor


import internal.GlobalVariable

public class Notification {

	private static final String USER_NAME = "Katalon Notification"
	private static final String ICON_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Katalon-logo-vector.svg/1200px-Katalon-logo-vector.svg.png"

	/**
	 * Push notification to Mattermost
	 * @param incomingWebhookUrl URL of Incoming Webhook created in Mattermost
	 * @param channelId ID of joined channel need to push in Mattermost
	 */
	@Keyword
	public static void pushToMattermost(String incomingWebhookUrl, String channelId, String projectName) {
		String requestBodyContent = new Notification().createRequestBodyContent(channelId, projectName)
		RequestObject notiRequest = new Notification().createRequest(incomingWebhookUrl, requestBodyContent)

		WS.sendRequest(notiRequest)
	}

	/**
	 * Create POST request to push notification
	 * @param webhookUrl API endpoint
	 * @param requestBodyContent Text body of request
	 * @return A POST request
	 */
	private RequestObject createRequest(String incomingWebhookUrl, String requestBodyContent) {
		def builder = new RestRequestObjectBuilder()

		def requestObject = builder
				.withRestRequestMethod("POST")
				.withRestUrl(incomingWebhookUrl)
				.withHttpHeaders([new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")])
				.withTextBodyContent(requestBodyContent)
				.build()

		return requestObject
	}

	/**
	 * 
	 * @param channelId ID of joined channel need to push in Mattermost
	 * @return Text body content
	 */
	private String createRequestBodyContent(String channelId, String projectName) {
		HashMap<String, String> mapBodyContent = new LinkedHashMap<>()
		mapBodyContent.put("channel",  channelId)
		mapBodyContent.put("username", USER_NAME)
		mapBodyContent.put("icon_url", ICON_URL)
		mapBodyContent.put("text", NotificationContent.getTestSuiteStatusSummary(projectName))

		// Convert map into Json
		def jsonBodyContent = new groovy.json.JsonBuilder(mapBodyContent)

		return jsonBodyContent.toString()
	}
}
