package com.example.pokeapikotlin.network.response

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)