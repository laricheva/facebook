package pages.localization.main

import base.BaseTest
import base.BaseUiTest
import base.BaseValidation
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import constants.*
import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.AfterClass
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.openqa.selenium.By
import pages.elements.profile.BaseProfilePageUi
import java.util.*

open class BaseMainPage: BaseUiTest() {

    companion object {

        @BeforeClass @JvmStatic fun setUp() {
            Selenide.open(Configuration.baseUrl)
            BaseTest().switchToCity(AMS)
        }

        @AfterClass @JvmStatic fun teardown() {
            Selenide.close()
        }
    }

    // login
    private val phoneInput = getByAttributeAndValue(FORM_CONTROL_NAME, PHONE)
    private val loginButton = getByClass(SIGNUP_NEXT_BUTTON_CLASS)
    val loginForm = getById(LOGIN)
    private val verifCodeTitle = getById(LOGIN_TITLE_ID1)
    private val verifCodeSentTextEl = loginForm.find(By.xpath(VERIF_CODE_SENT_TEXT_XPATH))
    private val wrongPhoneNumLink = loginForm.find(By.xpath(WRONG_PHONE_NUM_LINK_XPATH))
    private val verifCodeLabel = loginForm.find(By.xpath(VERIF_CODE_LABEL_XPATH))
    private val verifCodeInput = getByAttributeAndValue(FORM_CONTROL_NAME, VERIF_TOKEN_NAME)
    private val verifCodeContinue = loginForm.find(By.xpath(VERIF_CODE_CONTINUE_XPATH))
    private val formSpinner  = loginForm.find(By.className(SPINNER_CLASS))
    private val codeErrorTooltipEl = verifCodeInput.parent().find(By.className(ERROR_TOOLTIP_CLASS))
    private val resendingCodeLink = loginForm.find(By.xpath(RESENDING_CODE_LINK_XPATH))
    private val resendinCodeTextEl = loginForm.find(By.xpath(RESENDING_CODE_TEXT_XPATH))
    private val signUpHereTextEl = loginForm.find(By.className(SIGNUP_HERE_TEXT_CLASS))
    // signup
    private val signUpForm = getById(SIGNUP_ID).parent()
    private val signUpFormTitle = getById(SIGNUP_ID)
    private val firstName = getByAttributeAndValue(FORM_CONTROL_NAME, FIRST_NAME).parent()
    private val firstNameLabel = firstName.find(By.xpath(LABEL))
    private val firstNameErrorTooltip = firstName.find(By.xpath(DIV))
    private val firstNameInput = firstName.find(By.xpath(INPUT))
    private val nextButton = getByClass(SIGNUP_NEXT_BUTTON_CLASS)
    private val lastName = getByAttributeAndValue(FORM_CONTROL_NAME, LAST_NAME).parent()
    private val lastNameLabel = lastName.find(By.xpath(LABEL))
    private val lastNameInput = lastName.find(By.xpath(INPUT))
    private val lastNameErrorTooltip = lastName.find(By.xpath(DIV))
    private val phone = getByAttributeAndValue(FORM_CONTROL_NAME, PHONE).parent()
    private val phoneLabel = phone.find(By.xpath(LABEL))
    private val phoneErrorTooltip = phone.find(By.xpath(DIV))
    private val email = getByAttributeAndValue(FORM_CONTROL_NAME, EMAIL).parent()
    private val emailLabel = email.find(By.xpath(LABEL))
    private val emailErrorTooltip = email.find(By.xpath(DIV))
    private val emailInput = email.find(By.xpath(INPUT))
    private val city = getByAttributeAndValue(FORM_CONTROL_NAME, CITY).parent()
    private val cityLabel = city.find(By.xpath(LABEL))
    private val marketingMaterialsTextEl = getByAttributeAndValue(FOR_ATTRIBUTE, AGREE_AMS_ID)
    private val signUpAgreementTextEl = getByClass(SIGNUP_AGREE_CLASS)
    private val signUpBottomTextEl = signUpForm.find(By.className(SIGNUP_HERE_TEXT_CLASS))
    private val pendingTitle = getByClass(PENDING_TITLE_CLASS)
    private val pendingTextEl = getByClass(PENDING_TEXT_CLASS)
    // main
    private val supportLink = getById(ID_NAV, HEADER_SUPPORT_LINK_PATH)
    val loginLink = getById(ID_NAV, LOGIN_LINK_PATH)
    private val languageLink = getById(ID_NAV, LANGUAGE_LINK_PATH)
    private val weAreComingLink = getByXpath(WE_ARE_COMING_LINK)
    private val joinTeamViavan = getByXpath(JOIN_TEAM_VIAVAN_LINK)
    private val advantagesTitle = getById(ID_PAGE_CONTENT, ADVANTAGES_TITLE_LINK_PATH)
    private val firstAdvantageTitle = getById(ID_PAGE_CONTENT, LOWEST_SERVICE_TITLE_PATH)
    private val firstAdvantageText = getById(ID_PAGE_CONTENT, EARN_MORE_TEXT_PATH)
    private val secondAdvantageTitle = getById(ID_PAGE_CONTENT, THE_EARNINGS_TITLE_PATH)
    private val secondAdvantageText = getById(ID_PAGE_CONTENT, START_EARNING_TEXT_PATH)
    private val thirdAdvantageTitle = getById(ID_PAGE_CONTENT, LIVE_SUPPORT_TITLE_PATH)
    private val thirdAdvantageText = getById(ID_PAGE_CONTENT, TEXT_WITH_A_PERSON_TEXT_PATH)
    private val fourthAdvantageTitle = getById(ID_PAGE_CONTENT, INDUSTRY_LEADING_TITLE_PATH)
    private val fourthAdvantageText = getById(ID_PAGE_CONTENT, WE_MAKE_YOUR_LIFE_TEXT_PATH)
    private val applyNowButton = getById(ID_PAGE_CONTENT, APPLY_NOW_BUTTON_PATH)
    private val sliderNextButton = getById(ID_PAGE_CONTENT, CAROUSEL_NEXT_BUTTON_PATH)
    private val firstElementDriverPartner = getById(ID_PAGE_CONTENT, CAROUSEL_FIRST_ELEMENT_DRIVER_PARTNER_PATH)
    private val secondElementDriverPartner = getById(ID_PAGE_CONTENT, CAROUSEL_SECOND_ELEMENT_DRIVER_PARTNER_PATH)
    private val thirdElementDriverPartner = getById(ID_PAGE_CONTENT, CAROUSEL_THIRD_ELEMENT_DRIVER_PARTNER_PATH)
    private val fourthElementDriverPartner = getById(ID_PAGE_CONTENT, CAROUSEL_FOURTH_ELEMENT_DRIVER_PARTNER_PATH)
    private val firstElementText = getById(ID_PAGE_CONTENT, CAROUSEL_FIRST_ELEMENT_TEXT_PATH)
    private val secondElementText = getById(ID_PAGE_CONTENT, CAROUSEL_SECOND_ELEMENT_TEXT_PATH)
    private val thirdElementText = getById(ID_PAGE_CONTENT, CAROUSEL_THIRD_ELEMENT_TEXT_PATH)
    private val fourthElementText = getById(ID_PAGE_CONTENT, CAROUSEL_FOURTH_ELEMENT_TEXT_PATH)
    private val firstElementJoinMeButton = getById(ID_PAGE_CONTENT, CAROUSEL_FIRST_ELEMENT_BUTTON_PATH)
    private val secondElementJoinMeButton = getById(ID_PAGE_CONTENT, CAROUSEL_SECOND_ELEMENT_BUTTON_PATH)
    private val thirdElementJoinMeButton = getById(ID_PAGE_CONTENT, CAROUSEL_THIRD_ELEMENT_BUTTON_PATH)
    private val fourthElementJoinMeButton = getById(ID_PAGE_CONTENT, CAROUSEL_FOURTH_ELEMENT_BUTTON_PATH)
    private val loginBlockTitle = getByClass(LOGIN_TITLE_CLASS)
    private val loginBlockMessage = getByXpath(LOGIN_MESSAGE_XPATH)
    private val loginBlockPhoneLabel = getByXpath(LOGIN_PHONE_LABEL_XPATH)
    private val loginBlockButton = getByXpath(LOGIN_BUTTON_XPATH)
    private val loginBlockToSignUpLink = getByXpath(LOGIN_TO_SIGN_UP_LINK_XPATH)
    private val loginBlockPhoneInput = getByAttributeAndValue(FORM_CONTROL_NAME, PHONE)
    private val loginBlockPhoneErrorToolip = getByXpath(LOGIN_PHONE_ERROR_TOOLTIP)
    val enLink = getByExactText(EN_LINK_NL_TEXT)

