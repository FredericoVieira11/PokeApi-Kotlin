package com.example.pokeapikotlin.network.model

import java.util.regex.Matcher
import java.util.regex.Pattern

data class PokemonModel(
    val name: String,
    val url: String
) {
    fun getNumber(): String{
        val myArray: MutableList<String> = url.split("https://pokeapi.co/api/v2/pokemon/").toMutableList()
        val test = myArray[1].split("/")
        return test[0]
    }
}