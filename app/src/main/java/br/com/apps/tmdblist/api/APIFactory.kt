import br.com.apps.tmdblist.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by marcoscardoso on 26/08/17.
 */

object APIFactory {

    val service: APIService
        get() {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(APIService::class.java)
        }
}
