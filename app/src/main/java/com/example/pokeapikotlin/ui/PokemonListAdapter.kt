package com.example.pokeapikotlin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokeapikotlin.R
import com.example.pokeapikotlin.network.model.PokemonModel

class PokemonListAdapter(
    private val items: MutableList<PokemonModel>,
    private val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PokemonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_preview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is PokemonViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class PokemonViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val pokemonName = itemView.findViewById<TextView>(R.id.txtPokemonNameList)
    private val pokemonImage = itemView.findViewById<ImageView>(R.id.imgPokemonList)

    fun bind(pokemonModel: PokemonModel) {
        pokemonName.text = pokemonModel.name

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonModel.getNumber()}.png")
            .into(pokemonImage)
    }
}