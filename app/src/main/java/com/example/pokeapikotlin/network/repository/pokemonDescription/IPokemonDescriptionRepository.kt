package com.example.pokeapikotlin.network.repository.pokemonDescription

import com.example.pokeapikotlin.network.response.pokemonDescriptionResponse.PokemonDescriptionResponse
import retrofit2.Response

interface IPokemonDescriptionRepository {

    suspend fun getPokemonDescription(
            name: String
    ): Response<PokemonDescriptionResponse>

}