    private val baseValidation = BaseValidation()
    val DRIVER_PARTNER = "Driver Partner"
    private val incorrectPhoneNumber = "13546"

    @DisplayName("'Support' Test")
    @Description("'Support' should have text '{0}'")
    fun isValidSupportLinkText(label: String) {
        assertTrue("Text of 'Support' link is incorrect", checkElementText(supportLink, label))
    }

    @DisplayName("'Login' Test")
    @Description("'Login' should have text '{0}'")
    fun isValidLoginLinkText(label: String) {
        assertTrue("Text of 'Login' link is not correct", checkElementText(loginLink, label))
    }

    @DisplayName("Language Test")
    @Description("Language link should have text '{0}'")
    fun isValidLanguageLinkText(label: String) {
        assertTrue("Text of Language link is not correct", checkElementText(languageLink, label))
    }

    @DisplayName("'We are live...' Test")
    @Description("'We are live...' link should have text '{0}'")
    fun isValidWeAreComingLinkText(label: String) {
        assertTrue("Text of 'We are live...' link is not correct", checkElementText(weAreComingLink, label))
    }

    @DisplayName("'Join team ViaVan' Test")
    @Description("'Join team ViaVan' link should have text '{0}'")
    fun isValidJoinTeamLinkText(label: String) {
        assertTrue("Text of 'Join team ViaVan' link is not correct", checkElementText(joinTeamViavan, label))
    }

