package network

import com.google.gson.annotations.SerializedName

data class OAuthResponse(@SerializedName("access_token") val accessToken: String,
                         @SerializedName("instance_url") private val instanceUrl: String,
                         private val id: String,
                         @SerializedName("token_type") private val tokenType: String,
                         @SerializedName("issued_at") private val issuedAt: String,
                         val signature: String) {
    override fun toString(): String {
        return "access_token:  $accessToken\n" +
                "instance_url:  $instanceUrl\n" +
                "id:            $id\n" +
                "token_type:    $tokenType\n" +
                "issued_at:     $issuedAt"
    }
}