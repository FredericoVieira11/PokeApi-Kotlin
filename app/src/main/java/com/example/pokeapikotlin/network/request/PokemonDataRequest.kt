package com.example.pokeapikotlin.network.request

import com.google.gson.annotations.SerializedName

data class PokemonDataRequest (
        @SerializedName("pokeImage")
        val pokeImage: String,
        @SerializedName("pokeId")
        val pokeId: Int,
        @SerializedName("pokeName")
        val pokeName: String,
        @SerializedName("pokeHeight")
        val pokeHeight: Int,
        @SerializedName("pokeWeight")
        val pokeWeight: Int,
        @SerializedName("pokeBaseExp")
        val pokeBaseExp: Int,
        @SerializedName("pokeMove")
        val pokeMove: String,
        @SerializedName("pokeAbility")
        val pokeAbility: String,
        @SerializedName("pokeType")
        val pokeType: String
): BasePostRequest()