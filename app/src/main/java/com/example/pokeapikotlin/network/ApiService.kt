package com.example.pokeapikotlin.network

import com.example.pokeapikotlin.network.response.pokemonDescriptionResponse.PokemonDescriptionResponse
import com.example.pokeapikotlin.network.response.pokemonResponse.PokemonResponse
import com.example.pokeapikotlin.utils.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET(Utils.GET_POKEMON_LIST_ENDPOINT)
    suspend fun getListPokemon(
        @Query(Utils.QUERY_LIMIT) limit: Int,
        @Query(Utils.QUERY_OFFSET) offset: Int
    ): Response<PokemonResponse>

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET(Utils.GET_POKEMON_DESCRIPTION_ENDPOINT)
    suspend fun getPokemonDescription(
            @Path(Utils.QUERY_NAME) name: String
    ): Response<PokemonDescriptionResponse>

}