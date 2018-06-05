package pages.elements.main

import constants.APPLY_NOW_BUTTON_PATH
import constants.FIXED_HEADER_CLASS
import constants.ID_PAGE_CONTENT
import constants.JOIN_ME_BUTTON_PATH
import extensions.getByClass
import extensions.getById
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Main Page Block Test")
class MainPageBlock: BaseMainPageUi() {

    private val fixedHeader = getByClass(FIXED_HEADER_CLASS)
    private val applyNowButton = getById(ID_PAGE_CONTENT, APPLY_NOW_BUTTON_PATH)
    private val joinMeButton = getById(ID_PAGE_CONTENT, JOIN_ME_BUTTON_PATH)

    @After
    fun afterTest() {
        makeScreenShotOnResult()
    }

    @Test
    @DisplayName("'Apply Now!' Test")
    @Description("Clicking on 'Apply Now!' should scroll to the top of the page")
    fun applyNowScroll() {
        applyNowButton.scrollTo()
        applyNowButton.click()
        waitInSeconds(1)
        assertFalse("'Apply Now!' button functionality is not correct", fixedHeader.exists())
    }

    @Test
    @DisplayName("'Join me!' Test")
    @Description("Clicking on 'Join me!' should scroll to the top of the page")
    fun joinMeScroll() {
        joinMeButton.scrollTo()
        joinMeButton.click()
        waitInSeconds(1)
        assertFalse("'Join me!' button functionality is not correct", fixedHeader.exists())
    }
}