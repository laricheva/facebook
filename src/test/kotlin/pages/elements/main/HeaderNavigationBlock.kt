package pages.elements.main

import com.codeborne.selenide.Configuration
import constants.*
import extensions.checkColor
import extensions.getById
import extensions.isContainText
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Header Block Test")
class HeaderNavigationBlock: BaseMainPageUi() {

    private val supportLink = getById(ID_NAV, HEADER_SUPPORT_LINK_PATH)
    private val loginLink = getById(ID_NAV, LOGIN_LINK_PATH)
    private val languageLink = getById(ID_NAV, LANGUAGE_LINK_PATH)

    @After
    fun afterTest() {
        makeScreenShotOnResult()
    }

    @Test
    @DisplayName("'Language' color Test")
    @Description("'Language' should have white color")
    fun languageLinkColor() {
        getTabAt(0)
        assertTrue("'Language' color is not correct", languageLink.checkColor(COLOR_WHITE))
    }

    @Test
    @DisplayName("'Language' functionality Test")
    @Description("'Language' should change language to 'Dutch'")
    fun languageLinkFunctionality() {
        languageLink.click()
        waitInSeconds(1)
        getTabAt(0)
        assertTrue("'Language' functionality is not correct", languageLink.isContainText("Engels"))
    }

    @Test
    @DisplayName("'Login' color Test")
    @Description("'Login' should have white color")
    fun loginLinkColor() {
        getTabAt(0)
        assertTrue("'Login' color is not correct", loginLink.checkColor(COLOR_WHITE))
    }

    @Test
    @DisplayName("'Login' navigation Test")
    @Description("'Login' should open '$LOGIN_URL' and be changed to 'Registreren'")
    fun loginLinkNavigation() {
        loginLink.click()
        waitInSeconds(1)
        getTabAt(0)
        assertTrue("'Login' navigation is not correct",
                checkCurrentUrl("${Configuration.baseUrl}login") && loginLink.isContainText("Registreren"))
    }

    @Test
    @DisplayName("'Support' color Test")
    @Description("'Support' should have white color")
    fun supportLinkColor() {
        getTabAt(0)
        assertTrue("'Support' color is not correct", supportLink.checkColor(COLOR_WHITE))
    }

    @Test
    @DisplayName("'Support' navigation Test")
    @Description("'Support' should open '$SUPPORT_URL'")
    fun supportLinkNavigation() {
        supportLink.click()
        waitInSeconds(1)
        getTabAt(1)
        assertTrue("'Support' navigation is not correct", checkCurrentUrl(SUPPORT_URL))
    }
}