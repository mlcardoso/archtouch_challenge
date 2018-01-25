package br.com.apps.tmdblist.api

import APIFactory
import br.com.apps.tmdblist.BuildConfig
import br.com.apps.tmdblist.api.model.GenresListResponse
import br.com.apps.tmdblist.api.model.ListFilmResponse
import br.com.apps.tmdblist.api.model.MovieDetailResponse
import retrofit2.Call

/**
 * Created by marcoscardoso on 23/01/2018.
 */
object APIAuthenticatedProxy {

    private fun getAPIKey(): String = BuildConfig.API_KEY

    //A lot of films dont have portuguese data
    private fun getLocale(): String = "en" // Locale.getDefault().language

    fun getTopRatedFilmsPage(page: Int): Call<ListFilmResponse> = APIFactory.service.getTopRatedFilmsPage(getAPIKey(), getLocale(), page)

    fun getMovieDetails(movieId: Int): Call<MovieDetailResponse> = APIFactory.service.getMovieDetails(movieId, getAPIKey(), getLocale())

    fun getAllGenres(): Call<GenresListResponse> = APIFactory.service.getAllGenres(getAPIKey(), getLocale())

}