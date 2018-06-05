package extensions

import com.google.gson.Gson
import constants.*
import org.openqa.selenium.By
import resources.Localization
import resources.Questionnaire
import utils.NameGenerator
import java.io.File
import java.nio.file.Paths
import java.util.*

const val DIGITS_STRING = "0123456789"
const val LETTERS_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
const val SPEC_CHARS_STRING = "\"\\ ~`!@#%^&*()-_=+[{]}|;:',<.>/?"

const val DIGITS = "digits"
const val LETTERS = "letters"
const val SPEC_CHARS = "specChars"

fun getCurrentLocalization(lang: Languages): Any? {
    val currentPath = Paths.get(".").toAbsolutePath().normalize().toString()
    var filePath: String = currentPath
    if (System.getProperty(OS_NAME).startsWith(LINUX, true)
            || System.getProperty(OS_NAME).startsWith(MACOS, true)) {
        filePath = "$currentPath/src/test/resources/localization.json"
    } else if (System.getProperty(OS_NAME).startsWith(WINDOWS , true)) {
        filePath = "$currentPath\\src\\test\\resources\\localization.json"
    }
    val file = File(filePath)
    file.let {
        val buffer = file.readText()
        val localization =  Gson().fromJson<Localization>(buffer, Localization::class.java)
        return getCurrentLocalization(localization, lang)
    }
}

fun getCityQuestionnaire(cityId: Int): Questionnaire? {
    val currentPath = Paths.get(".").toAbsolutePath().normalize().toString()
    var filePath: String = currentPath
    if (System.getProperty(OS_NAME).startsWith(LINUX, true)
            || System.getProperty(OS_NAME).startsWith(MACOS, true)) {
        filePath = "$currentPath/src/test/resources/questionnaire.json"
    } else if (System.getProperty(OS_NAME).startsWith(WINDOWS , true)) {
        filePath = "$currentPath\\src\\test\\resources\\questionnaire.json"
    }
    val file = File(filePath)
    file.let {
        val buffer = file.readText()
        val questionnaire =  Gson().fromJson(buffer, Array<Questionnaire>::class.java).toList()
        return questionnaire.find { it.cityId == cityId }
    }
}

fun setLanguage(lang: Languages) {
    when(lang) {
        Languages.EN -> {
            By.linkText("Engels").get().let {
                it.click()
            }
        }
        Languages.NL -> {
            By.linkText("Dutch").get().let {
                it.click()
            }
        }
    }
}

private fun getCurrentLocalization(localization: Localization, lang: Languages) = when(lang) {
    Languages.EN -> localization.en
    Languages.NL -> localization.nl
}

data class amsSignUp(val firstName:String = NameGenerator.generateName(),
                     val lastName:String = NameGenerator.generateName(),
                     var phone: String = getRandomAmsPhone(),
                     var email: String = "$phone@softteco.com")

private fun getRandomAmsPhone(): String {
    return "20${Random().nextInt(9999999 - 1000000) + 1000000}"
}

fun <T> randomValueGenerate(vararg ts: T):String {
    var template = ""
    var length = 0
    for (t in ts) {
        when (t) {
            is Int -> length = t
            DIGITS -> template += DIGITS_STRING
            LETTERS -> template += LETTERS_STRING
            SPEC_CHARS -> template += SPEC_CHARS_STRING
            else -> template += t
        }
    }
    var randomValue = ""
    for (i in 1..length) {
        randomValue += template[Random().nextInt(template.length)]
    }
    return randomValue
}

