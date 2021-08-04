package totenhund.com.moviepicker.data.movies

import totenhund.com.moviepicker.data.actors.Actor

class MovieStore {

    var movies = listOf(
        Movie(
            1L,
            "Дрисня тигра",
            9.9f,
            9f,
            listOf(),
            "https://static.hdrezka.ac/i/2021/7/23/r40f9c67d018ddy10t31m.png",
            2000,
            listOf(
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/feb3b4c8b0927ij52p50c.jpg",
                    "Джонни\nДепп"
                ),
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/m33d9b4693561ml16l32a.jpg",
                    "Леонардо\nДиКаприо"
                ),
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/g282b3cf593a0zv92s72s.jpg",
                    "Джейк\nДжилленхол"
                )
            )
        ),
        Movie(
            2L,
            "Дрисня 2",
            9.9f,
            9f,
            listOf(),
            "https://www.kino-teatr.ru/movie/poster/133540/98823.jpg",
            2002,
            listOf(
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/feb3b4c8b0927ij52p50c.jpg",
                    "Джонни\nДепп"
                ),
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/m33d9b4693561ml16l32a.jpg",
                    "Леонардо\nДиКаприо"
                ),
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/g282b3cf593a0zv92s72s.jpg",
                    "Джейк\nДжилленхол"
                )
            )
        ),
        Movie(
            3L,
            "Дрисня",
            9.9f,
            9f,
            listOf(),
            "https://v1.popcornnews.ru/upload/editor/90091335_201554021101625_3735690152843615546_n.jpg",
            2001,
            listOf(
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/feb3b4c8b0927ij52p50c.jpg",
                    "Джонни\nДепп"
                ),
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/m33d9b4693561ml16l32a.jpg",
                    "Леонардо\nДиКаприо"
                ),
                Actor(
                    1L,
                    "https://static.hdrezka.ac/i/2016/3/10/g282b3cf593a0zv92s72s.jpg",
                    "Джейк\nДжилленхол"
                )
            )
        ),
    )

    fun get(id: Long): Movie? {
        return movies.firstOrNull { it.id == id }
    }

}