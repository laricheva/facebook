package pages.elements.main

import com.codeborne.selenide.Configuration
import constants.*
import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.Step
import io.qameta.allure.junit4.DisplayName
import org.junit.Assert.assertTrue
import org.junit.runners.MethodSorters
import base.BaseValidation
import org.junit.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Sign Up Block Test")

class SignUpBlock: BaseMainPageUi() {

    private val firstNameInput = getByAttributeAndValue(FORM_CONTROL_NAME, FIRST_NAME)
    private val lastNameInput = getByAttributeAndValue(FORM_CONTROL_NAME, LAST_NAME)
    private val phoneInput = getByAttributeAndValue(FORM_CONTROL_NAME, PHONE)
    private val emailInput = getByAttributeAndValue(FORM_CONTROL_NAME, EMAIL)
    private val flagIconEl = getByClass(LOGIN_ICON_CLASS)
    private val codePrefixEl = getByTag(LOGIN_CODE_TAG)
    private val loginLinkEl = getByClass(LINK_TO_LOGIN_CLASS)
    private val nextButton = getByClass(SIGNUP_NEXT_BUTTON_CLASS)
    private val phoneErrorTooltipEl = getLabelByText(localization.common.phone, FOLLOWING_SIBLING_DIV)
    private val firstNameErrorTooltipEl = getLabelByText(localization.common.firstName, FOLLOWING_SIBLING_DIV)
    private val lastNameErrorTooltipEl = getLabelByText(localization.common.lastName, FOLLOWING_SIBLING_DIV)
    private val emailErrorTooltipEl = getLabelByText(localization.main.email, FOLLOWING_SIBLING_DIV)
    private val linkViaVanTerms = getByExactText(VIAVAN_TERMS_OF_USE_LINK_TEXT)
    private val linkViaVanPrivacyPolicy = getByExactText(VIAVAN_PRIVACY_POLICY_LINK_TEXT)
    private val signUpData = amsSignUp()
    private val questionEl = getByXpath(QUESTIONNAIRE_BLOCK_XPATH)

    val baseValidation = BaseValidation()

    @Before
    fun openMainPage() {
        setUp()
    }

    @After
    fun closeBrowser() {
        makeScreenShotOnResult()
        teardown()
    }

    @Test
    @DisplayName("Flag icon Test")
    @Description("Flag should have nl icon")
    @Step("Check flag icon.")
    fun flagIcon() {
        Assert.assertTrue("'Flag icon' functionality is not correct",
                flagIconEl.checkImage("${Configuration.baseUrl}assets/images/nl.png"))
    }

    @Test
    @DisplayName("Phone prefix Test")
    @Description("Phone prefix should be +31")
    @Step("Check phone prefix.")
    fun phonePrefix() {
        Assert.assertTrue("'Code prefix' functionality is not correct", codePrefixEl.checkText(AMS_PHONE_CODE))
    }

    @Test
    @DisplayName("'Log in here' Test")
    @Description("'Log in here' should open http://54.146.35.74/login")
    @Step("Check 'Login in here'link path.")
    fun linkToSignUp() {
        Assert.assertTrue("'Link to Sign Up' is not correct", loginLinkEl.checkLinkPath("${Configuration.baseUrl}login"))
    }

    @Test
    @DisplayName("'ViaVan's Terms of Use' Test")
    @Description("'ViaVan's Terms of Use' should open $TERMS_URL")
    @Step("Check 'ViaVan's Terms of Use' link path.")
    fun linkToTermsOfUse() {
        Assert.assertTrue("'Link to ViaVan's Terms of Use' is not correct", linkViaVanTerms.checkLinkPath(TERMS_URL))
    }

    @Test
    @DisplayName("'ViaVan's Privacy Policy' Test")
    @Description("'ViaVan's Privacy Policy' should open $PRIVACY_POLICY_URL")
    @Step("Check 'ViaVan's Privacy Policy' link path.")
    fun linkToPrivacyPolicy() {
        Assert.assertTrue("'Link to ViaVan's Privacy Policy' is not correct",
                linkViaVanPrivacyPolicy.checkLinkPath(PRIVACY_POLICY_URL))
    }

    @Test
    @DisplayName("'Next' Test")
    @Description("Sign Up should be successful")
    fun successSignUp() {
        fillSignUpBlock(signUpData.firstName, signUpData.lastName, signUpData.phone, signUpData.email)
        nextButton.click()
        waitForElement(questionEl)
        assertTrue("Sign Up failed", questionEl.exists())
        salesforceBlock.deleteDriver(signUpData.email)
    }

    @Test
    @DisplayName("Phone number mandatory Test")
    @Description("Phone error tooltip should have text 'The field is mandatory'")
    @Step("Don't input anything in Phone Input and click next")
    fun phoneNumberCheckMandatory() {
        baseValidation.fieldCheckMandatory(nextButton, phoneInput, phoneErrorTooltipEl)
    }

    @Test
    @DisplayName("Phone number small length Test")
    @Description("Phone error tooltip should have text 'The mobile number should have 9 digits. Please do not include +31'")
    @Step("Input less than 9 digits in Phone Input and click next.")
    fun phoneNumberSmallLength() {
        baseValidation.phoneNumberSmallLength(nextButton, phoneInput, phoneErrorTooltipEl)
    }

