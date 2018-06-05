package pages.elements.main

import constants.*
import extensions.*
import io.qameta.allure.Description
import io.qameta.allure.junit4.DisplayName
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.runners.MethodSorters
import resources.Questionnaire
import java.util.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Questionnaire Amsterdam Test")
class Questionnaire: BaseMainPageUi() {

    val questionnaireAms = getCityQuestionnaire(AMS_ID) as Questionnaire
    private val yesNoQuestList = questionnaireAms.questionDtos.filter{it.type == "boolean"}.reversed()
    private val questionnaireErrors = listOf(
            localization.main.issue1,
            localization.main.issue2,
            localization.main.issue3,
            localization.main.issue4,
            localization.main.issue5,
            localization.main.issue6
            )

    private val firstNameInput = getByAttributeAndValue(FORM_CONTROL_NAME, FIRST_NAME)
    private val lastNameInput = getByAttributeAndValue(FORM_CONTROL_NAME, LAST_NAME)
    private val phoneInput = getByAttributeAndValue(FORM_CONTROL_NAME, PHONE)
    private val emailInput = getByAttributeAndValue(FORM_CONTROL_NAME, EMAIL)
    private val signUpNextButton = getButtonByText(localization.main.next)
    val questNextButton = getById(QUESTIONNAIRE_NEXT_BUTTON_ID)
    private val signUpData = amsSignUp()
    private val questionEl = getByXpath(QUESTIONNAIRE_BLOCK_XPATH)
    private val yesButton = getLabelByText(localization.main.yes)
    private val noButton = getLabelByText(localization.main.no)
    private val issuesCollection = ISSUE_COLLECTION.getAll()
    val dropDownEl = getByText(localization.common.pleaseSelect, FOLLOWING_SIBLING_SELECT_CONTROL)
    val dropdownList = getByAttributeAndValue(ARIA_LABELLEDBY, REMINDER_SWITCHER)
    val facebookItem = getByExactText(localization.main.acquisitionFb)
    private val referByDriverItem = getByExactText(localization.main.referredByViaDriver)
    private val referByRiderItem = getByExactText(localization.main.referredByRider)
    private val otherItem = getByExactText(localization.common.other)
    private val teamViavanItem = getByExactText(localization.main.acquisitionTeamViavanRode)
    val selectItemInput = getByXpath(QUESTIONNAIRE_BLOCK_SELECT_ITEM_XPATH)
    private val issueMessage = getByClass(QUESTIONNAIRE_ISSUE_MESSAGE_CLASS)
    val questSpinner = getByClass(SPINNER_CLASS)

    private val itemsWithInputArray = arrayOf(referByDriverItem, referByRiderItem, otherItem, teamViavanItem)
    val howDidYouHearItems = itemsWithInputArray + facebookItem

    fun clickYesButton() {
        while (yesButton.exists()) {
            yesButton.click()
            waitInSecondsFloat(QUEST_DELAY)
        }
    }

    @Before
    fun runSignUp() {
        setUp()
        waitForElement(signUpNextButton)
        firstNameInput.value = signUpData.firstName
        lastNameInput.value = signUpData.lastName
        phoneInput.value = signUpData.phone
        emailInput.value = signUpData.email
        signUpNextButton.click()
        waitForElement(questionEl)
    }

    @After
    fun closeBrowser() {
        makeScreenShotOnResult()
        salesforceBlock.deleteDriver(signUpData.email)
        teardown()
    }

    @Test
    @DisplayName("Questionnaire opens")
    @Description("Questionnaire should be shown after success sign up")
    fun isQuestAppears() {
        assertTrue("'Questionnaire' doesn't appear after sign up", questionEl.exists())
    }

    @Test
    @DisplayName("6 questions yes/no")
    @Description("The first 6 questions should be 'yes/no' type")
    fun yesNoQuestionsCount() {
        var count = 0;
        while (yesButton.exists()) {
            yesButton.click()
            waitInSeconds(1)
            count +=1
        }
        assertTrue("${yesNoQuestList.size} questions yes/no' is not correct", count == yesNoQuestList.size)
    }

    @Test
    @DisplayName("All answers 'no'")
    @Description("Screen with 6 issues should be shown after answer 'no' on all questions")
    fun allQuestionsNo() {
        while (noButton.exists()) {
            noButton.click()
            waitInSeconds(1)
        }
        questNextButton.click()
        questSpinner.waitUntilDisappears()
        assertTrue("'Errors are shown' is not correct", issuesCollection.size == yesNoQuestList.size)
    }

    @Test
    @DisplayName("Dropdown list")
    @Description("Dropdown list should be shown after 'yes/no' questions")
    fun isDropdownShown() {
        clickYesButton()
        assertTrue("'Dropdown' functionality is not correct", dropDownEl.exists())
    }

    @Test
    @DisplayName("One random answer 'no'")
    @Description("Screen with one issue should be shown after answering one random question 'no'")
    fun randomOneNoQuest() {
        var count = 0;
        val oneRandomIndex = Random().nextInt(yesNoQuestList.size)
        while (yesButton.exists()) {
            if (count == oneRandomIndex) {
                noButton.click()
            } else {
                yesButton.click()
            }
            waitInSeconds(1)
            count +=1
        }
        questNextButton.click()
        questSpinner.waitUntilDisappears()
        assertTrue("'Amount of issues equals the amount of negative answers' functionality doesn't work", issuesCollection.size == 1)
    }

