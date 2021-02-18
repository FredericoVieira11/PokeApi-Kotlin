package com.example.pokeapikotlin.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pokeapikotlin.network.repository.pokemonList.IPokemonListRespository
import com.example.pokeapikotlin.network.resource.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PokemonListViewModel @ViewModelInject constructor(
    private val repository: IPokemonListRespository
): ViewModel() {

    fun getPokemonList() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPokemonList(limit = 20, offset = 0)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}