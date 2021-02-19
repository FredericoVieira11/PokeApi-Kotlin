package com.example.pokeapikotlin.network.request

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

abstract class BasePostRequest {

    fun toJsonObject(): JsonObject {
        val jsonString = Gson().toJson(this)
        val jsonParser = JsonParser()
        return jsonParser.parse(jsonString) as JsonObject
    }

}