    @DisplayName("Advantages title Test")
    @Description("Advantages title should have text '{0}'")
    fun isValidAdvantagesTitleText(label: String) {
        assertTrue("Text of Advantages title is not correct", checkElementText(advantagesTitle, label))
    }

    @DisplayName("First advantage title Test")
    @Description("First advantage title should have text '{0}'")
    fun isValidFirstAdvantageTitleText(label: String) {
        assertTrue("Text of First advantage title is not correct", checkElementText(firstAdvantageTitle, label))
    }

    @DisplayName("Second advantage title Test")
    @Description("Second advantage title should have text '{0}'")
    fun isValidSecondAdvantageTitleText(label: String) {
        assertTrue("Text of Second advantage title is not correct", checkElementText(secondAdvantageTitle, label))
    }

    @DisplayName("Third advantage title Test")
    @Description("Third advantage title should have text '{0}'")
    fun isValidThirdAdvantageTitleText(label: String) {
        assertTrue("Text of Third advantage title is not correct", checkElementText(thirdAdvantageTitle, label))
    }

    @DisplayName("Fourth advantage title Test")
    @Description("Fourth advantage title should have text '{0}'")
    fun isValidFourthAdvantageTitleText(label: String) {
        assertTrue("Text of Fourth advantage title is not correct", checkElementText(fourthAdvantageTitle, label))
    }

    @DisplayName("First advantage text Test")
    @Description("First advantage text should be '{0}'")
    fun isValidFirstAdvantageText(label: String) {
        assertTrue("Text of First advantage is not correct", checkElementText(firstAdvantageText, label))
    }

    @DisplayName("Second advantage text Test")
    @Description("Second advantage text should be '{0}'")
    fun isValidSecondAdvantageText(label: String) {
        assertTrue("Text of Second advantage is not correct", checkElementText(secondAdvantageText, label))
    }

    @DisplayName("Third advantage text Test")
    @Description("Third advantage text should be '{0}'")
    fun isValidThirdAdvantageText(label: String) {
        assertTrue("Text of Third advantage is not correct", checkElementText(thirdAdvantageText, label))
    }

    @DisplayName("Fourth advantage text Test")
    @Description("Fourth advantage text should be '{0}'")
    fun isValidFourthAdvantageText(label: String) {
        assertTrue("Text of Fourth advantage is not correct", checkElementText(fourthAdvantageText, label))
    }

    @DisplayName("'Apply now' button Test")
    @Description("'Apply now' button text should be '{0}'")
    fun isValidApplyNowButtonText(label: String) {
        assertTrue("Text of 'Apply now' button is not correct", checkElementText(applyNowButton, label))
    }

