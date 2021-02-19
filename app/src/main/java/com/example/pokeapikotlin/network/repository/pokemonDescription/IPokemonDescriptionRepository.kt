package com.example.pokeapikotlin.network.repository.pokemonDescription

import com.example.pokeapikotlin.network.response.pokemonDescriptionResponse.PokemonDescriptionResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface IPokemonDescriptionRepository {

    suspend fun getPokemonDescription(
            name: String
    ): Response<PokemonDescriptionResponse>

    suspend fun sendPokemonData(
            pokemonData: JsonObject
    ): Response<String>

}