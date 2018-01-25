package br.com.apps.tmdblist.FilmDetail

import br.com.apps.tmdblist.repositories.FilmsRepository

/**
 * Created by marcoscardoso on 25/01/2018.
 */
class FilmDetailPresenter(var activity: FilmDetailActivityContract?) : FilmDetailPresentContract {
    override fun requestFullInformationsFromMovie(movieId: Int) {

        FilmsRepository.requestFilmDetailById(movieId, {
            activity?.loadingFullDetailFilm(it)
        }, {

        })

    }

    override fun onDestroy() {
        activity = null
    }

    override fun onResume(activityContract: FilmDetailActivityContract) {
        activity = activityContract
    }
}