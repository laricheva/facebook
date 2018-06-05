package base

import config.initSalesConfiguration
import constants.*
import extensions.notNullResponse
import network.*
import okhttp3.MultipartBody
import retrofit2.Call
import io.reactivex.Observable

open class BaseSalesforceTest: BaseTest() {
    companion object {

        var oAuth: OAuthResponse

        private val salesforceAuthService by lazy {
            NetworkManager.createAuthService(SalesforceAuthService::class.java, null)
        }

        private var salesforceService: SalesforceService

        private fun getAuthToken(): Call<OAuthResponse> {
            val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(GRANT_TYPE, PASSWORD_KEY)
                    .addFormDataPart(CLIENT_ID, CLIENT_ID_VALUE)
                    .addFormDataPart(CLIENT_SECRET, CLIENT_SECRET_VALUE)
                    .addFormDataPart(USERNAME, LOGIN_SF)
                    .addFormDataPart(PASSWORD_KEY, PASSWORD)
                    .build()
            return salesforceAuthService.getAuthToken(requestBody)
        }

        init {
            initSalesConfiguration()
            oAuth =  getAuthToken().execute().body().notNullResponse("SalesForce authorization is failed") as OAuthResponse
            salesforceService = NetworkManager.createService(SalesforceService::class.java, "OAuth ${oAuth.accessToken}")
        }

        fun getDriverId(key: String): UserIdResponse {
            val call= salesforceService.getDriverId(key)
            return call.execute().body().notNullResponse("User is not found") as UserIdResponse
        }

        fun getDriverVehicleJunctionId(key: String): DriverVehicleJunctionResponse {
            val call= salesforceService.getDriverVehicleJunctionId(key)
            return call.execute().body().notNullResponse("Driver_Vehicle_Junction__c is not found") as DriverVehicleJunctionResponse
        }

        fun getUserInfo(id: String): UserInfoResponse {
            val call= salesforceService.getDriverInfo(id)
            return call.execute().body().notNullResponse("User is not found") as UserInfoResponse
        }

        fun getVehicleInfo(id: String): VehicleInfoResponse {
            val call = salesforceService.getVehicleInfo(id)
            return call.execute().body().notNullResponse("Vehicle is not found") as VehicleInfoResponse
        }

        fun deleteUser(driverId: String, vehicleId: String, driverVehicleJunctionId: String): Observable<Unit> {
            if (vehicleId == EMPTY_STRING) {
                return salesforceService.deleteDriver(driverId)
            }
            val driver = salesforceService.deleteDriver(driverId)
            val vehicle = salesforceService.deleteVehicle(vehicleId)
            val driverVehicleJunction = salesforceService.deleteDriverVehicleJunction(driverVehicleJunctionId)
            return Observable.concat(vehicle, driverVehicleJunction, driver)
        }

    }

}