    @Test
    @DisplayName("3 random answers 'no'")
    @Description("The screen with 3 issues should be shown after answering three random questions 'no'")
    fun randomThreeNoQuest() {
        var count = 0;
        var randomIndexArray = arrayOf<Int>()
        while(randomIndexArray.size < 3) {
            val randomIndex = Random().nextInt(yesNoQuestList.size)
            if (!randomIndexArray.contains(randomIndex)) {
                randomIndexArray += randomIndex
            }
        }
        while (yesButton.exists()) {
            if (randomIndexArray.contains(count)) {
                noButton.click()
            } else {
                yesButton.click()
            }
            waitInSeconds(1)
            count +=1
        }
        questNextButton.click()
        questSpinner.waitUntilDisappears()
        assertTrue("'Amount of issues equals the amount of negative answers' functionality doesn't work", issuesCollection.size == 3)
    }

    @Test
    @DisplayName("All questions 'yes' + skip dropdown list")
    @Description("Driver info page should open after answering all questions 'yes' and skipping dropdown list")
    fun questSuccess() {
        clickYesButton()
        questNextButton.click()
        questSpinner.waitUntilDisappears()
        assertTrue("'Questionnaire success' functionality doesn't work", checkCurrentRelUrl(AMS + DRIVER_INFO_REL_URL))
    }

    @Test
    @DisplayName("Dropdown list opens")
    @Description("Dropdown list should open after clicking on the input field")
    fun openDropdownList() {
        clickYesButton()
        dropDownEl.click()
        assertTrue("'Dropdown opens' functionality doesn't work", dropdownList.exists())
    }

    @Test
    @DisplayName("Dropdown list: facebook")
    @Description("Driver info page should open after choosing 'facebook' option from the dropdown list")
    fun dropdownSelectFb() {
        clickYesButton()
        dropDownEl.click()
        facebookItem.click()
        questNextButton.click()
        questSpinner.waitUntilDisappears()
        assertTrue("'Questionnaire success' functionality doesn't work", checkCurrentRelUrl(AMS + DRIVER_INFO_REL_URL))
    }

    @Test
    @DisplayName("All options except 'facebook' should have the extra question")
    @Description("The screen with input field should open after choosing any option from the dropdown list except 'facebook'")
    fun checkInputForAllFields() {
        var inputsArray = arrayOf<Boolean>()
        for (i in itemsWithInputArray.indices) {
            clickYesButton()
            dropDownEl.click()
            itemsWithInputArray[i].click()
            questNextButton.click()
            waitInSeconds(1)
            inputsArray += selectItemInput.exists()
            getWebDriver().navigate().refresh()
            waitInSeconds(3)
        }
        assertTrue("'All items contains input except Fb' is not correct", inputsArray.all{ t -> t })
    }

    @Test
    @DisplayName("Skip extra question")
    @Description("Driver info page should open if user doesn't enter value in input and clicks 'next' button")
    fun skipInputField() {
        val randomItemSelect = Random().nextInt(4)
        clickYesButton()
        dropDownEl.click()
        itemsWithInputArray[randomItemSelect].click()
        questNextButton.click()
        waitInSeconds(1)
        questNextButton.click()
        questSpinner.waitUntilDisappears()
        assertTrue("'Questionnaire success' functionality doesn't work", checkCurrentRelUrl(AMS+DRIVER_INFO_REL_URL))
    }

    @Test
    @DisplayName("Fill in extra input field")
    @Description("Driver info page should open after filling extra input field and clicking 'next' button")
    fun fillInputField() {
        val randomItemSelect = Random().nextInt(4)
        clickYesButton()
        dropDownEl.click()
        val text = itemsWithInputArray[randomItemSelect].text()
        itemsWithInputArray[randomItemSelect].click()
        questNextButton.click()
        waitInSeconds(1)
        selectItemInput.value = text
        questNextButton.click()
        questSpinner.waitUntilDisappears()
        assertTrue("'Questionnaire success' functionality doesn't work", checkCurrentRelUrl(AMS+DRIVER_INFO_REL_URL))
    }

    @Test
    @DisplayName("Error messages")
    @Description("Appropriate error message should shown on each 'no' answer")
    fun checkErrorMessages() {
        var correctIssuesArray = arrayOf<Boolean>()
        for (i in yesNoQuestList.indices) {
            var count = 0;
            while (yesButton.exists()) {
                if (count == i) {
                    noButton.click()
                } else {
                    yesButton.click()
                }
                waitInSeconds(1)
                count += 1
            }
            questNextButton.click()
            questSpinner.waitUntilDisappears()
            correctIssuesArray += issueMessage.innerHtml() == questionnaireErrors[i]
            getWebDriver().navigate().refresh()
            waitInSeconds(3)
        }
        var nums = ""
        for (i in correctIssuesArray.indices) {
            if (!correctIssuesArray[i]) {
                nums  += "${i+1}, "
            }
        }
        if (!nums.isBlank()) nums = nums.substring(0, nums.length - 2)
        val message = if (correctIssuesArray.filter{it == false}.size == 1) "Issue message is wrong for $nums question" else "Issue messages are wrong for $nums questions"
        assertTrue(message, correctIssuesArray.all{ t -> t })
    }

    @Test
    @DisplayName("Not finished questionnaire Test")
    @Description("If user doesn't finish answer questionnaire and reload the page the beginning of the questionnaire should appear")
    fun notFinishedQuest() {
        val answeredQuestionsAmount = Random().nextInt(yesNoQuestList.size)
        var count = 0
        while (count < answeredQuestionsAmount) {
            yesButton.click()
            waitInSeconds(1)
            count += 1
        }
        reloadPage()
        waitForElement(getByClass(SIGN_FORM_CLASS))
        val questFirstQuestTitle = getByExactText(localization.main.questFirstQuestion)
        assertTrue("Returning to the beginning of the questionnaire doesn't work", questFirstQuestTitle.exists())
    }

}