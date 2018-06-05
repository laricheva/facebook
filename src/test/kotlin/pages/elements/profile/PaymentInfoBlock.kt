package pages.elements.profile

import base.BaseValidation
import constants.*
import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.*
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Payment Info Block Tests")
class PaymentInfoBlock: BaseProfilePageUi() {

    val finishButton = getByClass(SAVE_AND_NEXT_BUTTON_CLASS)
    val incompletePopUp = getByClass(INCOMPLETE_POPUP_CLASS)
    val addPaymentInfoButton = getByExactText(localization.profile.common.addPaymentInfo)
    val addDriverInfoButton = getByExactText(localization.profile.common.addDriverInfo)
    val paymentInfoHeader = getByExactText(localization.profile.paymentInfo.bankingAccountInfo)

    private val youRadioButton = getById(YOU_RADIO_BUTTON_ID)
    private val youRadioButtonContainer = getByXpath(YOU_RADIO_BUTTON_CONTAINER_XPATH)
    private val otherRadioButton = getByXpath(OTHER_RADIO_BUTTON_XPATH)
    private val payableNameInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, RECEIVER_NAME)
    private val closeButton = getByClass(CLOSE_BUTTON_CLASS)
    private val needHelpLink = getByExactText(localization.profile.common.needHelp)
    private val popUp = getByClass(POPUP_CLASS)
    private val subjectDropDown = getByExactText(localization.common.pleaseSelect)
    private val messageInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, MESSAGE)
    private val dropdownOtherItem = getByExactText(localization.common.other)
    private val popupSendButton = getByClass(POPUP_SEND_BUTTON_CLASS)
    private val bankNameInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, BANK_NAME)
    private val bankBranchCityInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, BANK_BRANCH_CITY)
    private val ibanNumberInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, IBAN)
    private val vatNumberInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, VAT_NUMBER)
    private val bankNameErrorTooltip = getByXpath(BANK_NAME_ERROR_TOOLTIP_XPATH)
    private val bankBranchCityErrorTooltip = getByXpath(BANK_BRANCH_CITY_ERROR_TOOLTIP_XPATH)
    private val ibanErrorTooltip = ibanNumberInput.getInputErrorTooltip()
    private val vatNumberErrorTooltip = vatNumberInput.getInputErrorTooltip()
    private val payableNameErrorTooltip = getByXpath(PAYABLE_NAME_ERROR_TOOLTIP_XPATH)
    private val errorCloseButton = getByClass(ERROR_CLOSE_BUTTON_CLASS)
    val percentagesFullImage = getByXpath(PAYMENT_INFO_PERCENTAGES_FULL_XPATH)

    private val PAYABLE_NAME_TEXT = "Your Name"
    private val BANK_NAME_TEXT = "Raiffeisen"
    private val IBAN_TEXT = "NL 12 ABCD 1234567890"
    private val CITY_TEXT = "Amsterdam"
    private val VAT_TEXT = "NL123456789B12"

    private val baseValidation = BaseValidation()

    @Before
    fun goToPaymentInfoBeforeTest() {
        navigateToPaymentInfo()
    }

    @Test
    @DisplayName("Default radio button Test")
    @Description("'You' radio button should be checked by default")
    fun defaultCheckedRadioButtonTest() {
        assertTrue("'You' radio button is not checked by default", youRadioButton.isSelected)
    }

    @Test
    @DisplayName("Default 'Payable Name' Test")
    @Description("'Payable Name' should not be empty by default")
    fun defaultPayableNameTest() {
        assertFalse("'Payable Name' is empty by default", payableNameInput.checkIsEmpty())
    }

    @Test
    @DisplayName("'Payable Name' after clicking Test")
    @Description("After clicking 'Other' radio button 'Payable Name' should be empty")
    fun payableNameBecameEmptyTest() {
        otherRadioButton.click()
        assertTrue("'Payable Name' is not empty after clicking 'Other' radio button", payableNameInput.checkIsEmpty())
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
    @DisplayName("Pop up Test")
    @Description("Pop up should disappear after sending message")
    fun popUpTest() {
        needHelpLink.click()
        waitForElement(popUp)
        subjectDropDown.click()
        waitForElement(dropdownOtherItem)
        dropdownOtherItem.click()
        messageInput.value = "My Problem"
        popupSendButton.click()
        waitForElementDisappear(popUp)
        assertFalse("Pop up does not disappear", popUp.exists())
    }

    @Test
    @DisplayName("All fields required Test")
    @Description("If click 'Finish' button with empty fields 'Incomplete popup' should appear with 'Add missing payment info' button")
    fun requiredAllFieldsTest() {
        if (errorCloseButton.exists()) {
            errorCloseButton.click()
        }
        otherRadioButton.click()
        finishButton.click()
        waitForElement(incompletePopUp)
        assertTrue("Add 'Add missing payment info' doesn't appear", addPaymentInfoButton.exists())
        addPaymentInfoButton.click()
        youRadioButtonContainer.click()
    }

    @Test
    @DisplayName("Success Test")
    @Description("Fill all required fields and click 'Finish' and 'Incomplete' popup should appear without 'Add missing payment info' button")
    fun successTest() {
        fillAllRequiredFields()
        waitForElement(incompletePopUp)
        assertFalse("'Add missing payment info' button exists",addPaymentInfoButton.exists())
    }

    @Test
    @DisplayName("Percentages after success Test")
    @Description("Fill all required fields and click 'Finish' and 'Payment info' percentages should be 100%")
    fun successPercentagesTest() {
        fillAllRequiredFields()
        waitForElement(incompletePopUp)
        addDriverInfoButton.click()
        assertTrue("Percent of 'Payment info' is not correct", percentagesFullImage.exists())
    }

    @Test
    @DisplayName("Bank name invalid Test")
    @Description("If enter invalid characters in 'Bank name' error tooltip should appear")
    fun bankNameInvalidTest() {
        baseValidation.bankNameInvalidTest(bankNameInput, bankNameErrorTooltip)
    }

    @Test
    @DisplayName("Bank name length validation Test")
    @Description("If enter longer than 255-length value in 'Bank name' it will contain 255 characters")
    fun bankNameBigLengthTest() {
        baseValidation.bankNameBigLengthTest(bankNameInput)
    }

    @Test
    @DisplayName("Bank branch city invalid Test")
    @Description("If enter a invalid characters in 'Bank branch city' error tooltip should appear")
    fun bankBranchCityInvaildTest() {
        baseValidation.bankBranchCityInvalidTest(bankBranchCityInput, bankBranchCityErrorTooltip)
    }

    @Test
    @DisplayName("Bank branch city length validation Test")
    @Description("If enter longer than 63-length value in 'Bank branch city' it will contain 63 characters")
    fun bankBranchCityBigLengthTest() {
        baseValidation.bankBranchCityBigLength(bankBranchCityInput)
    }

    @Test
    @DisplayName("'IBAN number' invalid format Test")
    @Description("Error tooltip should appear if user enters value which doesn't correspond to IBAN's format")
    fun ibanNumberUnvalidFormatTest() {
        baseValidation.ibanNumberInvalidFormatTest(ibanNumberInput, ibanErrorTooltip)
    }

    @Test
    @DisplayName("IBAN number invalid Test")
    @Description("If user enters invalid character IBAN number input should be empty")
    fun ibanNumberUnvalidCharTest() {
        baseValidation.ibanNumberInvalidCharTest(ibanNumberInput)
    }

    @Test
    @DisplayName("'VAT number' invalid format Test")
    @Description("Error tooltip should appear if user enters value which doesn't correspond to VAT's format")
    fun vatInvalidFormatTest() {
        baseValidation.vatInvalidFormatTest(vatNumberInput, vatNumberErrorTooltip)
    }

    @Test
    @DisplayName("VAT number invalid Test")
    @Description("Error tooltip should appear if user enters invalid character")
    fun vatNotEmptyTest() {
        baseValidation.vatInvalidCharTest(vatNumberInput, vatNumberErrorTooltip)
    }

    @Test
    @DisplayName("'Payable name' invalid Test")
    @Description("If enter a space in 'Payable name' error tooltip should appear")
    fun payableNameNotEmptyTest() {
        otherRadioButton.click()
        baseValidation.payableNameInvalidTest(payableNameInput, payableNameErrorTooltip)
    }

    @Test
    @DisplayName("Payable name big length Test")
    @Description("If enter more than 255-length value into payable name input it will contain 255 characters")
    fun payableNameBigLengthTest() {
        otherRadioButton.click()
        baseValidation.payableNameBigLengthTest(payableNameInput)
    }

    fun fillAllRequiredFields() {
        otherRadioButton.click()
        payableNameInput.value = PAYABLE_NAME_TEXT
        bankNameInput.value = BANK_NAME_TEXT
        bankBranchCityInput.value = CITY_TEXT
        ibanNumberInput.value = IBAN_TEXT
        vatNumberInput.value = VAT_TEXT
        finishButton.click()
    }
}

