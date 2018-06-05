package base

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.SelenideElement
import constants.*
import extensions.*
import org.junit.Assert.assertTrue
import org.openqa.selenium.Keys
import java.util.*

class BaseValidation: BaseUiTestClass() {

    val nameMaxLength = 39
    private val cityMaxLength = 63
    private val addressMaxLength = 255
    val phoneLength = 9
    private val zipMaxLength = 15
    private val emailMaxLength = 39
    private val plateNumLength = 6
    private val bankNameLength = 255
    private val bankBranchCityLength = 63
    private val payableNameMaxLength = 255

    private val nameInvalidChars = "\"\\~!@#%^&*()_=+[{]}|;:,<.>/?"
    private val cityInvalidChars = "\"\\~`!@#%^&*()-_=+[{]}|;:,<.>/?"
    private val emailInvalidChars = "<>()\\[].,;: @\"#&`=а-яА-Я%?/"
    private val addressInvalidChars = " \\"
    private val nameValidChars = "'‘’\\- "
    private val cityValidChars = "'‘’\\-"
    private val bankNameInvalidChars = "\"\\ ~!@#%^&*()_=+[{]}|;:,<.>?"
    private val ibanLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val vatInvalidLetters = "abcdefghijklmnopqrstuvwxyzACDEFGHIJKMOPQRSTUVWXYZ"
    private val payableNameValidSpecChars = "\"\\~`!@#%^&*()-_=+[{]}|;:',<.>/?"

    // common

    fun fieldCheckMandatory(nextButton: SelenideElement, nameInput: SelenideElement, nameErrorTooltipEl: SelenideElement) {
        nameInput.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        nextButton.click()
        assertTrue("'Field is mandatory' validation is not correct",
                nameErrorTooltipEl.isContainText(localization.common.fieldMandatory))
    }

    fun checkOptional(nextButton: SelenideElement, input: SelenideElement, nameErrorTooltip: SelenideElement) {
        input.value = EMPTY_STRING
        nextButton.click()
        assertTrue("'Field is optional' validation is not correct", nameErrorTooltip.shouldBe(hidden).exists())
    }

    // Name validation /[^[a-zA-Z'‘’\- ]*[a-zA-Z][a-zA-Z'‘’\- ]*$]{39}/

    fun nameBigLengthTest(nameInput: SelenideElement ) {
        nameInput.sendKeys(randomValueGenerate(LETTERS, nameValidChars, nameMaxLength + 1))
        assertTrue("'Name length' validation is not correct",
                nameInput.value.length == nameMaxLength)
    }

    fun nameMaxLengthTest(nameInput: SelenideElement ) {
        nameInput.sendKeys(randomValueGenerate(LETTERS, nameValidChars, nameMaxLength + 1))
        assertTrue("'Name length' validation is not correct",
                nameInput.value.length == nameMaxLength)
    }

    fun nameInvalid(nextButton: SelenideElement, nameInput: SelenideElement, nameErrorTooltipEl: SelenideElement) {
        nameInput.sendKeys(randomValueGenerate(nameInvalidChars, Random().nextInt(nameMaxLength + 1)))
        nextButton.click()
        waitForElement(nameErrorTooltipEl)
        val nameName = nameInput.getAttribute("name")
        if (nameName == FIRST_NAME) {
            assertTrue("First Name validation is not correct",
                    nameErrorTooltipEl.isContainText(localization.common.firstNameValidation))
        } else {
            assertTrue("Last Name validation is not correct",
                    nameErrorTooltipEl.isContainText(localization.common.lastNameValidation))
        }

    }

    // email validation
    /*r1 = /^(([^<>()\[\]\\.,;:\s@"#&`=а-яА-Я%?\/]+(\.[^<>()\[\]\\.,;:\s@"#&`=а-яА-Я%?\/]+)*)|(".+"))/;
    r2 = /@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    re = r1 + r2 {39}*/

