package totenhund.com.moviepicker.picker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import totenhund.com.moviepicker.R
import totenhund.com.moviepicker.databinding.CardViewMovieBinding
import totenhund.com.moviepicker.data.movies.Movie
import totenhund.com.utils.MovieDiffUtils


class MoviesAdapter(
    private val listener: MovieAdapterListener,
    private var movies: ArrayList<Movie> = ArrayList()
) : RecyclerView.Adapter<MoviesAdapter.ProfileViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.card_view_movie,
            parent,
            false
        ),
        listener
    )

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        movies.let {
            holder.binding.movie = it[position]
            holder.binding.executePendingBindings()
        }
    }

    fun setMovies(movies: List<Movie>) {

        val result = DiffUtil.calculateDiff(MovieDiffUtils(this.movies, movies))
        result.dispatchUpdatesTo(this)
        this.movies.clear()
        this.movies = ArrayList(movies)
    }

    inner class ProfileViewHolder(val binding: CardViewMovieBinding, listener: MovieAdapterListener) :
        RecyclerView.ViewHolder(binding.root){
            init {
                binding.listener = listener
            }
        }

}