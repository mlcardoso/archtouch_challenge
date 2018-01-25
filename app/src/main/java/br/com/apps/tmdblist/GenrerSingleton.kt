package br.com.apps.tmdblist

import br.com.apps.tmdblist.api.model.Genre

/**
 * Created by marcoscardoso on 24/01/2018.
 */
object GenrerSingleton {
    var genres: ArrayList<Genre> = ArrayList()

    fun saveGenres(list: ArrayList<Genre>) {
        genres = list
    }

    fun getGenresById(ids: List<Int>): List<Genre> {

        var filteredGenres: ArrayList<Genre> = ArrayList()

        ids.map { id ->
            val genre = genres.find { it.id == id }

            if (genre != null)
                filteredGenres.add(genre)
        }

        return filteredGenres
    }
}