package com.example.pokeapikotlin.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.pokeapikotlin.R
import com.google.android.material.snackbar.Snackbar

fun showAlert(context: Context?, view: View, text: String) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        .setBackgroundTint((ContextCompat.getColor(context!!, R.color.purple_500)))
        .setTextColor((ContextCompat.getColor(context, R.color.white)))
        .show()
}