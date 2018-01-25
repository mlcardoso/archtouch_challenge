package br.com.apps.tmdblist.ListFilms

import android.os.Bundle
import android.support.v4.app.SharedElementCallback
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import br.com.apps.tmdblist.R
import br.com.apps.tmdblist.api.model.ListFilmsItem
import kotlinx.android.synthetic.main.activity_list_films.*

class ListFilmsActvity : AppCompatActivity(), ListFilmsActivityContract {

    lateinit var presenter: ListFilmsPresenterContract
    lateinit var listFimlsAdapter: ListFimlsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_films)
        supportActionBar?.hide()
        title = getString(R.string.top_rated)
        presenter = ListFilmsPresenter(this)
        presenter.requestNextListFilms()

        rvListFilms.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)!!


        listFimlsAdapter = ListFimlsAdapter(presenter)
        rvListFilms.adapter = listFimlsAdapter

        var onclick = object : OnRecyclerCliked {
            override fun clicked(listFilmItem: ListFilmsItem, thumb: View, name: View, ratingBar: View, rating: View, genre: View) {
                presenter.goToFilmDetail(this@ListFilmsActvity, listFilmItem,thumb,name,ratingBar,rating,genre)
            }
        }

        listFimlsAdapter.onClick = onclick

        //https://github.com/facebook/fresco/issues/1445
        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onSharedElementEnd(names: List<String>, elements: List<View>, snapshots: List<View>) {
                super.onSharedElementEnd(names, elements, snapshots)
                for (view in elements) {
                    view.setVisibility(View.VISIBLE)
                }
            }
        })
    }

    override fun receivedListFilms(filmsItems: List<ListFilmsItem>) {
        listFimlsAdapter.list.addAll(filmsItems)
        listFimlsAdapter.notifyDataSetChanged()
    }

    override fun errorLoadingListFilms(message: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
