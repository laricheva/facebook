package base

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.*
import config.initTestConfiguration
import org.junit.AfterClass
import org.junit.BeforeClass
import javax.security.auth.login.Configuration as SelenideConfiguration

open class BaseUiTestClass: BaseTest() {

    companion object {

        init {
            initTestConfiguration()
        }
    }
}
