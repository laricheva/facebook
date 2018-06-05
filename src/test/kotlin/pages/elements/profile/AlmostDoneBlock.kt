package pages.elements.profile

import com.codeborne.selenide.Configuration
import constants.*
import extensions.checkImage
import extensions.checkLinkPath
import extensions.getByClass
import extensions.getByExactText
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Almost Done Block Tests")
class AlmostDoneBlock: BaseProfilePageUi() {

    private val addVehicleInfoButton = getByExactText(localization.profile.common.addVehicleInfo)
    private val addLaterButton = getByExactText(localization.profile.common.addLater)
    private val almostDoneHeader = getByExactText(localization.profile.common.almostDone)
    private val googlePlayButton = getByClass(GOOGLE_PLAY_BUTTON_CLASS)
    private val appStoreButton = getByClass(APP_STORE_BUTTON_CLASS)
    private val facebookButton = getByClass(FACEBOOK_BUTTON_CLASS)
    private val twitterButton = getByClass(TWITTER_BUTTON_CLASS)
    private val linkedInButton = getByClass(LINKEDIN_BUTTON_CLASS)
    private val profileImage = getByClass(PROFILE_IMAGE_CLASS)
    private val paymentInfoBlock = PaymentInfoBlock()

    private val EMPTY_PROFILE_IMAGE_SRC = "/assets/images/testimonials/empty.png"

    @Before
    fun goToPaymentInfo() {
        navigateToPaymentInfo()
        paymentInfoBlock.finishButton.click()
        waitForElement(paymentInfoBlock.incompletePopUp)
    }

    @Test
    @DisplayName("Incomplete popup Test")
    @Description("Your registration is incomplete' pop up appears after clicking 'finish' button on 'Payments' if any tab has completemandatory=false")
    fun incompletePopupTest() {
        assertTrue("Incomplete popup doesn't appear", paymentInfoBlock.incompletePopUp.exists())
    }

    @Test
    @DisplayName("Button on incomplete pop up Test")
    @Description("Buttons on 'Your registration is incomplete' pop up for tabs which are filled not for 100% or completemandatory=false")
    fun incompletePopupButtonsExistsTest() {
        assertTrue("Buttons are not appear", isButtonsExists())
    }

    @Test
    @DisplayName("'Add driver info' button on pop up Test")
    @Description("Clicking on 'Add driver info' button should open ${AMS + DRIVER_INFO_REL_URL}")
    fun addDriverInfoButtonPopUpTest() {
        paymentInfoBlock.addDriverInfoButton.click()
        waitInSeconds(5)
        assertTrue("'Add driver info' button navigation is not correct", checkCurrentRelUrl(AMS + DRIVER_INFO_REL_URL))
    }

    @Test
    @DisplayName("'Add vehicle info' button on pop up  Test")
    @Description("Clicking on 'Add vehicle info' button should open ${AMS + VEHICLE_INFO_REL_URL}")
    fun addVehicleInfoButtonPopUpTest() {
        addVehicleInfoButton.click()
        waitInSeconds(5)
        assertTrue("'Add vehicle info' button navigation is not correct", checkCurrentRelUrl(AMS + VEHICLE_INFO_REL_URL))
    }

    @Test
    @DisplayName("'Add payment info' button on pop up  Test")
    @Description("Clicking on 'Add payment info' button should open ${AMS + PAYMENT_INFO_REL_URL}")
    fun addPaymentInfoButtonPopUpTest() {
        paymentInfoBlock.addPaymentInfoButton.click()
        waitInSeconds(5)
        assertTrue("'Add payment info' button navigation is not correct", checkCurrentRelUrl(AMS + PAYMENT_INFO_REL_URL))
    }

    @Test
    @DisplayName("'Add later' button Test")
    @Description("Clicking 'add later'  button user is redirected to 'Almost done' screen")
    fun addLaterTest() {
        navigateToAlmostDoneScreen()
        assertTrue("'Add later' button navigation is not correct", checkCurrentRelUrl(AMS + ALMOST_DONE_REL_URL))
    }

    @Test
    @DisplayName("Buttons on 'Almost Done' screen Test")
    @Description("Buttons on 'Almost done' screen are the same as on 'Your registration is incomplete' pop up")
    fun almostDoneButtonsExistsTest() {
        navigateToAlmostDoneScreen()
        assertTrue("Buttons are not appear", isButtonsExists())
    }

