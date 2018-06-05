package pages.salesforce

import constants.AMS_PHONE_CODE
import data.UserInfo
import data.VehicleInfo
import extensions.waitUntilDisappears
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.After
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import pages.elements.main.Questionnaire
import pages.elements.profile.BaseProfilePageUi
import pages.elements.profile.DriverInfoBlock
import pages.elements.profile.DriverInfoPercentages
import pages.elements.profile.VehicleInfoBlock
import utils.getMonthIndex
import java.util.Random

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("SalesForce Block Test")
class SalesforceTests: BaseProfilePageUi() {

    private val questionnaire = Questionnaire()
    private val driverInfoBlock = DriverInfoBlock()
    private val vehicleInfoBlock = VehicleInfoBlock()
    private val salesforceBlock = SalesforceBlock()

    private val APT_SUITE_TEXT = "45"

    private var isDriverDeleted = false

    @After
    override fun afterTest() {
        makeScreenShotOnResult()
        if (!isDriverDeleted) salesforceBlock.deleteDriver(signUpData.email)
        isDriverDeleted = false
        close()
    }

    @Test
    @DisplayName("Check created driver on SF Test")
    @Description("Search by email for just created user on salesforce")
    fun checkIsUserCreated() {
        runSignUp()
        salesforceBlock.checkUserCreatedTest(signUpData.email)
    }

    @Test
    @DisplayName("Check created driver info on SF Test")
    @Description("Search by email for just created user and checking is all user data saved on salesforce")
    fun checkIsUserDataSaved() {
        runSignUp()
        salesforceBlock.checkUserFieldsSaved(signUpData)
    }

    @Test
    @DisplayName("Check all driver images are saved on SF Test")
    @Description("Load all images, click 'Save&next' button and then check on SF that all loaded images are saved")
    fun checkAllImagesOnSF() {
        navigateToDriverInfo()
        val email = driverInfoBlock.emailInput.value
        driverInfoBlock.loadImages(true)
        salesforceBlock.checkThatAllImagesAreSaved(email)
    }

    @Test
    @DisplayName("Check all driver information is saved on SF Test")
    @Description("Load all images, fill all fields, click 'Save&next' button and then check on SF that all information is saved")
    fun checkAllInfoOnSF() {
        navigateToDriverInfo()
        val percentagesBlockTests = DriverInfoPercentages()
        percentagesBlockTests.fillDateOfBirth()
        percentagesBlockTests.fillAddressFields()
        driverInfoBlock.aptSuiteEtcInput.value = APT_SUITE_TEXT
        driverInfoBlock.loadImages(true)
        val userInfo = UserInfo(driverInfoBlock.firstNameInput.value, driverInfoBlock.lastNameInput.value, driverInfoBlock.emailInput.value,
                "$AMS_PHONE_CODE${driverInfoBlock.phoneInput.value}", getDateOfBirthString(), driverInfoBlock.addressInput.value.toUpperCase(),
                driverInfoBlock.aptSuiteEtcInput.value.toUpperCase(), driverInfoBlock.zipInput.value.toUpperCase(),
                driverInfoBlock.cityInput.value.toUpperCase())
        saveAndNextButton.click()
        vehicleInfoBlock.spinner.waitUntilDisappears()
        salesforceBlock.checkThatAllInfoIsSaved(userInfo)
    }

    @Test
    @DisplayName("Check just created vehicle on SF Test")
    @Description("Check after clicking 'Save&Next' button Vehicle object (Driver Vehicle Junctions) is created on SF")
    fun checkVehicleCreated() {
        navigateToVehicleInfo()
        vehicleInfoBlock.fillMandatoryFields()
        val plateNumber = vehicleInfoBlock.plateText.value.toUpperCase()
        vehicleInfoBlock.saveMandatoryFields()
        salesforceBlock.checkThatVehicleCreated(signUpData.email, plateNumber)
        deleteVehicleDriver()
    }

    @Test
    @DisplayName("Check vehicle values on SF Test")
    @Description("Check after clicking 'Save&Next' button all values are saved/updated on SF")
    fun checkVehicleInfo() {
        navigateToVehicleInfo()
        vehicleInfoBlock.fillMandatoryFields()
        val vehicle = VehicleInfo(vehicleInfoBlock.makeText.text, vehicleInfoBlock.modelText.text,
                vehicleInfoBlock.colorText.text, vehicleInfoBlock.interiorText.text, vehicleInfoBlock.yearText.text,
                vehicleInfoBlock.plateText.value.toUpperCase())
        vehicleInfoBlock.saveMandatoryFields()
        salesforceBlock.checkThatAllVehicleValuesSaved(signUpData.email, vehicle)
        deleteVehicleDriver()
    }

    @Test
    @DisplayName("Check vehicle images on SF Test")
    @Description("Check after clicking 'Save&Next' button all uploaded images are saved/updated on SF")
    fun checkVehicleImages() {
        navigateToVehicleInfo()
        vehicleInfoBlock.fillMandatoryFields()
        val randomIndexArray = mutableListOf(0, 1, 2, 3, 4)
        randomIndexArray.all{vehicleInfoBlock.imagePendingApprovalStatus(it)}
        vehicleInfoBlock.saveMandatoryFields()
        salesforceBlock.checkThatAllVehicleImagesAreSaved(signUpData.email)
        deleteVehicleDriver()
    }

    @Test
    @DisplayName("Questionnaire values are saved on SF")
    @Description("Answer all questionnaire questions and check whether all values were saved on SF")
    fun questValuesAreSavedOnSF() {
        runSignUp()
        questionnaire.clickYesButton()
        questionnaire.dropDownEl.click()
        val randomItemIndex = Random().nextInt(questionnaire.howDidYouHearItems.size)
        var randomItemValue = questionnaire.howDidYouHearItems[randomItemIndex].text()
        questionnaire.howDidYouHearItems[randomItemIndex].click()
        questionnaire.questNextButton.click()
        if (randomItemValue != localization.main.acquisitionFb) {
            questionnaire.selectItemInput.value = randomItemValue
            questionnaire.questNextButton.click()
        } else {
            randomItemValue = randomItemValue.substring(0, randomItemValue.indexOf(" "))
        }
        questionnaire.questSpinner.waitUntilDisappears()
        salesforceBlock.checkQuestValueAreSaved(signUpData.email, randomItemValue)
    }

    private fun getDateOfBirthString(): String = "${getMonthIndex(driverInfoBlock.dobMonthDropDown.text)}/${driverInfoBlock.dobDayDropDown.text}/" +
            driverInfoBlock.dobYearDropDown.text

    private fun deleteVehicleDriver() {
        salesforceBlock.deleteVehicleDriver(signUpData.email)
        isDriverDeleted = true
    }
}