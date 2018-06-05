package pages.salesforce

import base.BaseSalesforceTest
import constants.*
import data.UserInfo
import data.VehicleInfo
import extensions.*
import network.DriverVehicleJunctionResponse
import network.UserIdResponse
import network.UserInfoResponse
import network.VehicleInfoResponse
import org.junit.Assert.assertTrue
import pages.elements.main.Questionnaire
import resources.Questionnaire as QuestModel

class SalesforceBlock : BaseSalesforceTest() {

    companion object {
        private var driverId = EMPTY_STRING
        private var vehicleId = EMPTY_STRING
        private var driverVehicleJunctionId = EMPTY_STRING
    }

    fun checkUserCreatedTest(email: String) {
        driverId = getDriverId(email).records[0].Id
        assertTrue("User with email: $email is not created", driverId.isNotEmpty())
    }

    fun checkUserFieldsSaved(signUpData: amsSignUp) {
        driverId = getDriverId(signUpData.email).records[0].Id
        assertTrue("User fields are not saved", checkFoundUserFields(driverId, signUpData))
    }

    fun checkThatAllImagesAreSaved(email: String) {
        driverId = getDriverId(email).records[0].Id
        val userInfoSF = BaseSalesforceTest.getUserInfo(driverId)
        assertTrue("Images are not saved on SF", checkImagesExist(userInfoSF))
    }

    fun checkThatAllInfoIsSaved(userInfo: UserInfo) {
        driverId = getDriverId(userInfo.email).records[0].Id
        val userInfoSF = BaseSalesforceTest.getUserInfo(driverId)
        assertTrue("Information is not saved on SF", checkImagesExist(userInfoSF) &&
                checkUserInfoFields(userInfo, userInfoSF))
    }

    fun checkThatVehicleCreated(email: String, plateNumber: String) {
        driverId = getDriverId(email).records[0].Id
        val userInfoSF = BaseSalesforceTest.getUserInfo(driverId)
        vehicleId = userInfoSF.vehicleId
        val vehicleInfoSF = BaseSalesforceTest.getVehicleInfo(userInfoSF.vehicleId)
        driverVehicleJunctionId = getDriverVehicleJunctionId(vehicleInfoSF.plateNumber.toUpperCase()).records[0].Id
        assertTrue("Vehicle with plate #: $plateNumber is not created", vehicleInfoSF.plateNumber == plateNumber)
    }

    fun checkThatAllVehicleValuesSaved(email: String, vehicle: VehicleInfo) {
        driverId = getDriverId(email).records[0].Id
        val userInfoSF = BaseSalesforceTest.getUserInfo(driverId)
        val vehicleInfoSF = BaseSalesforceTest.getVehicleInfo(userInfoSF.vehicleId)
        assertTrue("Values are not correct", checkAllVehicleValues(vehicle, vehicleInfoSF))
    }

    fun checkThatAllVehicleImagesAreSaved(email: String) {
        driverId = getDriverId(email).records[0].Id
        val userInfoSF = BaseSalesforceTest.getUserInfo(driverId)
        val vehicleInfoSF = BaseSalesforceTest.getVehicleInfo(userInfoSF.vehicleId)
        assertTrue("Vehicle image are not save on SF", checkVehicleImagesExist(vehicleInfoSF))
    }

    fun checkQuestValueAreSaved(email: String, source: String = EMPTY_STRING) {
        val howDidYouHearQuest: List<QuestModel.Question> = Questionnaire().questionnaireAms.questionDtos.filter { it: QuestModel.Question -> it.id == 7 }
        val answer: List<QuestModel.Question.Answer> = howDidYouHearQuest[0].answers.filter { it -> it.text == source }
        driverId = getDriverId(email).records[0].Id
        val userInfoSF = BaseSalesforceTest.getUserInfo(driverId)
        var sfFieldsArray = arrayOf<Boolean>()
        sfFieldsArray += userInfoSF.hasProfLicense == QUEST_YES_TEXT
        sfFieldsArray += userInfoSF.hasTaxivergunning == QUEST_YES_TEXT
        sfFieldsArray += userInfoSF.hasVehicle == QUEST_YES_TEXT
        sfFieldsArray += userInfoSF.hasBlueLicensePlate == QUEST_YES_TEXT
        if (source != QUEST_FACEBOOK) {
            sfFieldsArray += userInfoSF.howDidYouHearItem == answer[0].value
            if (source != QUEST_REF_DRIVER) {
                sfFieldsArray += userInfoSF.howDidYouHearText == source
            } else {
                sfFieldsArray += userInfoSF.referralDriver == source
            }
        } else {
            sfFieldsArray += userInfoSF.howDidYouHearItem == source
        }
        assertTrue("Questionnaire values aren't saved on SF", sfFieldsArray.all { it -> it })
    }

