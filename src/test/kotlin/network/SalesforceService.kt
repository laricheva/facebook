package network

import retrofit2.Call
import retrofit2.http.*
import io.reactivex.Observable

interface SalesforceService {

    @GET("data/v37.0/query/")
    fun getDriverId(@Query("q") key: String): Call<UserIdResponse>

    @GET("data/v37.0/query/")
    fun getDriverVehicleJunctionId(@Query("q") key: String): Call<DriverVehicleJunctionResponse>

    @GET("data/v37.0/sobjects/Driver__c/{id}")
    fun getDriverInfo(@Path("id") id: String): Call<UserInfoResponse>

    @GET("data/v37.0/sobjects/Vehicle__c/{id}")
    fun getVehicleInfo(@Path("id") id: String): Call<VehicleInfoResponse>

    @DELETE("data/v37.0/sobjects/Vehicle__c/{id}")
    fun deleteVehicle(@Path("id") id: String): Observable<Unit>

    @DELETE("data/v37.0/sobjects/Driver_Vehicle_Junction__c/{id}")
    fun deleteDriverVehicleJunction(@Path("id") id: String): Observable<Unit>

    @DELETE("data/v37.0/sobjects/Driver__c/{id}")
    fun deleteDriver(@Path("id") id: String): Observable<Unit>

}