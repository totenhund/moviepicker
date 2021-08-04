package totenhund.com.moviepicker.picker.adapter

import android.view.View
import totenhund.com.moviepicker.data.movies.Movie

interface MovieAdapterListener {

    fun onMovieClicked(cardView: View, movie: Movie)
}