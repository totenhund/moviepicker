package totenhund.com.moviepicker.data.movies

import totenhund.com.moviepicker.data.actors.Actor

data class Movie(
    val id: Long,
    val title: String,
    val kinopoiskRate: Float,
    val imdbRate: Float,
    val genre: List<String>,
    val moviePic: String,
    val year: Int,
    val actors: List<Actor>
)