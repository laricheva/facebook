package pages.elements.profile

import constants.*
import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.*
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.runners.MethodSorters
import org.openqa.selenium.By
import java.util.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Agreements Block Tests")
class AgreementsBlock: BaseProfilePageUi() {

    private val signAgreementButton = getByExactText(localization.profile.agreements.signAgreement)
    private val popUp = getByClass(POPUP_CLASS)
    private val closeButton = getByClass(CLOSE_BUTTON_CLASS)
    private val needHelpLink = getByExactText(localization.profile.common.needHelp)
    private val paymentInfoTitle = getByExactText(localization.profile.paymentInfo.bankingAccountInfo)
    val agreementsSaveAndNextButton = getByClass(AGREEMENTS_SAVE_AND_NEXT_CLASS)

    @Before
    fun goToAgreements() {
        navigateToAgreementsTab()
    }

    @Test
    @DisplayName("'Sign agreement Test'")
    @Description("'Sign agreement' button should be disabled")
    fun signButtonDisabledTest() {
        assertFalse("'Sign agreement' button is not disabled", signAgreementButton.isEnabled)
    }

    @Test
    @DisplayName("'I need help' navigation Test")
    @Description("'I need help' should show popup and title should be 'Need help with this form?'")
    fun needHelpLinkNavigationTest() {
        needHelpLink.click()
        waitForElement(popUp)
        assertTrue("'I need help' link is not correct", popUp.exists() && popUp.isContainText(localization.profile.common.needHelpQuestion))
        closeButton.click()
    }

    @Test
    @DisplayName("Header 'Save & next' button Test")
    @Description("After clicking 'save&next' Payment info tab should be opened")
    fun saveAndNextTest() {
        saveAndNextButton.click()
        waitForElement(paymentInfoTitle)
        assertTrue("'Save & next' button navigation not correct", paymentInfoTitle.exists())
        goBack()
    }

    @Test
    @DisplayName("Agreements 'Save & next' button Test")
    @Description("After clicking 'save&next' Payment info tab should be opened")
    fun agreementsSaveAndNextTest() {
        agreementsSaveAndNextButton.click()
        waitForElement(paymentInfoTitle)
        assertTrue("'Save & next' button navigation not correct", paymentInfoTitle.exists())
        goBack()
    }
}