    fun emailWithoutMandCharTest(nextButton: SelenideElement, emailInput: SelenideElement, emailErrorTooltipEl: SelenideElement) {
        val removedCharIndex = generateEmail().indexOf("@")
        emailInput.value = generateEmail().removeRange(removedCharIndex, removedCharIndex + 1)
        nextButton.click()
        assertTrue("'Email invalid validation is not correct",
                emailErrorTooltipEl.isContainText(localization.common.emailInvalid))
    }

    fun emailWithoutDotTest(nextButton: SelenideElement, emailInput: SelenideElement, emailErrorTooltipEl: SelenideElement) {
        val dotIndex = generateEmail().indexOf(".")
        emailInput.sendKeys(generateEmail().removeRange(dotIndex, dotIndex + 1))
        nextButton.click()
        assertTrue("Email validation is not correct",
                emailErrorTooltipEl.isContainText(localization.common.emailInvalid))
    }

    fun emailInvalidCharsTest(nextButton: SelenideElement, emailInput: SelenideElement, emailErrorTooltipEl: SelenideElement) {
        val emailInvalidChar = emailInvalidChars[Random().nextInt(emailInvalidChars.length) + 1]
        emailInput.sendKeys(emailInvalidChar + generateEmail())
        nextButton.click()
        assertTrue("'Email validation is not correct",
                emailErrorTooltipEl.isContainText(localization.common.emailInvalid))
    }

    fun emailMaxLengthTest(nextButton: SelenideElement, emailInput: SelenideElement, emailErrorTooltipEl: SelenideElement) {
        val emailInputValue = generateEmail()
        emailInput.sendKeys(randomValueGenerate(DIGITS, LETTERS, emailMaxLength - emailInputValue.length) + emailInputValue)
        nextButton.click()
        assertTrue("'Email invalid validation is not correct",
                emailErrorTooltipEl.value.isNullOrEmpty())
    }

    fun emailBigLengthTest(nextButton: SelenideElement, emailInput: SelenideElement, emailErrorTooltipEl: SelenideElement) {
        val emailInputValue = generateEmail()
        emailInput.sendKeys(randomValueGenerate(DIGITS, LETTERS, emailMaxLength - emailInputValue.length + 1) + emailInputValue)
        nextButton.click()
        assertTrue("'Email invalid validation is not correct",
                emailInput.value.length == nameMaxLength)
    }

    fun generateEmail(): String {
        val s1 = randomValueGenerate(DIGITS, LETTERS, 7) + "@"
        val s2 = randomValueGenerate(DIGITS, LETTERS, 5) + "."
        val s3 = randomValueGenerate(DIGITS, LETTERS, 3)
        return "$s1$s2$s3"
    }

    //phone validation [0-9]{9}

    fun phoneNumberSmallLength(nextButton: SelenideElement, phoneInput: SelenideElement, phoneErrorTooltipEl: SelenideElement) {
        phoneInput.value = randomValueGenerate(DIGITS, Random().nextInt(phoneLength - 1)+1)
        nextButton.click()
        assertTrue("Phone number length validation is not correct",
                phoneErrorTooltipEl.isContainText(localization.common.mobileNlValidation))
    }

    fun phoneNumberBigLengthTest(phoneInput: SelenideElement) {
        phoneInput.sendKeys(randomValueGenerate(DIGITS, phoneLength + 1))
        assertTrue("Phone number length validation is not correct",
                phoneInput.value.length == phoneLength)
    }

    fun phoneNumberNonDigitsTest(phoneInput: SelenideElement) {
        phoneInput.value = randomValueGenerate(LETTERS, SPEC_CHARS, Random().nextInt(phoneLength)+1)
        assertTrue("Phone number validation is not correct", phoneInput.value.isEmpty());
    }

    // address /[^\s\\]{1,255}/

