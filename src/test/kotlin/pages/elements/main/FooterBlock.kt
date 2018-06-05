package pages.elements.main

import constants.*
import extensions.getById
import extensions.getByXpath
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Footer Block Test")
class FooterBlock: BaseMainPageUi() {

    private val viaVanLink = getById(ID_PAGE_CONTENT, VIA_VAN_LINK_PATH)
    private val supportLink = getById(ID_PAGE_CONTENT, FOOTER_SUPPORT_LINK_PATH)
    private val privacyPolicyLink = getById(ID_PAGE_CONTENT, PRIVACY_POLICY_LINK_PATH)
    private val termsLink = getById(ID_PAGE_CONTENT, TERMS_LINK_PATH)
    private val supportPopUp = getByXpath(SUPPORT_POP_UP_XPATH)
    private val supportPopUpClose = getByXpath(SUPPORT_POP_UP_CLOSE_XPATH)

    @After
    fun afterTest() {
        makeScreenShotOnResult()
    }

    @Test
    @DisplayName("'Privacy Policy' Test")
    @Description("'Privacy Policy' should open $PRIVACY_POLICY_URL")
    fun privacyPolicyNavigation() {
        getTabAt(0)
        privacyPolicyLink.scrollTo()
        privacyPolicyLink.click()
        waitInSeconds(1)
        getTabAt(1)
        assertTrue("'Privacy Policy' navigation is not correct", checkCurrentUrl(PRIVACY_POLICY_URL))
        closeTabAt(1)
        getTabAt(0)
    }

    @Test
    @DisplayName("'Support' Test")
    @Description("'Support' should show Support PopUp")
    fun supportLinkNavigation() {
        supportLink.scrollTo()
        supportLink.click()
        waitInSeconds(1)
        assertTrue("'Support' navigation is not correct", supportPopUp.exists())
        supportPopUpClose.click()
    }

    @Test
    @DisplayName("'Terms' Test")
    @Description("'Terms' should open $TERMS_URL")
    fun termsLinkNavigation() {
        termsLink.scrollTo()
        termsLink.click()
        waitInSeconds(1)
        getTabAt(1)
        assertTrue("'Terms' navigation is not correct", checkCurrentUrl(TERMS_URL))
        closeTabAt(1)
        getTabAt(0)
    }

    @Test
    @DisplayName("'ViaVan' Test")
    @Description("'ViaVan' should open $VIA_VAN_HOME_URL")
    fun viaVanLinkNavigation() {
        viaVanLink.scrollTo()
        viaVanLink.click()
        waitInSeconds(1)
        getTabAt(1)
        assertTrue("'ViaVan' navigation is not correct", checkCurrentUrl(VIA_VAN_HOME_URL))
        closeTabAt(1)
        getTabAt(0)
    }
}