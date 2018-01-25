package br.com.apps.tmdblist.api.model

import java.io.Serializable

/**
 * Created by marcoscardoso on 23/01/2018.
 */
data class ListFilmResponse(val page: Int,
                            val results: List<ListFilmsItem>)

data class ListFilmsItem(val id: Int,
                         val poster_path: String,
                         val adult: Boolean,
                         val overview: String,
                         val release_date: String,
                         val genre_ids: List<Int>,
                         val original_title: String,
                         val original_language: String,
                         val title: String,
                         val backdrop_path: String,
                         val popularity: Double,
                         val vote_count: Int,
                         val video: Boolean,
                         val vote_average: Float) : Serializable


data class GenresListResponse(val genres: ArrayList<Genre>)

data class Genre(val id: Int,
                 val name: String)

data class Company(val id: Int,
                   val name: String)

data class Country(val iso_3166_1: String,
                   val name: String)

data class Languages(val iso_639_1: String,
                   val name: String)

data class MovieDetailResponse(val id: Int,
                               val poster_path: String,
                               val adult: Boolean,
                               val overview: String,
                               val release_date: String,
                               val status: String,
                               val tagline: String,
                               val genres: List<Genre>,
                               val original_title: String,
                               val original_language: String,
                               val title: String,
                               val production_companies: List<Company>,
                               val production_countries: List<Country>,
                               val spoken_languages: List<Languages>,
                               val backdrop_path: String,
                               val popularity: Double,
                               val vote_count: Int,
                               val budget: Double,
                               val revenue: Double,
                               val runtime: Int,
                               val video: Boolean,
                               val vote_average: Float)