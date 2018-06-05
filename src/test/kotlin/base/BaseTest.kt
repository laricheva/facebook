package base

import com.codeborne.selenide.Condition
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import constants.*
import extensions.*
import io.qameta.allure.Attachment
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import resources.Localization
import java.io.File

open class BaseTest {

    val localization = getCurrentLocalization(Languages.EN) as Localization.En
    private val AMS_ITEM = "Amsterdam"

    private val cityDropdown = getByAttributeAndValue(FORM_CONTROL_NAME, CITY)

    @Attachment("ScreenShot on result", type = "image/png")
    fun makeScreenShotOnResult(): ByteArray = (WebDriverRunner.getWebDriver() as TakesScreenshot).getScreenshotAs(OutputType.BYTES)

    fun waitInSeconds(seconds: Int) {
        Thread.sleep((seconds * 1000).toLong())
    }

    fun waitInSecondsFloat(seconds: Float) {
        Thread.sleep((seconds * 1000).toLong())
    }

    fun waitForElement(element: SelenideElement) {
        element.waitUntil(Condition.appear, 30000)
    }

    fun waitForElementDisappear(element: SelenideElement) {
        element.waitUntil(Condition.disappear, 30000)
    }

    fun getWebDriver(): WebDriver {
        return WebDriverRunner.getWebDriver()
    }

    fun getTabAt(position: Int) {
        getWebDriver().switchTo().window(getWebDriver().windowHandles.elementAt(position))
    }

    fun closeTabAt(position: Int) {
        getTabAt(position)
        getWebDriver().close()
    }

    fun getImagePath(type: String): String? {
        var file: File? = null
        if (System.getProperty(OS_NAME).startsWith(LINUX, true)
                || System.getProperty(OS_NAME).startsWith(MACOS, true)) {
            file =  File("${System.getProperty("user.dir")}/src/test/resources/$type.jpeg")
        } else if (System.getProperty(OS_NAME).startsWith(WINDOWS , true)) {
            file = File("${System.getProperty("user.dir")}\\src\\test\\resources\\$type.jpeg")
        }
        return file?.absolutePath
    }

    fun goBack() {
        getWebDriver().navigate().back()
    }

    fun reloadPage() {
        getWebDriver().navigate().refresh()
    }

    fun acceptAlert() {
        getWebDriver().switchTo().alert().accept()
    }

    fun isAlertOpened(): Boolean {
        return getWebDriver().switchTo().alert() != null
    }

    fun checkCurrentUrl(path: String): Boolean {
        return WebDriverRunner.getWebDriver().currentUrl == path
    }

    fun checkCurrentRelUrl(path: String): Boolean {
        return WebDriverRunner.getWebDriver().currentUrl.contains(path)
    }

    fun loadIncorrectImageToElement(element: SelenideElement) {
        loadImageToElement(element, BIG_IMAGE)
    }

    fun loadCorrectImageToElement(element: SelenideElement) {
        loadImageToElement(element, CORRECT_IMAGE)
    }

    private fun loadImageToElement(element: SelenideElement, path: String) {
        element.uploadFile(File(getImagePath(path)))
        waitInSeconds(5)
    }

    fun SelenideElement.uploadCorrectImage(uploadingBar: SelenideElement) {
        this.uploadFile(File(getImagePath(CORRECT_IMAGE)))
        uploadingBar.waitUntilDisappears()
    }

    fun switchToCity(city: String) {
        cityDropdown.click()
        when (city) {
            AMS -> {
              getByExactText(AMS_ITEM).click()
          }
        }
    }
}