    @Test
    @DisplayName("Phone number big length Test")
    @Description("Phone number input filed should contain 9 digits after attempt to enter more than 9 digits")
    @Step("Input '1234567890' in Phone Input")
    fun phoneNumberBigLengthTest() {
        baseValidation.phoneNumberBigLengthTest(phoneInput)
    }

    @Test
    @DisplayName("Phone number non-digits Test")
    @Description("Phone number input filed should be empty after attempt to enter non-digits characters")
    @Step("Input non-digits in Phone Input")
    fun phoneNumberNonDigitsTest() {
        baseValidation.phoneNumberNonDigitsTest(phoneInput)
    }

    @Test
    @DisplayName("First name mandatory test")
    @Description("Click next button with empty 'First Name' field")
    fun firstNameCheckMandatory() {
        baseValidation.fieldCheckMandatory(nextButton, firstNameInput, firstNameErrorTooltipEl)
    }

    @Test
    @DisplayName("'First Name' big length Test")
    @Description("'First Name' should contain 39 characters after the attempt to enter longer value")
    fun firstNameBigLengthTest() {
        baseValidation.nameBigLengthTest(firstNameInput)
    }

    @Test
    @DisplayName("'First Name' max length Test")
    @Description("'First Name' input with 39-length value should be valid")
    fun firstNameMaxLengthTest() {
        baseValidation.nameMaxLengthTest(firstNameInput)
    }

    @Test
    @DisplayName("'First Name' invalid characters Test")
    @Description("'First Name' error tooltip should have text 'First name may only contain letters and spaces.'")
    fun firstNameInvalid() {
        baseValidation.nameInvalid(nextButton, firstNameInput, firstNameErrorTooltipEl)
    }

    @Test
    @DisplayName("Last name mandatory test")
    @Description("Click next button with empty 'Last Name' field")
    fun lastNameCheckMandatory() {
        baseValidation.fieldCheckMandatory(nextButton, lastNameInput, lastNameErrorTooltipEl)
    }

    @Test
    @DisplayName("'Last Name' big length Test")
    @Description("'Last Name' should contain 39 characters after the attempt to enter longer value")
    fun lastNameBigLengthTest() {
        baseValidation.nameBigLengthTest(lastNameInput)
    }

    @Test
    @DisplayName("'Last Name' max length Test")
    @Description("'Last Name' input with 39-length value should be valid")
    fun lastNameMaxLengthTest() {
        baseValidation.nameMaxLengthTest(lastNameInput)
    }

    @Test
    @DisplayName("'Last Name' invalid characters Test")
    @Description("'Last Name' error tooltip should have text 'First name may only contain letters and spaces.'")
    fun lastNameInvalid() {
        baseValidation.nameInvalid(nextButton, lastNameInput, lastNameErrorTooltipEl)
    }

    @Test
    @DisplayName("Email mandatory Test")
    @Description("Email error tooltip should have text 'The field is mandatory'")
    fun emailCheckMandatory() {
        baseValidation.fieldCheckMandatory(nextButton, emailInput, emailErrorTooltipEl)
    }

    @Test
    @DisplayName("Email non-@ value Test")
    @Description("Email error tooltip should have text 'Invalid email address'")
    fun emailWithoutMandCharTest() {
        baseValidation.emailWithoutMandCharTest(nextButton, emailInput, emailErrorTooltipEl)
    }

    @Test
    @DisplayName("Email without dot value Test")
    @Description("Email error tooltip should have text 'Invalid email address'")
    fun emailWithoutDotTest() {
        baseValidation.emailWithoutDotTest(nextButton, emailInput, emailErrorTooltipEl)
    }

    @Test
    @DisplayName("Email shouldn't contain '<>()\\[\\]\\\\.,;:\\s@\"#&`=а-яА-Я%?\\/' characters")
    @Description("Email error tooltip should have text 'Invalid email address'")
    fun emailInvalidCharsTest() {
        baseValidation.emailInvalidCharsTest(nextButton, emailInput, emailErrorTooltipEl)
    }

    @Test
    @DisplayName("Email max length Test")
    @Description("Email with 39-length value should be valid")
    fun emailMaxLengthTest() {
        baseValidation.emailMaxLengthTest(nextButton, emailInput, emailErrorTooltipEl)
    }

    @Test
    @DisplayName("Email big length Test")
    @Description("Email input should contain 39 characters after the attemp to enter more than 39 characters")
    fun emailBigLengthTest() {
        baseValidation.emailBigLengthTest(nextButton, emailInput, emailErrorTooltipEl)
    }

    @Step("Input {0} in First Name and {1} in Last Name, " +
            "input {2} in phone field and {3} in email field")
    private fun fillSignUpBlock(firstName: String, lastName: String, phone: String, email: String) {
        firstNameInput.sendKeys(firstName)
        lastNameInput.sendKeys(lastName)
        phoneInput.sendKeys(phone)
        emailInput.sendKeys(email)
    }
}