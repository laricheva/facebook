package pages.elements.profile

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import constants.*
import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.runners.MethodSorters
import pages.salesforce.SalesforceBlock
import java.util.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Driver Info Percentages Block Test")
class DriverInfoPercentages: BaseProfilePageUi() {

    private val percentsText = Selenide.`$$`(PERCENT_TEXT_CSS_SELECTOR).findBy(Condition.text(localization.profile.common.driverInfo))
    private val percentsFullImage = getByXpath(PERCENTS_FULL_XPATH)
    val profileImageInput = getById(PROFILE_UPLOAD_INPUT_ID)
    private val profileImageUploadBar = getImageUploadBar(localization.profile.driverInfo.profileImage)
    private val taxiImageInput = getById(TAXI_UPLOAD_INPUT_ID)
    private val taxiImageUploadBar = getImageUploadBar(localization.profile.driverInfo.taxivergunningbewijs)
    private val proDriverLicenseImageInput = getById(PRO_DRIVER_LICENSE_UPLOAD_INPUT_ID)
    private val proDriverLicenseImageUploadBar = getImageUploadBar(localization.profile.driverInfo.profDriverLicenseAms)
    private val backDriverLicenseImageInput = getById(BACK_DRIVER_LICENSE_UPLOAD_INPUT_ID)
    private val backDriverLicenseImageUploadBar = getImageUploadBar(localization.profile.vehicleInfo.rijbewijsLicenseBack)
    private val frontDriverLicenseImageInput = getById(FRONT_DRIVER_LICENSE_UPLOAD_INPUT_ID)
    private val frontDriverLicenseImageUploadBar = getImageUploadBar(localization.profile.driverInfo.rijbewijsLicenseFront)
    private val dobDayDropDown = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, DAY)
    private val dobMonthDropDown = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, MONTH)
    private val dobYearDropDown = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, YEAR)
    private val daysList = getAllByXpath(DOB_DAY_LIST_XPATH)
    private val monthsList = getAllByXpath(DOB_MONTH_LIST_XPATH)
    private val yearsList = getAllByXpath(DOB_YEAR_LIST_XPATH)
    private val addressInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, ADDRESS_ONE)
    private val zipInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, ZIP)
    private val cityInput = getByAttributeAndValue(FORM_CONTROL_NAME_ALL_LOW, CITY)

    private val ADDRESS_TEXT = "Stationsplein"
    private val ZIP_TEXT = "1012 AB"
    private val CITY_TEXT = "Amsterdam"

    val random = Random()

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

    fun fillDateOfBirth() {
        dobMonthDropDown.click()
        monthsList[random.nextInt(monthsList.size - 1)].click()
        dobDayDropDown.click()
        daysList[random.nextInt(daysList.size - 1)].click()
        dobYearDropDown.click()
        yearsList[random.nextInt(yearsList.size - 1)].click()
    }

    fun fillAddressFields() {
        addressInput.value = ADDRESS_TEXT
        zipInput.value = ZIP_TEXT
        cityInput.value = CITY_TEXT
    }

    @Test
    @DisplayName("Default percent of profile Test")
    @Description("Default percent of profile should be 38%")
    fun defaultPercentOfProfileTest() {
        assertTrue("Default percent of profile is not correct", percentsText.isContainText("38%"))
    }

    @Test
    @DisplayName("One image loaded percentages Test")
    @Description("Percent of profile should be 46%")
    fun oneImagePercentTest() {
        taxiImageInput.uploadCorrectImage(taxiImageUploadBar)
        waitInSeconds(2)
        assertTrue("Percent of profile is not correct", percentsText.isContainText("46%"))
    }

    @Test
    @DisplayName("Two images loaded percentages Test")
    @Description("Percent of profile should be 53%")
    fun twoImagesPercentTest() {
        taxiImageInput.uploadCorrectImage(taxiImageUploadBar)
        proDriverLicenseImageInput.uploadCorrectImage(proDriverLicenseImageUploadBar)
        assertTrue("Percent of profile is not correct", percentsText.isContainText("53%"))
    }

    @Test
    @DisplayName("Three images loaded percentages Test")
    @Description("Percent of profile should be 61%")
    fun threeImagesPercentTest() {
        taxiImageInput.uploadCorrectImage(taxiImageUploadBar)
        proDriverLicenseImageInput.uploadCorrectImage(proDriverLicenseImageUploadBar)
        backDriverLicenseImageInput.uploadCorrectImage(backDriverLicenseImageUploadBar)
        assertTrue("Percent of profile is not correct", percentsText.isContainText("61%"))
    }
    @Test
    @DisplayName("Four images loaded percentages Test")
    @Description("Percent of profile should be 69%")
    fun fourImagesPercentTest() {
        taxiImageInput.uploadCorrectImage(taxiImageUploadBar)
        proDriverLicenseImageInput.uploadCorrectImage(proDriverLicenseImageUploadBar)
        backDriverLicenseImageInput.uploadCorrectImage(backDriverLicenseImageUploadBar)
        frontDriverLicenseImageInput.uploadCorrectImage(frontDriverLicenseImageUploadBar)
        assertTrue("Percent of profile is not correct", percentsText.isContainText("69%"))
    }

    @Test
    @DisplayName("Five images loaded percentages Test")
    @Description("Percent of profile should be 71%")
    fun fiveImagesPercentTest() {
        taxiImageInput.uploadCorrectImage(taxiImageUploadBar)
        proDriverLicenseImageInput.uploadCorrectImage(proDriverLicenseImageUploadBar)
        backDriverLicenseImageInput.uploadCorrectImage(backDriverLicenseImageUploadBar)
        frontDriverLicenseImageInput.uploadCorrectImage(frontDriverLicenseImageUploadBar)
        profileImageInput.uploadCorrectImage(profileImageUploadBar)
        assertTrue("Percent of profile is not correct", percentsText.isContainText("71%"))
    }

    @Test
    @DisplayName("Required and optional images Test")
    @Description("Percent of profile should change to checked image even though the optional images are not loaded.")
    fun requiredImagesTest() {
        fillDateOfBirth()
        fillAddressFields()
        taxiImageInput.uploadCorrectImage(taxiImageUploadBar)
        proDriverLicenseImageInput.uploadCorrectImage(proDriverLicenseImageUploadBar)
        backDriverLicenseImageInput.uploadCorrectImage(backDriverLicenseImageUploadBar)
        frontDriverLicenseImageInput.uploadCorrectImage(frontDriverLicenseImageUploadBar)
        saveAndNextButton.click()
        waitInSeconds(5)
        assertTrue("Percent of profile is not correct", percentsFullImage.exists())
    }
}