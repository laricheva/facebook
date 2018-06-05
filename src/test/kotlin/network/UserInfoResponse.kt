package network

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(@SerializedName("First_Name__c") val firstName: String,
                            @SerializedName("Last_Name__c") val lastName: String,
                            @SerializedName("Email__c") val email: String,
                            @SerializedName("Mobile_Phone_Number__c") val phoneNumber: String,
                            @SerializedName("Headshot_ID__c") val profileImage: String,
                            @SerializedName("Professional_License_2_Doc_ID__c") val taxivergunningbewijsImage: String,
                            @SerializedName("TLC_License_Doc_Id__c") val profDriverLicenseImage: String,
                            @SerializedName("Driving_License_2_Doc_ID__c") val driverLicenseBackImage: String,
                            @SerializedName("DMV_License_Doc_Id__c") val driverLicenseFrontImage: String,
                            @SerializedName("Additional_Doc_Id__c") val tramwayExemptionImage: String,
                            @SerializedName("Date_of_Birth__c") val dateOfBirth: String,
                            @SerializedName("Driver_Address_1__c") val address1: String,
                            @SerializedName("Driver_Address_2__c") val address2: String,
                            @SerializedName("Driver_Address_City__c") val addressCity: String,
                            @SerializedName("Driver_Address_Zip__c") val addressZip: String,
                            @SerializedName("Vehicle_ID__c") val vehicleId: String,
                            @SerializedName("Has_TLC_License__c") val hasProfLicense: String,
                            @SerializedName("Has_Professional_License_2__c") val hasTaxivergunning: String,
                            @SerializedName("Has_Vehicle__c") val hasVehicle: String,
                            @SerializedName("Has_Vehicle_TLC__c") val hasBlueLicensePlate: String,
                            @SerializedName("Referral_Source__c") val howDidYouHearItem: String, // "Rider", "Other"
                            @SerializedName("Current_Via_Driver_Partner_Referral__c") val referralDriver: String,
                            @SerializedName("Acquisition_Notes__c") val howDidYouHearText: String)