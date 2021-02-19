package com.example.pokeapikotlin.network

import com.example.pokeapikotlin.utils.Utils
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface WebHookService {

    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(Utils.WEBHOOK_ENDPOINT)
    suspend fun sendPokemonData(
            @Body pokemonData: JsonObject
    ): Response<String>

}