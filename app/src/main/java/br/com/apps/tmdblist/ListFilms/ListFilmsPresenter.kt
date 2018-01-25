package br.com.apps.tmdblist.ListFilms

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.View
import br.com.apps.tmdblist.FilmDetail.FilmDetailActivity
import br.com.apps.tmdblist.api.model.ListFilmsItem
import br.com.apps.tmdblist.repositories.FilmsRepository

/**
 * Created by marcoscardoso on 17/01/18.
 */
class ListFilmsPresenter(var activityContract: ListFilmsActivityContract?) : ListFilmsPresenterContract {

    var page = 0

    override fun requestNextListFilms() {
        page += 1
        FilmsRepository.requestListFilmsFromServer(page, { activityContract?.receivedListFilms(it.results) }, { activityContract?.errorLoadingListFilms(it) })
    }

    override fun onResume(activityContract: ListFilmsActivityContract) {
        this.activityContract = activityContract
    }

    override fun onDestroy() {
        this.activityContract = null
        page = 0
    }

    override fun goToFilmDetail(listFilmsActvity: ListFilmsActvity, listFilmItem: ListFilmsItem, thumb: View, name: View, ratingBar: View, rating: View, genre: View) {
        var intent = Intent(listFilmsActvity, FilmDetailActivity::class.java)
        intent.putExtra("FILM_FIRST_INFORMATIONS", listFilmItem)

        val p1 = android.support.v4.util.Pair(name, ViewCompat.getTransitionName(name))
        val p2 = android.support.v4.util.Pair(ratingBar, ViewCompat.getTransitionName(ratingBar))
        val p3 = android.support.v4.util.Pair(rating, ViewCompat.getTransitionName(rating))
        val p4 = android.support.v4.util.Pair(genre, ViewCompat.getTransitionName(genre))
        val p5 = android.support.v4.util.Pair(thumb, "thumb")

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(listFilmsActvity, p1, p2, p3, p4, p5)
        listFilmsActvity.startActivity(intent, options.toBundle())
    }
}