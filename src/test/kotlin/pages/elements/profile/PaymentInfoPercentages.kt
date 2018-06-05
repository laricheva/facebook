package pages.elements.profile

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import constants.PERCENT_TEXT_CSS_SELECTOR
import extensions.isContainText
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Payment Info Percentages Block Test")
class PaymentInfoPercentages: BaseProfilePageUi() {

    private val percentsText = Selenide.`$$`(PERCENT_TEXT_CSS_SELECTOR).findBy(Condition.text(localization.profile.common.paymentInfo))

    private val paymentInfoBlock = PaymentInfoBlock()

    @Before
    fun goToPaymentInfoBeforeTest() {
        navigateToPaymentInfo()
    }

    @Test
    @DisplayName("Default percent of 'Payment Info' Test")
    @Description("Default percent of 'Payment Info' should be 16%")
    fun defaultPercentOfProfileTest() {
        assertTrue("Default percent of profile is not correct", percentsText.isContainText("16%"))
    }

    @Test
    @DisplayName("Required fields of 'Payment Info' Test")
    @Description("Percent of 'Payment Info' should change to checked image and after clicking 'Finish' there is.")
    fun requiredFieldsTest() {
        paymentInfoBlock.fillAllRequiredFields()
        waitForElement(paymentInfoBlock.incompletePopUp)
        paymentInfoBlock.addDriverInfoButton.click()
        assertTrue("Percent of 'Payment info' is not correct", paymentInfoBlock.percentagesFullImage.exists())
    }
}