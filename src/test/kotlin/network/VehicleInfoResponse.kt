package network

import com.google.gson.annotations.SerializedName

data class VehicleInfoResponse(@SerializedName("Plate__c") val plateNumber: String,
                               @SerializedName("Make__c") val make: String,
                               @SerializedName("Model__c") val model: String,
                               @SerializedName("Color__c") val color: String,
                               @SerializedName("Year__c") val year: String,
                               @SerializedName("Vehicle_Interior__c") val interior: String,
                               @SerializedName("Vehicle_Registration_2_Doc_ID__c") val vehRegBackImage: String,
                               @SerializedName("Vehicle_Registration_Doc_Id__c") val vehRegFrontImage: String,
                               @SerializedName("Certificate_of_Insurance_Coverage_Doc_Id__c") val vehInsurImage: String,
                               @SerializedName("Vehicle_Inspection_Form_ID__c") val vehInspectImage: String,
                               @SerializedName("Vehicle_Verification_Exterior_Doc_ID__c") val vehExteriorImage: String,
                               @SerializedName("Id") val id: String)