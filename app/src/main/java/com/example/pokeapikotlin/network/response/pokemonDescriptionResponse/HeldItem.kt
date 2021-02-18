package com.example.pokeapikotlin.network.response.pokemonDescriptionResponse

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)