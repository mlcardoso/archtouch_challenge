package br.com.apps.tmdblist.FilmDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.apps.tmdblist.BuildConfig
import br.com.apps.tmdblist.GenrerSingleton
import br.com.apps.tmdblist.R
import br.com.apps.tmdblist.api.model.Genre
import br.com.apps.tmdblist.api.model.ListFilmsItem
import br.com.apps.tmdblist.api.model.MovieDetailResponse
import br.com.apps.tmdblist.moneyFormat
import kotlinx.android.synthetic.main.activity_film_detail.*


class FilmDetailActivity : AppCompatActivity(), FilmDetailActivityContract {
    lateinit var presenter: FilmDetailPresentContract
    val FILM_FIRST_INFORMATIONS: String = "FILM_FIRST_INFORMATIONS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        presenter = FilmDetailPresenter(this)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(FILM_FIRST_INFORMATIONS)) {
            var listFilmsItem: ListFilmsItem = intent.getSerializableExtra(FILM_FIRST_INFORMATIONS) as ListFilmsItem
            title = listFilmsItem.title
            loadFirstInformations(listFilmsItem)
            presenter.requestFullInformationsFromMovie(listFilmsItem.id)
        } else {
            Toast.makeText(this, R.string.no_movie_selected, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                onBackPressed()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFirstInformations(listFilmsItem: ListFilmsItem) {
        sdvFilmThumb.setImageURI(BuildConfig.BASE_IMAGE_URL + listFilmsItem.poster_path)
        sdvBackFilm.setImageURI(BuildConfig.BASE_IMAGE_URL + listFilmsItem.backdrop_path)

        rbRatingFilm.rating = listFilmsItem.vote_average / 2f
        tvRatio.text = String.format(getString(R.string.ratio_label), listFilmsItem.vote_average)

        tvTitleFilm.text = listFilmsItem.title
        tvDescriptionFilm.text = listFilmsItem.overview

        ivAdult.visibility = if (listFilmsItem.adult) View.VISIBLE else View.INVISIBLE

        tvRelease.text = listFilmsItem.release_date

        var genres: List<Genre> = GenrerSingleton.getGenresById(listFilmsItem.genre_ids)

        var gen = ""

        genres.forEach { gen += it.name + " " }

        tvGenresFilm.text = gen
    }

    override fun loadingFullDetailFilm(movieDetail: MovieDetailResponse) {

        llMoreInformations.visibility = View.VISIBLE

        tvBudget.text = movieDetail.budget.moneyFormat("R$")
        tvRevenue.text = movieDetail.revenue.moneyFormat("R$")

        var countries = ArrayList<String>()
        movieDetail.production_countries.map { countries.add(it.name) }
        tvContry.text = countries.toList().joinToString("\n")

        var companies = ArrayList<String>()
        movieDetail.production_companies.map { companies.add(it.name) }
        tvCompanies.text = companies.toList().joinToString("\n")

        var languages = ArrayList<String>()
        movieDetail.spoken_languages.map { languages.add(it.name) }
        tvLanguages.text = languages.toList().joinToString("\n")
    }
}
