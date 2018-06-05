package network

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class UserIdResponse(@SerializedName("totalSize") private val totalSize: Int,
                          @SerializedName("done") private val done: Boolean,
                          @SerializedName("records") val records: ArrayList<Record>) {
    data class Record(@SerializedName("attributes") private val attributes: Attribute,
                      @SerializedName("Id") val Id: String) {
        data class Attribute(@SerializedName("type") private val type: String,
                             @SerializedName("url") private val url: String)
    }
}