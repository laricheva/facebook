package pages.elements.profile

import base.BaseTest
import base.BaseUiTest
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import constants.*
import extensions.*
import io.qameta.allure.Step
import org.junit.After
import org.openqa.selenium.By
import pages.salesforce.SalesforceBlock
import java.util.*

open class BaseProfilePageUi: BaseUiTest() {

    companion object {
        private val baseTest = BaseTest()
        private val firstNameInput = getByAttributeAndValue(FORM_CONTROL_NAME, FIRST_NAME)
        private val lastNameInput = getByAttributeAndValue(FORM_CONTROL_NAME, LAST_NAME)
        private val phoneInput = getByAttributeAndValue(FORM_CONTROL_NAME, PHONE)
        private val emailInput = getByAttributeAndValue(FORM_CONTROL_NAME, EMAIL)
        private val signUpNextButton = getByClass(SIGNUP_NEXT_BUTTON_CLASS)
        private val questionEl = getByXpath(QUESTIONNAIRE_BLOCK_XPATH)
        private val yesButton = getLabelByText(baseTest.localization.main.yes)
        private val questNextButton = getById(QUESTIONNAIRE_NEXT_BUTTON_ID)
        lateinit var signUpData: amsSignUp

        @Step("Sign Up and create new user")
        fun runSignUp() {
            Selenide.open(Configuration.baseUrl)
            baseTest.switchToCity(AMS)
            signUpData = amsSignUp()
            waitForElement(signUpNextButton)
            firstNameInput.value = signUpData.firstName
            lastNameInput.value = signUpData.lastName
            phoneInput.value = signUpData.phone
            emailInput.value = signUpData.email
            signUpNextButton.click()
            waitForElement(questionEl)
        }

        @Step("Answering all questions.")
        private fun questSuccess() {
            clickYesButton()
            questNextButton.click()
            waitInSeconds(5)
        }

        @Step("Clicking 'Yes' button.")
        private fun clickYesButton() {
            while (yesButton.exists()) {
                yesButton.click()
                baseTest.waitInSecondsFloat(QUEST_DELAY)
            }
        }

        fun close() {
            Selenide.close()
        }

        fun navigateToDriverInfo() {
            runSignUp()
            questSuccess()
        }

        fun navigateToVehicleInfo() {
            navigateToDriverInfo()
            fillInDriverInfo()
        }

        fun navigateToAgreementsTab() {
            navigateToVehicleInfo()
            fillVehicleInfoBlock()
        }

        fun navigateToPaymentInfo() {
            val agreementsBlock = AgreementsBlock()
            val paymentInfoBlock = PaymentInfoBlock()
            navigateToAgreementsTab()
            agreementsBlock.agreementsSaveAndNextButton.click()
            waitForElement(paymentInfoBlock.paymentInfoHeader)
        }

        private fun fillInDriverInfo() {
            val percentagesBlockTests = DriverInfoPercentages()
            percentagesBlockTests.fillDateOfBirth()
            percentagesBlockTests.fillAddressFields()
            percentagesBlockTests.saveAndNextButton.click()
            waitForElement(VehicleInfoBlock().vehicleInfoTitle)
        }

        private fun fillVehicleInfoBlock() {
            val vehicleInfoBlock = VehicleInfoBlock()
            vehicleInfoBlock.inputsCollection = vehicleInfoBlock.getEnabledInputs(vehicleInfoBlock.inputsCollection)
            vehicleInfoBlock.inputsCollection.forEach{
                if (!it.isContainText("Plate #")) {
                    it.click()
                    it.find(By.className(DROPDOWN_MENU_CLASS)).find(By.className(DROPDOWN_ITEM_CLASS)).click()
                }
            }
            vehicleInfoBlock.modelDropdown.click()
            vehicleInfoBlock.modelDropdown.find(By.className(DROPDOWN_MENU_CLASS)).find(By.className(DROPDOWN_ITEM_CLASS)).click()
            vehicleInfoBlock.inputsCollection.filter { it.isContainText("Plate #") }[0].find("input").value =
                    (Random().nextInt(999999 - 100000) + 100000).toString()
            vehicleInfoBlock.saveAndNextButton.click()
            waitForElement(getByClass(AGREEMENTS_HEADER_BLOCK_CLASS))
        }

        fun waitInSeconds(seconds: Int) {
            try {
                Thread.sleep((seconds * 1000).toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        fun waitForElement(element: SelenideElement) {
            element.waitUntil(Condition.appear, 10000)
        }
    }

    val saveAndNextButton = getByClass(SAVE_AND_NEXT_BUTTON_CLASS)

    @After
    open fun afterTest() {
        makeScreenShotOnResult()
        SalesforceBlock().deleteVehicleDriver(signUpData.email)
        close()
    }

}
