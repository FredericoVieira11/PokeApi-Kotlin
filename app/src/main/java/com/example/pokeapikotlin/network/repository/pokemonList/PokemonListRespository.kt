package com.example.pokeapikotlin.network.repository.pokemonList

import com.example.pokeapikotlin.network.ApiService
import com.example.pokeapikotlin.network.response.pokemonResponse.PokemonResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonListRespository @Inject constructor(
    private val apiService: ApiService
): IPokemonListRespository {

    override suspend fun getPokemonList(
        limit: Int, offset: Int
    ): Response<PokemonResponse> = apiService.getListPokemon(limit = limit, offset = offset)

}