package pages.elements.profile

import base.BaseValidation
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Selectors.withText
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
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
import org.openqa.selenium.By
import pages.salesforce.SalesforceBlock
import java.io.File
import java.util.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Vehicle Info Block Test")
class VehicleInfoBlock: BaseProfilePageUi() {

    val vehicleInfoTitle = `$$`(".v-sidebar_item--active").findBy(text(localization.profile.common.vehicleInfo))

    val percentsEl = `$$`(".v-sidebar_item").findBy(text(localization.profile.common.vehicleInfo))
    private val agreementsTab = `$$`(".v-sidebar_item").findBy(text(localization.profile.common.agreements))
    private val seeApprovedVehiclesListLink = `$`(byText(localization.profile.vehicleInfo.seeApprovedVehicleInfo))
    var inputsCollection: List<SelenideElement> = `$$`(".v-content-input-group")

    private val random = Random()

    private val salesforceBlock = SalesforceBlock()

    private val modelLabel = getByExactText("${localization.profile.vehicleInfo.model}:")
    val modelDropdown = modelLabel.parent().find("select-control")

    private val plateNumberCell = getByExactText("${localization.profile.vehicleInfo.plate} #:").parent()
    private val plateNumInput = plateNumberCell.find("input")
    private val plateNumErrorTooltip = plateNumberCell.parent().find(By.className(ERROR_TOOLTIP_CLASS))

    val makeText = getByXpath(VEH_MAKE_XPATH)
    val modelText = getByXpath(VEH_MODEL_XPATH)
    val colorText = getByXpath(VEH_COLOR_XPATH)
    val interiorText = getByXpath(VEH_INTERIOR_XPATH)
    val yearText = getByXpath(VEH_YEAR_XPATH)
    val plateText = getByXpath(VEH_PLATE_XPATH)

    private val needHelpLink = getByExactText(localization.profile.common.needHelp)
    private val popUp = getByClass(POPUP_CLASS)
    val spinner = `$`(".spinner")

    val vehRegBackImageInput = getByXpath(VEH_REG_BACK_UPLOAD_INPUT_XPATH)
    private val vehRegBackImageUpdateBtn = getByXpath(VEH_REG_BACK_UPDATE_BTN_XPATH)
    val vehRegBackImageUploadBar = getByXpath(VEH_REG_BACK_UPLOAD_BAR_XPATH)

    val vehRegFrontImageInput = getByXpath(VEH_REG_FRONT_UPLOAD_INPUT_XPATH)
    private val vehRegFrontImageUpdateBtn = getByXpath(VEH_REG_FRONT_UPDATE_BTN_XPATH)
    val vehRegFrontImageUploadBar = getImageUploadBar(localization.profile.vehicleInfo.vehRegKentFront)

    val vehInsurImageInput = getByXpath(VEH_INSUR_UPLOAD_INPUT_XPATH)
    private val vehInsurImageUpdateBtn = getByXpath(VEH_INSUR_UPDATE_BTN_XPATH)
    val vehInsurImageUploadBar = getImageUploadBar(localization.profile.vehicleInfo.vehInsur)

    val vehInspectImageInput = getByXpath(VEH_INSPECT_UPLOAD_INPUT_XPATH)
    private val vehInspectImageUpdateBtn = getByXpath(VEH_INSPECT_UPDATE_BTN_XPATH)
    val vehInspectImageUploadBar = getImageUploadBar(localization.profile.vehicleInfo.vehInspect)

    val vehExterImageInput = getByXpath(VEH_EXTER_UPLOAD_INPUT_XPATH)
    private val vehExterImageUpdateBtn = getByXpath(VEH_EXTER_UPDATE_BTN_XPATH)
    val vehExterImageUploadBar = `$`(withText(localization.profile.vehicleInfo.vehExterFront)).parent().find(By.className("v-upload_uploading"))

    private val imagesInputList = listOf(
            vehRegBackImageInput,
            vehRegFrontImageInput,
            vehInsurImageInput,
            vehInspectImageInput,
            vehExterImageInput)

    private val imagesUploadBarList = listOf(
            vehRegBackImageUploadBar,
            vehRegFrontImageUploadBar,
            vehInsurImageUploadBar,
            vehInspectImageUploadBar,
            vehExterImageUploadBar)

    private val imagesUpdateBtnList = listOf(
            vehRegBackImageUpdateBtn,
            vehRegFrontImageUpdateBtn,
            vehInsurImageUpdateBtn,
            vehInspectImageUpdateBtn,
            vehExterImageUpdateBtn)

