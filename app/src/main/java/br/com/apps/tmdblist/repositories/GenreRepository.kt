package br.com.apps.tmdblist.repositories

import br.com.apps.tmdblist.api.APIAuthenticatedProxy
import br.com.apps.tmdblist.api.model.Genre
import br.com.apps.tmdblist.api.model.GenresListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by marcoscardoso on 24/01/2018.
 */
object GenreRepository {
    fun requestGenres(success: (genreList: ArrayList<Genre>) -> Unit?, error: (message: String) -> Unit?) {

        val callback: Callback<GenresListResponse> = object : Callback<GenresListResponse> {
            override fun onResponse(call: Call<GenresListResponse>?, filmResponse: Response<GenresListResponse>?) {
                if (filmResponse?.body() != null) {
                    success(filmResponse.body()!!.genres!!)
                } else {
                    //Todo Corrigir menasagem de erro
                    error("Falha ao carregar Generos")
                }
            }

            override fun onFailure(call: Call<GenresListResponse>?, t: Throwable?) {
                error(t?.message.toString())
            }
        }

        APIAuthenticatedProxy.getAllGenres().enqueue(callback)
    }
}