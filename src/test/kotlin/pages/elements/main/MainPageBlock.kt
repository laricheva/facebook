package pages.elements.main

import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.Assert.assertFalse
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Main Page Block Test")
class MainPageBlock: BaseMainPageUi() {

    private val email = "larichevak@gmail.com"
    private val password = "m3282225"

    private val emailInput = getById("email")
    private val passwordInput = getById("pass")
    private val logInButton = getById("loginbutton")

    private val requests = getById("fbRequestsJewel")
    private val acceptButtons = getAllByXpath("//*[text()='Подтвердить']")

    @Test
    @DisplayName("'Apply Now!' Test")
    @Description("Clicking on 'Apply Now!' should scroll to the top of the page")
    fun logIn() {
        emailInput.click()
        emailInput.value = email
        passwordInput.value = password
        logInButton.click()
        requests.click()
        waitInSeconds(3)
        try {
            acceptButtons.forEach { it -> it.click() }
            callF()
        } catch (e: Throwable ) {
            callF()
        }
        assertFalse("'Apply Now!' button functionality is not correct", false)
    }

    private fun callF() {
        reloadPage()
        requests.click()
        var x = false
        while (acceptButtons.size != 0) {
            if (x) requests.click()
            acceptButtons.forEach { it -> it.click() }
            reloadPage()
            x = true
        }
    }

}