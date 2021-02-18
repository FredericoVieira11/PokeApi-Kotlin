package com.example.pokeapikotlin.network.repository.pokemonDescription

import com.example.pokeapikotlin.network.ApiService
import com.example.pokeapikotlin.network.response.pokemonDescriptionResponse.PokemonDescriptionResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonDescriptionRepository @Inject constructor(
        private val apiService: ApiService
): IPokemonDescriptionRepository {
    override suspend fun getPokemonDescription(
            name: String
    ): Response<PokemonDescriptionResponse> = apiService.getPokemonDescription(name = name)
}