    fun addressInvalidValueTest(nextButton: SelenideElement, addressInput: SelenideElement, addressErrorTooltip: SelenideElement) {
        addressInput.sendKeys(randomValueGenerate(addressInvalidChars, 1))
        nextButton.click()
        assertTrue("Address validation is not correct", addressErrorTooltip.isContainText(localization.profile.driverInfo.addressInvalid))
    }

    fun addressBigLengthTest(nextButton: SelenideElement, addressInput: SelenideElement) {
        addressInput.sendKeys(randomValueGenerate(LETTERS, DIGITS, SPEC_CHARS, addressMaxLength + 1))
        nextButton.click()
        assertTrue("'Address length validation' is not correct", addressInput.value.length == addressMaxLength)
    }

    //zip {1,15}
    fun zipBigLengthTest(nextButton: SelenideElement, zipInput: SelenideElement) {
        zipInput.sendKeys(randomValueGenerate(LETTERS, DIGITS, SPEC_CHARS, zipMaxLength + 1))
        nextButton.click()
        assertTrue("'zip length' validation is not correct", zipInput.value.length == zipMaxLength)
    }

    // city /^[a-zA-Z'‘’\-]+( [a-zA-Z'‘’\-]+)*$/ {1,63}
    fun cityBigLengthTest(nextButton: SelenideElement, cityInput: SelenideElement) {
        cityInput.sendKeys(randomValueGenerate(LETTERS, cityValidChars, cityMaxLength + 1))
        nextButton.click()
        assertTrue("'City length validation' is not correct", cityInput.value.length == cityMaxLength)
    }

    fun cityInvalidTest(nextButton: SelenideElement, cityInput: SelenideElement, cityErrorTooltip: SelenideElement) {
        cityInput.sendKeys(randomValueGenerate(DIGITS, cityInvalidChars, cityMaxLength))
        nextButton.click()
        assertTrue("'city validation' is not correct", cityErrorTooltip.isContainText(localization.profile.driverInfo.cityValidation))
    }

    // plate number /^[a-zA-Z0-9]{6}$/
    fun plateNumberSmallLengthTest(nextButton: SelenideElement, input: SelenideElement, errorTooltip: SelenideElement) {
        input.value = randomValueGenerate(DIGITS, LETTERS, Random().nextInt(plateNumLength - 1) + 1)
        nextButton.click()
        assertTrue("'Plate number length validation' is not correct", errorTooltip.isContainText(localization.profile.vehicleInfo.plateNumAmsValidation))
    }

    fun plateNumberBigLengthTest(nextButton: SelenideElement, input: SelenideElement) {
        input.value = randomValueGenerate(DIGITS, LETTERS, plateNumLength + 1)
        nextButton.click()
        assertTrue("'Plate number length validation' is not correct", input.value.length == plateNumLength)
    }

    fun plateNumberInvalidTest(nextButton: SelenideElement, input: SelenideElement, errorTooltip: SelenideElement) {
        input.value = randomValueGenerate(SPEC_CHARS, plateNumLength)
        nextButton.click()
        assertTrue("'Plate number validation' is not correct", errorTooltip.isContainText(localization.profile.vehicleInfo.plateNumAmsValidation))
    }

    // bank name /^[ ]*[a-zA-Z0-9'‘’\-]+( [a-zA-Z0-9'‘’\-]+)*[ ]*$/ {0,255}
    fun bankNameInvalidTest(input: SelenideElement, errorTooltip: SelenideElement) {
        input.value = randomValueGenerate(bankNameInvalidChars, 1)
        input.pressEnter()
        assertTrue("Error tooltip does not appear", errorTooltip.exists())
    }

    fun bankNameBigLengthTest(input: SelenideElement) {
        input.value = randomValueGenerate(LETTERS, DIGITS, bankNameLength + 1)
        assertTrue("'Bank name length validation' is not correct",  input.value.length == bankNameLength)
    }

