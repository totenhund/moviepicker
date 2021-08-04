package totenhund.com.moviepicker.picker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import totenhund.com.moviepicker.data.movies.Movie
import totenhund.com.moviepicker.data.movies.MovieStore

class MoviePickerViewModel: ViewModel() {

    private var moviesRepository = MovieStore()
    private lateinit var moviesList: MutableList<Movie>
    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies
    private var _swipedMovies = MutableLiveData<MutableList<Movie>>()


    init {
        _swipedMovies.value = mutableListOf()
        initMovies()
    }

    private fun initMovies(){
        moviesList = mutableListOf()
        moviesList.addAll(moviesRepository.movies)
        _movies.value = moviesList
    }

    fun onRewind(){

        if(_swipedMovies.value?.isNotEmpty() == true){
            val new = mutableListOf<Movie>()
            _movies.value?.let { new.addAll(it) }
            _swipedMovies.value?.removeLast()?.let { new.add(0, it) }
            moviesList = new
            _movies.value = moviesList
        }

    }

    fun onSwipeLeft(){
        if(moviesList.isNotEmpty()){
            _swipedMovies.value?.add(moviesList.removeAt(0))
            _movies.value = moviesList
            _swipedMovies.value?.toString()?.let { Log.e("Swiped", it) }
        }
    }

    fun onSwipeRight(){
        if(moviesList.isNotEmpty()){
            _swipedMovies.value?.add(moviesList.removeAt(0))
            _movies.value = moviesList
            _swipedMovies.value?.toString()?.let { Log.e("Swiped", it) }
        }
    }

}