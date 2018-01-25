package br.com.apps.tmdblist.FilmDetail

import br.com.apps.tmdblist.ListFilms.ListFilmsActivityContract
import br.com.apps.tmdblist.api.model.MovieDetailResponse

/**
 * Created by marcoscardoso on 25/01/2018.
 */
interface FilmDetailActivityContract {
    fun loadingFullDetailFilm(movieDetail: MovieDetailResponse)
}

interface FilmDetailPresentContract {
    fun onResume(activityContract: FilmDetailActivityContract)
    fun requestFullInformationsFromMovie(movieId: Int)
    fun onDestroy()
}
