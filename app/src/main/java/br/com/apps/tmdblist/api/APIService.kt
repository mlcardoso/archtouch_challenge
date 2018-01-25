import br.com.apps.tmdblist.api.model.GenresListResponse
import br.com.apps.tmdblist.api.model.ListFilmResponse
import br.com.apps.tmdblist.api.model.MovieDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/top_rated")
    fun getTopRatedFilmsPage(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Call<ListFilmResponse>


    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String, @Query("language") language: String): Call<MovieDetailResponse>

    @GET("genre/movie/list")
    fun getAllGenres(@Query("api_key") apiKey: String, @Query("language") language: String): Call<GenresListResponse>
}


