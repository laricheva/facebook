package pages.elements.profile

import extensions.isContainText
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.Assert.assertTrue
import org.junit.runners.MethodSorters
import org.junit.*
import org.openqa.selenium.By
import pages.salesforce.SalesforceBlock

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Vehicle Info Percentages Block Test")
open class VehicleInfoPercentages: BaseProfilePageUi() {

    private val vehicleInfoBlock = VehicleInfoBlock()

    private val percentsEl = vehicleInfoBlock.percentsEl
    private val checkedIcon = percentsEl.find(By.className("v-sidebar_item__checked"))
    private val vehRegBackImageInput = vehicleInfoBlock.vehRegBackImageInput
    private val vehRegBackImageUploadBar = vehicleInfoBlock.vehRegBackImageUploadBar
    private val vehRegFrontImageInput = vehicleInfoBlock.vehRegFrontImageInput
    private val vehRegFrontImageUploadBar = vehicleInfoBlock.vehRegFrontImageUploadBar
    private val vehInsurImageInput = vehicleInfoBlock.vehInsurImageInput
    private val vehInsurImageUploadBar = vehicleInfoBlock.vehInsurImageUploadBar
    private val vehInspectImageInput = vehicleInfoBlock.vehInspectImageInput
    private val vehExterImageInput = vehicleInfoBlock.vehExterImageInput
    private val vehInspectImageUploadBar = vehicleInfoBlock.vehInspectImageUploadBar
    private val vehExterImageUploadBar = vehicleInfoBlock.vehExterImageUploadBar
    private fun fillAndSaveMandatoryFields() = vehicleInfoBlock.fillMandatoryFields()
    private fun saveMandatoryFields() = vehicleInfoBlock.saveMandatoryFields()
    private val salesforceBlock = SalesforceBlock()
    private var isDriverDeleted = false;

    @Before
    fun goToVehicleInfo() {
        vehicleInfoBlock.goToVehicleInfo()
    }

    @After
    override fun afterTest() {
        makeScreenShotOnResult()
        if (!isDriverDeleted) salesforceBlock.deleteVehicleDriver(signUpData.email)
        close()
        isDriverDeleted = false
    }

    @Test
    @DisplayName("Default percent of vehicle info tab")
    @Description("Default percent of vehicle info should be 0%")
    fun defaultPercentOfVehicleInfo() {
        assertTrue("Default percent of vehicle info is not correct", percentsEl.isContainText("0%"))
        salesforceBlock.deleteDriver(signUpData.email)
        isDriverDeleted = true
    }

    @Test
    @DisplayName("Percent after filling mandatory fields")
    @Description("Percent should be 54% after filling mandatory fields")
    fun mandatoryFieldsFilledPercent() {
        vehicleInfoBlock.fillMandatoryFields()
        vehicleInfoBlock.saveMandatoryFields()
        assertTrue("Percent value is not correct after filling mandatory fields", percentsEl.isContainText("54%"))
    }

    @Test
    @DisplayName("Percent after filling mandatory fields and uploading 1st image")
    @Description("Percent should be 63% after filling mandatory fields and uploading 1st image")
    fun vehRegBackUploadedPercent() {
        vehRegBackImageInput.uploadCorrectImage(vehRegBackImageUploadBar)
        fillAndSaveMandatoryFields()
        saveMandatoryFields()
        assertTrue("Percent value is not correct after filling mandatory fields", percentsEl.isContainText("63%"))
    }

    @Test
    @DisplayName("Percent after filling mandatory fields and uploading 2 images")
    @Description("Percent should be 72% after filling mandatory fields and uploading 2 images")
    fun vehRegFrontUploadedPercent() {
        vehRegBackImageInput.uploadCorrectImage(vehRegBackImageUploadBar)
        vehRegFrontImageInput.uploadCorrectImage(vehRegFrontImageUploadBar)
        fillAndSaveMandatoryFields()
        saveMandatoryFields()
        assertTrue("Percent value is not correct after filling mandatory fields", percentsEl.isContainText("72%"))
    }

    @Test
    @DisplayName("Percent after filling mandatory fields and uploading 3 images")
    @Description("Percent should be 81% after filling mandatory fields and uploading 3 images")
    fun vehInsurUploadedPercent() {
        vehRegBackImageInput.uploadCorrectImage(vehRegBackImageUploadBar)
        vehRegFrontImageInput.uploadCorrectImage(vehRegFrontImageUploadBar)
        vehInsurImageInput.uploadCorrectImage(vehInsurImageUploadBar)
        fillAndSaveMandatoryFields()
        saveMandatoryFields()
        assertTrue("Percent value is not correct after filling mandatory fields", percentsEl.isContainText("81%"))
    }

    @Test
    @DisplayName("Percent after filling mandatory fields and uploading 4 images")
    @Description("Percent should be 90% after filling mandatory fields and uploading 4 images")
    fun vehInspectUploadedPercent() {
        vehRegBackImageInput.uploadCorrectImage(vehRegBackImageUploadBar)
        vehRegFrontImageInput.uploadCorrectImage(vehRegFrontImageUploadBar)
        vehInsurImageInput.uploadCorrectImage(vehInsurImageUploadBar)
        vehInspectImageInput.uploadCorrectImage(vehInspectImageUploadBar)
        fillAndSaveMandatoryFields()
        saveMandatoryFields()
        assertTrue("Percent value is not correct after filling mandatory fields", percentsEl.isContainText("90%"))
    }

    @Test
    @DisplayName("Percent after filling all vehicle info fields")
    @Description("Percent should be 100% after filling all vehicle info fields")
    fun allFieldsFilledPercent() {
        vehRegBackImageInput.uploadCorrectImage(vehRegBackImageUploadBar)
        vehRegFrontImageInput.uploadCorrectImage(vehRegFrontImageUploadBar)
        vehInsurImageInput.uploadCorrectImage(vehInsurImageUploadBar)
        vehInspectImageInput.uploadCorrectImage(vehInspectImageUploadBar)
        vehExterImageInput.uploadCorrectImage(vehExterImageUploadBar)
        fillAndSaveMandatoryFields()
        saveMandatoryFields()
        assertTrue("Percent value is not correct after filling mandatory fields", checkedIcon.exists())
    }
}
