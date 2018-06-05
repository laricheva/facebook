package utils

import java.util.Random



class NameGenerator {
    companion object {

        private val Beginning = arrayOf("Kr", "Ca", "Ra", "Mrok", "Cru", "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer",
                "Jar", "Mjol", "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro", "Mar", "Luk")
        private val Middle = arrayOf("air", "ir", "mi", "sor", "mee", "clo", "red", "cra", "ark", "arc", "miri", "lori",
                "cres", "mur", "zer", "marac", "zoir", "slamar", "salmar", "urak")
        private val End = arrayOf("d", "ed", "ark", "arc", "es", "er", "der", "tron", "med", "ure", "zur", "cred", "mur")

        private val rand = Random()

        fun generateName(): String {

            return Beginning[rand.nextInt(Beginning.size)] +
                    Middle[rand.nextInt(Middle.size)] +
                    End[rand.nextInt(End.size)]

        }
    }
}