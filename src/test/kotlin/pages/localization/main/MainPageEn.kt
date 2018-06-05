package pages.localization.main

import constants.Languages
import extensions.getCurrentLocalization
import io.qameta.allure.junit4.DisplayName
import org.junit.Test
import resources.Localization

@DisplayName("Main Page in EN Test")
class MainPageEn: BaseMainPage() {

    private val en = getCurrentLocalization(Languages.EN) as Localization.En

    @Test
    fun supportLinkTextTest() {
        isValidSupportLinkText(en.main.support)
    }

    @Test
    fun loginLinkTextTest() {
        isValidLoginLinkText(en.main.loginLink)
    }

    @Test
    fun languageLinkTextTest() {
        isValidLanguageLinkText(en.main.dutch)
    }

    @Test
    fun weAreComingLinkText() {
        isValidWeAreComingLinkText(en.main.weAreComingToAms)
    }

    @Test
    fun joinTeamLinkText() {
        isValidJoinTeamLinkText(en.main.joinTeamViavan)
    }

    @Test
    fun advantagesTitleText() {
        isValidAdvantagesTitleText(en.main.whyDriveWithViavan)
    }

    @Test
    fun firstAdvantageTitleTest() {
        isValidFirstAdvantageTitleText(en.main.lowestComissionAms)
    }

    @Test
    fun firstAdvantageTextTest() {
        isValidFirstAdvantageText(en.main.benefitEarnMoreAms)
    }

    @Test
    fun secondAdvantageTitleTest() {
        isValidSecondAdvantageTitleText(en.main.industryAms)
    }

    @Test
    fun secondAdvantageTextTest() {
        isValidSecondAdvantageText(en.profile.driverInfo.benefitWeGiveEfficientAms)
    }

    @Test
    fun thirdAdvantageTitleTest() {
        isValidThirdAdvantageTitleText(en.main.realTimeAms)
    }

    @Test
    fun thirdAdvantageTextTest() {
        isValidThirdAdvantageText(en.main.talOrText2)
    }

    @Test
    fun fourthAdvantageTitleTest() {
        isValidFourthAdvantageTitleText(en.main.industry)
    }

    @Test
    fun fourthAdvantageTextTest() {
        isValidFourthAdvantageText(en.main.benefitJoinTeamThatAms)
    }

    @Test
    fun applyNowButtonTextTest() {
        isValidApplyNowButtonText(en.main.applyNow)
    }

    @Test
    fun firstDriverPartnerTextTest() {
        isValidFirstDriverPartnerText(DRIVER_PARTNER)
    }

    @Test
    fun secondDriverPartnerTextTest() {
        isValidSecondDriverPartnerText(DRIVER_PARTNER)
    }

    @Test
    fun thirdDriverPartnerTextTest() {
        isValidThirdDriverPartnerText(DRIVER_PARTNER)
    }

    @Test
    fun fourthDriverPartnerTextTest() {
        isValidFourthDriverPartnerText(DRIVER_PARTNER)
    }

    @Test
    fun firstCarouselElementTextTest() {
        isValidFirstElementText(en.main.ifSomeone)
    }

    @Test
    fun secondCarouselElementTextTest() {
        isValidSecondElementText(en.main.iLikeViaAms)
    }

    @Test
    fun thirdCarouselElementTextTest() {
        isValidThirdElementText(en.main.aGoodJobAms)
    }

    @Test
    fun fourthCarouselElementTextTest() {
        isValidFourthElementText(en.main.whatILoveAms)
    }

    @Test
    fun firstJoinMeTextTest() {
        isValidFirstJoinMeButtonText(en.main.joinMe)
    }

    @Test
    fun secondJoinMeTextTest() {
        isValidSecondJoinMeButtonText(en.main.joinMe)
    }

    @Test
    fun thirdJoinMeTextTest() {
        isValidThirdJoinMeButtonText(en.main.joinMe)
    }

    @Test
    fun fourthJoinMeTextTest() {
        isValidFourthJoinMeButtonText(en.main.joinMe)
    }

    // sign up
    @Test
    fun signUPTitleTest() {
        signUPTitleTest(en.main.signUpLink)
    }

    @Test
    fun firstNameLabelTest() {
        firstNameLabelTest(en.common.firstName)
    }

    @Test
    fun firstNameEmptyErrorMessageTest() {
        firstNameEmptyErrorMessageTest(en.common.fieldMandatory)
    }

    @Test
    fun firstNameInvalidErrorMessageTest() {
        firstNameInvalidErrorMessageTest(en.common.firstNameValidation)
    }

    @Test
    fun lastNameLabelTest() {
        lastNameLabelTest(en.common.lastName)
    }

    @Test
    fun lastNameEmptyErrorMessageTest() {
        lastNameEmptyErrorMessageTest(en.common.fieldMandatory)
    }

    @Test
    fun lastNameInvalidErrorMessageTest() {
        lastNameInvalidErrorMessageTest(en.common.lastNameValidation)
    }

    @Test
    fun phoneLabelTest() {
        phoneLabelTest(en.common.phone)
    }

    @Test
    fun phoneEmptyErrorMessageTest() {
        phoneEmptyErrorMessageTest(en.common.fieldMandatory)
    }

    @Test
    fun phoneInvalidErrorMessageTest() {
        phoneInvalidErrorMessageTest(en.common.mobileNlValidation)
    }

    @Test
    fun emailLabelTest() {
        emailLabelTest(en.main.email)
    }

    @Test
    fun emailEmptyErrorMessageTest() {
        emailEmptyErrorMessageTest(en.common.fieldMandatory)
    }

    @Test
    fun emailInvalidErrorMessageTest() {
        emailInvalidErrorMessageTest(en.common.emailInvalid)
    }

    @Test
    fun cityLabelTest() {
        cityLabelTest(en.common.city)
    }

    @Test
    fun marketingMaterialsTextTest() {
        marketingMaterialsTextTest(en.main.receiveMarketingAgreement)
    }

    @Test
    fun signUpAgreementTextTest() {
        signUpAgreementTextTest(en.main.agreeClick)
    }

    @Test
    fun signUpSubmitButtonTest() {
        signUpSubmitButtonTest(en.main.next)
    }

    @Test
    fun signUpBottomTextTest() {
        signUpBottomTextTest("${en.main.alreadyDrivingViavan} ${en.main.logInHere}")
    }

    @Test
    fun signUpPendingTitleTest() {
        signUpPendingTitleTest(en.main.holdTight)
    }

    @Test
    fun signUpPendingTextTest() {
        signUpPendingTextTest(en.main.wellDone)
    }
}
