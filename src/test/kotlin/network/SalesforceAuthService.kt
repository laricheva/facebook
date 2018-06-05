package network

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface SalesforceAuthService {

    @Headers("Authorization: Basic YS5zdGFsbWFrb3ZAc29mdHRlY28uY29tLmRldjpzb2Z0dGVjbzFMRm1kR09LeVJNc3pmN3dWUVlOUWhVZFlo")
    @POST("oauth2/token")
    fun getAuthToken(@Body requestBody: RequestBody): Call<OAuthResponse>

}