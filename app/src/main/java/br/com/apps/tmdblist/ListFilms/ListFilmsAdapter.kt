package br.com.apps.tmdblist.ListFilms

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.apps.tmdblist.BuildConfig
import br.com.apps.tmdblist.GenrerSingleton
import br.com.apps.tmdblist.R
import br.com.apps.tmdblist.api.model.Genre
import br.com.apps.tmdblist.api.model.ListFilmsItem
import br.com.apps.tmdblist.inflate
import kotlinx.android.synthetic.main.list_films_footer.view.*
import kotlinx.android.synthetic.main.list_item_films.view.*


/**
 * Created by marcoscardoso on 23/01/2018.
 */

class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setupView(presenter: ListFilmsPresenterContract?) = with(itemView) {
        tvShowMore.setOnClickListener { presenter?.requestNextListFilms() }
    }
}

class ListFilmsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setupView(onClick: OnRecyclerCliked?, listFilmsItem: ListFilmsItem) = with(itemView) {
        sdvFilmThumb.setImageURI(BuildConfig.BASE_IMAGE_URL + listFilmsItem.poster_path)

        rbRatingFilm.rating = listFilmsItem.vote_average / 2f
        tvRatio.text = String.format(itemView.context.getString(R.string.ratio_label), listFilmsItem.vote_average)

        tvTitleFilm.text = listFilmsItem.title
        tvDescriptionFilm.text = listFilmsItem.overview

        ivAdult.visibility = if(listFilmsItem.adult) View.VISIBLE else View.INVISIBLE

        var genres: List<Genre> = GenrerSingleton.getGenresById(listFilmsItem.genre_ids)

        var gen: String = ""

        genres.forEach { gen += it.name + " " }

        tvGenresFilm.text = gen

        itemFilmRoot.setOnClickListener { onClick?.clicked(listFilmsItem, sdvFilmThumb, tvTitleFilm, rbRatingFilm, tvRatio, tvGenresFilm) }
    }
}

class ListFimlsAdapter(var presenter: ListFilmsPresenterContract?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val FOOTER_VIEW: Int = 99

    var list: ArrayList<ListFilmsItem> = ArrayList<ListFilmsItem>()
    var onClick: OnRecyclerCliked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return if (viewType == FOOTER_VIEW) {
            FooterViewHolder(parent.inflate(R.layout.list_films_footer))
        } else {
            ListFilmsItemViewHolder(parent.inflate(R.layout.list_item_films))
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ListFilmsItemViewHolder)
            viewHolder.setupView(this.onClick, list[position])
        else if (viewHolder is FooterViewHolder)
            viewHolder.setupView(presenter)

    }

    override fun getItemViewType(position: Int): Int = if (position == list.size) FOOTER_VIEW else super.getItemViewType(position)

    override fun getItemCount() = list.size + 1 //size + footer

}

interface OnRecyclerCliked {
    fun clicked(listFilmsItem: ListFilmsItem, thumb: View, name: View, ratingBar: View, rating: View, genre: View)
}