    @DisplayName("First 'Driver Partner' Test")
    @Description("First 'Driver Partner' text should be '{0}'")
    fun isValidFirstDriverPartnerText(label: String) {
        assertTrue("Text of First 'Driver Partner' is not correct", checkElementText(firstElementDriverPartner, label))
    }

    @DisplayName("Second 'Driver Partner' Test")
    @Description("Second 'Driver Partner' text should be '{0}'")
    fun isValidSecondDriverPartnerText(label: String) {
        sliderNextButton.click()
        try {
            assertTrue("Text of Second 'Driver Partner' is not correct", checkElementText(secondElementDriverPartner, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("Third 'Driver Partner' Test")
    @Description("Third 'Driver Partner' text should be '{0}'")
    fun isValidThirdDriverPartnerText(label: String) {
        for (i in 0..1) {
            sliderNextButton.click()
        }
        try {
            assertTrue("Text of Third 'Driver Partner' is not correct", checkElementText(thirdElementDriverPartner, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("Fourth 'Driver Partner' Test")
    @Description("Fourth 'Driver Partner' text should be '{0}'")
    fun isValidFourthDriverPartnerText(label: String) {
        for (i in 0..2) {
            sliderNextButton.click()
        }
        try {
            assertTrue("Text of Fourth 'Driver Partner' is not correct", checkElementText(fourthElementDriverPartner, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("First carousel element Test")
    @Description("First carousel element text should be '{0}'")
    fun isValidFirstElementText(label: String) {
        assertTrue("Text of First carousel element is not correct", checkElementText(firstElementText, label))
    }

    @DisplayName("Second carousel element Test")
    @Description("Second carousel element text should be '{0}'")
    fun isValidSecondElementText(label: String) {
        sliderNextButton.click()
        try {
            assertTrue("Text of Second carousel element is not correct", checkElementText(secondElementText, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("Third carousel element Test")
    @Description("Third carousel element text should be '{0}'")
    fun isValidThirdElementText(label: String) {
        for (i in 0..1) {
            sliderNextButton.click()
        }
        try {
            assertTrue("Text of Third carousel element is not correct", checkElementText(thirdElementText, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("Fourth carousel element Test")
    @Description("Fourth carousel element text should be '{0}'")
    fun isValidFourthElementText(label: String) {
        for (i in 0..2) {
            sliderNextButton.click()
        }
        try {
            assertTrue("Text of Fourth carousel element is not correct", checkElementText(fourthElementText, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("First 'Join me' button Test")
    @Description("First 'Join me' button text should be '{0}'")
    fun isValidFirstJoinMeButtonText(label: String) {
        assertTrue("Text of First 'Join me' button is not correct", checkElementText(firstElementJoinMeButton, label))
    }

    @DisplayName("Second 'Join me' button Test")
    @Description("Second 'Join me' button text should be '{0}'")
    fun isValidSecondJoinMeButtonText(label: String) {
        sliderNextButton.click()
        try {
            assertTrue("Text of Second 'Join me' button is not correct", checkElementText(secondElementJoinMeButton, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("Third 'Join me' button Test")
    @Description("Third 'Join me' button text should be '{0}'")
    fun isValidThirdJoinMeButtonText(label: String) {
        for (i in 0..1) {
            sliderNextButton.click()
        }
        try {
            assertTrue("Text of Third 'Join me' button is not correct", checkElementText(thirdElementJoinMeButton, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("Fourth 'Join me' button Test")
    @Description("Fourth 'Join me' button text should be '{0}'")
    fun isValidFourthJoinMeButtonText(label: String) {
        for (i in 0..2) {
            sliderNextButton.click()
        }
        try {
            assertTrue("Text of Fourth 'Join me' button is not correct", checkElementText(fourthElementJoinMeButton, label))
        } finally {
            reloadToAms()
        }
    }

    @DisplayName("Login title Test")
    @Description("Login title should have text '{0}'")
    fun isValidLoginTitleText(label: String) {
        assertTrue("Text of Login title is incorrect", checkElementText(loginBlockTitle, label))
    }

    @DisplayName("Login message Test")
    @Description("Login message should have text '{0}'")
    fun isValidLoginMessageText(label: String) {
        assertTrue("Text of Login message is incorrect", checkElementText(loginBlockMessage, label))
    }

    @DisplayName("Login button Test")
    @Description("Login button should have text '{0}'")
    fun isValidLoginButtonText(label: String) {
        assertTrue("Text of Login button is incorrect", checkElementText(loginBlockButton, label))
    }

    @DisplayName("Login phone label Test")
    @Description("Login phone label should have text '{0}'")
    fun isValidLoginPhoneLabelText(label: String) {
        assertTrue("Text of Login phone label is incorrect", checkElementText(loginBlockPhoneLabel, label))
    }

    @DisplayName("Login link to Sign Up Test")
    @Description("Login link to Sign Up should have text '{0}'")
    fun isValidLoginLinkToSignUpText(label: String) {
        assertTrue("Text of Login link to Sign Up is incorrect", checkElementText(loginBlockToSignUpLink, label))
    }

    @DisplayName("First Login phone error tooltip Test")
    @Description("Login phone error tooltip should have text '{0}'")
    fun isValidLoginErrorTooltipFirstText(label: String) {
        loginBlockButton.click()
        try {
            assertTrue("Text of Login phone error tooltip is incorrect", checkElementText(loginBlockPhoneErrorToolip, label))
        } finally {
            backToMainPage()
            goToLogin()
        }
    }

    @DisplayName("Second Login phone error tooltip Test")
    @Description("Login phone error tooltip should have text '{0}'")
    fun isValidLoginErrorTooltipSecondText(label: String) {
        loginBlockPhoneInput.value = incorrectPhoneNumber
        loginBlockButton.click()
        try {
            assertTrue("Text of Login phone error tooltip is incorrect", checkElementText(loginBlockPhoneErrorToolip, label))
        } finally {
            backToMainPage()
            goToLogin()
        }
    }

    // login

    @DisplayName("'We have sent verification code...' text Test")
    @Description("After enter phone number in login form appropriate text should appear")
    fun verifCodeMessageTest(verifCodeText: String) {
        val verifCodeTextWithNum = verifCodeText.replace("{{phone}}", LOGIN_PHONE_NUM_AMS)
        goToVerificationCodeScreen()
        assertTrue("Verification code text is not correct", checkElementText(verifCodeSentTextEl, verifCodeTextWithNum))
        backToMainPage()
        goToLogin()
    }

    @DisplayName("'Wrong phone Number?' text Test")
    @Description("After enter phone number in login form link with text '{0}' should appear")
    fun wrongPhoneNumberTest(wrongPhoneNumText: String) {
        goToVerificationCodeScreen()
        assertTrue("Text is not correct", checkElementText(wrongPhoneNumLink, wrongPhoneNumText))
        backToMainPage()
        goToLogin()
    }

    @DisplayName("'Verification code label' text Test")
    @Description("'Verification code' label should have text '{0}'")
    fun verifCodeLabelTest(verifCodeLabelText: String) {
        goToVerificationCodeScreen()
        assertTrue("'Verification code' label text is not correct", checkElementText(verifCodeLabel, verifCodeLabelText))
        backToMainPage()
        goToLogin()
    }

    @DisplayName("Verification code empty message Test")
    @Description("Error tooltip on empty verification code should contain text '{0}'")
    fun verifCodeEmptyTest(errorMessage: String) {
        goToVerificationCodeScreen()
        verifCodeContinue.click()
        formSpinner.waitUntilDisappears()
        assertTrue("Empty verification code error message is not correct", checkElementText(codeErrorTooltipEl, errorMessage))
        backToMainPage()
        goToLogin()
    }

    @DisplayName("Wrong verification code error message Test")
    @Description("Error tooltip on wrong verification code should contain text '{0}'")
    fun wrongVerifCodeTest(errorMessage: String) {
        goToVerificationCodeScreen()
        verifCodeInput.value = randomValueGenerate(DIGITS, 3)
        verifCodeContinue.click()
        formSpinner.waitUntilDisappears()
        assertTrue("Wrong verification code error message is not correct", checkElementText(codeErrorTooltipEl, errorMessage))
        backToMainPage()
        goToLogin()
    }

    @DisplayName("Resending verification code link text Test")
    @Description("Link for resending verification code should contain '{0}' text")
    fun didntReceiveCodeTest(text: String) {
        goToVerificationCodeScreen()
        assertTrue("Text is not correct", checkElementText(resendingCodeLink, text))
        backToMainPage()
        goToLogin()
    }

    @DisplayName("Resending code text Test")
    @Description("Message about resending the code should contain '{0}' text")
    fun resendingCodeMessage(text: String) {
        goToVerificationCodeScreen()
        resendingCodeLink.click()
        assertTrue("Message about resending verification code is not correct", checkElementText(resendinCodeTextEl, text))
        backToMainPage()
        goToLogin()
    }

    @DisplayName("Continue button text Test")
    @Description("Login form submit button should contain '{0}' text")
    fun continueButtonTest(text: String) {
        assertTrue("Login form submit button text is not correct", checkElementText(loginButton, text))
        backToMainPage()
        goToLogin()
    }

    @DisplayName("'Sign up here' text")
    @Description("Login form at the bottom should contain text '{0}'")
    fun signUpHereTest(text: String) {
        assertTrue("Login form bottom text is not correct", checkElementText(signUpHereTextEl, text))
        backToMainPage()
        goToLogin()
    }

    // signup
    @DisplayName("Sign Up from title")
    @Description("Title for 'Sign up' form should contain text '{0}'")
    fun signUPTitleTest(text: String) {
        assertTrue("'Sign up' form title is not correct", checkElementText(signUpFormTitle, text))
    }

    @DisplayName("Sign Up 'First Name' label Test")
    @Description("'First Name' label should contain text '{0}'")
    fun firstNameLabelTest(text: String) {
        assertTrue("'First name' label text is not correct", checkElementText(firstNameLabel, text))
    }

    @DisplayName("First name empty error message Test")
    @Description("Error tooltip on empty first name should contain text '{0}'")
    fun firstNameEmptyErrorMessageTest(text: String) {
        nextButton.click()
        assertTrue("Error message on empty fisrt name is not correct", checkElementText(firstNameErrorTooltip, text))
        resetSignUpForm()
    }

    @DisplayName("First name invalid error message Test")
    @Description("Error tooltip on invalid first name should contain '{0}' text")
    fun firstNameInvalidErrorMessageTest(text: String) {
        firstNameInput.value = randomValueGenerate(DIGITS, 1)
        nextButton.click()
        assertTrue("Invalid first anme error message is not correct", checkElementText(firstNameErrorTooltip, text))
        resetSignUpForm()
    }

    @DisplayName("Sign Up 'Last Name' label Test")
    @Description("'Last Name' label should contain text '{0}'")
    fun lastNameLabelTest(text: String) {
        assertTrue("'Last name' label text is not correct", checkElementText(lastNameLabel, text))
    }

    @DisplayName("Last name empty error message Test")
    @Description("Error tooltip on empty last name should contain text '{0}'")
    fun lastNameEmptyErrorMessageTest(text: String) {
        nextButton.click()
        assertTrue("Error message on empty last name is not correct", checkElementText(lastNameErrorTooltip, text))
        resetSignUpForm()
    }

    @DisplayName("Last name invalid error message Test")
    @Description("Error tooltip on invalid last name should contain '{0}' text")
    fun lastNameInvalidErrorMessageTest(text: String) {
        lastNameInput.value = randomValueGenerate(DIGITS, 1)
        nextButton.click()
        assertTrue("Invalid last name error message is not correct", checkElementText(lastNameErrorTooltip, text))
        resetSignUpForm()
    }

    @DisplayName("Sign Up 'Phone' label Test")
    @Description("'Phone' label should contain text '{0}'")
    fun phoneLabelTest(text: String) {
        assertTrue("'Phone' label text is not correct", checkElementText(phoneLabel, text))
    }

    @DisplayName("Phone empty error message Test")
    @Description("Error tooltip on empty phone should contain text '{0}'")
    fun phoneEmptyErrorMessageTest(text: String) {
        nextButton.click()
        assertTrue("Error message on empty phone is not correct", checkElementText(phoneErrorTooltip, text))
        resetSignUpForm()
    }

    @DisplayName("Phone invalid error message Test")
    @Description("Error tooltip on invalid phone should contain '{0}' text")
    fun phoneInvalidErrorMessageTest(text: String) {
        phoneInput.value = randomValueGenerate(DIGITS, 1)
        nextButton.click()
        assertTrue("Invalid phone error message is not correct", checkElementText(phoneErrorTooltip, text))
        resetSignUpForm()
    }

    @DisplayName("Sign Up 'Email' label Test")
    @Description("'Email' label should contain text '{0}'")
    fun emailLabelTest(text: String) {
        assertTrue("'Email' label text is not correct", checkElementText(emailLabel, text))
    }

    @DisplayName("Email empty error message Test")
    @Description("Error tooltip on empty email should contain text '{0}'")
    fun emailEmptyErrorMessageTest(text: String) {
        nextButton.click()
        assertTrue("Error message on empty email is not correct", checkElementText(emailErrorTooltip, text))
        resetSignUpForm()
    }

    @DisplayName("Email invalid error message Test")
    @Description("Error tooltip on invalid email should contain '{0}' text")
    fun emailInvalidErrorMessageTest(text: String) {
        emailInput.value = randomValueGenerate(DIGITS, 1)
        nextButton.click()
        assertTrue("Invalid email error message is not correct", checkElementText(emailErrorTooltip, text))
        resetSignUpForm()
    }

    @DisplayName("Sign Up 'City' label Test")
    @Description("'City' label should contain text '{0}'")
    fun cityLabelTest(text: String) {
        assertTrue("'City' label text is not correct", checkElementText(cityLabel, text))
    }

    @DisplayName("Marketing materials text Test")
    @Description("Message about marketing materials should contain text '{0}'")
    fun marketingMaterialsTextTest(text: String) {
        assertTrue("Text about marketing materials is not correct", checkElementText(marketingMaterialsTextEl, text))
    }

    @DisplayName("Sign up agreement text Test")
    @Description("Sign up agreement text should contain '{0}'")
    fun signUpAgreementTextTest(text: String) {
        assertTrue("Sign up agreement text is not correct", checkElementText(signUpAgreementTextEl, text))
    }

    @DisplayName("Sign up submit button text Test")
    @Description("Sign up submit button should contain text '{0}'")
    fun signUpSubmitButtonTest(text: String) {
        assertTrue("Sign up submit button text is not correct", checkElementText(nextButton, text))
    }

    @DisplayName("Sign up bottom text Test")
    @Description("Sign up bottom text should be '{0}'")
    fun signUpBottomTextTest(text: String) {
        assertTrue("Sign up bottom text is not correct", checkElementText(signUpBottomTextEl, text))
    }

    @DisplayName("Sign up pending title Test")
    @Description("Sign up pending title should contain '{0}' text")
    fun signUpPendingTitleTest(text: String) {
        sendSignUpData()
        assertTrue("Sign up pending title text is not correct", checkElementText(pendingTitle, text))
        backToMainPage()
    }

    @DisplayName("Sign up pending text Test")
    @Description("Sign up pending text should contain '{0}'")
    fun signUpPendingTextTest(text: String) {
        sendSignUpData()
        assertTrue("Sign up pending text is not correct", checkElementText(pendingTextEl, text))
        backToMainPage()
    }

    private fun checkElementText(element: SelenideElement, text: String): Boolean {
        waitForElement(element)
        element.scrollTo()
        return element.text == text
    }

    private fun goToVerificationCodeScreen() {
        phoneInput.value = LOGIN_PHONE_NUM_AMS;
        loginButton.click()
        waitForElement(verifCodeTitle)
    }

    private fun backToMainPage() {
        Selenide.close()
        Selenide.open(TEST_URL)
        switchToCity(AMS)
    }

    private fun resetSignUpForm() {
        reloadPage()
        waitForElement(signUpForm)
        switchToCity(AMS)
    }

    private fun sendSignUpData() {
        firstNameInput.value = randomValueGenerate(LETTERS, Random().nextInt(baseValidation.nameMaxLength) + 1)
        lastNameInput.value = randomValueGenerate(LETTERS, Random().nextInt(baseValidation.nameMaxLength) + 1)
        phoneInput.value = "20${randomValueGenerate(DIGITS, baseValidation.phoneLength - 2)}"
        emailInput.value = "${phoneInput.value}@softteco.com"
        nextButton.click()
    }

    private fun goToLogin() {
        loginLink.click()
        waitForElement(loginForm)
    }

    private fun reloadToAms() {
        reloadPage()
        switchToCity(AMS)
    }

    fun switchToNl() {
        setLanguage(Languages.NL)
        BaseProfilePageUi.waitForElement(enLink)
    }

}
