package totenhund.com.utils

import androidx.recyclerview.widget.DiffUtil
import totenhund.com.moviepicker.data.movies.Movie

class MovieDiffUtils(
    private val old: List<Movie>,
    private val new: List<Movie>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}