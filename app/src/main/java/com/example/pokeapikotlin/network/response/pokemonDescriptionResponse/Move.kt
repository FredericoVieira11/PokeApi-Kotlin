package com.example.pokeapikotlin.network.response.pokemonDescriptionResponse

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)