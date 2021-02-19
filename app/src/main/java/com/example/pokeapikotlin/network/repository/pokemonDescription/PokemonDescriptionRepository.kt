package com.example.pokeapikotlin.network.repository.pokemonDescription

import com.example.pokeapikotlin.network.ApiService
import com.example.pokeapikotlin.network.WebHookService
import com.example.pokeapikotlin.network.response.pokemonDescriptionResponse.PokemonDescriptionResponse
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class PokemonDescriptionRepository @Inject constructor(
        private val apiService: ApiService,
        private val webHookService: WebHookService
): IPokemonDescriptionRepository {
    override suspend fun getPokemonDescription(
            name: String
    ): Response<PokemonDescriptionResponse> = apiService.getPokemonDescription(name = name)

    override suspend fun sendPokemonData(
            pokemonData: JsonObject
    ): Response<String> = webHookService.sendPokemonData(pokemonData = pokemonData)


}