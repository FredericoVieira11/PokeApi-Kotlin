package com.example.pokeapikotlin.network.model

import java.util.regex.Matcher
import java.util.regex.Pattern

data class PokemonModel(
    val name: String,
    val url: String
) {
    fun getNumberFromUrl(urlStr: String?): Int? {
        val p: Pattern = Pattern.compile("pokemon/(\\d+)")
        val m: Matcher = p.matcher(urlStr)
        return if (m.find()) {
            m.group(1).toInt()
        } else null
    }

}