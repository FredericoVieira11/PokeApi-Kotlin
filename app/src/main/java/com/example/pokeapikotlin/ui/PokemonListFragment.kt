package com.example.pokeapikotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapikotlin.databinding.FragmentPokemonListBinding
import com.example.pokeapikotlin.network.model.PokemonModel
import com.example.pokeapikotlin.network.resource.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var adapterPokemon: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        getPokemonList()
        return binding.root
    }

    private fun getPokemonList() {
        viewModel.getPokemonList().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        val listOfPokemons: MutableList<PokemonModel> = mutableListOf()

                        if (!it.data?.body()?.results.isNullOrEmpty()) {
                            for (results in it.data?.body()?.results!!) {
                                val listOfItems = PokemonModel(
                                    name = results.name,
                                    url = results.url
                                )
                                listOfPokemons.add(listOfItems)
                            }
                            setUpRecyclerView(listOfPokemons)
                        }
                    }
                    Status.ERROR -> {
                        print("ERRO AO RESGATAR DADOS DA API !!!!!!!!!!!!! ERRO !!!!!!!")
                    }
                    Status.LOADING -> {
                        print("LOADING ...")
                    }
                }
            }
        })
    }

    private fun setUpRecyclerView(list: MutableList<PokemonModel>){
        adapterPokemon = PokemonListAdapter(items = list, context = requireContext())

        binding.rvPokemonList.apply {
            adapter = adapterPokemon
            adapterPokemon.notifyDataSetChanged()
            layoutManager = LinearLayoutManager(activity)
        }
    }

}