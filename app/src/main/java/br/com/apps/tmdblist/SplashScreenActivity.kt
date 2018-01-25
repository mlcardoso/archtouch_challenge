package br.com.apps.tmdblist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.apps.tmdblist.ListFilms.ListFilmsActvity
import br.com.apps.tmdblist.repositories.GenreRepository

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        GenreRepository.requestGenres({ genreList ->
            GenrerSingleton.saveGenres(genreList)
            startActivity(Intent(this@SplashScreenActivity, ListFilmsActvity::class.java))
            finish()
        }, {})
    }
}
