package pages.localization.main

import constants.EN_LINK_NL_TEXT
import constants.Languages
import extensions.getCurrentLocalization
import extensions.setLanguage
import io.qameta.allure.junit4.DisplayName
import org.junit.After
import org.junit.BeforeClass
import org.junit.Test
import pages.elements.profile.BaseProfilePageUi
import resources.Localization

@DisplayName("Login Block in Nl Test")
class LoginBlockNl : BaseMainPage() {

    companion object {

        @BeforeClass
        @JvmStatic
        fun setup() {
            BaseMainPage().switchToNl()
            BaseMainPage().loginLink.click()
            BaseProfilePageUi.waitForElement(BaseMainPage().loginForm)
        }

    }

    @After
    fun switchLang() {
        if (!enLink.exists()) switchToNl()
    }

    private val nl = getCurrentLocalization(Languages.NL) as Localization.Nl

    @Test
    fun loginBlockHeaderTest() {
        isValidLoginTitleText(nl.main.loginLink)
    }

    @Test
    fun loginBlockMessageTest() {
        isValidLoginMessageText(nl.main.inputPhoneNumber)
    }

    @Test
    fun loginBlockButtonTest() {
        isValidLoginButtonText(nl.main.continuee)
    }

    @Test
    fun loginBlockToSignUpLinkTest() {
        isValidLoginLinkToSignUpText(nl.main.dontHaveAccountQ)
    }

    @Test
    fun loginBlockPhoneLabelTest() {
        isValidLoginPhoneLabelText(nl.common.phone)
    }

    @Test
    fun loginBlockPhoneErrorFirstTest() {
        isValidLoginErrorTooltipFirstText(nl.common.fieldMandatory)
        switchToNl()
    }

    @Test
    fun loginBlockPhoneErrorSecondTest() {
        isValidLoginErrorTooltipSecondText(nl.common.mobileNlValidation)
        switchToNl()
    }

    @Test
    fun verifCodeMessageTest() {
        verifCodeMessageTest(nl.main.verificationCodeSent)
        switchToNl()
    }

    @Test
    fun wrongPhoneNumberTest() {
        wrongPhoneNumberTest(nl.main.wrongPhoneNumberQ)
        switchToNl()
    }

    @Test
    fun verifCodeLabelTest() {
        verifCodeLabelTest(nl.main.verificationCode)
        switchToNl()
    }

    @Test
    fun verifCodeEmptyTest() {
        verifCodeEmptyTest(nl.main.emptyVerifCodeMessage)
        switchToNl()
    }

    @Test
    fun wrongVerifCodeTest() {
        wrongVerifCodeTest(nl.main.invalidVerifCodeMessage)
        switchToNl()
    }

    @Test
    fun didntReceiveCodeTest() {
        didntReceiveCodeTest(nl.main.didntReceiveCodeQ)
        switchToNl()
    }

    @Test
    fun resendingCodeMessage() {
        resendingCodeMessage(nl.main.verifCodeResend)
        switchToNl()
    }

    @Test
    fun continueButtonTest() {
        continueButtonTest(nl.main.continuee)
        switchToNl()
    }

    @Test
    fun signUpHereTest() {
        signUpHereTest(nl.main.dontHaveAccountQ + nl.main.signUpHere)
        switchToNl()
    }
}