package br.com.apps.tmdblist.repositories

import br.com.apps.tmdblist.api.APIAuthenticatedProxy
import br.com.apps.tmdblist.api.model.ListFilmResponse
import br.com.apps.tmdblist.api.model.MovieDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by marcoscardoso on 23/01/2018.
 */
object FilmsRepository {
    var memoryCash: MutableMap<Int, MovieDetailResponse> = HashMap()

    fun requestListFilmsFromServer(page: Int, success: (filmResponse: ListFilmResponse) -> Unit?, error: (message: String) -> Unit?) {

        val callback: Callback<ListFilmResponse> = object : Callback<ListFilmResponse> {
            override fun onResponse(call: Call<ListFilmResponse>?, filmResponse: Response<ListFilmResponse>?) {
                if (filmResponse?.body() != null) {
                    success(filmResponse.body()!!)
                } else {
                    //Todo Corrigir menasagem de erro
                    error("Falha ao carregar lista")
                }
            }

            override fun onFailure(call: Call<ListFilmResponse>?, t: Throwable?) {
                error(t?.message.toString())
            }
        }

        APIAuthenticatedProxy.getTopRatedFilmsPage(page).enqueue(callback)
    }

    fun requestFilmDetailById(movieId: Int, success: (MovieDetailResponse) -> Unit, error: (String) -> Unit) {

        if (memoryCash.containsKey(movieId)) {
            success(memoryCash.get(movieId)!!)
            return
        }

        val callback: Callback<MovieDetailResponse> = object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>?, filmResponse: Response<MovieDetailResponse>?) {
                if (filmResponse?.body() != null) {
                    memoryCash.put(movieId, filmResponse.body()!!)
                    success(filmResponse.body()!!)
                } else {
                    //Todo Corrigir menasagem de erro
                    error("Falha ao carregar lista")
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>?, t: Throwable?) {
                error(t?.message.toString())
            }
        }

        APIAuthenticatedProxy.getMovieDetails(movieId).enqueue(callback)

    }
}