    // bank branch city /^[ ]*[a-zA-Z0-9'‘’\-]+( [a-zA-Z0-9'‘’\-]+)*[ ]*$/ {0,63}
    fun bankBranchCityInvalidTest(input: SelenideElement, errorTooltip: SelenideElement) {
        input.value = randomValueGenerate(bankNameInvalidChars, 1)
        input.pressEnter()
        assertTrue("Error tooltip does not appear", errorTooltip.exists())
    }

    fun bankBranchCityBigLength(input: SelenideElement) {
        input.value = randomValueGenerate(LETTERS, DIGITS, bankBranchCityLength + 1)
        assertTrue("'Bank branch city validation' is not correct", input.value.length == bankBranchCityLength)
    }

    //IBAN number /^[A-Z]{2} [0-9]{2} [A-Z]{4} [0-9]{10}$/
    fun ibanNumberInvalidFormatTest(input: SelenideElement, errorTooltip: SelenideElement) {
        val validIban = ibanGenerate()
        val randomIndex = Random().nextInt(validIban.length)
        val invalidIban = when (randomIndex) {
            0, 1, in 4..7 -> validIban.replaceRange(randomIndex, randomIndex + 1, randomValueGenerate(DIGITS, 1))
            else -> validIban.replaceRange(randomIndex, randomIndex + 1, randomValueGenerate(ibanLetters, 1))
        }
        input.value = invalidIban
        input.pressEnter()
        assertTrue("IBAN invalid format is not detected", errorTooltip.isDisplayed)
    }

    fun ibanNumberInvalidCharTest(input: SelenideElement) {
        input.value = randomValueGenerate(SPEC_CHARS, 1)
        assertTrue("IBAN validation is not correct", input.value.isEmpty())
    }

    fun ibanGenerate(): String {
        val s1 = randomValueGenerate(ibanLetters, 2)
        val s2 = randomValueGenerate(DIGITS, 2)
        val s3 = randomValueGenerate(ibanLetters, 4)
        val s4 = randomValueGenerate(DIGITS, 10)
        return "$s1$s2$s3$s4"
    }
    // VAT number /^NL[0-9]{9}B[0-9]{2}$/
    fun vatInvalidFormatTest(input: SelenideElement, errorTooltip: SelenideElement) {
        val validVat = vatGenerate()
        val randomIndex = Random().nextInt(validVat.length)
        val invalidVat = when (randomIndex) {
            0, 1, 11 -> validVat.replaceRange(randomIndex, randomIndex + 1, randomValueGenerate(DIGITS, vatInvalidLetters, 1))
            else -> validVat.replaceRange(randomIndex, randomIndex + 1, randomValueGenerate("NLB", 1))
        }
        input.value = invalidVat
        input.pressEnter()
        assertTrue("VAT invalid format is not detected", errorTooltip.isDisplayed)
    }

    fun vatInvalidCharTest(input: SelenideElement, errorTooltip: SelenideElement) {
        input.value = randomValueGenerate(SPEC_CHARS, vatInvalidLetters, 1)
        input.pressEnter()
        assertTrue("VAT validation is not correct", errorTooltip.isDisplayed)
    }

    fun vatGenerate(): String {
        val s1 = randomValueGenerate(DIGITS, 9)
        val s2 = randomValueGenerate(DIGITS, 2)
        return "NL${s1}B$s2"
    }

    //other entity payable name /[^\s-]/ {0, 255}
    fun payableNameInvalidTest(input: SelenideElement, errorTooltip: SelenideElement) {
        input.value = SPACE_STRING
        input.pressEnter()
        assertTrue("Payable name validation is not correct", errorTooltip.isDisplayed)
    }

    fun payableNameBigLengthTest(input: SelenideElement) {
        input.value = randomValueGenerate(LETTERS, DIGITS, payableNameValidSpecChars, payableNameMaxLength + 1)
        assertTrue("Payable name length validation is not correct", input.value.length == payableNameMaxLength)
    }
}
