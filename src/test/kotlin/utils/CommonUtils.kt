package utils

const val JANUARY = "january"
const val FEBRUARY = "february"
const val MARCH = "march"
const val APRIL = "april"
const val MAY = "may"
const val JUNE = "june"
const val JULY = "july"
const val AUGUST = "august"
const val SEPTEMBER = "september"
const val OCTOBER = "october"
const val NOVEMBER = "november"
const val DECEMBER = "december"

fun getMonthIndex(month: String): Int {
    when(month.toLowerCase()) {
        JANUARY -> return 1
        FEBRUARY -> return 2
        MARCH -> return 3
        APRIL -> return 4
        MAY -> return 5
        JUNE -> return 6
        JULY -> return 7
        AUGUST -> return 8
        SEPTEMBER -> return 9
        OCTOBER -> return 10
        NOVEMBER -> return 11
        DECEMBER -> return 12
    }
    return 0
}