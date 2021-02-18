package com.example.pokeapikotlin.network.repository.pokemonList

import com.example.pokeapikotlin.network.response.PokemonResponse
import retrofit2.Response

interface IPokemonListRespository {

    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Response<PokemonResponse>

}