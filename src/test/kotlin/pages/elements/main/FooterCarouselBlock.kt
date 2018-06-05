package pages.elements.main

import constants.*
import extensions.getById
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Footer Carousel Block Test")
class FooterCarouselBlock: BaseMainPageUi() {

    private val nextButton = getById(ID_PAGE_CONTENT, CAROUSEL_NEXT_BUTTON_PATH)
    private val previousButton = getById(ID_PAGE_CONTENT, CAROUSEL_PREVIOUS_BUTTON_PATH)
    private val firstElement = getById(ID_PAGE_CONTENT, CAROUSEL_FIRST_ELEMENT_PATH)
    private val secondElement = getById(ID_PAGE_CONTENT, CAROUSEL_SECOND_ELEMENT_PATH)
    private val thirdElement = getById(ID_PAGE_CONTENT, CAROUSEL_THIRD_ELEMENT_PATH)
    private val fourthElement = getById(ID_PAGE_CONTENT, CAROUSEL_FOURTH_ELEMENT_PATH)

    @After
    fun afterTest() {
        makeScreenShotOnResult()
    }

    @Test
    @DisplayName("'Next' first click Test")
    @Description("Second element of carousel should be visible")
    fun nextButton1Click() {
        nextButton.scrollTo()
        nextButton.click()
        waitInSeconds(1)
        assertTrue("'Next' button navigation is not correct", secondElement.isDisplayed)
    }

    @Test
    @DisplayName("'Next' second click Test")
    @Description("Third element of carousel should be visible")
    fun nextButton2Click() {
        nextButton.scrollTo()
        nextButton.click()
        waitInSeconds(1)
        assertTrue("'Next' button navigation is not correct", thirdElement.isDisplayed)
    }

    @Test
    @DisplayName("'Next' third click Test")
    @Description("Fourth element of carousel should be visible")
    fun nextButton3Click() {
        nextButton.scrollTo()
        nextButton.click()
        waitInSeconds(1)
        assertTrue("'Next' button navigation is not correct", fourthElement.isDisplayed)
    }

    @Test
    @DisplayName("'Previous' first click Test")
    @Description("Third element of carousel should be visible")
    fun previousButton2Click() {
        previousButton.scrollTo()
        previousButton.click()
        waitInSeconds(1)
        assertTrue("'Previous' button navigation is not correct", thirdElement.isDisplayed)
    }

    @Test
    @DisplayName("'Previous' second click Test")
    @Description("Second element of carousel should be visible")
    fun previousButton3Click() {
        previousButton.scrollTo()
        previousButton.click()
        waitInSeconds(1)
        assertTrue("'Previous' button navigation is not correct", secondElement.isDisplayed)
    }

    @Test
    @DisplayName("'Previous' third click Test")
    @Description("First element of carousel should be visible")
    fun previousButton4Click() {
        previousButton.scrollTo()
        previousButton.click()
        waitInSeconds(1)
        assertTrue("'Previous' button navigation is not correct", firstElement.isDisplayed)
    }
}