    private val baseValidation = BaseValidation()
    private var isDriverDeleted  = false;

    @Before
    fun goToVehicleInfo() {
        navigateToVehicleInfo()
        inputsCollection = getEnabledInputs(inputsCollection)
    }

    @After
    override fun afterTest() {
        makeScreenShotOnResult()
        if (!isDriverDeleted) salesforceBlock.deleteDriver(signUpData.email)
        close()
        isDriverDeleted = false
    }

    @Test
    @DisplayName("Default percent of vehicle info tab")
    @Description("Default percent of vehicle info should be 0%")
    fun defaultPercentOfVehicleInfo() {
        assertTrue("Default percent of vehicle info is not correct", percentsEl.isContainText("0%"))
    }

    @Test
    @DisplayName("link 'see approved vehicles list'")
    @Description("link 'see approved vehicles list' should have url 'https://drive-ams.viavan.com/ams-approved-vehicles/'")
    fun approvedVehiclesListLink() {
        assertTrue("'see approved vehicles list' link is not correct", seeApprovedVehiclesListLink.checkLinkPath("https://drive-ams.viavan.com/ams-approved-vehicles/"))
    }

    @Test
    @DisplayName("'see approved vehicles list' link test")
    @Description("'see approved vehicles list' link should open 'https://drive-ams.viavan.com/ams-approved-vehicles/'")
    fun approvedVehiclesListNavigation() {
        seeApprovedVehiclesListLink.click()
        getTabAt(1)
        assertTrue("'see approved vehicles list' link is not correct", checkCurrentUrl("https://drive-ams.viavan.com/ams-approved-vehicles/"))
        closeTabAt(1)
        getTabAt(0)
    }

    @Test
    @DisplayName("Mandatory fields test")
    @Description("Error tooltips should be shown for empty 'make', 'interior', 'year', 'color', 'plate#' empty fields after clicking 'save&next' button")
    fun mandatoryFieldsTest() {
        saveAndNextButton.click()
        assertTrue("Error tooltips are not shown for mandatory fields", inputsCollection.all { it.parent().find(By.className(ERROR_TOOLTIP_CLASS)).isDisplayed })
    }

    @Test
    @DisplayName("'Model' dropdown test")
    @Description("'Model' dropdown is disabled without selected 'Make' item")
    fun modelDropdownDisabled() {
        assertTrue("'Model' dropdown is not disabled", modelLabel.getAttribute("class").contains("v-content_cell__label-disabled") )
    }

    @Test
    @DisplayName("'Color' dropdown test")
    @Description("Selected value from the dropdown list should be shown in 'Color' cell")
    fun colorDropdownShowValue() {
        testDropdown(localization.profile.vehicleInfo.color)
    }

    @Test
    @DisplayName("'Interior' dropdown test")
    @Description("Selected value from the dropdown list should be shown in 'Interior' cell")
    fun interiorDropdownShowValue() {
        testDropdown(localization.profile.vehicleInfo.interior)
    }

    @Test
    @DisplayName("'Year' dropdown test")
    @Description("Selected value from the dropdown list should be shown in 'Year' cell")
    fun yearDropdownShowValue() {
        testDropdown(localization.profile.vehicleInfo.year)
    }

    @Test
    @DisplayName("'I need help' navigation Test")
    @Description("'I need help' should show popup and title should be 'Need help with this form?'")
    fun needHelpLinkNavigationTest() {
        needHelpLink.click()
        waitForElement(popUp)
        assertTrue("'I need help' link is not correct", popUp.exists() && popUp.isContainText("Need help with this form?"))
    }

    @Test
    @DisplayName("Vehicle info success")
    @Description("'Agreements' page opens after filling mandatory fields on 'Vehicle info' page and clicking 'save&next' button")
    fun vehicleInfoSuccess() {
        fillMandatoryFields()
        saveMandatoryFields()
        assertTrue("'Agreements' page doesn't open after filling mandatory fields on 'Vehicle info' page and clicking 'save&next' button",
                agreementsTab.getAttribute("class").contains("v-sidebar_item--active"))
        salesforceBlock.deleteVehicleDriver(signUpData.email)
        isDriverDeleted = true
    }

