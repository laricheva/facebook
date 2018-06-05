package pages.elements.main

import com.codeborne.selenide.Configuration
import constants.*
import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import base.BaseValidation

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Login Block Test")
class LoginBlock: BaseMainPageUi() {
    private val loginLink = getByLinkText(localization.main.loginLink)
    private val signUpLink = getByLinkText(localization.main.signUpLink)
    private val flagIconEl = getByClass(LOGIN_ICON_CLASS)
    private val codePrefixEl = getByTag(LOGIN_CODE_TAG)
    private val signUpLinkEl = getByClass(SIGNUP_LINK_CLASS)
    private val phoneInput = getByAttributeAndValue(FORM_CONTROL_NAME, PHONE)
    private val phoneErrorTooltipEl = getLabelByText(localization.common.phone, FOLLOWING_SIBLING_DIV)
    private val codeErrorTooltipEl = getLabelByText(localization.main.verificationCode, FOLLOWING_SIBLING_DIV)
    private val loginButton = getByClass(LOGIN_BUTTON_CLASS, FOLLOWING_SIBLING_BUTTON)
    private val verifCodeInput = getLabelByText(localization.main.verificationCode, PRECEDING_SIBLING_INPUT)
    private val wrongPhoneLink = getByExactText(localization.main.wrongPhoneNumberQ)
    private val didntReceiveCodeQLink = getByLinkText(localization.main.didntReceiveCodeQ)
    private val verifCodeButton = getByText(localization.main.didntReceiveCodeQ, FOLLOWING_SIBLING_BUTTON)
    private val loginForm = getById(LOGIN)
    private val verifCodeResend = getByExactText(localization.main.verifCodeResend)

    private val baseValidation = BaseValidation()

    @Before
    fun goToLogin() {
        loginLink.click()
        waitForElement(loginForm)
    }

    @After
    fun afterTest() {
        makeScreenShotOnResult()
        signUpLink.click()
    }

    @Test
    @DisplayName("Flag icon Test")
    @Description("Flag should have nl icon")
    fun flagIcon() {
        assertTrue("'Flag icon' functionality is not correct", flagIconEl.checkImage("${Configuration.baseUrl}assets/images/nl.png"))
    }

    @Test
    @DisplayName("Phone prefix Test")
    @Description("Phone prefix should be +31")
    fun phonePrefix() {
        assertTrue("'Code prefix' functionality is not correct", codePrefixEl.checkText(AMS_PHONE_CODE));
    }

    @Test
    @DisplayName("'Sign Up' Test")
    @Description("'Sign Up' should open http://54.146.35.74/")
    fun linkToSignUp() {
        assertTrue("'Link to Sign Up' is not correct", signUpLinkEl.checkLinkPath(Configuration.baseUrl));
    }


    @Test
    @DisplayName("Phone number small length Test")
    @Description("Phone error tooltip should have text 'The mobile number should have 9 digits. Please do not include +31'")
    fun phoneNumberSmallLength() {
        baseValidation.phoneNumberSmallLength(loginButton, phoneInput, phoneErrorTooltipEl)
    }

    @Test
    @DisplayName("Phone number big length Test")
    @Description("Phone number input filed should contain 9 digits after attempt to enter more than 9 digits")
    fun phoneNumberBigLength() {
        baseValidation.phoneNumberBigLengthTest(phoneInput)
    }

    @Test
    @DisplayName("Phone number non-digits Test")
    @Description("Phone number input filed should be empty after attempt to enter non-digits characters")
    fun phoneNumberNonDigitsTest() {
        baseValidation.phoneNumberNonDigitsTest(phoneInput)
    }

    @Test
    @DisplayName("Phone number mandatory Test")
    @Description("Phone error tooltip should have text 'The field is mandatory'")
    fun phoneNumberMandatory() {
        baseValidation.fieldCheckMandatory(loginButton, phoneInput, phoneErrorTooltipEl)
    }

    @Test
    @DisplayName("Valid phone number Test")
    @Description("Verify code input should be displayed")
    fun phoneNumberValid() {
        phoneInput.value = LOGIN_PHONE_NUM_AMS;
        loginButton.click()
        assertTrue("'Link to Sign Up' is not correct", verifCodeInput.checkFormControlName("verificationToken"))
    }

    @Test
    @DisplayName("'Wrong phone number?' Test")
    @Description("Phone input should be displayed")
    fun wrongPhoneLink() {
        phoneInput.value = LOGIN_PHONE_NUM_AMS;
        loginButton.click()
        waitForElement(wrongPhoneLink)
        wrongPhoneLink.click()
        assertTrue("'Wrong phone number?' link is not correct", phoneInput.exists())
    }

    @Test
    @DisplayName("'Verification Code field is empty' Test")
    @Description("Verification Code field should have text 'Please input verification code'")
    fun verifCodeEmpty() {
        phoneInput.value = LOGIN_PHONE_NUM_AMS;
        loginButton.click()
        verifCodeButton.click()
        waitInSeconds(1)
        assertTrue("'Verification Code field is empty' validation is not correct", codeErrorTooltipEl.isContainText(localization.main.emptyVerifCodeMessage))
    }

    @Test
    @DisplayName("'Verification code is incorrect' Test")
    @Description("Verification Code field should have text 'That code does not match the one sent to you most recently'")
    fun verifCodeIncorrect() {
        phoneInput.value = LOGIN_PHONE_NUM_AMS
        loginButton.click()
        verifCodeInput.value = "123"
        verifCodeButton.click()
        waitInSeconds(1)
        assertTrue("'Verification code is incorrect' validation is not correct", codeErrorTooltipEl.isContainText(localization.main.invalidVerifCodeMessage))
    }

    @Test
    @DisplayName("'Didn't receive your code' Test")
    @Description("'We’ve sent you a new one...' text should appear")
    fun linkDidntReceiveCode() {
        phoneInput.value = LOGIN_PHONE_NUM_AMS
        loginButton.click()
        waitForElement(didntReceiveCodeQLink)
        didntReceiveCodeQLink.click()
        assertTrue("'Didn't receive your code' link is not correct", verifCodeResend.exists())
    }
}