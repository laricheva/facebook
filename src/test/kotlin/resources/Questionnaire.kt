package resources

import com.google.gson.annotations.SerializedName

data class Questionnaire(
        @SerializedName("cityId") val cityId: Int,
        @SerializedName("questionDtos") val questionDtos: Array<Question>
) {
    data class Question(
            @SerializedName("text") val text: String,
            @SerializedName("type") val type: String,
            @SerializedName("id") val id: Int,
            @SerializedName("answers") val answers: Array<Answer>
            ) {
        data class Answer(
                @SerializedName("text") val text: String,
                @SerializedName("value") val value: String
        )
    }
}
