package pages.localization.main

import constants.Languages
import extensions.getCurrentLocalization
import io.qameta.allure.junit4.DisplayName
import org.junit.BeforeClass
import org.junit.Test
import resources.Localization

@DisplayName("Main Page in NL Test")
class MainPageNl: BaseMainPage() {

    companion object {

        @BeforeClass @JvmStatic fun setup() {
            BaseMainPage().switchToNl()
        }

    }

    private val nl = getCurrentLocalization(Languages.NL) as Localization.Nl

    @Test
    fun supportLinkTextTest() {
        isValidSupportLinkText(nl.main.support)
    }

    @Test
    fun loginLinkTextTest() {
        isValidLoginLinkText(nl.main.loginLink)
    }

    @Test
    fun languageLinkTextTest() {
        isValidLanguageLinkText(nl.main.english)
    }

    @Test
    fun weAreComingLinkText() {
        isValidWeAreComingLinkText(nl.main.weAreComingToAms)
    }

    @Test
    fun joinTeamLinkText() {
        isValidJoinTeamLinkText(nl.main.joinTeamViavan)
    }

    @Test
    fun advantagesTitleText() {
        isValidAdvantagesTitleText(nl.main.whyDriveWithViavan)
    }

    @Test
    fun firstAdvantageTitleTest() {
        isValidFirstAdvantageTitleText(nl.main.lowestComissionAms)
    }

    @Test
    fun firstAdvantageTextTest() {
        isValidFirstAdvantageText(nl.main.benefitEarnMoreAms)
    }

    @Test
    fun secondAdvantageTitleTest() {
        isValidSecondAdvantageTitleText(nl.main.industryAms)
    }

    @Test
    fun secondAdvantageTextTest() {
        isValidSecondAdvantageText(nl.profile.driverInfo.benefitWeGiveEfficientAms)
    }

    @Test
    fun thirdAdvantageTitleTest() {
        isValidThirdAdvantageTitleText(nl.main.realTimeAms)
    }

    @Test
    fun thirdAdvantageTextTest() {
        isValidThirdAdvantageText(nl.main.talOrText2)
    }

    @Test
    fun fourthAdvantageTitleTest() {
        isValidFourthAdvantageTitleText(nl.main.industry)
    }

    @Test
    fun fourthAdvantageTextTest() {
        isValidFourthAdvantageText(nl.main.benefitJoinTeamThatAms)
    }

    @Test
    fun applyNowButtonTextTest() {
        isValidApplyNowButtonText(nl.main.applyNow)
    }

    @Test
    fun firstDriverPartnerTextTest() {
        isValidFirstDriverPartnerText(DRIVER_PARTNER)
    }

    @Test
    fun secondDriverPartnerTextTest() {
        isValidSecondDriverPartnerText(DRIVER_PARTNER)
        switchToNl()
    }

    @Test
    fun thirdDriverPartnerTextTest() {
        isValidThirdDriverPartnerText(DRIVER_PARTNER)
        switchToNl()
    }

    @Test
    fun fourthDriverPartnerTextTest() {
        isValidFourthDriverPartnerText(DRIVER_PARTNER)
        switchToNl()
    }

    @Test
    fun firstCarouselElementTextTest() {
        isValidFirstElementText(nl.main.ifSomeone)
    }

    @Test
    fun secondCarouselElementTextTest() {
        isValidSecondElementText(nl.main.iLikeViaAms)
        switchToNl()
    }

    @Test
    fun thirdCarouselElementTextTest() {
        isValidThirdElementText(nl.main.aGoodJobAms)
        switchToNl()
    }

    @Test
    fun fourthCarouselElementTextTest() {
        isValidFourthElementText(nl.main.whatILoveAms)
        switchToNl()
    }

    @Test
    fun firstJoinMeTextTest() {
        isValidFirstJoinMeButtonText(nl.main.joinMe)
    }

    @Test
    fun secondJoinMeTextTest() {
        isValidSecondJoinMeButtonText(nl.main.joinMe)
        switchToNl()
    }

    @Test
    fun thirdJoinMeTextTest() {
        isValidThirdJoinMeButtonText(nl.main.joinMe)
        switchToNl()
    }

    @Test
    fun fourthJoinMeTextTest() {
        isValidFourthJoinMeButtonText(nl.main.joinMe)
        switchToNl()
    }

    // sign up
    @Test
    fun signUPTitleTest() {
        signUPTitleTest(nl.main.signUpLink)
    }

    @Test
    fun firstNameLabelTest() {
        firstNameLabelTest(nl.common.firstName)
    }

    @Test
    fun firstNameEmptyErrorMessageTest() {
        firstNameEmptyErrorMessageTest(nl.common.fieldMandatory)
        switchToNl()
    }

    @Test
    fun firstNameInvalidErrorMessageTest() {
        firstNameInvalidErrorMessageTest(nl.common.firstNameValidation)
        switchToNl()
    }

    @Test
    fun lastNameLabelTest() {
        lastNameLabelTest(nl.common.lastName)
    }

    @Test
    fun lastNameEmptyErrorMessageTest() {
        lastNameEmptyErrorMessageTest(nl.common.fieldMandatory)
        switchToNl()
    }

    @Test
    fun lastNameInvalidErrorMessageTest() {
        lastNameInvalidErrorMessageTest(nl.common.lastNameValidation)
        switchToNl()
    }

    @Test
    fun phoneLabelTest() {
        phoneLabelTest(nl.common.phone)
    }

    @Test
    fun phoneEmptyErrorMessageTest() {
        phoneEmptyErrorMessageTest(nl.common.fieldMandatory)
        switchToNl()
    }

    @Test
    fun phoneInvalidErrorMessageTest() {
        phoneInvalidErrorMessageTest(nl.common.mobileNlValidation)
        switchToNl()
    }

    @Test
    fun emailLabelTest() {
        emailLabelTest(nl.main.email)
    }

    @Test
    fun emailEmptyErrorMessageTest() {
        emailEmptyErrorMessageTest(nl.common.fieldMandatory)
        switchToNl()
    }

    @Test
    fun emailInvalidErrorMessageTest() {
        emailInvalidErrorMessageTest(nl.common.emailInvalid)
        switchToNl()
    }

    @Test
    fun cityLabelTest() {
        cityLabelTest(nl.common.city)
    }

    @Test
    fun marketingMaterialsTextTest() {
        marketingMaterialsTextTest(nl.main.receiveMarketingAgreement)
    }

    @Test
    fun signUpAgreementTextTest() {
        signUpAgreementTextTest(nl.main.agreeClick)
    }

    @Test
    fun signUpSubmitButtonTest() {
        signUpSubmitButtonTest(nl.main.next)
    }

    @Test
    fun signUpBottomTextTest() {
        signUpBottomTextTest("${nl.main.alreadyDrivingViavan} ${nl.main.logInHere}")
    }

    @Test
    fun signUpPendingTitleTest() {
        signUpPendingTitleTest(nl.main.holdTight)
        switchToNl()
    }

    @Test
    fun signPendingTextTest() {
        signUpPendingTextTest(nl.main.wellDone)
        switchToNl()
    }

}
