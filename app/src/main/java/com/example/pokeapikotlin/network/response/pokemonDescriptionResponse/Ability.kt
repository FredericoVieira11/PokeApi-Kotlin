package com.example.pokeapikotlin.network.response.pokemonDescriptionResponse

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)