    @Test
    @DisplayName("'Add driver info' button on almost done screen Test")
    @Description("Clicking on 'Add driver info' button should open ${AMS + DRIVER_INFO_REL_URL}")
    fun addDriverInfoButtonAlmostDoneScreenTest() {
        navigateToAlmostDoneScreen()
        paymentInfoBlock.addDriverInfoButton.click()
        waitInSeconds(5)
        assertTrue("'Add driver info' button navigation is not correct", checkCurrentRelUrl(AMS + DRIVER_INFO_REL_URL))
    }

    @Test
    @DisplayName("'Add vehicle info' button on almost done screen  Test")
    @Description("Clicking on 'Add vehicle info' button should open ${AMS + VEHICLE_INFO_REL_URL}")
    fun addVehicleInfoButtonAlmostDoneScreenTest() {
        navigateToAlmostDoneScreen()
        addVehicleInfoButton.click()
        waitInSeconds(5)
        assertTrue("'Add vehicle info' button navigation is not correct", checkCurrentRelUrl(AMS + VEHICLE_INFO_REL_URL))
    }

    @Test
    @DisplayName("'Add payment info' button on almost done screen  Test")
    @Description("Clicking on 'Add payment info' button should open ${AMS + PAYMENT_INFO_REL_URL}")
    fun addPaymentInfoButtonAlmostDoneScreenTest() {
        navigateToAlmostDoneScreen()
        paymentInfoBlock.addPaymentInfoButton.click()
        waitInSeconds(5)
        assertTrue("'Add payment info' button navigation is not correct", checkCurrentRelUrl(AMS + PAYMENT_INFO_REL_URL))
    }

    @Test
    @DisplayName("Google Play button Test")
    @Description("Google play button should redirect to $GOOGLE_PLAY_LINK")
    fun googlePlayButtonTest() {
        navigateToAlmostDoneScreen()
        googlePlayButton.click()
        getTabAt(1)
        assertTrue("Google Play button navigation is not correct", checkCurrentUrl(GOOGLE_PLAY_LINK))
    }

    @Test
    @DisplayName("App Store button Test")
    @Description("App Store button should redirect to $APP_STORE_LINK")
    fun appStoreButtonTest() {
        navigateToAlmostDoneScreen()
        appStoreButton.click()
        getTabAt(1)
        assertTrue("App store button navigation is not correct", checkCurrentUrl(APP_STORE_LINK))
    }

    @Test
    @DisplayName("Facebook button Test")
    @Description("Facebook button should redirect to $FACEBOOK_LINK")
    fun facebookButtonTest() {
        navigateToAlmostDoneScreen()
        facebookButton.click()
        getTabAt(1)
        assertTrue("Facebook button navigation is not correct", checkCurrentUrl(FACEBOOK_LINK))
    }

    @Test
    @DisplayName("Twitter button Test")
    @Description("Twitter button should redirect to $TWITTER_LINK")
    fun twitterButtonTest() {
        navigateToAlmostDoneScreen()
        twitterButton.click()
        getTabAt(1)
        assertTrue("Twitter button navigation is not correct", checkCurrentUrl(TWITTER_LINK))
    }

    @Test
    @DisplayName("LinkedIn button Test")
    @Description("LinkedIn button should redirect to $LINKEDIN_LINK")
    fun linkedInButtonTest() {
        navigateToAlmostDoneScreen()
        linkedInButton.click()
        assertTrue("LinkedIn button navigation is not correct", linkedInButton.checkLinkPath(LINKEDIN_LINK))
    }

    @Test
    @DisplayName("Profile image Test")
    @Description("Profile image should be displayed")
    fun profileImageTest() {
        navigateToAlmostDoneScreen()
        assertTrue("Profile image is not displayed", !profileImage.checkImage(Configuration.baseUrl + EMPTY_PROFILE_IMAGE_SRC))
    }

    private fun isButtonsExists(): Boolean =
            paymentInfoBlock.addPaymentInfoButton.exists() && paymentInfoBlock.addDriverInfoButton.exists() && addVehicleInfoButton.exists()


    private fun navigateToAlmostDoneScreen() {
        addLaterButton.click()
        waitForElement(almostDoneHeader)
    }
}