    private fun checkAllVehicleValues(vehicle: VehicleInfo, vehicleSF: VehicleInfoResponse): Boolean {
        val sfVehicleInfo = VehicleInfo(vehicleSF.make, vehicleSF.model, vehicleSF.color, vehicleSF.interior, vehicleSF.year, vehicleSF.plateNumber)
        return sfVehicleInfo == vehicle
    }

    private fun checkVehicleImagesExist(vehicleInfoSF: VehicleInfoResponse): Boolean =
        vehicleInfoSF.vehRegBackImage.notNull()
        && vehicleInfoSF.vehRegFrontImage.notNull()
        && vehicleInfoSF.vehInsurImage.notNull()
        && vehicleInfoSF.vehInspectImage.notNull()
        && vehicleInfoSF.vehExteriorImage.notNull()

    private fun checkImagesExist(userInfo: UserInfoResponse): Boolean =
        userInfo.profileImage.notNull()
        && userInfo.taxivergunningbewijsImage.notNull()
        && userInfo.profDriverLicenseImage.notNull()
        && userInfo.driverLicenseBackImage.notNull()
        && userInfo.driverLicenseFrontImage.notNull()
        && userInfo.tramwayExemptionImage.notNull()

    private fun checkFoundUserFields(id: String, signUpData: amsSignUp): Boolean {
        val userInfoData = BaseSalesforceTest.getUserInfo(id)
        return userInfoData.firstName == signUpData.firstName
                && userInfoData.lastName == signUpData.lastName
                && userInfoData.phoneNumber == "$AMS_PHONE_CODE${signUpData.phone}"
    }

    private fun checkUserInfoFields(userInfo: UserInfo, userInfoSF: UserInfoResponse): Boolean {
        val driverInfo = UserInfo(userInfoSF.firstName,
                                    userInfoSF.lastName,
                                    userInfoSF.email,
                                    userInfoSF.phoneNumber,
                                    userInfoSF.dateOfBirth.dateFormat(),
                                    userInfoSF.address1,
                                    userInfoSF.address2,
                                    userInfoSF.addressZip,
                                    userInfoSF.addressCity)
        return driverInfo == userInfo
    }

    private fun getDriverId(email: String): UserIdResponse {
        return BaseSalesforceTest.getDriverId("SELECT Id from Driver__c WHERE Email__c LIKE '$email'")
    }

    private fun getDriverVehicleJunctionId(plate: String): DriverVehicleJunctionResponse {
        return BaseSalesforceTest.getDriverVehicleJunctionId("SELECT Id from Driver_Vehicle_Junction__c WHERE Plate__c LIKE '$plate'")
    }

    private fun deleteUser() {
        BaseSalesforceTest.deleteUser(driverId, vehicleId, driverVehicleJunctionId ).subscribe(
                {_ -> resetIds() },
                {_ -> resetIds() }
        )
    }

    private fun resetIds() {
        if (driverId != EMPTY_STRING) {
            driverId = EMPTY_STRING
            vehicleId = EMPTY_STRING
            driverVehicleJunctionId = EMPTY_STRING
        }
    }

    fun deleteDriver(email: String) {
        driverId = getDriverId(email).records[0].Id
        deleteUser()
    }

    fun deleteVehicleDriver(email: String) {
        driverId = getDriverId(email).records[0].Id
        val userInfoSF = BaseSalesforceTest.getUserInfo(driverId)
        vehicleId = userInfoSF.vehicleId
        val vehicleInfoSF = BaseSalesforceTest.getVehicleInfo(userInfoSF.vehicleId)
        driverVehicleJunctionId = getDriverVehicleJunctionId(vehicleInfoSF.plateNumber.toUpperCase()).records[0].Id
        deleteUser()
    }

}