package br.com.apps.tmdblist.ListFilms

import android.view.View
import br.com.apps.tmdblist.api.model.ListFilmsItem

/**
 * Created by marcoscardoso on 17/01/18.
 */
interface ListFilmsActivityContract {
    fun receivedListFilms(filmsItems: List<ListFilmsItem>)
    fun errorLoadingListFilms(message: String)
}

interface ListFilmsPresenterContract {
    fun onResume(activityContract: ListFilmsActivityContract)
    fun requestNextListFilms()
    fun onDestroy()
    fun goToFilmDetail(listFilmsActvity: ListFilmsActvity, listFilmItem: ListFilmsItem, thumb: View, name: View, ratingBar: View, rating: View, genre: View)
}