package pages.elements.profile

import base.BaseValidation
import com.codeborne.selenide.Condition
import constants.*
import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.Step
import io.qameta.allure.junit4.DisplayName
import org.junit.*
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.runners.MethodSorters
import pages.salesforce.SalesforceBlock
import java.io.File
import java.util.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Driver Info Block Test")
class DriverInfoBlock: BaseProfilePageUi() {

    val firstNameInput = getByAttributeAndValue(NAME, FIRST_NAME)
    private val firstNameErrorTooltip = firstNameInput.getInputErrorTooltip()

    val lastNameInput = getByAttributeAndValue(NAME, LAST_NAME)
    private val lastNameErrorTooltip = lastNameInput.getInputErrorTooltip()

    val emailInput = getByAttributeAndValue(NAME, EMAIL)
    private val emailErrorTooltip = emailInput.getInputErrorTooltip()

    val phoneInput = getByAttributeAndValue(NAME, PHONE)
    private val phoneErrorTooltip = phoneInput.parent().getInputErrorTooltip()

    val zipInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, ZIP)
    private val zipErrorTooltip = getByXpath(ZIP_ERROR_TOOLTIP_XPATH)

    val cityInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, CITY)
    private val cityErrorTooltip = cityInput.getInputErrorTooltip()

    private val contactUsLink = getByExactText(localization.profile.driverInfo.contactUs)
    private val popUp = getByClass(POPUP_CLASS)
    private val closeButton = getByClass(CLOSE_BUTTON_CLASS)
    private val subjectDropDown = getByExactText(localization.common.pleaseSelect)
    private val messageInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, MESSAGE)
    private val dropdownOtherItem = getByExactText(localization.common.other)
    private val popupSendButton = getByClass(POPUP_SEND_BUTTON_CLASS)
    private val dobEmptyErrorTooltip = getByXpath(DOB_EMPTY_ERROR_TOOLTIP_XPATH)
    private val dobPartErrorTooltip = getByXpath(DOB_PART_ERROR_TOOLTIP_XPATH)
    val dobDayDropDown = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, DAY)
    val dobMonthDropDown = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, MONTH)
    val dobYearDropDown = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, YEAR)
    private val daysList = getAllByXpath(DOB_DAY_LIST_XPATH)
    private val monthsList = getAllByXpath(DOB_MONTH_LIST_XPATH)
    private val yearsList = getAllByXpath(DOB_YEAR_LIST_XPATH)

    val addressInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, ADDRESS_ONE)
    private val addressErrorTooltip = addressInput.getInputErrorTooltip()

    private val errorOnTheTopField = getByClass(ERROR_ON_TOP_CLASS)

    val aptSuiteEtcInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, ADDRESS_TWO)
    private val aptSuiteErrorTooltip = aptSuiteEtcInput.getInputErrorTooltip()

    private val needHelpLink = getByExactText(localization.profile.common.needHelp)

    private val profileImageInput = getById(PROFILE_UPLOAD_INPUT_ID)
    private val profileImage = getByXpath(PROFILE_IMAGE_XPATH)
    private val profileImageUpdateButton = getByXpath(PROFILE_IMAGE_UPDATE_XPATH)
    private val profileImageUploadBar = getImageUploadBar(localization.profile.driverInfo.profileImage)

    private val taxiImageInput = getById(TAXI_UPLOAD_INPUT_ID)
    private val taxiImage = getByXpath(TAXI_IMAGE_XPATH)
    private val taxiImageUploadBar = getImageUploadBar(localization.profile.driverInfo.taxivergunningbewijs)
    private val taxiImageUpdateButton = getByXpath(TAXI_IMAGE_UPDATE_XPATH)

    private val proDriverLicenseImageInput = getById(PRO_DRIVER_LICENSE_UPLOAD_INPUT_ID)
    private val proDriverLicenseImage = getByXpath(PRO_DRIVER_LICENSE_IMAGE)
    private val proDriverLicenseImageUpdateButton = getByXpath(PROD_DRIVER_LICENSE_IMAGE_UPDATE_XPATH)
    private val proDriverLicenseImageUploadBar = getImageUploadBar(localization.profile.driverInfo.profDriverLicenseAms)

    private val backDriverLicenseImageInput = getById(BACK_DRIVER_LICENSE_UPLOAD_INPUT_ID)
    private val backDriverLicenseImage = getByXpath(BACK_DRIVER_LICENSE_IMAGE)
    private val backDriverLicenseImageUploadBar = getImageUploadBar(localization.profile.vehicleInfo.rijbewijsLicenseBack)
    private val backDriverLicenseImageUpdateButton = getByXpath(BACK_DRIVER_LICENSE_IMAGE_UPDATE_XPATH)

    private val frontDriverLicenseImageInput = getById(FRONT_DRIVER_LICENSE_UPLOAD_INPUT_ID)
    private val frontDriverLicenseImage = getByXpath(FRONT_DRIVER_LICENSE_IMAGE)
    private val frontDriverLicenseImageUpdateButton = getByXpath(FRONT_DRIVER_LICENSE_IMAGE_UPDATE_XPATH)
    private val frontDriverLicenseImageUploadBar = getImageUploadBar(localization.profile.driverInfo.rijbewijsLicenseFront)

    private val tramwayImageInput = getById(TRAMWAY_UPLOAD_INPUT_ID)
    private val tramwayImage = getByXpath(TRAMWAY_IMAGE_XPATH)
    private val tramwayImageUpdateButton = getByXpath(TRAMWAY_IMAGE_UPDATE_XPATH)
    private val tramwayImageUploadBar = getImageUploadBar(localization.profile.driverInfo.tramwayExemptionAms)

    private val vehicleInfoTitle = getAllByClass(ACTIVE_SIDEBAR_TAB_CLASS).findBy(Condition.text(localization.profile.common.vehicleInfo))

    private val random = Random()
    private val baseValidation = BaseValidation()

    @Before
    fun goToDriverInfo() {
        navigateToDriverInfo()
    }

    @After
    override fun afterTest() {
        makeScreenShotOnResult()
        SalesforceBlock().deleteDriver(signUpData.email)
        close()
    }

    @Test
    @DisplayName("'First name' input Test")
    @Description("'First name' input shouldn't be empty")
    fun firstNameInputTest() {
        assertFalse("'First name' is not correct", firstNameInput.checkIsEmpty())
    }

    @Test
    @DisplayName("'Last name' input Test")
    @Description("'Last name' input shouldn't be empty")
    fun lastNameInputTest() {
        assertFalse("'Last name' is not correct", lastNameInput.checkIsEmpty())
    }

    @Test
    @DisplayName("'Email' input Test")
    @Description("'Email' input shouldn't be empty")
    fun emailInputTest() {
        assertFalse("'Email' is not correct", emailInput.checkIsEmpty())
    }

    @Test
    @DisplayName("'Phone' input Test")
    @Description("'Phone' input shouldn't be empty")
    fun phoneInputTest() {
        assertFalse("'Phone' is not correct", phoneInput.checkIsEmpty())
    }

    @Test
    @DisplayName("'Contact us' link Test")
    @Description("'Contact us' link on click should show popup and title should be 'Need help with this form?'")
    fun contactUsTest() {
        contactUsLink.click()
        waitForElement(popUp)
        assertTrue("'Contact us' link is not correct", popUp.exists() && popUp.isContainText(localization.profile.common.needHelpQuestion))
        closeButton.click()
    }

    @Test
    @DisplayName("'Contact us' popup Subject Test")
    @Description("'Contact us' popup: Subject dropdown placeholder shouldn't be empty.")
    fun contactUsPopupSubjectPlaceholderTest() {
        contactUsLink.click()
        waitForElement(popUp)
        assertFalse("'Contact us' popup: Subject dropdown placeholder is empty", subjectDropDown.checkIsEmpty())
        closeButton.click()
    }

    @Test
    @DisplayName("'Contact us' popup: Message placeholder empty Test")
    @Description("'Contact us' popup: Message placeholder shouldn't be empty.")
    fun contactUsPopupMessagePlaceholderEmptyTest() {
        contactUsLink.click()
        waitForElement(popUp)
        assertFalse("'Contact us' popup: Message placeholder is empty", messageInput.isPlaceholderEmpty())
        closeButton.click()
    }

    @Test
    @DisplayName("'Contact us' popup: Message placeholder correct text Test")
    @Description("'Contact us' popup: Message placeholder should contain text 'Type your message here'.")
    fun contactUsPopupMessagePlaceholderCorrectTextTest() {
        contactUsLink.click()
        waitForElement(popUp)
        assertTrue("'Contact us' popup: Message placeholder doesn't contain correct text",
                messageInput.isPlaceholderContainsText(localization.profile.common.typeYourMessage))
        closeButton.click()
    }

    @Test
    @DisplayName("'Contact us' popup Test")
    @Description("'Contact us' popup should disappear.")
    fun contactUsSuccessTest() {
        contactUsLink.click()
        waitForElement(popUp)
        subjectDropDown.click()
        waitForElement(dropdownOtherItem)
        dropdownOtherItem.click()
        messageInput.value = "My Problem"
        popupSendButton.click()
        waitInSeconds(5)
        assertFalse("'Contact us' popup: Message placeholder is empty", popUp.exists())
    }

    @Test
    @DisplayName("'Date of Birth' empty Test")
    @Description("If 'Date of Birth' is empty error tooltip should contain text 'This field is mandatory'")
    fun dobEmptyTest() {
        saveAndNextButton.click()
        assertTrue("'Date of Birth' error tooltip is not correct",
        dobEmptyErrorTooltip.isContainText(localization.common.fieldMandatory))
    }

    @Test
    @DisplayName("'Date of Birth' part Test")
    @Description("If 'Date of Birth' is part of date error tooltip should contain text 'Please finish selecting the date'")
    fun dobPartTest() {
        dobDayDropDown.click()
        daysList[random.nextInt(daysList.size - 1)].click()
        saveAndNextButton.click()
        assertTrue("'Date of Birth' error tooltip is not correct",
        dobPartErrorTooltip.isContainText(localization.profile.driverInfo.datePickerError))
    }

    @Test
    @DisplayName("'Date of Birth' success Test")
    @Description("If all fields of 'Date of Birth' are selected error tooltip shouldn't appear")
    fun dobSuccessTest() {
        dobMonthDropDown.click()
        monthsList[random.nextInt(monthsList.size - 1)].click()
        dobDayDropDown.click()
        daysList[random.nextInt(daysList.size - 1)].click()
        dobYearDropDown.click()
        yearsList[random.nextInt(yearsList.size - 1)].click()
        assertFalse("'Date of Birth' error tooltip appears",
                dobPartErrorTooltip.exists() || dobEmptyErrorTooltip.exists())
    }

    @Test
    @DisplayName("'Address' placeholder empty Test")
    @Description("'Address' placeholder shouldn't be empty.")
    fun addressPlaceholderEmptyTest() {
        assertFalse("'Address' placeholder is empty", addressInput.isPlaceholderEmpty())
    }

    @Test
    @DisplayName("'Address' placeholder correct text Test")
    @Description("'Address' placeholder should contain text 'Type address'.")
    fun addressPlaceholderCorrectTextTest() {
        assertTrue("'Address' placeholder doesn't contain correct text", addressInput.isPlaceholderContainsText(localization.profile.driverInfo.typeAddress))
    }

    @Test
    @DisplayName("Error field on the top Test")
    @Description("Error field on the top should appear")
    fun errorOnTheTopTest() {
        saveAndNextButton.click()
        assertTrue("Error on the top doesn't appear", errorOnTheTopField.exists())
        reloadPage()
    }

    @Test
    @DisplayName("'Apt., Suite, etc.' placeholder empty Test")
    @Description("'Apt., Suite, etc.' placeholder shouldn't be empty.")
    fun aptEtcSuitePlaceholderEmptyTest() {
        assertFalse("'Apt., Suite, etc.' placeholder is empty", aptSuiteEtcInput.isPlaceholderEmpty())
    }

    @Test
    @DisplayName("'Apt., Suite, etc.' placeholder correct text Test")
    @Description("'Apt., Suite, etc.' placeholder should contain text 'Type here'.")
    fun aptEtcSuitePlaceholderCorrectTextTest() {
        assertTrue("'Apt., Suite, etc.' placeholder doesn't contain correct text", aptSuiteEtcInput.isPlaceholderContainsText("Type here"))
    }

    @Test
    @DisplayName("'I need help' navigation Test")
    @Description("'I need help' should show popup and title should be 'Need help with this form?'")
    fun needHelpLinkNavigationTest() {
        needHelpLink.scrollTo()
        needHelpLink.click()
        waitForElement(popUp)
        assertTrue("'I need help' link is not correct", popUp.exists() && popUp.isContainText(localization.profile.common.needHelpQuestion))
        closeButton.click()
    }

    @Test
    @DisplayName("Upload image larger then 10MB for profile Test")
    @Description("Browser should open popup with error message")
    fun profileBigImageUploadTest() {
        profileImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct for profile image Test")
    @Description("'Update' button should appear")
    fun profileCorrectImageUploadTest() {
        profileImageInput.uploadCorrectImage(profileImageUploadBar)
        assertTrue("Image wasn't uploaded", profileImageUpdateButton.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for Taxivergunningbewijs Test")
    @Description("Browser should open popup with error message")
    fun taxiBigImageUploadTest() {
        taxiImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct Taxivergunningbewijs image Test")
    @Description("'Update' button should appear")
    fun taxiCorrectImageUploadTest() {
        taxiImageInput.uploadCorrectImage(taxiImageUploadBar)
        assertTrue("Image wasn't uploaded", taxiImageUpdateButton.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for Chauffeurskaart Test")
    @Description("Browser should open popup with error message")
    fun proDriverLicenseBigImageUploadTest() {
        proDriverLicenseImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct Chauffeurskaart image Test")
    @Description("'Update' button should appear")
    fun proDriverLicenseCorrectImageUploadTest() {
        proDriverLicenseImageInput.uploadCorrectImage(proDriverLicenseImageUploadBar)
        assertTrue("Image wasn't uploaded", proDriverLicenseImageUpdateButton.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for Rijbewijs back Test")
    @Description("Browser should open popup with error message")
    fun backDriverLicenseBigImageUploadTest() {
        backDriverLicenseImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct Rijbewijs back image Test")
    @Description("'Update' button should appear")
    fun backDriverLicenseCorrectImageUploadTest() {
        backDriverLicenseImageInput.uploadCorrectImage(backDriverLicenseImageUploadBar)
        assertTrue("Image wasn't uploaded", backDriverLicenseImageUpdateButton.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for Rijbewijs front Test")
    @Description("Browser should open popup with error message")
    fun frontDriverLicenseBigImageUploadTest() {
        frontDriverLicenseImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct Rijbewijs front image Test")
    @Description("'Update' button should appear")
    fun frontDriverLicenseCorrectImageUploadTest() {
        frontDriverLicenseImageInput.uploadCorrectImage(frontDriverLicenseImageUploadBar)
        assertTrue("Image wasn't uploaded", frontDriverLicenseImageUpdateButton.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for Tramway exemption Test")
    @Description("Browser should open popup with error message")
    fun tramwayBigImageUploadTest() {
        tramwayImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct Tramway exemption image Test")
    @Description("'Update' button should appear")
    fun tramwayCorrectImageUploadTest() {
        tramwayImageInput.uploadCorrectImage(tramwayImageUploadBar)
        assertTrue("Image wasn't uploaded", tramwayImageUpdateButton.exists())
    }

    @Test
    @DisplayName("Driver Info success Test")
    @Description("'Vehicle info' page opens after filling mandatory fields on 'Driver info' page and clicking 'save&next' button")
    fun userInfoSuccessTest() {
        val percentagesBlockTests = DriverInfoPercentages()
        percentagesBlockTests.fillDateOfBirth()
        percentagesBlockTests.fillAddressFields()
        saveAndNextButton.click()
        waitForElement(vehicleInfoTitle)
        assertTrue("'Vehicle info' page doesn't open after filling mandatory fields on 'Driver info' page " +
                "and clicking 'save&next' button", vehicleInfoTitle.exists())
    }

    @Test
    @DisplayName("First name mandatory test")
    @Description("Click next button with empty 'First Name' field")
    fun firstNameCheckMandatory() {
        baseValidation.fieldCheckMandatory(saveAndNextButton, firstNameInput, firstNameErrorTooltip)
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
        baseValidation.nameInvalid(saveAndNextButton, firstNameInput, firstNameErrorTooltip)
    }

    @Test
    @DisplayName("'Last Name' empty Test")
    @Description("If 'Last Name' is empty error tooltip should contain text 'This field is mandatory'")
    fun lastNameEmptyTest() {
        baseValidation.fieldCheckMandatory(saveAndNextButton, lastNameInput, lastNameErrorTooltip)
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
        baseValidation.nameInvalid(saveAndNextButton, lastNameInput, lastNameErrorTooltip)
    }

    @Test
    @DisplayName("'Email' empty Test")
    @Description("If 'Email' is empty error tooltip should contain text 'This field is mandatory'")
    fun emailEmptyTest() {
        baseValidation.fieldCheckMandatory(saveAndNextButton, emailInput, emailErrorTooltip)
    }

    @Test
    @DisplayName("Email non-@ value Test")
    @Description("Email error tooltip should have text 'Invalid email address'")
    fun emailWithoutMandCharTest() {
        baseValidation.emailWithoutMandCharTest(saveAndNextButton, emailInput, emailErrorTooltip)
    }

    @Test
    @DisplayName("Check uploaded images after page reloading Test")
    @Description("Load all images and reload page")
    fun checkImagesAfterReloadingTest() {
        loadImages(true)
        reloadPage()
        waitForElement(saveAndNextButton)
        assertTrue("Images are not saved", checkAllImagesExists())
    }

    @Test
    @DisplayName("Email without dot value Test")
    @Description("Email error tooltip should have text 'Invalid email address'")
    fun emailWithoutDotTest() {
        baseValidation.emailWithoutDotTest(saveAndNextButton, emailInput, emailErrorTooltip)
    }

    @Test
    @DisplayName("Email shouldn't contain '<>()\\[\\]\\\\.,;:\\s@\"#&`=а-яА-Я%?\\/' characters")
    @Description("Email error tooltip should have text 'Invalid email address'")
    fun emailInvalidCharsTest() {
        baseValidation.emailInvalidCharsTest(saveAndNextButton, emailInput, emailErrorTooltip)
    }

    @Test
    @DisplayName("Email max length Test")
    @Description("Email with 39-length value should be valid")
    fun emailMaxLengthTest() {
        baseValidation.emailMaxLengthTest(saveAndNextButton, emailInput, emailErrorTooltip)
    }

    @Test
    @DisplayName("Email big length Test")
    @Description("Email input should contain 39 characters after the attemp to enter more than 39 characters")
    fun emailBigLengthTest() {
        baseValidation.emailBigLengthTest(saveAndNextButton, emailInput, emailErrorTooltip)
    }

    @Test
    @DisplayName("'Phone Number' empty Test")
    @Description("If 'Phone Number' is empty error tooltip should contain text 'This field is mandatory'")
    fun phoneEmptyTest() {
        baseValidation.fieldCheckMandatory(saveAndNextButton, phoneInput, phoneErrorTooltip)
    }

    @Test
    @DisplayName("Phone number small length Test")
    @Description("Phone error tooltip should have text 'The mobile number should have 9 digits. Please do not include +31'")
    @Step("Input less than 9 digits in Phone Input and click next.")
    fun phoneNumberSmallLength() {
        baseValidation.phoneNumberSmallLength(saveAndNextButton, phoneInput, phoneErrorTooltip)
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
    @DisplayName("'Address' empty Test")
    @Description("If 'Address' is empty error tooltip should contain text 'This field is mandatory'")
    fun addressEmptyTest() {
        baseValidation.fieldCheckMandatory(saveAndNextButton, addressInput, addressErrorTooltip)
    }

    @Test
    @DisplayName("Invalid address value Test")
    @Description("If address input contains invalid value error tooltip should contain 'Address validation error'")
    fun invalidAddressTest() {
        baseValidation.addressInvalidValueTest(saveAndNextButton, addressInput, addressErrorTooltip)
    }

    @Test
    @DisplayName("Address big length Test")
    @Description("Address input filed should contain 255-length value after the attempt to enter longer value")
    fun addressBigLengthTest() {
        baseValidation.addressBigLengthTest(saveAndNextButton, addressInput)
    }

    @Test
    @DisplayName("Invalid 'Apt., Suite' value Test")
    @Description("If 'Apt., Suite' input contains invalid value error tooltip should contain 'Address validation error'")
    fun invalidAptSuiteTest() {
        baseValidation.addressInvalidValueTest(saveAndNextButton, aptSuiteEtcInput, aptSuiteErrorTooltip)
    }

    @Test
    @DisplayName("'Apt., Suite' field is optional test")
    @Description("Error tooltip should be hidden if the 'Apt., Suite' field is empty")
    fun aptSuiteCheckOptional() {
        baseValidation.checkOptional(saveAndNextButton, aptSuiteEtcInput, aptSuiteErrorTooltip)
    }

    @Test
    @DisplayName("'Apt., Suite' big length Test")
    @Description("'Apt., Suite' input filed should contain 255-length value after the attempt to enter longer value")
    fun aptSuiteBigLengthTest() {
        baseValidation.addressBigLengthTest(saveAndNextButton, aptSuiteEtcInput)
    }

    @Test
    @DisplayName("'Zip' empty Test")
    @Description("If 'Zip' is empty error tooltip should contain text 'This field is mandatory'")
    fun zipEmptyTest() {
        baseValidation.fieldCheckMandatory(saveAndNextButton, zipInput, zipErrorTooltip)
    }

    @Test
    @DisplayName("'Zip' big length Test")
    @Description("'Zip' input filed should contain 15-length value after the attempt to enter longer value")
    fun zipBigLengthTest() {
        baseValidation.zipBigLengthTest(saveAndNextButton, zipInput)
    }

    @Test
    @DisplayName("'City' empty Test")
    @Description("If 'City' is empty error tooltip should contain text 'This field is mandatory'")
    fun cityEmptyTest() {
        baseValidation.fieldCheckMandatory(saveAndNextButton, cityInput, cityErrorTooltip)
    }

    @Test
    @DisplayName("'City' big length Test")
    @Description("'City' input field should conatain 63-length value after the attempt to enter longer one")
    fun cityBigLengthTest() {
        baseValidation.cityBigLengthTest(saveAndNextButton, cityInput)
    }

    @Test
    @DisplayName("'City' invalid value Test")
    @Description("If 'City' input contain invalid value error tooltip should contain text'City may only contain letters and one space between words'")
    fun cityInvalidTest() {
        baseValidation.cityInvalidTest(saveAndNextButton, cityInput, cityErrorTooltip)
    }

    fun loadImages(isWithOptional: Boolean) {
        taxiImageInput.uploadCorrectImage(taxiImageUploadBar)
        proDriverLicenseImageInput.uploadCorrectImage(proDriverLicenseImageUploadBar)
        backDriverLicenseImageInput.uploadCorrectImage(backDriverLicenseImageUploadBar)
        frontDriverLicenseImageInput.uploadCorrectImage(frontDriverLicenseImageUploadBar)
        if (isWithOptional) {
            tramwayImageInput.uploadCorrectImage(tramwayImageUploadBar)
            profileImageInput.uploadCorrectImage(profileImageUploadBar)
        }
    }

    private fun checkAllImagesExists(): Boolean =
            profileImage.exists() && taxiImage.exists() && proDriverLicenseImage.exists() &&
                    backDriverLicenseImage.exists() && frontDriverLicenseImage.exists() && tramwayImage.exists()
}
