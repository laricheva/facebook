package network

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class DriverVehicleJunctionResponse(@SerializedName("records") val records: ArrayList<Record>) {
    data class Record(@SerializedName("Id") val Id: String)
}