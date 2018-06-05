package network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import org.apache.http.util.TextUtils

class NetworkManager {
    companion object {

        private const val API_BASE_URL = "https://ridewithvia--via.cs25.my.salesforce.com/services/"
        private val httpClient = OkHttpClient.Builder()
        private val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
        private var retrofit = builder.build()

        fun <S> createAuthService(serviceClass: Class<S> , authToken: String?) : S {
            return createService(serviceClass,  authToken)
        }

        fun <S> createService(
                serviceClass: Class<S>, authToken: String?): S {
            if (!TextUtils.isEmpty(authToken)) {
                httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .header("Authorization", authToken)
                            .build()
                    chain.proceed(request)
                }
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
            return retrofit.create(serviceClass)
        }

    }
}