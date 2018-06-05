package pages.localization.main

import constants.Languages
import extensions.getCurrentLocalization
import io.qameta.allure.junit4.DisplayName
import org.junit.BeforeClass
import org.junit.Test
import pages.elements.profile.BaseProfilePageUi
import resources.Localization

@DisplayName("Login Block in EN Test")
class LoginBlockEn: BaseMainPage() {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            BaseMainPage().loginLink.click()
            BaseProfilePageUi.waitForElement(BaseMainPage().loginForm)
        }

    }

    private val en = getCurrentLocalization(Languages.EN) as Localization.En

    @Test
    fun loginBlockHeaderTest() {
        isValidLoginTitleText(en.main.loginLink)
    }

    @Test
    fun loginBlockMessageTest() {
        isValidLoginMessageText(en.main.inputPhoneNumber)
    }

    @Test
    fun loginBlockButtonTest() {
        isValidLoginButtonText(en.main.continuee)
    }

    @Test
    fun loginBlockToSignUpLinkTest() {
        isValidLoginLinkToSignUpText(en.main.dontHaveAccountQ)
    }

    @Test
    fun loginBlockPhoneLabelTest() {
        isValidLoginPhoneLabelText(en.common.phone)
    }

    @Test
    fun loginBlockPhoneErrorFirstTest() {
        isValidLoginErrorTooltipFirstText(en.common.fieldMandatory)
    }

    @Test
    fun loginBlockPhoneErrorSecondTest() {
        isValidLoginErrorTooltipSecondText(en.common.mobileNlValidation)
    }

    @Test
    fun verifCodeMessageTest() {
        verifCodeMessageTest(en.main.verificationCodeSent)
    }

    @Test
    fun wrongPhoneNumberTest() {
        wrongPhoneNumberTest(en.main.wrongPhoneNumberQ)
    }

    @Test
    fun verifCodeLabelTest() {
        verifCodeLabelTest(en.main.verificationCode)
    }

    @Test
    fun verifCodeEmptyTest() {
        verifCodeEmptyTest(en.main.emptyVerifCodeMessage)
    }

    @Test
    fun wrongVerifCodeTest() {
        wrongVerifCodeTest(en.main.invalidVerifCodeMessage)
    }

    @Test
    fun didntReceiveCodeTest() {
        didntReceiveCodeTest(en.main.didntReceiveCodeQ)
    }

    @Test
    fun resendingCodeMessage() {
        resendingCodeMessage(en.main.verifCodeResend)
    }

    @Test
    fun continueButtonTest() {
        continueButtonTest(en.main.continuee)
    }

    @Test
    fun signUpHereTest() {
        signUpHereTest(en.main.dontHaveAccountQ + en.main.signUpHere)
    }
}