package br.com.apps.tmdblist

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.NumberFormat
import java.util.*

/**
 * Created by marcoscardoso on 23/01/2018.
 */
fun ViewGroup.inflate(@LayoutRes layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun Double.moneyFormat(currency: String): String {
    return currency + " " + NumberFormat.getNumberInstance(Locale.getDefault()).format(this)
}