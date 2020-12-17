package com.extcode.project.jetpacksubmission3.utils

import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.data.source.remote.response.Movie
import com.extcode.project.jetpacksubmission3.data.source.remote.response.TvShow

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(
            MovieEntity(
                "Nory and her best friend Reina enter the Sage Academy for Magical Studies, where Nory’s unconventional powers land her in a class for those with wonky, or “upside-down,” magic. Undaunted, Nory sets out to prove that that upside-down magic can be just as powerful as right-side-up.",
                "en",
                "2020-07-31",
                2323.196,
                7.7,
                671583,
                "Upside-Down Magic",
                68,
                "/xfYMQNApIIh8KhpNVtG1XRz0ZAp.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
                "en",
                "2020-11-20",
                2379.091,
                5.7,
                590706,
                "Jiu Jitsu",
                123,
                "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "en",
                "2020-08-22",
                1387.921,
                7.4,
                577922,
                "Tenet",
                2729,
                "/k68nPLbIST6NP96JmTxmZijEvCA.jpg"
            )
        )
        return movies
    }

    fun generateDummyTvShows(): List<MovieEntity> {
        val tvShows = ArrayList<MovieEntity>()
        tvShows.add(
            MovieEntity(
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "en",
                "2019-11-12",
                1959.989,
                8.5,
                82856,
                "The Mandalorian",
                3607,
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg"
            )
        )
        tvShows.add(
            MovieEntity(
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
                "en",
                "2017-09-25",
                909.814,
                8.6,
                71712,
                "The Good Doctor",
                5956,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
            )
        )
        tvShows.add(
            MovieEntity(
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "en",
                "2005-03-27",
                871.161,
                8.1,
                1416,
                "Grey's Anatomy",
                4554,
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
            )
        )
        return tvShows
    }

    fun generateRemoteDummyMovies(): List<Movie> {
        val movies = ArrayList<Movie>()
        movies.add(
            Movie(
                "Nory and her best friend Reina enter the Sage Academy for Magical Studies, where Nory’s unconventional powers land her in a class for those with wonky, or “upside-down,” magic. Undaunted, Nory sets out to prove that that upside-down magic can be just as powerful as right-side-up.",
                "en",
                "2020-07-31",
                2323.196,
                7.7,
                671583,
                "Upside-Down Magic",
                68,
                "/xfYMQNApIIh8KhpNVtG1XRz0ZAp.jpg"
            )
        )
        movies.add(
            Movie(
                "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
                "en",
                "2020-11-20",
                2379.091,
                5.7,
                590706,
                "Jiu Jitsu",
                123,
                "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg"
            )
        )
        movies.add(
            Movie(
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "en",
                "2020-08-22",
                1387.921,
                7.4,
                577922,
                "Tenet",
                2729,
                "/k68nPLbIST6NP96JmTxmZijEvCA.jpg"
            )
        )
        return movies
    }

    fun generateRemoteDummyTvShows(): List<TvShow> {
        val tvShows = ArrayList<TvShow>()
        tvShows.add(
            TvShow(
                "2019-11-12",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "en",
                1959.989,
                8.5,
                "The Mandalorian",
                82856,
                3607,
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg"
            )
        )
        tvShows.add(
            TvShow(
                "2017-09-25",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
                "en",
                909.814,
                8.6,
                "The Good Doctor",
                71712,
                5956,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
            )
        )
        tvShows.add(
            TvShow(
                "2005-03-27",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "en",
                871.161,
                8.1,
                "Grey's Anatomy",
                1416,
                4554,
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
            )
        )
        return tvShows
    }
}