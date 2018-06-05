package pages.elements.main

import base.BaseTest
import base.BaseUiTest
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import constants.AMS
import org.junit.AfterClass
import org.junit.BeforeClass
import pages.salesforce.SalesforceBlock
import org.openqa.selenium.chrome.ChromeDriver

open class BaseMainPageUi: BaseUiTest() {

    companion object {
        private val baseTest = BaseTest()

        @BeforeClass
        @JvmStatic fun setUp() {
            Selenide.open(Configuration.baseUrl)
        }

        @AfterClass
        @JvmStatic fun teardown() {
            Selenide.close()
        }
    }

    val salesforceBlock = SalesforceBlock()
}