    @Test
    @DisplayName("Upload image larger then 10MB for 'Vehicle Registration back' Test")
    @Description("Browser should open popup with error message")
    fun vehRegBackBigImageUploadTest() {
        vehRegBackImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct 'Vehicle Registration back' image Test")
    @Description("'Update' button should appear")
    fun vehRegBackCorrectImageUploadTest() {
        vehRegBackImageInput.uploadCorrectImage(vehRegBackImageUploadBar)
        assertTrue("Image wasn't uploaded", vehRegBackImageUpdateBtn.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for 'Vehicle Registration front' Test")
    @Description("Browser should open popup with error message")
    fun vehRegFrontBigImageUploadTest() {
        vehRegFrontImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct 'Vehicle Registration front' image Test")
    @Description("'Update' button should appear")
    fun vehRegFrontCorrectImageUploadTest() {
        vehRegFrontImageInput.uploadCorrectImage(vehRegFrontImageUploadBar)
        assertTrue("Image wasn't uploaded", vehRegFrontImageUpdateBtn.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for 'Vehicle Insurance' Test")
    @Description("Browser should open popup with error message")
    fun vehInsurBigImageUploadTest() {
        vehInsurImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct 'Vehicle Insurance' image Test")
    @Description("'Update' button should appear")
    fun vehInsurCorrectImageUploadTest() {
        vehInsurImageInput.uploadCorrectImage(vehInsurImageUploadBar)
        assertTrue("Image wasn't uploaded", vehInsurImageUpdateBtn.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for 'Vehicle Inspection' Test")
    @Description("Browser should open popup with error message")
    fun vehInpectBigImageUploadTest() {
        vehInspectImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct 'Vehicle Inspection' image Test")
    @Description("'Update' button should appear")
    fun vehInspectCorrectImageUploadTest() {
        vehInspectImageInput.uploadCorrectImage(vehInspectImageUploadBar)
        assertTrue("Image wasn't uploaded", vehInspectImageUpdateBtn.exists())
    }

    @Test
    @DisplayName("Upload image larger then 10MB for 'Vehicle\'s Exterior' Test")
    @Description("Browser should open popup with error message")
    fun vehExterBigImageUploadTest() {
        vehExterImageInput.uploadFile(File(getImagePath(BIG_IMAGE)))
        assertTrue("Alert is not shown", isAlertOpened())
        acceptAlert()
    }

    @Test
    @DisplayName("Upload correct 'Vehicle\'s Exterior' image Test")
    @Description("'Update' button should appear")
    fun vehExterCorrectImageUploadTest() {
        vehExterImageInput.uploadCorrectImage(vehExterImageUploadBar)
        assertTrue("Image wasn't uploaded", vehExterImageUpdateBtn.exists())
    }

    @Test
    @DisplayName("One image 'Pending Approval' status")
    @Description("One image after uploading should have status 'Pending Approval'")
    fun oneImagePendingApproval() {
        val randomIndex = random.nextInt(imagesInputList.size)
        assertTrue("'Pending Approval' status is not shown after uploading image", imagePendingApprovalStatus(randomIndex))
    }

    @Test
    @DisplayName("Two images 'Pending Approval' status")
    @Description("Two images after uploading should have status 'Pending Approval'")
    fun twoImagesPendingApproval() {
        val randomIndex1 = random.nextInt(imagesInputList.size)
        var randomIndex2: Int
        do {
            randomIndex2 = random.nextInt(imagesInputList.size)
        } while (randomIndex2 == randomIndex1)
        assertTrue("'Pending Approval' status is not shown for two uploaded images", imagePendingApprovalStatus(randomIndex1) && imagePendingApprovalStatus(randomIndex2))
    }

    @Test
    @DisplayName("Three images 'Pending Approval' status")
    @Description("Three images after uploading should have status 'Pending Approval'")
    fun threeImagesPendingApproval() {
        var randomIndexArray = arrayOf<Int>()
        while(randomIndexArray.size < 3) {
            val randomIndex = Random().nextInt(imagesInputList.size)
            if (!randomIndexArray.contains(randomIndex)) {
                randomIndexArray += randomIndex
            }
        }
        val areAllImagesHavePendApprStatus = randomIndexArray.all{imagePendingApprovalStatus(it)}
        assertTrue("'Pending Approval' status is not shown for three uploaded images", areAllImagesHavePendApprStatus)
    }

    @Test
    @DisplayName("Four images 'Pending Approval' status")
    @Description("Four images after uploading should have status 'Pending Approval'")
    fun fourImagesPendingApproval() {
        val randomIndexArray = mutableListOf(0, 1, 2, 3, 4)
        randomIndexArray.removeAt(random.nextInt(imagesInputList.size))
        val areAllImagesHavePendApprStatus = randomIndexArray.all{imagePendingApprovalStatus(it)}
        assertTrue("'Pending Approval' status is not shown for four uploaded images", areAllImagesHavePendApprStatus)
    }

    @Test
    @DisplayName("All images 'Pending Approval' status")
    @Description("All images after uploading should have status 'Pending Approval'")
    fun allImagesPendingApproval() {
        val randomIndexArray = mutableListOf(0, 1, 2, 3, 4)
        val areAllImagesHavePendApprStatus = randomIndexArray.all{imagePendingApprovalStatus(it)}
        assertTrue("'Pending Approval' status is not shown for four uploaded images", areAllImagesHavePendApprStatus)
    }

    @Test
    @DisplayName("Short plate number length")
    @Description("Tooltip with error message should be shown when you enter plate number less than 6-characters length")
    fun plateNumberShortLengthTest() {
        baseValidation.plateNumberSmallLengthTest(percentsEl, plateNumInput, plateNumErrorTooltip)
    }

    @Test
    @DisplayName("Plate number big length")
    @Description("Plate number should contain 6 cahracters after the attempt to enter longer value")
    fun plateNumberBigLengthTest() {
        baseValidation.plateNumberBigLengthTest(percentsEl, plateNumInput)
    }

    @Test
    @DisplayName("Invalid plate number character")
    @Description("Tooltip with error message should be shown when you enter plate number with invalid character")
    fun plateNumberInvalidTest() {
        baseValidation.plateNumberInvalidTest(percentsEl, plateNumInput, plateNumErrorTooltip)
    }

    fun imagePendingApprovalStatus (imageIndex: Int): Boolean {
        imagesInputList[imageIndex].uploadFile(File(getImagePath(CORRECT_IMAGE)))
        imagesUploadBarList[imageIndex].waitUntil(disappears, 10000)
        val pendingApproval = imagesUpdateBtnList[imageIndex].parent().parent().find(By.tagName("div"))
        waitForElement(pendingApproval)
        return pendingApproval.isContainText(localization.profile.driverInfo.docStatusPending)
    }

    fun fillMandatoryFields() {
        inputsCollection.forEach{
            if (!it.isContainText("${localization.profile.vehicleInfo.plate} #") && !it.isContainText(localization.profile.vehicleInfo.model)) {
                it.click()
                waitForElement(it.find(By.className("dropdown-menu")))
                val itemsCollection = it.find(By.className("dropdown-menu")).findAll(By.className("dropdown-item"))
                val randomItem = itemsCollection[random.nextInt(itemsCollection.size)]
                randomItem.click()
            }
        }
        modelDropdown.click()
        val itemsCollection = modelDropdown.find(By.className("dropdown-menu")).findAll(By.className("dropdown-item"))
        val randomItem = itemsCollection[random.nextInt(itemsCollection.size)]
        randomItem.click()
        plateNumberCell.find("input").value = randomValueGenerate("numbers", "letters", 6)
    }

    fun saveMandatoryFields() {
        saveAndNextButton.click()
        spinner.waitUntilDisappears()
    }

    private fun testDropdown(name: String) {
        val cell = inputsCollection.find { it.isContainText(name) }
        cell.notNull ({
            val dropdownItems = it.findAll(By.className("dropdown-item"))
            it.click()
            val nameSize = dropdownItems.size
            val randomItem = random.nextInt(nameSize)
            val selectedValue = dropdownItems[randomItem].text()
            dropdownItems[randomItem].click()
            val cellValue = it.find(By.cssSelector("select-control")).text()
            assertTrue("Selected value is not shown in the $name cell", selectedValue == cellValue)},
                "dropdown cell '$name' not found"
        )
    }

    fun getEnabledInputs(inputsCollection: List<SelenideElement>): List<SelenideElement> {
        return inputsCollection.filter{
            it.isContainText(localization.profile.vehicleInfo.make) ||
            it.isContainText(localization.profile.vehicleInfo.interior) ||
            it.isContainText(localization.profile.vehicleInfo.year) ||
            it.isContainText(localization.profile.vehicleInfo.color) ||
            it.isContainText("${localization.profile.vehicleInfo.plate} #")
        }
    }
}
