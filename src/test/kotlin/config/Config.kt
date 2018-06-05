package config

import constants.*
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.*
import com.codeborne.selenide.Configuration as SelenideConfiguration

fun initConfiguration() {
    if (System.getProperty(OS_NAME).startsWith(LINUX, true)
            || System.getProperty(OS_NAME).startsWith(MACOS, true)) {
        System.setProperty("webdriver.chrome.driver", "${System.getProperty("user.dir")}/src/test/resources/chromedriver")
    } else if (System.getProperty(OS_NAME).startsWith(WINDOWS , true)) {
        System.setProperty("webdriver.chrome.driver", "${System.getProperty("user.dir")}\\src\\test\\resources\\chromedriver.exe")
    }
    val capabilities = DesiredCapabilities.chrome()
    capabilities.setCapability("chrome.switches", Arrays.asList("--disable-extensions"))
    SelenideConfiguration.browser = "chrome"
    SelenideConfiguration.browserCapabilities = capabilities
}

fun initSalesConfiguration() {
    initConfiguration()
}

fun initTestConfiguration() {
    initConfiguration()
    SelenideConfiguration.baseUrl = TEST_URL
}